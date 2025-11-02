package com.bcnc.prices.infrastructure.adapter.in.rest.mapper;

import com.bcnc.prices.domain.model.PriceModel;
import com.bcnc.prices.infrastructure.adapter.in.rest.dto.PriceResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Mapper para convertir PriceModel a PriceResponse.
 */
@Mapper(componentModel = "spring")
public interface PriceResponseMapper {

  PriceResponseMapper INSTANCE = Mappers.getMapper(PriceResponseMapper.class);

  PriceResponse toResponse(PriceModel price);
}
