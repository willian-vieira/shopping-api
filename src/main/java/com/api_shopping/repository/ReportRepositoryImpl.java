package com.api_shopping.repository;

import com.api_shopping.dto.ShopReportDTO;
import com.api_shopping.entities.Shop;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class ReportRepositoryImpl implements IReportRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Shop> getShopByFilters(Date dataInicio, Date dataFim, Float valorMinimo) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT s ");
        sql.append("FROM Shop s ");
        sql.append("WHERE s.date >= :dataInicio ");

        if (dataFim != null) {
            sql.append("AND s.date <= :dataFim ");
        }

        if (valorMinimo != null) {
            sql.append("AND s.total <= :valorMinimo ");
        }

        Query query = entityManager.createQuery(sql.toString(), Shop.class);
        query.setParameter("dataInicio", dataInicio);

        if (dataFim != null) {
            query.setParameter("dataFim", dataFim);
        }

        if (valorMinimo != null) {
            query.setParameter("valorMinimo", valorMinimo);
        }
        return query.getResultList();
    }

    @Override
    public ShopReportDTO getReportByDate(Date dataInicio, Date dataFim) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT COUNT(sp.id), sum(sp.total), avg(sp.total) ");
        sql.append("FROM Shop sp ");
        sql.append("WHERE sp.date >= :dataInicio ");
        sql.append("AND sp.date <= :dataFim ");

        Query query = entityManager.createNativeQuery(sql.toString(), ShopReportDTO.class);
        query.setParameter("dataInicio", dataInicio);
        query.setParameter("dataFim", dataFim);

        Object[] result = (Object[]) query.getSingleResult();
        ShopReportDTO shopReportDTO = new ShopReportDTO();
        shopReportDTO.setCount(((BigDecimal) result[0]).intValue());
        shopReportDTO.setTotal((Double) result[1]);
        shopReportDTO.setMean((Double) result[1]);
        return null;
    }
}