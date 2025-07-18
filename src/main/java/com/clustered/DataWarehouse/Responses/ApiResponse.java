package com.clustered.DataWarehouse.Responses;

import lombok.*;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {
    private String message;
    private Object data;
    private Object errors;

    public ApiResponse(String message) {
        this.message = message;
    }

    public ApiResponse(String message, Object data) {
        this.message = message;
        this.data = data;
    }
}
