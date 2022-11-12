package fr.hndgy.testcrystal.controllers;

import java.net.URI;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.hndgy.testcrystal.dtos.CreatePositionDto;
import fr.hndgy.testcrystal.exceptions.PositionNotFoundException;
import fr.hndgy.testcrystal.models.PositionGPS;
import fr.hndgy.testcrystal.services.PositionService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/positions")
@RequiredArgsConstructor
public class PositionController {
    
    private final PositionService positionService;

    @PostMapping
    public ResponseEntity<PositionGPS> create(@RequestBody CreatePositionDto dto){
        var saved = this.positionService.createPosition(dto);
        return ResponseEntity.created(null).body(saved);
    }

    @GetMapping
    public ResponseEntity<List<PositionGPS>> getAll(){
        return ResponseEntity.ok(this.positionService.getAllPositions());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathParam("id") Long id){
        this.positionService.deletePosition(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{idFrom}/distance/{idTo}")
    public ResponseEntity<Double> getDistance(@PathParam("idFrom") Long idFrom,@PathParam("idTo") Long idTo ) throws PositionNotFoundException{
        Double res = this.positionService.getDistance(idFrom, idTo);
        return ResponseEntity.ok(res);
    }
}
