package com.example.vacs.repositories;

import com.example.vacs.models.Firm;
import com.example.vacs.models.Model;
import org.apache.catalina.LifecycleState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FirmRepository extends JpaRepository<Firm, Long> {


}

