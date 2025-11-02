package com.bcnc.prices.infrastructure.adapter.out.persistence.mapper;

import com.bcnc.prices.domain.model.PriceModel;
import com.bcnc.prices.infrastructure.adapter.out.persistence.entity.PriceEntity;
import org.mapstruct.Mapper;

/**
 * Mapper para la persistencia de PriceEntity.
 */
@Mapper(componentModel = "spring")
public interface PricePersistenceMapper {

  PriceModel toDomain(PriceEntity entity);
}