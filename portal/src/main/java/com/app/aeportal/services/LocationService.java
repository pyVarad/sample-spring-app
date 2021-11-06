package com.app.aeportal.services;

import com.app.aeportal.dto.request.LocationRequestDto;
import com.app.aeportal.dto.response.LocationResponseDto;

public interface LocationService {

    LocationResponseDto[] getAllLocations();
    LocationResponseDto addNewLocation(LocationRequestDto request);
    LocationResponseDto updateLocation(Long id, LocationRequestDto request);
    void deleteLocation(Long id);
    LocationResponseDto getLocationById(Long id);
}
