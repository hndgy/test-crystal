package fr.hndgy.testcrystal.dtos;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
public class CreatePositionDto {
    @Min(-180) @Max(180)
    private Double latitude;
    @Min(-180) @Max(180)
    private Double longitude;
}
