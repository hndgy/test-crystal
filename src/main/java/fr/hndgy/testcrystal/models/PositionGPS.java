package fr.hndgy.testcrystal.models;

import java.util.Date;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class PositionGPS {
    private Long id;
    private Double latitude;
    private Double longitude;
    private Date dateCreation;
}
