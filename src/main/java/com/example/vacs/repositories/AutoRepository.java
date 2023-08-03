package com.example.vacs.repositories;

import com.example.vacs.models.Auto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AutoRepository extends JpaRepository<Auto, Long> {


}
