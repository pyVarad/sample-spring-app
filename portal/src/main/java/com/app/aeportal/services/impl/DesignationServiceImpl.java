package com.app.aeportal.services.impl;

import com.app.aeportal.aop.NoSuchElementFoundException;
import com.app.aeportal.domain.Designation;
import com.app.aeportal.dto.request.DesignationRequestDto;
import com.app.aeportal.dto.response.DesignationResponseDto;
import com.app.aeportal.repository.DesignationRepository;
import com.app.aeportal.services.DesignationService;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class DesignationServiceImpl implements DesignationService {

    @Autowired
    private final DesignationRepository designationRepository;

    public DesignationServiceImpl(
            DesignationRepository designationRepository
    ) {
        this.designationRepository = designationRepository;
    }

    private Designation getDesignationForGivenId(Long id) {
        Optional<Designation> designation = this.designationRepository.findById(id);
        return designation.orElseThrow(() -> {
            throw new NoSuchElementFoundException("Requested designation does not exist.");
        });
    }

    @Override
    public DesignationResponseDto getDesignationById(Long id) {
        return new ModelMapper().map(this.getDesignationForGivenId(id), DesignationResponseDto.class);
    }

    @Override
    public DesignationResponseDto[] getAllDesignation() {
        return new ModelMapper().map(this.designationRepository.findAll(), DesignationResponseDto[].class);
    }

    @Override
    public DesignationResponseDto addNewDesignation(DesignationRequestDto request) {
        Designation designation = request.toDesignation();
        return new ModelMapper().map(this.designationRepository.save(designation), DesignationResponseDto.class);
    }

    @Override
    @Transactional
    public DesignationResponseDto updateDesignation(Long id, DesignationRequestDto request) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        Designation designation = this.getDesignationForGivenId(id);
        modelMapper.map(request.toDesignation(), designation);
        return new ModelMapper().map(this.designationRepository.save(designation), DesignationResponseDto.class);
    }

    @Override
    @Transactional
    public void deleteDesignation(Long id) {
        Designation designation = this.getDesignationForGivenId(id);
        this.designationRepository.delete(designation);
    }
}
