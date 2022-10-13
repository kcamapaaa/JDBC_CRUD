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
        when(developerRepository.getById(10)).thenReturn(developer);

        Developer developerById = developerService.getDeveloperById(10);

        assertThat(developerById).isNotNull();
        assertThat(developerById.getFirstName()).isEqualTo("Vasya");
    }

    @Test
    public void shouldReturnNullWhenNoDeveloperInDataBase() {
        when(developerRepository.getById(1)).thenReturn(null);

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
    public void shouldReturnNullWhenCallGetAllDevelopers() {
        when(developerRepository.getAll()).thenReturn(null);

        List<Developer> allDevelopers = developerService.getAllDevelopers();

        assertThat(allDevelopers).isNull();
    }

    @Test
    public void shouldReturnDeveloperWhenAddingOne() {
        when(developerRepository.save(developer)).thenReturn(developer);

        Developer newDeveloper = developerService.addNewDeveloper(developer);

        assertThat(newDeveloper).isNotNull().isEqualTo(developer);
    }

    @Test
    public void shouldReturnOneWhenDeveloperWasUpdated() {
        when(developerRepository.update(developer, 1)).thenReturn(1);

        int updateIndex = developerService.updateDeveloper(developer, 1);

        assertThat(updateIndex).isEqualTo(1);
    }

    @Test
    public void shouldReturnZeroWhenNotUpdated() {
        when(developerRepository.update(developer, 10)).thenReturn(0);

        int updateIndex = developerService.updateDeveloper(developer, 10);

        assertThat(updateIndex).isEqualTo(0);
    }

    @Test
    public void shouldReturnOneWhenDeleteByIdWasCorrect() {
        when(developerRepository.deleteById(1)).thenReturn(1);

        int deleteIndex = developerService.deleteDeveloperById(1);

        assertThat(deleteIndex).isEqualTo(1);
    }

    @Test
    public void shouldReturnZeroWhenNotDeleted() {
        when(developerRepository.deleteById(10)).thenReturn(0);

        int deleteIndex = developerService.deleteDeveloperById(10);

        assertThat(deleteIndex).isEqualTo(0);
    }
}