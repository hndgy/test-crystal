package fr.hndgy.testcrystal.services;

import java.util.List;

import fr.hndgy.testcrystal.dtos.CreatePositionDto;
import fr.hndgy.testcrystal.exceptions.PositionNotFoundException;
import fr.hndgy.testcrystal.models.PositionGPS;

public interface PositionService {

    /**
     * Crée une postion avec une latitude et une longitude
     * @param dto
     * @return nouvelle postion
     */
    PositionGPS createPosition(CreatePositionDto dto);

    /**
     * Récupère toutes les postions
     */
    List<PositionGPS> getAllPositions();
    /**
     * Supprime une position avec son id
     */
    void deletePosition(Long id);

    /**
     * Calcul une distance en km entre deux positions
     * @param idFrom
     * @param idTo
     * @return distance
     * @throws PositionNotFoundException
     */
    Double getDistance(Long idFrom, Long idTo) throws PositionNotFoundException;
}
