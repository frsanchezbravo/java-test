package com.bcnc.prices.infrastructure.adapter.out.persistence.entity;

import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Repositorio de Spring Data para acceder a la tabla de precios.
 * Proporciona métodos de consulta básicos y personalizados.
 */
public interface SpringDataPriceRepository extends JpaRepository<PriceEntity, Long> {

  @Query("""
          SELECT p FROM PriceEntity p
          WHERE p.productId = :productId
            AND p.brandId = :brandId
            AND :applicationDate BETWEEN p.startDate AND p.endDate
          ORDER BY p.priority DESC
          LIMIT 1
      """)
  Optional<PriceEntity> findApplicablePrice(@Param("productId") Long productId,
      @Param("brandId") Long brandId, @Param("applicationDate") LocalDateTime applicationDate);
}
