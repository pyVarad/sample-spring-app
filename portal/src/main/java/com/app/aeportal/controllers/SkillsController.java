package com.app.aeportal.controllers;

import com.app.aeportal.services.SkillsService;
import com.app.aeportal.dto.request.SkillsRequestDto;
import com.app.aeportal.dto.response.SkillsResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/skills")
public class SkillsController {

    @Autowired
    private final SkillsService  skillsService;

    public SkillsController(SkillsService skillsService) {
        this.skillsService = skillsService;
    }

    @GetMapping
    public ResponseEntity<SkillsResponseDto[]> getAllSkills() {
        return ResponseEntity.ok().body(this.skillsService.getAllSkills());
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<SkillsResponseDto> getAllSkillById(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(this.skillsService.getSkillsById(id));
    }

    @PostMapping
    public ResponseEntity<SkillsResponseDto> addNewSkills(@RequestBody SkillsRequestDto skills) {
        return new ResponseEntity<>(this.skillsService.addSkills(skills), HttpStatus.CREATED);
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<SkillsResponseDto> updateSkill(
            @PathVariable("id") long id,
            @RequestBody SkillsRequestDto skills
    ) {
        return ResponseEntity.ok().body(this.skillsService.updateSkills(id, skills));
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<SkillsResponseDto> deleteSkill(@PathVariable("id") long id) {
        this.skillsService.deleteSkills(id);
        return ResponseEntity.noContent().build();
    }
}
