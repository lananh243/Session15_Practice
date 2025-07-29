package com.ra.ss15.service;

import com.ra.ss15.model.dto.request.ComboRequest;
import com.ra.ss15.model.entity.Combo;

import java.util.List;

public interface ComboService {
    List<Combo> findAll();
    Combo findById(Long id);
    Combo save(ComboRequest request);
    Combo update(ComboRequest request, Long id);
    void delete(Long id);
}
