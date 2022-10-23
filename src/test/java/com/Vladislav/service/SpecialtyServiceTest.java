package com.Vladislav.service;

import com.Vladislav.model.Specialty;
import com.Vladislav.repository.SpecialtyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SpecialtyServiceTest {
    @Mock
    private SpecialtyRepository specialtyRepository;
    @InjectMocks
    private SpecialtyService specialtyService;
    Specialty specialty;

    @BeforeEach
    public void setUp() {
        specialty = new Specialty("Java Developer");
    }

    @Test
    public void shouldReturnSpecialtyIfFound() {
        when(specialtyRepository.getById(anyInt())).thenReturn(specialty);

        Specialty specialtyById = specialtyService.getSpecialtyById(1);

        assertThat(specialtyById).isNotNull().isEqualTo(specialty);
    }

    @Test
    public void shouldReturnNullIfNotFound() {
        when(specialtyRepository.getById(1)).thenReturn(null);

        Specialty specialtyById = specialtyService.getSpecialtyById(1);

        assertThat(specialtyById).isNull();
    }

    @Test
    public void shouldReturnListOfSpecialties() {
        when(specialtyRepository.getAll()).thenReturn(List.of(specialty));

        List<Specialty> allSpecialties = specialtyService.getAllSpecialties();

        assertThat(allSpecialties).isNotNull().contains(specialty);
    }

    @Test
    public void shouldReturnNullWhenNoSpecialties() {
        when(specialtyRepository.getAll()).thenReturn(null);

        List<Specialty> allSpecialties = specialtyService.getAllSpecialties();

        assertThat(allSpecialties).isNull();
    }

    @Test
    public void shouldReturnSpecialtyIfSavedCorrectly() {
        when(specialtyRepository.save(specialty)).thenReturn(specialty);

        Specialty newSpecialty = specialtyService.addNewSpecialty(specialty);

        assertThat(newSpecialty).isNotNull().isEqualTo(specialty);
    }

    @Test
    public void shouldReturnNullIfNotSavedCorrectly() {
        when(specialtyRepository.save(specialty)).thenReturn(null);

        Specialty newSpecialty = specialtyService.addNewSpecialty(specialty);

        assertThat(newSpecialty).isNull();
    }

    @Test
    public void shouldReturnSpecialtyIfUpdatedCorrect() {
        when(specialtyRepository.update(specialty)).thenReturn(specialty);

        Specialty specialtyIndex = specialtyService.updateSpecialty(specialty);

        assertThat(specialtyIndex).isEqualTo(specialty);
    }

    @Test
    public void shouldReturnNullIfUpdateWasNotCorrect() {
        when(specialtyRepository.update(specialty)).thenReturn(null);

        Specialty specialtyIndex = specialtyService.updateSpecialty(specialty);

        assertThat(specialtyIndex).isNull();
    }

    @Test
    public void shouldReturnTrueIfDeletedCorrect() {
        when(specialtyRepository.deleteById(anyInt())).thenReturn(true);

        boolean specialtyBoolean = specialtyService.deleteSpecialtyById(1);

        assertTrue(specialtyBoolean);
    }

    @Test
    public void shouldReturnZeroIfDeleteWasNotCorrect() {
        when(specialtyRepository.deleteById(anyInt())).thenReturn(false);

        boolean specialtyBoolean = specialtyService.deleteSpecialtyById(1);

        assertFalse(specialtyBoolean);
    }
}
