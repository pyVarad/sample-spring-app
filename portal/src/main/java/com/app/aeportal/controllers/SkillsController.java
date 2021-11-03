package com.app.aeportal.controllers;

import com.app.aeportal.Services.SkillsService;
import com.app.aeportal.domain.Skills;
import com.app.aeportal.dto.request.SkillsRequestDto;
import com.app.aeportal.dto.response.SkillsResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/skills")
public class SkillsController {

    @Autowired
    private final SkillsService  skillsService;

    public SkillsController(SkillsService skillsService) {
        this.skillsService = skillsService;
    }

    @GetMapping
    public ResponseEntity<SkillsResponseDto[]> getAllSkills() {
        return new ResponseEntity<>(this.skillsService.getAllSkills(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<SkillsResponseDto> getAllSkillById(@PathVariable Long id) {
        return new ResponseEntity<>(this.skillsService.getSkillsById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<SkillsResponseDto> addNewSkills(@RequestBody SkillsRequestDto skills) {
        return new ResponseEntity<>(this.skillsService.addSkills(skills), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<SkillsResponseDto> updateSkill(@PathVariable long id, @RequestBody SkillsRequestDto skills) {
        return new ResponseEntity<>(this.skillsService.updateSkills(id, skills), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<SkillsResponseDto> deleteSkill(@PathVariable long id) {
        return new ResponseEntity<>(this.skillsService.deleteSkills(id), HttpStatus.NO_CONTENT);
    }
}
