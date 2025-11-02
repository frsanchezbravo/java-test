package com.bcnc.prices;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.bcnc.prices.domain.port.in.GetPriceQuery;
import com.bcnc.prices.domain.port.out.PriceRepositoryPort;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TestJavaApplicationTests {

  @Autowired private PriceRepositoryPort priceRepositoryPort;

  @Autowired private GetPriceQuery getPriceQuery;

  @Test
  void contextLoads() {
    assertNotNull(priceRepositoryPort, "El PriceRepositoryPort debería inyectarse correctamente");
    assertNotNull(getPriceQuery, "El GetPriceQuery debería inyectarse correctamente");
  }
}
