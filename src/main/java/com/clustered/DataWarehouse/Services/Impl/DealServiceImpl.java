package com.clustered.DataWarehouse.Services.Impl;

import com.clustered.DataWarehouse.DTO.DealDTO;
import com.clustered.DataWarehouse.Exceptions.AlreadyExistException;
import com.clustered.DataWarehouse.Exceptions.ValidationException;
import com.clustered.DataWarehouse.Services.DealService;
import com.clustered.DataWarehouse.Validations.DealValidation;
import com.clustered.DataWarehouse.Models.Deal;
import com.clustered.DataWarehouse.Repositories.DealRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DealServiceImpl implements DealService {

    private final DealRepository dealRepository;
    private final ModelMapper modelMapper;

    public DealDTO create(DealDTO dealDTO) {
        ArrayList<String> validationErrors = new ArrayList<>();
        DealValidation.validate(dealDTO, validationErrors);


        if (dealRepository.findById(dealDTO.getId()).isPresent()) {
            throw new AlreadyExistException("Deal " + dealDTO.getId() + " already exists.");
        }

        if (!validationErrors.isEmpty()) {
            throw new ValidationException(validationErrors);
        }

        Deal deal = modelMapper.map(dealDTO, Deal.class);
        deal.setDealTimestamp(LocalDateTime.now());

        Deal saved = dealRepository.save(deal);
        return modelMapper.map(saved, DealDTO.class);
    }

    public Map<String, Object> insert(ArrayList<DealDTO> dealDTOs) {
        ArrayList<DealDTO> inserted = new ArrayList<>();
        ArrayList<Map<String, Object>> errors = new ArrayList<>();

        for (DealDTO dealDTO : dealDTOs) {
            ArrayList<String> validationErrors = new ArrayList<>();
            DealValidation.validate(dealDTO, validationErrors);

            if (!validationErrors.isEmpty()) {
                Map<String, Object> errorDetail = new HashMap<>();
                errorDetail.put("id", dealDTO.getId());
                errorDetail.put("errors", validationErrors);
                errors.add(errorDetail);
                continue;
            }

            if (dealRepository.findById(dealDTO.getId()).isPresent()) {
                Map<String, Object> errorDetail = new HashMap<>();
                errorDetail.put("id", dealDTO.getId());
                errorDetail.put("errors", List.of("Deal already exists."));
                errors.add(errorDetail);
                continue;
            }

            Deal deal = modelMapper.map(dealDTO, Deal.class);
            deal.setDealTimestamp(LocalDateTime.now());

            Deal saved = dealRepository.save(deal);
            inserted.add(modelMapper.map(saved, DealDTO.class));
        }

        Map<String, Object> result = new HashMap<>();
        result.put("inserted", inserted);
        result.put("errors", errors);
        return result;
    }
}
