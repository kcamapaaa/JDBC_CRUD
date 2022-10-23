package com.Vladislav.service;

import com.Vladislav.model.Developer;
import com.Vladislav.model.Skill;
import com.Vladislav.model.Specialty;
import com.Vladislav.repository.DeveloperRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class DeveloperServiceTest {
    @Mock
    private DeveloperRepository developerRepository;
    @InjectMocks
    private DeveloperService developerService;
    private Developer developer;

    @BeforeEach
    void setUp() {
        List<Skill> skillList = List.of(new Skill("A"), new Skill("B"));
        developer = new Developer(10, "Vasya", "Pupkin", skillList, new Specialty("Java Developer"));
    }

    @Test
    public void shouldReturnDeveloperById() {
        when(developerRepository.getById(anyInt())).thenReturn(developer);

        Developer developerById = developerService.getDeveloperById(1);

        assertThat(developerById).isNotNull();
        assertThat(developerById).isEqualTo(developer);
    }

    @Test
    public void shouldReturnNullWhenNoDeveloperInDataBase() {
        when(developerRepository.getById(anyInt())).thenReturn(null);

        Developer developerById = developerService.getDeveloperById(1);

        assertThat(developerById).isNull();
    }

    @Test
    public void shouldReturnListOfAllDevelopers() {
        when(developerRepository.getAll()).thenReturn(List.of(developer));

        List<Developer> allDevelopers = developerService.getAllDevelopers();

        assertThat(allDevelopers).isNotNull().contains(developer);
    }

    @Test
    public void shouldReturnEmptyListWhenCallGetAllDevelopers() {
        when(developerRepository.getAll()).thenReturn(List.of());

        List<Developer> allDevelopers = developerService.getAllDevelopers();

        assertThat(allDevelopers).isEmpty();
    }

    @Test
    public void shouldReturnDeveloperWhenAddingOne() {
        when(developerRepository.save(developer)).thenReturn(developer);

        Developer newDeveloper = developerService.addNewDeveloper(developer);

        assertThat(newDeveloper).isNotNull().isEqualTo(developer);
    }

    @Test
    public void shouldReturnNullWhenAddingOne() {
        when(developerRepository.save(developer)).thenReturn(null);

        Developer newDeveloper = developerService.addNewDeveloper(developer);

        assertThat(newDeveloper).isNull();
    }



    @Test
    public void shouldReturnDeveloperWhenDeveloperWasUpdated() {
        when(developerRepository.update(developer)).thenReturn(developer);

        Developer newDeveloper = developerService.updateDeveloper(developer);

        assertThat(newDeveloper).isNotNull().isEqualTo(developer);
    }

    @Test
    public void shouldReturnNullWhenNotUpdated() {
        when(developerRepository.update(developer)).thenReturn(null);

        Developer newDeveloper = developerService.updateDeveloper(developer);

        assertThat(newDeveloper).isNull();
    }

    @Test
    public void shouldReturnTrueWhenDeleteByIdWasCorrect() {
        when(developerRepository.deleteById(anyInt())).thenReturn(true);

        boolean deleteCheck = developerService.deleteDeveloperById(1);

        assertTrue(deleteCheck);
    }

    @Test
    public void shouldReturnFalseWhenNotDeleted() {
        when(developerRepository.deleteById(anyInt())).thenReturn(false);

        boolean deleteCheck = developerService.deleteDeveloperById(1);

        assertFalse(deleteCheck);
    }
}
