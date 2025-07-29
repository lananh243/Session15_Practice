package com.ra.ss15.service.imp;

import com.ra.ss15.model.dto.request.ComboRequest;
import com.ra.ss15.model.entity.Combo;
import com.ra.ss15.repository.ComboRepository;
import com.ra.ss15.service.ComboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ComboServiceImp implements ComboService {
    @Autowired
    private ComboRepository comboRepository;

    @Override
    public List<Combo> findAll() {
        return comboRepository.findAll();
    }

    @Override
    public Combo findById(Long id) {
        return comboRepository.findById(id).orElseThrow(()-> new RuntimeException("Combo not found"));
    }

    @Override
    public Combo save(ComboRequest request) {
        Combo combo = Combo.builder()
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .items(request.getItems())
                .status(request.getStatus())
                .build();
        return comboRepository.save(combo);
    }

    @Override
    public Combo update(ComboRequest request, Long id) {
        Combo combo = comboRepository.findById(id).orElseThrow(()-> new RuntimeException("Combo not found"));
        combo.setName(request.getName());
        combo.setDescription(request.getDescription());
        combo.setPrice(request.getPrice());
        combo.setItems(request.getItems());
        combo.setStatus(request.getStatus());
        return comboRepository.save(combo);
    }

    @Override
    public void delete(Long id) {
        Combo combo = comboRepository.findById(id).orElseThrow(()-> new RuntimeException("Combo not found"));
        comboRepository.delete(combo);
    }
}
