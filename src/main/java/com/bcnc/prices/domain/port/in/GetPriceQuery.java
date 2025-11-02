package com.bcnc.prices.domain.port.in;

import com.bcnc.prices.domain.model.PriceModel;
import java.time.LocalDateTime;

/**
 * Puerto de entrada (inbound port) que define la operación de obtención de precio.
 */
public interface GetPriceQuery {

  PriceModel getApplicablePrice(Long productId, Long brandId, LocalDateTime applicationDate);

}
