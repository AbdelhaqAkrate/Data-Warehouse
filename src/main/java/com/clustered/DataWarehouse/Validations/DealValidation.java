package com.clustered.DataWarehouse.Validations;

import com.clustered.DataWarehouse.DTO.DealDTO;

import java.util.ArrayList;

public class DealValidation {

    public static void validate(DealDTO dealDTO, ArrayList<String> errors) {
        if (dealDTO.getId() == null) {
            errors.add("Id is required.");
        }

        if (dealDTO.getAmount() == null || dealDTO.getAmount() <= 0) {
            errors.add("Amount must be greater than 0.");
        }

        if (dealDTO.getFromCurrency() == null || dealDTO.getFromCurrency().isBlank()) {
            errors.add("fromCurrency is required.");
        }

        if (dealDTO.getToCurrency() == null || dealDTO.getToCurrency().isBlank()) {
            errors.add("toCurrency is required.");
        }

        if (dealDTO.getFromCurrency() != null && !isValidCurrency(dealDTO.getFromCurrency())) {
            errors.add("fromCurrency must contain only letters and be 3 characters.");
        }

        if (dealDTO.getToCurrency() != null && !isValidCurrency(dealDTO.getToCurrency())) {
            errors.add("toCurrency must contain only letters and be 3 characters.");
        }
    }

    private static boolean isValidCurrency(String currency) {
        return currency.matches("^[a-zA-Z]{3}$");
    }
}