package com.Vladislav.service;

import com.Vladislav.model.Developer;
import com.Vladislav.repository.DataBaseRepository.DataBaseDeveloperRepository;
import com.Vladislav.repository.DeveloperRepository;

import java.util.List;

public class DeveloperService {
    DeveloperRepository developerRepository = new DataBaseDeveloperRepository();

    public Developer getDeveloperById(int id) {
        return developerRepository.getById(id);
    }

    public List<Developer> getAllDevelopers() {
        return developerRepository.getAll();
    }

    public Developer addNewDeveloper(Developer developer) {
        return developerRepository.save(developer);
    }

    public int updateDeveloper(Developer developer, int id) {
        return developerRepository.update(developer, id);
    }

    public int deleteDeveloperById(int id) {
        return developerRepository.deleteById(id);
    }
}
