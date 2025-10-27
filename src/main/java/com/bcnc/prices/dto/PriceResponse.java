package com.bcnc.prices.dto;

import java.time.LocalDateTime;

public record PriceResponse(
        Long productId,
        Long brandId,
        Long priceList,
        LocalDateTime startDate,
        LocalDateTime endDate,
        Double price,
        String curr
) {}