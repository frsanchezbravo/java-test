package com.bcnc.prices.infrastructure.adapter.in.rest.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO de respuesta para exponer la información de un precio.
 * Contiene todos los datos relevantes de un PriceModel.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PriceResponse {

  private Long productId;
  private Long brandId;
  private Integer priceList;

  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
  private LocalDateTime startDate;

  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
  private LocalDateTime endDate;

  private BigDecimal price;
  private String currency;
}