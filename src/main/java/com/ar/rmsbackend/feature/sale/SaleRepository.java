package com.ar.rmsbackend.feature.sale;

import com.ar.rmsbackend.generic.repository.AbstractRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;


@Repository
public interface SaleRepository extends AbstractRepository<Sale> {


    @Query("select sum(s.totalPrice) from Sale s where s.saleDate = :sellDate")
    Double getTotalSaleAmountByDate(LocalDate sellDate);

    @Query(value = "select sum(total_price)  as maxSale,\n" +
            "                  sale_date     as maxSaleDate\n" +
            "from sale\n" +
            "group by sale_date\n" +
            "order by maxSale desc\n" +
            "limit 1", nativeQuery = true)
    MaxSaleRes findMaxSaleDay();
}
