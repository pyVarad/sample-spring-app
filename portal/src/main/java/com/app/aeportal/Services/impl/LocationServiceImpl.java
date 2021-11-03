package com.app.aeportal.Services.impl;

import com.app.aeportal.Services.LocationService;
import com.app.aeportal.aop.NoSuchElementFoundException;
import com.app.aeportal.domain.Location;
import com.app.aeportal.dto.request.LocationRequestDto;
import com.app.aeportal.dto.response.LocationResponseDto;
import com.app.aeportal.repository.LocationRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class LocationServiceImpl implements LocationService {

    @Autowired
    private final LocationRepository locationRepository;

    @Autowired
    private final ObjectMapper objectMapper;

    public LocationServiceImpl(
            LocationRepository locationRepository,
            ObjectMapper objectMapper) {
        this.locationRepository = locationRepository;
        this.objectMapper = objectMapper;
    }

    private Location getLocationForId(Long id) {
        Optional<Location> location = this.locationRepository.findById(id);
        return location.orElseThrow(() -> {
            throw new NoSuchElementFoundException("Requested designation does not exist.");
        });
    }

    @Override
    public LocationResponseDto getLocationById(Long id) {
        return this.objectMapper.convertValue(this.getLocationForId(id), LocationResponseDto.class);
    }


    @Override
    public LocationResponseDto[] getAllLocations() {
        return this.objectMapper.convertValue(this.locationRepository.findAll(), LocationResponseDto[].class);
    }

    @Override
    public LocationResponseDto addNewLocation(LocationRequestDto request) {
        Location location = request.toLocation();
        return this.objectMapper.convertValue(this.locationRepository.save(location), LocationResponseDto.class);
    }

    @Override
    @Transactional
    public LocationResponseDto updateLocation(Long id, LocationRequestDto request) {
        Location location = this.getLocationForId(id);
        Location updatedLocation = request.toLocation();
        updatedLocation.setId(location.getId());
        return this.objectMapper.convertValue(this.locationRepository.save(updatedLocation), LocationResponseDto.class);
    }

    @Override
    @Transactional
    public LocationResponseDto deleteLocation(Long id) {
        Location location = this.getLocationForId(id);
        this.locationRepository.delete(location);
        return this.objectMapper.convertValue(location, LocationResponseDto.class);
    }
}
