package com.bcnc.prices.domain.port.out;

import com.bcnc.prices.domain.model.PriceModel;
import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Puerto de salida (outbound port) para operaciones de persistencia de precios.
 */
public interface PriceRepositoryPort {

  Optional<PriceModel> findApplicablePrice(Long productId, Long brandId,
      LocalDateTime applicationDate);
}
