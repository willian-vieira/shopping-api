package com.api_shopping.repository;

import com.api_shopping.dto.ShopReportDTO;
import com.api_shopping.entities.Shop;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;

public class ReportRepositoryImpl implements IReportRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Shop> getShopByFilters(Date dataInicio, Date dataFim, Float valorMinimo) {
        return List.of();
    }

    @Override
    public ShopReportDTO getReportByDate(Date dataInicio, Date dataFim) {
        return null;
    }
}