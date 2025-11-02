package com.bcnc.prices.infrastructure.adapter.out.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * Entidad JPA que representa la tabla PRICES.
 */
@Entity
@Table(name = "PRICES", indexes = {
    @Index(name = "idx_product_brand", columnList = "product_id, brand_id"),
    @Index(name = "idx_start_end", columnList = "start_date, end_date") })
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Accessors(fluent = false)
public class PriceEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Long brandId;
  private LocalDateTime startDate;
  private LocalDateTime endDate;
  private Long priceList;
  private Long productId;
  private int priority;
  private Long price;
  private String curr;

}
