package com.bcnc.prices.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class PriceControllerTest {

    private static final Logger log = LoggerFactory.getLogger(PriceControllerTest.class);

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private String url(Long productId, Long brandId, LocalDateTime date) {
        return "/prices?productId=" + productId + "&brandId=" + brandId + "&applicationDate=" + date.toString();
    }

    private void runTest(String testName, Long productId, Long brandId, LocalDateTime date, Long expectedPriceList) throws Exception {
        log.info("Ejecutando {}", testName);

        MvcResult result = mockMvc.perform(get(url(productId, brandId, date))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String responseJson = result.getResponse().getContentAsString();

        // Parseamos el JSON
        JsonNode jsonNode = objectMapper.readTree(responseJson);
        Long returnedPriceList = jsonNode.get("priceList").asLong();

        log.info("Resultado {}: {}", testName, responseJson);
        log.info("PriceList esperado: {}, PriceList devuelto: {}", expectedPriceList, returnedPriceList);

        // Validamos que coincida con el esperado
        assertEquals(expectedPriceList, returnedPriceList, "El priceList devuelto no coincide con el esperado");
    }

    @Test
    void test1() throws Exception {
        runTest("Test 1", 35455L, 1L, LocalDateTime.of(2020,6,14,10,0,0,0), 1L);
    }

    @Test
    void test2() throws Exception {
        runTest("Test 2", 35455L, 1L, LocalDateTime.of(2020,6,14,16,0,0,0), 2L);
    }

    @Test
    void test3() throws Exception {
        runTest("Test 3", 35455L, 1L, LocalDateTime.of(2020,6,14,21,0,0,0), 1L);
    }

    @Test
    void test4() throws Exception {
        runTest("Test 4", 35455L, 1L, LocalDateTime.of(2020,6,15,10,0,0,0),3L);
    }

    @Test
    void test5() throws Exception {
        runTest("Test 5", 35455L, 1L, LocalDateTime.of(2020,6,16,21,0,0,0), 4L);
    }
}
