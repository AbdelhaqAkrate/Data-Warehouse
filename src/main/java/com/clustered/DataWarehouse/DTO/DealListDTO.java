package com.clustered.DataWarehouse.DTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.ArrayList;

@Data
public class DealListDTO {

    @NotEmpty(message = "deals can't be empty")
    @Valid
    private ArrayList<DealDTO> deals;
}

