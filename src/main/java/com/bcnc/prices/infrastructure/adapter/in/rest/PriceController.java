package com.bcnc.prices.infrastructure.adapter.in.rest;

import com.bcnc.prices.application.port.in.GetPriceQuery;
import com.bcnc.prices.domain.model.Price;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/prices")
public class PriceController {

    private final GetPriceQuery getPriceQuery;

    public PriceController(GetPriceQuery getPriceQuery) {
        this.getPriceQuery = getPriceQuery;
    }

    @GetMapping
    public ResponseEntity<Price> getPrice(
            @RequestParam Long productId,
            @RequestParam Long brandId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime applicationDate
    ) {
        Price price = getPriceQuery.getApplicablePrice(productId, brandId, applicationDate);
        return price != null ? ResponseEntity.ok(price) : ResponseEntity.notFound().build();
    }
}
