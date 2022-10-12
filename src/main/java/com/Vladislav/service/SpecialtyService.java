package com.Vladislav.service;

import com.Vladislav.model.Specialty;
import com.Vladislav.repository.DataBaseRepository.DataBaseSpecialtyRepository;
import com.Vladislav.repository.SpecialtyRepository;

import java.util.List;

public class SpecialtyService {
    private final DataBaseSpecialtyRepository spcialtyRepository = new DataBaseSpecialtyRepository();

    public Specialty getSpecialtyById(int id) {
        return spcialtyRepository.getById(id);
    }

    public List<Specialty> getAllSpecialties() {
        return spcialtyRepository.getAll();
    }

    public Specialty addNewSpecialty(Specialty specialty) {
        return spcialtyRepository.save(specialty);
    }

    public int updateSpecialty(Specialty specialty, int id) {
        return spcialtyRepository.update(specialty, id);
    }

    public int deleteSpecialtyById(int id) {
        return spcialtyRepository.deleteById(id);
    }

}
