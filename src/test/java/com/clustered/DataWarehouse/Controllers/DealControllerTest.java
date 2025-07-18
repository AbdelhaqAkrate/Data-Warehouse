package com.clustered.DataWarehouse.Controllers;

import com.clustered.DataWarehouse.DTO.DealDTO;
import com.clustered.DataWarehouse.DTO.DealListDTO;
import com.clustered.DataWarehouse.Services.DealService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DealController.class)
class DealControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DealService dealService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Test create method")
    void testCreate() throws Exception {
        DealDTO validDeal = new DealDTO("Deal1", "MAD", "MAD", 120.22);

        Mockito.when(dealService.create(any(DealDTO.class))).thenReturn(validDeal);

        mockMvc.perform(post("/api/deals/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(validDeal)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message").value("Deal inserted successfully"))
                .andExpect(jsonPath("$.data.id").value("Deal1"))
                .andExpect(jsonPath("$.errors").doesNotExist());
    }

    @Test
    @DisplayName("Test insert method")
    void testInsert() throws Exception {
        DealDTO validDeal = new DealDTO("Deal1", "MAD", "MAD", 120.22);
        DealDTO invalidDeal1 = new DealDTO("Deal2", "MAD", "MAD", -120.22);
        DealDTO invalidDeal2 = new DealDTO("Deal3", "MA", "MA", 120.22);

        List<DealDTO> dealList = List.of(validDeal, invalidDeal1, invalidDeal2);
        DealListDTO listDTO = new DealListDTO(new ArrayList<>(dealList));

        List<DealDTO> inserted = new ArrayList<>(List.of(validDeal));

        List<Map<String, Object>> errors = new ArrayList<>();
        errors.add(Map.of(
                "id", "Deal2",
                "errors", List.of("Amount must be greater than 0.")
        ));
        errors.add(Map.of(
                "id", "Deal3",
                "errors", List.of(
                        "fromCurrency must contain only letters and be 3 characters.",
                        "toCurrency must contain only letters and be 3 characters.")
        ));

        Map<String, Object> result = new HashMap<>();
        result.put("inserted", inserted);
        result.put("errors", errors);

        Mockito.when(dealService.insert(any())).thenReturn(result);

        mockMvc.perform(post("/api/deals/insert")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(listDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Deals inserted successfully"))
                .andExpect(jsonPath("$.data[0].id").value("Deal1"))
                .andExpect(jsonPath("$.errors[0].id").value("Deal2"))
                .andExpect(jsonPath("$.errors[0].errors[0]").value("Amount must be greater than 0."))
                .andExpect(jsonPath("$.errors[1].id").value("Deal3"))
                .andExpect(jsonPath("$.errors[1].errors[0]").value("fromCurrency must contain only letters and be 3 characters."))
                .andExpect(jsonPath("$.errors[1].errors[1]").value("toCurrency must contain only letters and be 3 characters."));
    }
}
