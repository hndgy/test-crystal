package fr.hndgy.testcrystal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.hndgy.testcrystal.models.PositionGPS;

@Repository
public interface PositionGPSRepository extends JpaRepository<PositionGPS, Long> {
}
