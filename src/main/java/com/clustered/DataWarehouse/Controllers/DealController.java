package com.clustered.DataWarehouse.Controllers;

import com.clustered.DataWarehouse.DTO.DealDTO;
import com.clustered.DataWarehouse.DTO.DealListDTO;
import com.clustered.DataWarehouse.Responses.ApiResponse;
import com.clustered.DataWarehouse.Services.DealService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/deals")
@RequiredArgsConstructor
@Tag(name = "Deals", description = "Creating a deal or many deals")
public class DealController {

    private final DealService dealService;

    @Operation(summary = "Create a single deal")
    @PostMapping("/create")
    public ResponseEntity<ApiResponse> create(@RequestBody DealDTO dealDTO) {
        DealDTO created = dealService.create(dealDTO);
        ApiResponse response = new ApiResponse("Deal inserted successfully", created);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Insert many deals")
    @PostMapping("/insert")
    public ResponseEntity<ApiResponse> insert(@RequestBody DealListDTO dealListDTO) {
        var result = dealService.insert(dealListDTO.getDeals());

        var succeeded = (ArrayList<?>) result.get("inserted");
        var failed = (ArrayList<?>) result.get("errors");

        ApiResponse response = new ApiResponse("Deals inserted successfully", succeeded, failed.isEmpty() ? null : failed);
        return ResponseEntity.ok(response);
    }
}
