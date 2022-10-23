package com.Vladislav.controller;

import com.Vladislav.model.Developer;
import com.Vladislav.service.DeveloperService;


import java.util.List;

public class DeveloperController {
    private final DeveloperService developerService = new com.Vladislav.service.DeveloperService();

    public Developer getDeveloperById(int id) {
        return developerService.getDeveloperById(id);
    }

    public List<Developer> getAllDevelopers() {
        return developerService.getAllDevelopers();
    }

    public Developer addNewDeveloper(Developer developer) {
        return developerService.addNewDeveloper(developer);
    }

    public Developer updateDeveloper(Developer developer) {
        return developerService.updateDeveloper(developer);
    }

    public boolean deleteDeveloperById(int id) {
        return developerService.deleteDeveloperById(id);
    }

}
