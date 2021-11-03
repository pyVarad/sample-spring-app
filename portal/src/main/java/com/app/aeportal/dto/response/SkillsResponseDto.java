package com.app.aeportal.dto.response;


public class SkillsResponseDto {

    private Long id;
    private String skillCategory;
    private String skill;

    public SkillsResponseDto(
            Long id,
            String skillCategory,
            String skill
    ) {
        this.id = id;
        this.skillCategory = skillCategory;
        this.skill = skill;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
