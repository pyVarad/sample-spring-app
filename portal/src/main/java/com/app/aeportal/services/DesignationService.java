package com.app.aeportal.services;

import com.app.aeportal.dto.request.DesignationRequestDto;
import com.app.aeportal.dto.response.DesignationResponseDto;

public interface DesignationService {

    DesignationResponseDto[] getAllDesignation();
    DesignationResponseDto addNewDesignation(DesignationRequestDto request);
    DesignationResponseDto updateDesignation(Long id, DesignationRequestDto request);
    void deleteDesignation(Long id);
    DesignationResponseDto getDesignationById(Long id);
}
