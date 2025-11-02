package com.bcnc.prices.infrastructure.adapter.out.persistence.entity.mapper;

import com.bcnc.prices.domain.model.PriceModel;
import com.bcnc.prices.infrastructure.adapter.out.persistence.entity.PriceEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper para convertir entre PriceEntity y PriceModel.
 */
@Mapper(componentModel = "spring")
public interface PriceModelMapper {

  // Accede a la propiedad 'price' de la entidad para la expresión
  @Mapping(target = "price", expression = "java(toDouble(entity.getPrice()))")
  PriceModel toDomain(PriceEntity entity); // <-- Cambiado de (PriceEntity price)

  /**
   * Convierte un precio en centimos (Long) a euros (Double).
   *
   * @param priceInCents Precio en centavos
   * @return Precio en euros, o {@code null} si el valor de entrada es {@code null}
   */
  default Double toDouble(Long priceInCents) {
    if (priceInCents == null) {
      return null;
    }
    return priceInCents.doubleValue() / 100.0;
  }
}