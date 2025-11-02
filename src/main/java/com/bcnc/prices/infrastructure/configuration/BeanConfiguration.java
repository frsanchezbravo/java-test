package com.bcnc.prices.infrastructure.configuration;

import com.bcnc.prices.application.service.GetPriceUseCase;
import com.bcnc.prices.domain.port.in.GetPriceQuery;
import com.bcnc.prices.domain.port.out.PriceRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuración de beans para la aplicación.
 */
@Configuration
public class BeanConfiguration {

  @Bean
  public GetPriceQuery getPriceQuery(PriceRepositoryPort priceRepositoryPort) {
    return new GetPriceUseCase(priceRepositoryPort);
  }
}
