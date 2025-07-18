package com.clustered.DataWarehouse.DTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class DealListDTO {

    @NotEmpty(message = "deals can't be empty")
    @Valid
    private ArrayList<DealDTO> deals;
}

