package com.Vladislav.controller;

import com.Vladislav.model.Skill;
import com.Vladislav.model.Specialty;
import com.Vladislav.service.SpecialtyService;

import java.util.List;

public class SpecialtyController {
    private final SpecialtyService specialtyService = new SpecialtyService();

    public Specialty getSpecialtyById(int id) {
        return specialtyService.getSpecialtyById(id);
    }

    public List<Specialty> getAllSpecialties() {
        return specialtyService.getAllSpecialties();
    }

    public Specialty addNewSpecialty(Specialty specialty) {
        return specialtyService.addNewSpecialty(specialty);
    }

    public int updateSpecialty(Specialty specialty, int id) {
        return specialtyService.updateSpecialty(specialty, id);
    }

    public int deleteSpecialtyById(int id) {
        return specialtyService.deleteSpecialtyById(id);
    }

}
