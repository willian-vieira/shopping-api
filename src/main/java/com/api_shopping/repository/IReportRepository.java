package com.api_shopping.repository;

import com.api_shopping.dto.ShopReportDTO;
import com.api_shopping.entities.Shop;
import java.util.Date;
import java.util.List;

public interface IReportRepository {
    public List<Shop> getShopByFilters(Date dataInicio, Date dataFim, Float valorMinimo);
    public ShopReportDTO getReportByDate(Date dataInicio, Date dataFim);
}