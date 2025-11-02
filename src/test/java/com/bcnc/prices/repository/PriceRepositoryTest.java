package com.bcnc.prices.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.bcnc.prices.infrastructure.adapter.out.persistence.entity.PriceEntity;
import com.bcnc.prices.infrastructure.adapter.out.persistence.entity.SpringDataPriceRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class PriceRepositoryTest {

  @Autowired private SpringDataPriceRepository repository;

  private PriceEntity price1;
  private PriceEntity price2;

  @BeforeEach
  void setUp() {
    // Limpiamos la tabla antes de cada test
    repository.deleteAll();

    LocalDateTime start1 = LocalDateTime.of(2020, 6, 14, 0, 0);
    LocalDateTime end1 = LocalDateTime.of(2020, 12, 31, 23, 59);
    price1 =
        PriceEntity.builder()
            .productId(35455L)
            .brandId(1L)
            .priceList(1L)
            .priority(0)
            .price(3550L)
            .curr("EUR")
            .startDate(start1)
            .endDate(end1)
            .build();

    LocalDateTime start2 = LocalDateTime.of(2020, 6, 14, 15, 0);
    LocalDateTime end2 = LocalDateTime.of(2020, 6, 14, 18, 30);
    price2 =
        PriceEntity.builder()
            .productId(35455L)
            .brandId(1L)
            .priceList(2L)
            .priority(1)
            .price(2545L)
            .curr("EUR")
            .startDate(start2)
            .endDate(end2)
            .build();

    repository.saveAll(List.of(price1, price2));
  }

  @Test
  void testFindApplicablePrice_singleResult() {
    LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 14, 10, 0);
    Optional<PriceEntity> result = repository.findApplicablePrice(35455L, 1L, applicationDate);

    assertTrue(result.isPresent());
    assertEquals(price1.getPriceList(), result.get().getPriceList());
  }

  @Test
  void testFindApplicablePrice_multipleResults_returnsHighestPriority() {
    LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 14, 16, 0);
    Optional<PriceEntity> result = repository.findApplicablePrice(35455L, 1L, applicationDate);

    assertTrue(result.isPresent());
    assertEquals(price2.getPriceList(), result.get().getPriceList());
  }

  @Test
  void testFindApplicablePrice_noResult() {
    LocalDateTime applicationDate = LocalDateTime.of(2021, 1, 1, 0, 0);
    Optional<PriceEntity> result = repository.findApplicablePrice(35455L, 1L, applicationDate);

    assertTrue(result.isEmpty());
  }
}
