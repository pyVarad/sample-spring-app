package com.app.aeportal.services.impl;

import com.app.aeportal.aop.NoSuchElementFoundException;
import com.app.aeportal.domain.Location;
import com.app.aeportal.dto.request.LocationRequestDto;
import com.app.aeportal.dto.response.LocationResponseDto;
import com.app.aeportal.repository.LocationRepository;
import com.app.aeportal.services.LocationService;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class LocationServiceImpl implements LocationService {


    private final LocationRepository locationRepository;

    @Autowired
    public LocationServiceImpl(
            LocationRepository locationRepository
    ) {
        this.locationRepository = locationRepository;
    }

    private Location getLocationForId(Long id) {
        Optional<Location> location = this.locationRepository.findById(id);
        return location.orElseThrow(() -> {
            throw new NoSuchElementFoundException("Requested designation does not exist.");
        });
    }

    @Override
    public LocationResponseDto getLocationById(Long id) {
        return new ModelMapper().map(this.getLocationForId(id), LocationResponseDto.class);
    }


    @Override
    public LocationResponseDto[] getAllLocations() {
        return new ModelMapper().map(this.locationRepository.findAll(), LocationResponseDto[].class);
    }

    @Override
    public LocationResponseDto addNewLocation(LocationRequestDto request) {
        Location location = request.toLocation();
        return new ModelMapper().map(this.locationRepository.save(location), LocationResponseDto.class);
    }

    @Override
    @Transactional
    public LocationResponseDto updateLocation(Long id, LocationRequestDto request) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        Location location = this.getLocationForId(id);
        modelMapper.map(request.toLocation(), location);
        return new ModelMapper().map(this.locationRepository.save(location), LocationResponseDto.class);
    }

    @Override
    @Transactional
    public void deleteLocation(Long id) {
        Location location = this.getLocationForId(id);
        this.locationRepository.delete(location);
    }
}
