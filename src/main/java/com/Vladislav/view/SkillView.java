package com.Vladislav.view;

import com.Vladislav.model.Skill;
import com.Vladislav.controller.SkillController;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class SkillView {
    private final Scanner s = new Scanner(System.in);
    private final SkillController skillController = new SkillController();

    public void getSkillById() {
        System.out.println("Enter ID: ");
        int id;
        try {
            id = Integer.parseInt(s.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Incorrect typo.");
            return;
        }
        Skill skill = skillController.getSkillById(id);
        System.out.println(skill == null ? "No skill" : skill);
    }

    public void getAllSkills() {
        List<Skill> allSkills = skillController.getAllSkills();
        if(allSkills.isEmpty()) {
            System.out.println("No skills.");
        } else {
            allSkills.stream().sorted().forEach(System.out::println);
        }
    }

    public void addNewSkill() {
        System.out.println("Enter skill name: ");
        String name = s.nextLine();
        Skill skill = new Skill(name);
        Skill check = skillController.addNewSkill(skill);
        System.out.println(check == null ? "Not saved" : "Saved!");
    }

    public void updateSkill() {
        System.out.println("Enter ID of the skill you want to update: ");
        int id;
        try {
            id = Integer.parseInt(s.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Incorrect typo.");
            return;
        }
        System.out.println("Enter new name: ");
        String name = s.nextLine();
        Skill skill = new Skill(id, name);
        Skill update = skillController.updateSkill(skill);
        System.out.println(update == null ? "Not updated." : "Updated!");
    }

    public void deleteSkillByID() {
        System.out.println("Enter ID: ");
        int id;
        try {
            id = Integer.parseInt(s.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Incorrect typo.");
            return;
        }
        boolean update = skillController.deleteSkillById(id);
        System.out.println(!update ? "Not deleted." : "Deleted!");
    }

    public void printSkillOptions() {
        System.out.println("\n==========================\n");
        System.out.println("1. Get skill by ID");
        System.out.println("2. Get all skills");
        System.out.println("3. Add new skill");
        System.out.println("4. Update skill");
        System.out.println("5. Delete skill by ID");
        System.out.println("Any number to return back");
        System.out.println("\n==========================\n");
    }
}
