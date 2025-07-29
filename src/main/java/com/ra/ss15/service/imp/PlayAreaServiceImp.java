package com.ra.ss15.service.imp;

import com.ra.ss15.model.dto.request.PlayAreaRequest;
import com.ra.ss15.model.entity.PlayArea;
import com.ra.ss15.repository.PlayAreaRepository;
import com.ra.ss15.service.PlayAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayAreaServiceImp implements PlayAreaService {
    @Autowired
    private PlayAreaRepository playAreaRepository;

    @Override
    public List<PlayArea> findAll() {
        return playAreaRepository.findAll();
    }

    @Override
    public PlayArea findById(Long id) {
        return playAreaRepository.findById(id).orElseThrow(()-> new RuntimeException("PlayArea Not Found"));
    }

    @Override
    public PlayArea save(PlayAreaRequest request) {
        PlayArea playArea = PlayArea.builder()
                .name(request.getName())
                .description(request.getDescription())
                .maxCapacity(request.getMaxCapacity())
                .status(request.getStatus())
                .build();
        return playAreaRepository.save(playArea);
    }

    @Override
    public PlayArea update(PlayAreaRequest request, Long id) {
        PlayArea updated = playAreaRepository.findById(id).orElseThrow(()-> new RuntimeException("PlayArea Not Found"));
        updated.setName(request.getName());
        updated.setDescription(request.getDescription());
        updated.setMaxCapacity(request.getMaxCapacity());
        updated.setStatus(request.getStatus());
        return playAreaRepository.save(updated);
    }

    @Override
    public void delete(Long id) {
        PlayArea playArea = playAreaRepository.findById(id).orElseThrow(()-> new RuntimeException("PlayArea Not Found"));
        playAreaRepository.delete(playArea);
    }
}
