package com.bcnc.prices.application.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.bcnc.prices.application.service.GetPriceUseCase;
import com.bcnc.prices.domain.model.PriceModel;
import com.bcnc.prices.domain.port.out.PriceRepositoryPort;
import java.time.LocalDateTime;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class GetPriceUseCaseTest {

    private PriceRepositoryPort priceRepositoryPort;
    private GetPriceUseCase getPriceUseCase;

    @BeforeEach
    void setUp() {
        priceRepositoryPort = Mockito.mock(PriceRepositoryPort.class);
        getPriceUseCase = new GetPriceUseCase(priceRepositoryPort);
    }

    @Test
    void shouldReturnPriceWhenFound() {
        LocalDateTime date = LocalDateTime.of(2020, 6, 14, 10, 0);
        PriceModel expectedPrice = new PriceModel(35455L, 1L, 1L, date, date.plusDays(1), 35.50, "EUR");

        when(priceRepositoryPort.findApplicablePrice(35455L, 1L, date))
                .thenReturn(Optional.of(expectedPrice));

        PriceModel result = getPriceUseCase.getApplicablePrice(35455L, 1L, date);

        assertNotNull(result);
        assertEquals(expectedPrice.productId(), result.productId());
        assertEquals(expectedPrice.price(), result.price());
        verify(priceRepositoryPort, times(1))
                .findApplicablePrice(35455L, 1L, date);
    }

    @Test
    void shouldReturnEmptyWhenPriceNotFound() {
        LocalDateTime date = LocalDateTime.of(2020, 6, 14, 10, 0);

        when(priceRepositoryPort.findApplicablePrice(35455L, 1L, date))
                .thenReturn(Optional.empty());

        PriceModel result = getPriceUseCase.getApplicablePrice(35455L, 1L, date);
        assertNull(result);
    }
}
