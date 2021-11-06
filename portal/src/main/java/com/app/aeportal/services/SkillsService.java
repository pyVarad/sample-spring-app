package com.app.aeportal.services;

import com.app.aeportal.dto.request.SkillsRequestDto;
import com.app.aeportal.dto.response.SkillsResponseDto;

public interface SkillsService {

    SkillsResponseDto[] getAllSkills();
    SkillsResponseDto addSkills(SkillsRequestDto skills);
    void deleteSkills(Long id);
    SkillsResponseDto updateSkills(Long id, SkillsRequestDto skills);
    SkillsResponseDto getSkillsById(Long id);
}
