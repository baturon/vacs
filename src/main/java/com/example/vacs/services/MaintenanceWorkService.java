package com.example.vacs.services;

import com.example.vacs.models.MaintenanceWork;
import com.example.vacs.repositories.MaintenanceWorkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MaintenanceWorkService {
    private final MaintenanceWorkRepository maintenanceWorkRepository;

//    public MaintenanceWork saveMaintenanceWork(MaintenanceWork  maintenanceWork) {
//        return maintenanceWorkRepository.save(maintenanceWork);
//    }
}
