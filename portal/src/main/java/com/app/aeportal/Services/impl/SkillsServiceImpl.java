package com.app.aeportal.Services.impl;

import com.app.aeportal.Services.SkillsService;
import com.app.aeportal.aop.NoSuchElementFoundException;
import com.app.aeportal.domain.Skills;
import com.app.aeportal.dto.request.SkillsRequestDto;
import com.app.aeportal.dto.response.SkillsResponseDto;
import com.app.aeportal.repository.SkillsRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class SkillsServiceImpl implements SkillsService {

    @Autowired
    private final ObjectMapper objectMapper;

    @Autowired
    private final SkillsRepository skillsRepository;

    public SkillsServiceImpl(
            ObjectMapper objectMapper,
            SkillsRepository skillsRepository
    ) {
        this.objectMapper = objectMapper;
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
        return this.objectMapper.convertValue(this.skillsRepository.findAll(), SkillsResponseDto[].class);
    }

    @Override
    @Transactional
    public SkillsResponseDto addSkills(SkillsRequestDto request) {
        Skills skills = request.toSkills();
        return this.objectMapper.convertValue(this.skillsRepository.save(skills), SkillsResponseDto.class);
    }

    @Override
    @Transactional
    public SkillsResponseDto deleteSkills(Long id) {
        Skills skills = getSkillsForGivenId(id);
        this.skillsRepository.delete(skills);
        return this.objectMapper.convertValue(skills, SkillsResponseDto.class);
    }

    @Override
    @Transactional
    public SkillsResponseDto updateSkills(Long id, SkillsRequestDto request) {
        Skills skills = getSkillsForGivenId(id);
        Skills updatedSkills = request.toSkills();
        updatedSkills.setId(skills.getId());
        return this.objectMapper.convertValue(this.skillsRepository.save(skills), SkillsResponseDto.class);
    }

    @Override
    public SkillsResponseDto getSkillsById(Long id) {
        return this.objectMapper.convertValue(this.getSkillsForGivenId(id), SkillsResponseDto.class);
    }
}
