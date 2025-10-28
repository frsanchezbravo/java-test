package com.bcnc.prices.infrastructure.adapter.out.persistence.entity;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.bcnc.prices.application.port.out.PriceRepositoryPort;
import com.bcnc.prices.domain.model.Price;

@Component
public class PriceRepositoryAdapter implements PriceRepositoryPort {

    private final SpringDataPriceRepository repository;

    public PriceRepositoryAdapter(SpringDataPriceRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Price> findApplicablePrice(Long productId, Long brandId, LocalDateTime applicationDate) {
        return repository.findTopByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(
                productId, brandId, applicationDate, applicationDate)
                .map(pe -> new Price(
                        pe.getProductId(),
                        pe.getBrandId(),
                        pe.getPriceList(),
                        pe.getStartDate(),
                        pe.getEndDate(),
                        pe.getPrice() / 100.0,
                        pe.getCurr()
                ));
    }
}
