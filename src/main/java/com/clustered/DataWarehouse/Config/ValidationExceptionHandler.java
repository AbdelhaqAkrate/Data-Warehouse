package com.clustered.DataWarehouse.Config;

import com.clustered.DataWarehouse.Exceptions.AlreadyExistException;
import com.clustered.DataWarehouse.Exceptions.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class ValidationExceptionHandler {

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handleValidationException(ValidationException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", ex.getMessage());
        response.put("errors", ex.getErrors());
        return response;
    }

    @ExceptionHandler(AlreadyExistException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handleAlreadyExistsException(AlreadyExistException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", ex.getMessage());
        return response;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, Object> handleAllExceptions(Exception ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Something went wrong please try again");
        return response;
    }
}
