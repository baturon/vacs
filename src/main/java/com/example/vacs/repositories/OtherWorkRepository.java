package com.example.vacs.repositories;

import com.example.vacs.models.Auto;
import com.example.vacs.models.OtherWork;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface OtherWorkRepository extends JpaRepository<OtherWork,Long> {
    List<OtherWork> findAllByDateChangeBetween(Date startDate, Date endDate);
    List<OtherWork> findByAutoAndDateChangeBetween(Auto auto, LocalDate startDate, LocalDate endDate);
}
