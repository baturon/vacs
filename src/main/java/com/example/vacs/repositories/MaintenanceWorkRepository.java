package com.example.vacs.repositories;

import com.example.vacs.models.MaintenanceWork;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaintenanceWorkRepository extends JpaRepository<MaintenanceWork,Long> {

}
