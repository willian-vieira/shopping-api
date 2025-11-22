package com.api_shopping.repository;

import com.api_shopping.dto.ShopReportDTO;
import com.api_shopping.entities.Shop;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import java.util.Date;
import java.util.List;

public class ReportRepositoryImpl implements IReportRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Shop> getShopByFilters(Date dataInicio, Date dataFim, Float valorMinimo) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT s ");
        sql.append("FROM shop s ");
        sql.append("WHERE s.date >= :dataInicio ");

        if (dataFim != null) {
            sql.append("AND s.date <= :dataFim ");
        }

        if (valorMinimo != null) {
            sql.append("AND s.total <= :valorMinimo ");
        }

        Query query = entityManager.createQuery(sql.toString());
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
        return null;
    }
}