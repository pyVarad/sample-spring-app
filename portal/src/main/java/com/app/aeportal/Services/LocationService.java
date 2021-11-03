package com.app.aeportal.Services;

import com.app.aeportal.dto.request.LocationRequestDto;
import com.app.aeportal.dto.response.LocationResponseDto;

public interface LocationService {

    LocationResponseDto[] getAllLocations();
    LocationResponseDto addNewLocation(LocationRequestDto request);
    LocationResponseDto updateLocation(Long id, LocationRequestDto request);
    LocationResponseDto deleteLocation(Long id);
    LocationResponseDto getLocationById(Long id);
}
