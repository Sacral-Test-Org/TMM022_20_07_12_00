package com.example.tmm022_fmb.service;

import com.example.tmm022_fmb.repository.LineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LineService {

    @Autowired
    private LineRepository lineRepository;

    public boolean validateLineIdAndDescription(int globalParameter, String lineId, String lineDescription) {
        int count = lineRepository.validateLineIdAndDescription(globalParameter, lineId, lineDescription);
        return count > 0;
    }
}
