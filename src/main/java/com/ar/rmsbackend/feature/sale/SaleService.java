package com.ar.rmsbackend.feature.sale;

import com.ar.rmsbackend.generic.service.IService;

import java.time.LocalDate;

public interface SaleService extends IService<Sale, SaleDto> {
    TotalSaleResponse getTotalSaleAmountByDate(LocalDate saleDate);

    MaxSellDayResponseDto getMaxSaleDay();
}
