package fr.hndgy.testcrystal.services;

import java.util.List;

import fr.hndgy.testcrystal.dtos.CreatePositionDto;
import fr.hndgy.testcrystal.models.PositionGPS;

public interface PositionService {
    PositionGPS createPosition(CreatePositionDto dto);
    List<PositionGPS> getAllPositions();
    void deletePosition(Long id);
    Double getDistance(Long idFrom, Long idTo);
}
