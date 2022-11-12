package fr.hndgy.testcrystal.services;

import org.springframework.stereotype.Component;

import fr.hndgy.testcrystal.models.PositionGPS;

@Component
public class DistanceCalcul {
    
    public double distance(PositionGPS positionFrom, PositionGPS positionTo){
        Double latFrom = positionFrom.getLatitude();
        Double latTo = positionTo.getLatitude();
        Double longiFrom = positionFrom.getLongitude();
        Double longiTo = positionTo.getLongitude();

        return 6371. * Math.acos(
            Math.sin(latFrom) * Math.sin(latTo)
            +  Math.cos(latFrom) * Math.cos(latTo)
            * Math.cos(longiTo - longiFrom)
        );
    }
}
