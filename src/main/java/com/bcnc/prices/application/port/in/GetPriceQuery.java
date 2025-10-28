package com.bcnc.prices.application.port.in;

import com.bcnc.prices.domain.model.Price;
import java.time.LocalDateTime;

public interface GetPriceQuery {
    Price getApplicablePrice(Long productId, Long brandId, LocalDateTime applicationDate);
}
