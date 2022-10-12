package com.Vladislav.view;

import com.Vladislav.controller.DeveloperController;
import com.Vladislav.controller.SkillController;
import com.Vladislav.controller.SpecialtyController;
import com.Vladislav.model.Developer;
import com.Vladislav.model.Skill;
import com.Vladislav.model.Specialty;

import javax.accessibility.AccessibleKeyBinding;
import java.util.ArrayList;
import java.util.List;
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
        int id = s.nextInt();
        Developer developer = developerController.getDeveloperById(id);
        System.out.println(developer == null ? "No developer" : developer);
    }

    public void getAllDevelopers() {
        developerController.getAllDevelopers().forEach(System.out::println);
    }

    public void deleteDeveloperById() {
        System.out.println("Enter ID: ");
        int id = s.nextInt();
        int check = developerController.deleteDeveloperById(id);
        System.out.println(check == 0 ? "Can not delete the developer" : "Successfully deleted!");
    }

    public void addNewDeveloper() {
        System.out.println("Enter first name: ");
        String firstName = s.nextLine();
        System.out.println("Enter last name: ");
        String lastName = s.nextLine();

        List<Skill> skillList = new ArrayList<>();
        System.out.println("Enter skills id: ");
        skillView.getAllSkills();
        String[] split = s.nextLine().split(" ");
        for (String s : split) {
            Skill skill = skillController.getSkillById(Integer.parseInt(s));
            skillList.add(skill);
        }

        System.out.println("Choice specialty id: ");
        specialtyView.getAllSpecialties();
        int specialtyChoice = s.nextInt();
        Specialty specialty = specialtyController.getSpecialtyById(specialtyChoice);
        Developer check = developerController.addNewDeveloper(new Developer(firstName, lastName, skillList, specialty));
        System.out.println(check == null ? "Not saved" : "Saved");
    }

    public void updateDeveloper() {
        System.out.println("Enter id of the developer you want to update:");
        int devId = s.nextInt();
        s.nextLine();
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
