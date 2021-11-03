package com.app.aeportal.dto.request;


import com.app.aeportal.domain.Skills;

import javax.validation.constraints.NotBlank;

public class SkillsRequestDto {

    @NotBlank(message = "Skill Category is mandatory parameter for the request.")
    private String skillCategory;

    @NotBlank(message = "Skill is mandatory parameter for the request.")
    private String skill;

    public SkillsRequestDto(
            String skillCategory,
            String skill
    ) {
        this.skillCategory = skillCategory;
        this.skill = skill;
    }

    public String getSkillCategory() {
        return skillCategory;
    }

    public void setSkillCategory(String skillCategory) {
        this.skillCategory = skillCategory;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public Skills toSkills() {
        return new Skills(
                skillCategory,
                skill
        );
    }
}
