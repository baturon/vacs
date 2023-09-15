package com.example.vacs.repositories;

import com.example.vacs.models.OtherWork;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OtherWorkRepository extends JpaRepository<OtherWork,Long> {

}
