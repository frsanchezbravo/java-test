package com.bcnc.prices.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Sql(scripts = "/data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class PriceControllerTest {

  private static final Logger log = LoggerFactory.getLogger(PriceControllerTest.class);

  @Autowired private MockMvc mockMvc;

  private final ObjectMapper objectMapper = new ObjectMapper();

  private String url(Long productId, Long brandId, LocalDateTime date) {
    return "/api/v1/prices?productId="
        + productId
        + "&brandId="
        + brandId
        + "&applicationDate="
        + date.toString();
  }

  private void runTest(
      String testName, Long productId, Long brandId, LocalDateTime date, Long expectedPriceList)
      throws Exception {
    log.info("Ejecutando {}", testName);

    MvcResult result =
        mockMvc
            .perform(get(url(productId, brandId, date)).contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andReturn();

    String responseJson = result.getResponse().getContentAsString();

    // Parseamos el JSON
    JsonNode jsonNode = objectMapper.readTree(responseJson);
    Long returnedPriceList = jsonNode.get("priceList").asLong();

    log.info("Resultado {}: {}", testName, responseJson);
    log.info(
        "PriceList esperado: {}, PriceList devuelto: {}", expectedPriceList, returnedPriceList);

    // Validamos que coincida con el esperado
    assertEquals(
        expectedPriceList, returnedPriceList, "El priceList devuelto no coincide con el esperado");
  }

  @Test
  void test1() throws Exception {
    runTest("Test 1", 35455L, 1L, LocalDateTime.of(2020, 6, 14, 10, 0, 0, 0), 1L);
  }

  @Test
  void test2() throws Exception {
    runTest("Test 2", 35455L, 1L, LocalDateTime.of(2020, 6, 14, 16, 0, 0, 0), 2L);
  }

  @Test
  void test3() throws Exception {
    runTest("Test 3", 35455L, 1L, LocalDateTime.of(2020, 6, 14, 21, 0, 0, 0), 1L);
  }

  @Test
  void test4() throws Exception {
    runTest("Test 4", 35455L, 1L, LocalDateTime.of(2020, 6, 15, 10, 0, 0, 0), 3L);
  }

  @Test
  void test5() throws Exception {
    runTest("Test 5", 35455L, 1L, LocalDateTime.of(2020, 6, 16, 21, 0, 0, 0), 4L);
  }

  @Test
  void shouldReturn404WhenPriceNotFound() throws Exception {
    log.info("Ejecutando test: shouldReturn404WhenPriceNotFound");

    mockMvc
        .perform(
            get(url(99999L, 1L, LocalDateTime.of(2020, 6, 14, 10, 0, 0)))
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound());
  }

  @Test
  void shouldReturn400WhenMissingParams() throws Exception {
    log.info("Ejecutando test: shouldReturn400WhenMissingParams");

    mockMvc
        .perform(get("/api/v1//prices?productId=35455").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest());
  }

  @Test
  void shouldReturn400WhenInvalidDateFormat() throws Exception {
    log.info("Ejecutando test: shouldReturn400WhenInvalidDateFormat");

    mockMvc
        .perform(
            get("/api/v1/prices?productId=35455&brandId=1&applicationDate=fecha-invalida")
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest());
  }

  @Test
  void testInvalidParams() throws Exception {
    log.info("Ejecutando test: testInvalidParams");
    mockMvc
        .perform(get("/api/v1//prices?productId=-1&brandId=1&applicationDate=invalid"))
        .andExpect(status().isBadRequest());
  }
}
