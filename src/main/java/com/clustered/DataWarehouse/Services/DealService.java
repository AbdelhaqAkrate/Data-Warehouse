package com.clustered.DataWarehouse.Services;

import com.clustered.DataWarehouse.DTO.DealDTO;

import java.util.ArrayList;
import java.util.Map;

public interface DealService {
    public DealDTO create(DealDTO deal);
    public Map<String, Object> insert(ArrayList<DealDTO> dealDTOs);
}
