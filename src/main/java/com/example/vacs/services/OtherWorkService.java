package com.example.vacs.services;

import com.example.vacs.models.Auto;
import com.example.vacs.models.MaintenanceWork;
import com.example.vacs.models.OtherWork;
import com.example.vacs.repositories.AutoRepository;
import com.example.vacs.repositories.OtherWorkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OtherWorkService {
    private final OtherWorkRepository otherWorkRepository;
    private final AutoRepository autoRepository;


    public List<OtherWork> getSortedListOtherWorkByDate(Auto auto,String startDateStr, String endDateStr) throws ParseException {
        LocalDate startDate=LocalDate.parse(startDateStr);
        LocalDate endDate=LocalDate.parse(endDateStr);
        List<OtherWork> otherWorkListSorted=otherWorkRepository.findByAutoAndDateChangeBetween(auto, startDate, endDate);
        Collections.sort(otherWorkListSorted,Comparator.comparing(OtherWork::getDateChange));
        return otherWorkListSorted;
    }
}