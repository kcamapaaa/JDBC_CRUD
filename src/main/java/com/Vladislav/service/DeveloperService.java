package com.Vladislav.service;

import com.Vladislav.model.Developer;
import com.Vladislav.repository.jdbc.JdbcDeveloperRepository;
import com.Vladislav.repository.DeveloperRepository;

import java.util.List;

public class DeveloperService {
    private final DeveloperRepository developerRepository;

    public DeveloperService() {
        developerRepository = new JdbcDeveloperRepository();
    }

    public DeveloperService(DeveloperRepository developerRepository) {
        this.developerRepository = developerRepository;
    }

    public Developer getDeveloperById(int id) {
        return developerRepository.getById(id);
    }

    public List<Developer> getAllDevelopers() {
        return developerRepository.getAll();
    }

    public Developer addNewDeveloper(Developer developer) {
        return developerRepository.save(developer);
    }

    public Developer updateDeveloper(Developer developer) {
        return developerRepository.update(developer);
    }

    public boolean deleteDeveloperById(int id) {
        return developerRepository.deleteById(id);
    }
}
