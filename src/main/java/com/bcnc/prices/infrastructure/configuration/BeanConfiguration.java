package com.bcnc.prices.infrastructure.configuration;

import com.bcnc.prices.application.port.in.GetPriceQuery;
import com.bcnc.prices.application.service.GetPriceUseCase;
import com.bcnc.prices.application.port.out.PriceRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public GetPriceQuery getPriceQuery(PriceRepositoryPort priceRepositoryPort) {
        return new GetPriceUseCase(priceRepositoryPort);
    }
}
