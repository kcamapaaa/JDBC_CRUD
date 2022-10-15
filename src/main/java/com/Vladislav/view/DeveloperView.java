package com.Vladislav.view;

import com.Vladislav.controller.DeveloperController;
import com.Vladislav.controller.SkillController;
import com.Vladislav.controller.SpecialtyController;
import com.Vladislav.model.Developer;
import com.Vladislav.model.Skill;
import com.Vladislav.model.Specialty;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class DeveloperView {
    private final Scanner s = new Scanner(System.in);
    private final DeveloperController developerController = new DeveloperController();
    private final SkillController skillController = new SkillController();
    private final SkillView skillView = new SkillView();
    private final SpecialtyView specialtyView = new SpecialtyView();
    private final SpecialtyController specialtyController = new SpecialtyController();

    public void getDeveloperById() {
        System.out.println("Enter ID: ");
        int id;
        try {
            id = Integer.parseInt(s.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Incorrect typo.");
            return;
        }
        Developer developer = developerController.getDeveloperById(id);
        System.out.println(developer == null ? "No developer" : developer);
    }

    public void getAllDevelopers() {
        List<Developer> allDevelopers = developerController.getAllDevelopers();
        if (allDevelopers == null) {
            System.out.println("No developers.");
        } else {
            allDevelopers.stream().filter(Objects::nonNull).forEach(System.out::println);
        }
    }

    public void deleteDeveloperById() {
        System.out.println("Enter ID: ");
        int id;
        try {
            id = Integer.parseInt(s.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Incorrect typo.");
            return;
        }
        int check = developerController.deleteDeveloperById(id);
        System.out.println(check == 0 ? "Can not delete the developer" : "Successfully deleted!");
    }

    public void addNewDeveloper() {
        System.out.println("Enter first name: ");
        String firstName = s.nextLine();
        System.out.println("Enter last name: ");
        String lastName = s.nextLine();

        if(skillController.getAllSkills() == null) {
            System.out.println("Create any skill!");
            return;
        } else {
            System.out.println("Enter skills id with spaces\n(Example: 2 3 4 10): ");
            skillView.getAllSkills();
        }
        List<Skill> skillList = new ArrayList<>();
        String[] split = s.nextLine().split(" ");
        for (String s : split) {
            try {
                Skill skill = skillController.getSkillById(Integer.parseInt(s));
                skillList.add(skill);
            } catch (NumberFormatException e) {
                System.out.println("Incorrect format");
                return;
            }
        }

        if(specialtyController.getAllSpecialties() == null) {
            System.out.println("Create any specialty!");
            return;
        } else {
            System.out.println("Choice specialty id: ");
            specialtyView.getAllSpecialties();
        }
        int specialtyChoice;
        try {
            specialtyChoice = Integer.parseInt(s.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Incorrect typo.");
            return;
        }
        Specialty specialty = specialtyController.getSpecialtyById(specialtyChoice);
        Developer check = developerController.addNewDeveloper(new Developer(firstName, lastName, skillList, specialty));
        System.out.println(check == null ? "Not saved" : "Saved");
    }

    public void updateDeveloper() {
        System.out.println("Enter id of the developer you want to update:");
        int devId;
        try {
            devId = Integer.parseInt(s.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Incorrect typo.");
            return;
        }
        System.out.println("Enter new first name: ");
        String firstName = s.nextLine();
        System.out.println("Enter new last name: ");
        String lastName = s.nextLine();

        Developer developer = new Developer(firstName, lastName);
        int update = developerController.updateDeveloper(developer, devId);
        System.out.println(update == 0 ? "Not updated." : "Updated!");
    }

    public void printDeveloperOptions() {
        System.out.println("\n==========================\n");
        System.out.println("1. Get developer by ID");
        System.out.println("2. Get all developers");
        System.out.println("3. Add new developer");
        System.out.println("4. Update developer");
        System.out.println("5. Delete developer by ID");
        System.out.println("Any number to return back");
        System.out.println("\n==========================\n");
    }
}
