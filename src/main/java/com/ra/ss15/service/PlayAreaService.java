package com.ra.ss15.service;

import com.ra.ss15.model.dto.request.PlayAreaRequest;
import com.ra.ss15.model.entity.PlayArea;

import java.util.List;

public interface PlayAreaService {
    List<PlayArea> findAll();
    PlayArea findById(Long id);
    PlayArea save(PlayAreaRequest request);
    PlayArea update(PlayAreaRequest request, Long id);
    void delete(Long id);
}
