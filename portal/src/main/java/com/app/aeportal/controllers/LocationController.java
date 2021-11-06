package com.app.aeportal.controllers;

import com.app.aeportal.services.LocationService;
import com.app.aeportal.dto.request.LocationRequestDto;
import com.app.aeportal.dto.response.LocationResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/location")
public class LocationController {

    @Autowired
    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping
    public ResponseEntity<LocationResponseDto[]> getAllLocations() {
        return ResponseEntity.ok().body(this.locationService.getAllLocations());
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<LocationResponseDto> getAllLocationById(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(this.locationService.getLocationById(id));
    }

    @PostMapping
    public ResponseEntity<LocationResponseDto> addNewLocation(@Valid @RequestBody LocationRequestDto request) {
        return new ResponseEntity<>(this.locationService.addNewLocation(request), HttpStatus.CREATED);
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<LocationResponseDto> updateLocation(
            @PathVariable("id") Long id,
            @RequestBody LocationRequestDto request
    ) {
        return ResponseEntity.ok().body(this.locationService.updateLocation(id, request));
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<LocationResponseDto> deleteLocation(@PathVariable("id")  Long id) {
        this.locationService.deleteLocation(id);
        return ResponseEntity.noContent().build();
    }
}
