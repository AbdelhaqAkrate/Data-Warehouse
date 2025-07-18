package com.clustered.DataWarehouse.Controllers;

import com.clustered.DataWarehouse.DTO.DealDTO;
import com.clustered.DataWarehouse.DTO.DealListDTO;
import com.clustered.DataWarehouse.Responses.ApiResponse;
import com.clustered.DataWarehouse.Services.DealService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/deals")
@RequiredArgsConstructor
public class DealController {

    private final DealService dealService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> create(@RequestBody DealDTO dealDTO) {
        DealDTO created = dealService.create(dealDTO);
        ApiResponse response = new ApiResponse("Deal inserted successfully", created);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/insert")
    public ResponseEntity<ApiResponse> insert(@RequestBody DealListDTO dealListDTO) {
        var result = dealService.insert(dealListDTO.getDeals());

        var succeeded = (ArrayList<?>) result.get("inserted");
        var failed = (ArrayList<String>) result.get("errors");

        ApiResponse response = new ApiResponse("Deals inserted successfully", succeeded, failed.isEmpty() ? null : failed);
        return ResponseEntity.ok(response);
    }
}
