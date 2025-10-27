package com.bcnc.prices.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.bcnc.prices.dto.PriceResponse;
import com.bcnc.prices.model.Price;
import com.bcnc.prices.repository.PriceRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PriceService {

    private final PriceRepository priceRepository;

    public Optional<PriceResponse> getApplicablePrice(Long productId, Long brandId, LocalDateTime applicationDate) {
        List<Price> prices = priceRepository.findApplicablePrices(productId, brandId, applicationDate);

        if (prices.isEmpty()) {
            return Optional.empty();
        }

        Price price = prices.get(0); // el de mayor prioridad

        return Optional.of(new PriceResponse(
                price.getProductId(),
                price.getBrandId(),
                price.getPriceList(),
                price.getStartDate(),
                price.getEndDate(),
                price.getPrice() / 100.0, 
                price.getCurr()
        ));
    }
}
