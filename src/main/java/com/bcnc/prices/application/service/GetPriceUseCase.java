package com.bcnc.prices.application.service;

import com.bcnc.prices.domain.model.PriceModel;
import com.bcnc.prices.domain.port.in.GetPriceQuery;
import com.bcnc.prices.domain.port.out.PriceRepositoryPort;
import java.time.LocalDateTime;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Caso de uso para obtener el precio aplicable de un producto dado.
 * Implementa la interfaz {@link GetPriceQuery}.
 */
@RequiredArgsConstructor
@Component
public class GetPriceUseCase implements GetPriceQuery {

  private final PriceRepositoryPort priceRepositoryPort;

  @Override
  public PriceModel getApplicablePrice(
      Long productId, Long brandId, LocalDateTime applicationDate) {
    Optional<PriceModel> priceOpt =
        priceRepositoryPort.findApplicablePrice(productId, brandId, applicationDate);
    return priceOpt.orElse(null);
  }
}
