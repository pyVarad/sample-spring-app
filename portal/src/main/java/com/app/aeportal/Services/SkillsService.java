package com.app.aeportal.Services;

import com.app.aeportal.domain.Skills;
import com.app.aeportal.dto.request.SkillsRequestDto;
import com.app.aeportal.dto.response.SkillsResponseDto;

public interface SkillsService {

    SkillsResponseDto[] getAllSkills();
    SkillsResponseDto addSkills(SkillsRequestDto skills);
    SkillsResponseDto deleteSkills(Long id);
    SkillsResponseDto updateSkills(Long id, SkillsRequestDto skills);
    SkillsResponseDto getSkillsById(Long id);
}
