package com.bcnc.prices.application.service;

import com.bcnc.prices.application.port.in.GetPriceQuery;
import com.bcnc.prices.application.port.out.PriceRepositoryPort;
import com.bcnc.prices.domain.model.Price;

import java.time.LocalDateTime;
import java.util.Optional;

public class GetPriceUseCase implements GetPriceQuery {

    private final PriceRepositoryPort priceRepositoryPort;

    public GetPriceUseCase(PriceRepositoryPort priceRepositoryPort) {
        this.priceRepositoryPort = priceRepositoryPort;
    }

    @Override
    public Price getApplicablePrice(Long productId, Long brandId, LocalDateTime applicationDate) {
        Optional<Price> priceOpt = priceRepositoryPort.findApplicablePrice(productId, brandId, applicationDate);
        return priceOpt.orElse(null);
    }
}
