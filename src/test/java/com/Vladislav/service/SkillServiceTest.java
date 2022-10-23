package com.Vladislav.service;

import com.Vladislav.model.Skill;
import com.Vladislav.repository.SkillRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class SkillServiceTest {
    @Mock
    private SkillRepository skillRepository;
    @InjectMocks
    private SkillService skillService;
    private Skill skill;

    @BeforeEach
    void setUp() {
        skill = new Skill("Coding");
    }

    @Test
    public void shouldReturnSkillById() {
        when(skillRepository.getById(anyInt())).thenReturn(skill);

        Skill skillById = skillService.getSkillById(1);

        assertThat(skillById).isNotNull();
        assertThat(skillById.getName()).isEqualTo("Coding");
    }

    @Test
    public void shouldReturnNull() {
        when(skillRepository.getById(anyInt())).thenReturn(null);

        Skill skillById = skillService.getSkillById(10);

        assertThat(skillById).isNull();
    }

    @Test
    public void shouldReturnSkillsList() {
        when(skillRepository.getAll()).thenReturn(List.of(skill));

        List<Skill> allSkills = skillService.getAllSkills();

        assertThat(allSkills).isNotNull().contains(skill);
    }

    @Test
    public void shouldReturnNullWhenListIsEmpty() {
        when(skillRepository.getAll()).thenReturn(null);

        List<Skill> allSkills = skillService.getAllSkills();

        assertThat(allSkills).isNull();
    }

    @Test
    public void shouldReturnSkill() {
        when(skillRepository.save(skill)).thenReturn(skill);

        Skill newSkill = skillService.addNewSkill(skill);

        assertThat(newSkill).isNotNull().isEqualTo(skill);
    }

    @Test
    public void shouldReturnNullWhenNotSaved() {
        when(skillRepository.save(skill)).thenReturn(null);

        Skill newSkill = skillService.addNewSkill(skill);

        assertThat(newSkill).isNull();
    }

    @Test
    public void shouldReturnOneIfUpdated() {
        when(skillRepository.update(skill)).thenReturn(new Skill(1, "TEST"));

        Skill result = skillService.updateSkill(skill);

        assertNotNull(result);
    }

    @Test
    public void shouldReturnZeroIfNotUpdated() {
        when(skillRepository.update(skill)).thenReturn(null);

        Skill result = skillService.updateSkill(skill);

        assertNull(result);
    }

    @Test
    public void shouldReturnOneIfDeleted() {
        when(skillRepository.deleteById(1)).thenReturn(true);

        boolean result = skillService.deleteSkillById(1);

        assertTrue(result);
    }

    @Test
    public void shouldReturnZeroIfNotDeleted() {
        when(skillRepository.deleteById(1)).thenReturn(false);
       boolean result =  skillService.deleteSkillById(1);
        assertFalse(result);
    }

}
