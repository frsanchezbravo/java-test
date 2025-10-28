package com.bcnc.prices.application.port.out;

import com.bcnc.prices.domain.model.Price;
import java.time.LocalDateTime;
import java.util.Optional;

public interface PriceRepositoryPort {
    Optional<Price> findApplicablePrice(Long productId, Long brandId, LocalDateTime applicationDate);
}
