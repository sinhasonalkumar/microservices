package com.demo.perf.services.commands;

import java.util.concurrent.CompletableFuture;

import com.demo.perf.dto.commands.SkillCreateDTO;
import com.demo.perf.dto.commands.SkillUpdateDTO;

public interface SkillCommandService {

	CompletableFuture<String> createSkill(SkillCreateDTO skillCreateDTO);

	CompletableFuture<String> updateSkill(SkillUpdateDTO skillUpdateDTO, String SkillId);

	CompletableFuture<String> deleteSkill(String skillId);

	
}
