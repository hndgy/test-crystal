package fr.hndgy.testcrystal.services;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import fr.hndgy.testcrystal.dtos.CreatePositionDto;
import fr.hndgy.testcrystal.exceptions.PositionNotFoundException;
import fr.hndgy.testcrystal.models.PositionGPS;
import fr.hndgy.testcrystal.repositories.PositionGPSRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class PositionServiceImpl implements PositionService{

    private final PositionGPSRepository positionGPSRepository;
    private final DistanceCalcul distanceCalcul;

    @Override
    public PositionGPS createPosition(CreatePositionDto dto) {
        var position = new PositionGPS();
        position.setLatitude(dto.getLatitude());
        position.setLongitude(dto.getLongitude());
        position =  this.positionGPSRepository.save(position);
        log.info("New position saved with id : {}", position.getId());
        return position;
    }

    @Override
    public List<PositionGPS> getAllPositions() {
        log.info("Positions fetched");
        return this.positionGPSRepository.findAll();
    }

    @Override
    public void deletePosition(Long id) {
        this.positionGPSRepository.deleteById(id);
    }

    @Override
    public Double getDistance(Long idFrom, Long idTo) throws PositionNotFoundException {
        log.info("Get distance from {} to {}", idFrom, idTo);
        var positionFrom = this.positionGPSRepository.findById(idFrom).orElseThrow(PositionNotFoundException::new);
        var positionTo = this.positionGPSRepository.findById(idTo).orElseThrow(PositionNotFoundException::new);
        return distanceCalcul.distance(positionFrom, positionTo);
    }
    
}
