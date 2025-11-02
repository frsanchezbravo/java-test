package com.bcnc.prices.domain.model;

import java.time.LocalDateTime;

/**
 * Modelo de dominio que representa un precio de un producto.
 *
 * @param productId ID del producto
 * @param brandId   ID de la marca
 * @param priceList ID de la tarifa
 * @param startDate Fecha de inicio de aplicación del precio
 * @param endDate   Fecha de fin de aplicación del precio
 * @param price     Precio en euros
 * @param curr      Moneda
 */
public record PriceModel(Long productId, Long brandId, Long priceList, LocalDateTime startDate,
    LocalDateTime endDate, Double price, String curr) {
}
