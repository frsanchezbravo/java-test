package com.bcnc.prices.infrastructure.adapter.out.persistence.entity;

import com.bcnc.prices.domain.model.PriceModel;
import com.bcnc.prices.domain.port.out.PriceRepositoryPort;
import com.bcnc.prices.infrastructure.adapter.out.persistence.entity.mapper.PriceModelMapper;
import java.time.LocalDateTime;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Adaptador que implementa el puerto de salida PriceRepositoryPort usando JPA.
 */
@Component
@AllArgsConstructor
public class PriceRepositoryAdapter implements PriceRepositoryPort {

  private final SpringDataPriceRepository repository;

  private final PriceModelMapper priceModelMapper;

  @Override
  public Optional<PriceModel> findApplicablePrice(Long productId, Long brandId,
      LocalDateTime applicationDate) {
    return repository.findApplicablePrice(productId, brandId, applicationDate)
        .map(priceModelMapper::toDomain);
  }
}
