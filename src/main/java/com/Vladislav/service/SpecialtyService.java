package com.Vladislav.service;

import com.Vladislav.model.Specialty;
import com.Vladislav.repository.jdbc.JdbcSpecialtyRepository;
import com.Vladislav.repository.SpecialtyRepository;

import java.util.List;

public class SpecialtyService {
    private final SpecialtyRepository specialtyRepository;
    public SpecialtyService() {
        this.specialtyRepository = new JdbcSpecialtyRepository();
    }

    public SpecialtyService(SpecialtyRepository specialtyRepository) {
        this.specialtyRepository = specialtyRepository;
    }

    public Specialty getSpecialtyById(int id) {
        return specialtyRepository.getById(id);
    }

    public List<Specialty> getAllSpecialties() {
        return specialtyRepository.getAll();
    }

    public Specialty addNewSpecialty(Specialty specialty) {
        return specialtyRepository.save(specialty);
    }

    public Specialty updateSpecialty(Specialty specialty) {
        return specialtyRepository.update(specialty);
    }

    public boolean deleteSpecialtyById(int id) {
        return specialtyRepository.deleteById(id);
    }

}
