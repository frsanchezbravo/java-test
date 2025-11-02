package com.bcnc.prices.infrastructure.adapter.out.jpa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.bcnc.prices.domain.model.PriceModel;
import com.bcnc.prices.infrastructure.adapter.out.persistence.entity.PriceEntity;
import com.bcnc.prices.infrastructure.adapter.out.persistence.entity.PriceRepositoryAdapter;
import com.bcnc.prices.infrastructure.adapter.out.persistence.entity.SpringDataPriceRepository;
import com.bcnc.prices.infrastructure.adapter.out.persistence.entity.mapper.PriceModelMapper;
import java.time.LocalDateTime;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PriceRepositoryAdapterTest {

  private SpringDataPriceRepository repository;
  private PriceModelMapper mapper;
  private PriceRepositoryAdapter adapter;

  @BeforeEach
  void setUp() {
    repository = mock(SpringDataPriceRepository.class);
    mapper = mock(PriceModelMapper.class);
    adapter = new PriceRepositoryAdapter(repository, mapper);
  }

  @Test
  void shouldReturnMappedDomainPrice() {
    LocalDateTime date = LocalDateTime.now();

    PriceEntity entity =
        PriceEntity.builder()
            .productId(35455L)
            .brandId(1L)
            .priceList(1L)
            .price(3550L)
            .curr("EUR")
            .startDate(date)
            .endDate(date.plusDays(1))
            .build();

    PriceModel expectedModel = new PriceModel(35455L, 1L, 1L, date, date.plusDays(1), 35.50, "EUR");

    when(repository.findApplicablePrice(anyLong(), anyLong(), any()))
        .thenReturn(Optional.of(entity));
    when(mapper.toDomain(entity)).thenReturn(expectedModel);

    Optional<PriceModel> result = adapter.findApplicablePrice(35455L, 1L, date);

    assertTrue(result.isPresent());
    assertEquals(expectedModel.productId(), result.get().productId());
    verify(mapper, times(1)).toDomain(entity);
  }

  @Test
  void shouldReturnEmptyWhenRepositoryReturnsEmpty() {
    when(repository.findApplicablePrice(anyLong(), anyLong(), any())).thenReturn(Optional.empty());

    Optional<PriceModel> result = adapter.findApplicablePrice(35455L, 1L, LocalDateTime.now());

    assertTrue(result.isEmpty());
    verify(mapper, never()).toDomain(any());
  }
}
