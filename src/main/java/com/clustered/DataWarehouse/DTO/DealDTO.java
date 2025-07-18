package com.clustered.DataWarehouse.DTO;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DealDTO {
    @NotBlank(message = "Deal ID is required")
    private String id;

    @NotBlank(message = "From currency is required")
    @Size(min = 3, max = 3)
    private String fromCurrency;

    @NotBlank(message = "To currency is required")
    @Size(min = 3, max = 3)
    private String toCurrency;

    @NotNull(message = "Amount is required")
    @Positive(message = "Amount must be greater than 0")
    private Double amount;
}
