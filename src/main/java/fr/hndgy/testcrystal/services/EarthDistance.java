package fr.hndgy.testcrystal.services;

import fr.hndgy.testcrystal.models.PositionGPS;

public interface EarthDistance {
    double distance(PositionGPS positionFrom, PositionGPS positionTo);
}
