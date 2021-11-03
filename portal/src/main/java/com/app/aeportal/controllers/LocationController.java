package com.app.aeportal.controllers;

import com.app.aeportal.Services.LocationService;
import com.app.aeportal.dto.request.LocationRequestDto;
import com.app.aeportal.dto.response.LocationResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/location")
public class LocationController {

    @Autowired
    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping
    public ResponseEntity<LocationResponseDto[]> getAllLocations() {
        return new ResponseEntity<>(this.locationService.getAllLocations(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<LocationResponseDto> getAllLocationById(@PathVariable Long id) {
        return new ResponseEntity<>(this.locationService.getLocationById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<LocationResponseDto> addNewLocation(@Valid @RequestBody LocationRequestDto request) {
        return new ResponseEntity<>(this.locationService.addNewLocation(request), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<LocationResponseDto> updateLocation(@PathVariable Long id, @RequestBody LocationRequestDto request) {
        return new ResponseEntity<>(this.locationService.updateLocation(id, request), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<LocationResponseDto> deleteLocation(@PathVariable  Long id) {
        return new ResponseEntity<>(this.locationService.deleteLocation(id), HttpStatus.NO_CONTENT);
    }
}
