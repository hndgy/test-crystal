package fr.hndgy.testcrystal.services;

import org.springframework.stereotype.Component;

import fr.hndgy.testcrystal.models.PositionGPS;


@Component
public class EarthDistanceWithSinus implements EarthDistance{

    public static final double EARTH_RADIUS = 6366.;
    public static final double RADIUS = 180.;

    public double distance(PositionGPS positionFrom, PositionGPS positionTo){
        double latFrom = positionFrom.getLatitude();
        double latTo = positionTo.getLatitude();
        double longiFrom = positionFrom.getLongitude();
        double longiTo = positionTo.getLongitude();

        double pk = (RADIUS /Math.PI);
 
        double a1 = latFrom / pk;
        double a2 = longiFrom / pk;
        double b1 = latTo / pk;
        double b2 = longiTo / pk;
     
        double t1 = Math.cos(a1)*Math.cos(a2)*Math.cos(b1)*Math.cos(b2);
        double t2 = Math.cos(a1)*Math.sin(a2)*Math.cos(b1)*Math.sin(b2);
        double t3 = Math.sin(a1)*Math.sin(b1);
        double tt = Math.acos(t1 + t2 + t3);
     
        return EARTH_RADIUS * tt;
    }
}
