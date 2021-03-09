package com.demo.perf.controllers.command;

import java.util.concurrent.CompletableFuture;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.perf.dto.commands.SkillCreateDTO;
import com.demo.perf.dto.commands.SkillUpdateDTO;
import com.demo.perf.services.commands.SkillCommandService;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(value = "/Skill")
@AllArgsConstructor
@Api(value = "Skill Commands", description = "Skill Commands Related Endpoints", tags = "Skill Commands")
public class SkillCommandController {

	private final SkillCommandService skillCommandService;
	  
    @PostMapping(path = "/create")
    public CompletableFuture<String> createCapability(@RequestBody SkillCreateDTO skillUpsertDTO){
        return skillCommandService.createSkill(skillUpsertDTO);
    }
    
    @PutMapping(path = "/{skillId}")
    public CompletableFuture<String> updateCapability(@RequestBody SkillUpdateDTO skillUpdateDTO,@PathVariable(value = "skillId") String skillId){
        return skillCommandService.updateSkill(skillUpdateDTO, skillId);
    }
    
    @DeleteMapping(path = "/{skillId}")
    public CompletableFuture<String> deleteCapability(@PathVariable(value = "skillId") String skillId){
        return skillCommandService.deleteSkill(skillId);
    }
}
