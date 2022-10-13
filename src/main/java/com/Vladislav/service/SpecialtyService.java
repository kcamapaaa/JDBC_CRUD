package com.Vladislav.service;

import com.Vladislav.model.Specialty;
import com.Vladislav.repository.DataBaseRepository.DataBaseSpecialtyRepository;
import com.Vladislav.repository.SpecialtyRepository;

import java.util.List;

public class SpecialtyService {
    private SpecialtyRepository specialtyRepository = new DataBaseSpecialtyRepository();

    public Specialty getSpecialtyById(int id) {
        return specialtyRepository.getById(id);
    }

    public List<Specialty> getAllSpecialties() {
        return specialtyRepository.getAll();
    }

    public Specialty addNewSpecialty(Specialty specialty) {
        return specialtyRepository.save(specialty);
    }

    public int updateSpecialty(Specialty specialty, int id) {
        return specialtyRepository.update(specialty, id);
    }

    public int deleteSpecialtyById(int id) {
        return specialtyRepository.deleteById(id);
    }

}
