package com.Vladislav.service;

import com.Vladislav.model.Skill;
import com.Vladislav.repository.jdbc.JdbcSkillRepository;
import com.Vladislav.repository.SkillRepository;

import java.util.List;

public class SkillService {
    private final SkillRepository skillRepository;


    public SkillService() {
        this.skillRepository = new JdbcSkillRepository();
    }

    public SkillService(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    public Skill getSkillById(int id) {
        return skillRepository.getById(id);
    }

    public List<Skill> getAllSkills() {
        return skillRepository.getAll();
    }

    public Skill addNewSkill(Skill skill) {
        return skillRepository.save(skill);
    }

    public Skill updateSkill(Skill skill) {
        return skillRepository.update(skill);
    }

    public boolean deleteSkillById(int id) {
        return skillRepository.deleteById(id);
    }
}
