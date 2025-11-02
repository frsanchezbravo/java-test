package com.bcnc.prices.infrastructure.adapter.in.rest;

import com.bcnc.prices.domain.model.PriceModel;
import com.bcnc.prices.domain.port.in.GetPriceQuery;
import com.bcnc.prices.infrastructure.adapter.in.rest.dto.PriceResponse;
import com.bcnc.prices.infrastructure.adapter.in.rest.mapper.PriceResponseMapper;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/** Controlador REST para exponer los endpoints relacionados con precios. */
@RestController
@RequestMapping(value = "/api/v1/prices", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class PriceController {

  private final GetPriceQuery getPriceQuery;
  private final PriceResponseMapper priceResponseMapper;

  /**
   * Obtiene el precio aplicable de un producto para una marca y fecha dadas.
   *
   * @param productId ID del producto
   * @param brandId ID de la marca
   * @param applicationDate Fecha de aplicacion del precio
   * @return PriceResponse con el precio correspondiente
   */
  @GetMapping
  public ResponseEntity<PriceResponse> getPrice(
      @RequestParam Long productId,
      @RequestParam Long brandId,
      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
          LocalDateTime applicationDate) {
    PriceModel price = getPriceQuery.getApplicablePrice(productId, brandId, applicationDate);
    if (price == null) {
      return ResponseEntity.notFound().build();
    }
    PriceResponse response = priceResponseMapper.toResponse(price);
    return ResponseEntity.ok(response);
  }
}
