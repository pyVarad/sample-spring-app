package com.app.aeportal.services.impl;

import com.app.aeportal.aop.NoSuchElementFoundException;
import com.app.aeportal.domain.Skills;
import com.app.aeportal.dto.request.SkillsRequestDto;
import com.app.aeportal.dto.response.SkillsResponseDto;
import com.app.aeportal.repository.SkillsRepository;
import com.app.aeportal.services.SkillsService;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class SkillsServiceImpl implements SkillsService {

    private final SkillsRepository skillsRepository;

    @Autowired
    public SkillsServiceImpl(
            SkillsRepository skillsRepository
    ) {
        this.skillsRepository = skillsRepository;
    }

    private Skills getSkillsForGivenId(Long id) {
        Optional<Skills> skills = this.skillsRepository.findById(id);
        return skills.orElseThrow(() -> {
            throw new NoSuchElementFoundException("Requested designation does not exist.");
        });
    }

    @Override
    public SkillsResponseDto[] getAllSkills() {
        return new ModelMapper().map(this.skillsRepository.findAll(), SkillsResponseDto[].class);
    }

    @Override
    @Transactional
    public SkillsResponseDto addSkills(SkillsRequestDto request) {
        Skills skills = request.toSkills();
        return new ModelMapper().map(this.skillsRepository.save(skills), SkillsResponseDto.class);
    }

    @Override
    @Transactional
    public void deleteSkills(Long id) {
        Skills skills = getSkillsForGivenId(id);
        this.skillsRepository.delete(skills);
    }

    @Override
    @Transactional
    public SkillsResponseDto updateSkills(Long id, SkillsRequestDto request) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        Skills skills = getSkillsForGivenId(id);
        modelMapper.map(request.toSkills(), skills);
        return new ModelMapper().map(this.skillsRepository.save(skills), SkillsResponseDto.class);
    }

    @Override
    public SkillsResponseDto getSkillsById(Long id) {
        return new ModelMapper().map(this.getSkillsForGivenId(id), SkillsResponseDto.class);
    }
}
