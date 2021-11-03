package com.app.aeportal.Services.impl;

import com.app.aeportal.Services.DesignationService;
import com.app.aeportal.aop.NoSuchElementFoundException;
import com.app.aeportal.domain.Designation;
import com.app.aeportal.dto.request.DesignationRequestDto;
import com.app.aeportal.dto.response.DesignationResponseDto;
import com.app.aeportal.repository.DesignationRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DesignationServiceImpl implements DesignationService {

    @Autowired
    private final DesignationRepository designationRepository;

    @Autowired
    private final ObjectMapper objectMapper;

    public DesignationServiceImpl(
            DesignationRepository designationRepository,
            ObjectMapper objectMapper
    ) {
        this.designationRepository = designationRepository;
        this.objectMapper = objectMapper;
    }

    private Designation getDesignationForGivenId(Long id) {
        Optional<Designation> designation = this.designationRepository.findById(id);
        return designation.orElseThrow(() -> {
            throw new NoSuchElementFoundException("Requested designation does not exist.");
        });
    }

    @Override
    public DesignationResponseDto getDesignationById(Long id) {
        return this.objectMapper.convertValue(this.getDesignationForGivenId(id), DesignationResponseDto.class);
    }

    @Override
    public DesignationResponseDto[] getAllDesignation() {
        return this.objectMapper.convertValue(this.designationRepository.findAll(), DesignationResponseDto[].class);
    }

    @Override
    public DesignationResponseDto addNewDesignation(DesignationRequestDto request) {
        Designation designation = request.toDesignation();
        return this.objectMapper.convertValue(this.designationRepository.save(designation), DesignationResponseDto.class);
    }

    @Override
    public DesignationResponseDto updateDesignation(Long id, DesignationRequestDto request) {
        Designation designation = this.getDesignationForGivenId(id);
        Designation updatedDesignationInfo = request.toDesignation();
        updatedDesignationInfo.setId(designation.getId());
        return this.objectMapper.convertValue(this.designationRepository.save(updatedDesignationInfo), DesignationResponseDto.class);
    }

    @Override
    public DesignationResponseDto deleteDesignation(Long id) {
        Designation designation = this.getDesignationForGivenId(id);
        this.designationRepository.delete(designation);
        return this.objectMapper.convertValue(designation, DesignationResponseDto.class);
    }
}
