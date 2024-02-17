package com.ar.rmsbackend.feature.sale;

import com.ar.rmsbackend.common.Routes.Router;
import com.ar.rmsbackend.generic.controller.AbstractController;
import com.ar.rmsbackend.generic.service.IService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping(Router.SALE)
public class SaleController extends AbstractController<Sale, SaleDto> {

    private final SaleService saleService;

    public SaleController(IService<Sale, SaleDto> service, SaleService saleService) {
        super(service);
        this.saleService = saleService;
    }


    @GetMapping(Router.TOTAL_SALE_AMOUNT_BY_DATE)
    public ResponseEntity<TotalSaleResponse> getTotalSaleAmount(
            @RequestParam(value = "sale-Date", required = false) LocalDate saleDate) {
        return new ResponseEntity<>(saleService.getTotalSaleAmountByDate(saleDate), HttpStatus.OK);
    }

    @GetMapping(Router.MAX_SALE_DAY)
    public ResponseEntity<MaxSellDayResponseDto> getMaxSaleDay() {
        return new ResponseEntity<>(saleService.getMaxSaleDay(), HttpStatus.OK);
    }
}
