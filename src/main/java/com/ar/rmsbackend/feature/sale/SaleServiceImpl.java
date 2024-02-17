package com.ar.rmsbackend.feature.sale;

import com.ar.rmsbackend.feature.order.OrderService;
import com.ar.rmsbackend.generic.repository.AbstractRepository;
import com.ar.rmsbackend.generic.service.AbstractService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Objects;

@Service
public class SaleServiceImpl extends AbstractService<Sale, SaleDto> implements SaleService {

    private final OrderService orderService;

    private final SaleRepository saleRepository;

    public SaleServiceImpl(AbstractRepository<Sale> repository, OrderService orderService, SaleRepository saleRepository) {
        super(repository);
        this.orderService = orderService;
        this.saleRepository = saleRepository;
    }

    @Override
    protected SaleResponseDto convertToResponseDto(Sale sale) {
        return SaleResponseDto.builder()
                .id(sale.getId())
                .orderId(sale.getOrderId())
                .saleDate(sale.getSaleDate())
                .totalPrice(sale.getTotalPrice())
                .build();
    }

    @Override
    protected Sale convertToEntity(SaleDto dto) {
        return mapToEntity(new Sale(), dto);
    }

    @Override
    protected Sale convertToEntity(SaleDto dto, Sale entity) {
        return mapToEntity(entity, dto);
    }

    private Sale mapToEntity(Sale entity, SaleDto dto) {
        entity.setOrder(orderService.findById(dto.getOrderId()));
        entity.setSaleDate(LocalDate.now());
        entity.setTotalPrice(dto.getTotalPrice());
        return entity;
    }

    @Override
    public TotalSaleResponse getTotalSaleAmountByDate(LocalDate saleDate) {
        if (Objects.isNull(saleDate)) {
            saleDate = LocalDate.now();
        }
        Double amount = saleRepository.getTotalSaleAmountByDate(saleDate);
        return TotalSaleResponse.builder().amount(amount).saleDate(saleDate).build();
    }

    @Override
    public MaxSellDayResponseDto getMaxSaleDay() {
        MaxSaleRes maxSaleRes = saleRepository.findMaxSaleDay();
        return MaxSellDayResponseDto.builder()
                .saleDate(maxSaleRes.getMaxSaleDate())
                .amount(maxSaleRes.getMaxSale())
                .build();
    }
}
