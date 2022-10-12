package com.Vladislav.service;

import com.Vladislav.model.Skill;
import com.Vladislav.repository.DataBaseRepository.DataBaseSkillRepository;
import com.Vladislav.repository.SkillRepository;

import java.util.List;

public class SkillService {
    private final SkillRepository skillRepository = new DataBaseSkillRepository();

    public Skill getSkillById(int id) {
        return skillRepository.getById(id);
    }

    public List<Skill> getAllSkills() {
        return skillRepository.getAll();
    }

    public Skill addNewSkill(Skill skill) {
        return skillRepository.save(skill);
    }

    public int updateSkill(Skill skill, int id) {
        return skillRepository.update(skill, id);
    }

    public int deleteSkillById(int id) {
        return skillRepository.deleteById(id);
    }
}
