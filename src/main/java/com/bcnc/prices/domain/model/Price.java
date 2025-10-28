package com.bcnc.prices.domain.model;

import java.time.LocalDateTime;

public record Price(
        Long productId,
        Long brandId,
        Long priceList,
        LocalDateTime startDate,
        LocalDateTime endDate,
        Double price,
        String curr
) {}
