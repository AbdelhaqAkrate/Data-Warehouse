package com.clustered.DataWarehouse.Repositories;

import com.clustered.DataWarehouse.Models.Deal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DealRepository extends JpaRepository<Deal, String> { }
