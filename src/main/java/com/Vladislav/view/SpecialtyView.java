package com.Vladislav.view;

import com.Vladislav.controller.SpecialtyController;
import com.Vladislav.model.Skill;
import com.Vladislav.model.Specialty;

import java.util.Scanner;

public class SpecialtyView {
    private final Scanner s = new Scanner(System.in);

    private final SpecialtyController specialtyController = new SpecialtyController();

    public void getSpecialtyById() {
        System.out.println("Enter ID: ");
        int id = s.nextInt();
        Specialty specialty = specialtyController.getSpecialtyById(id);
        System.out.println(specialty == null ? "No specialty" : specialty);
    }

    public void getAllSpecialties() {
        specialtyController.getAllSpecialties().forEach(System.out::println);
    }

    public void addNewSpecialty() {
        System.out.println("Enter specialty name: ");
        String name = s.nextLine();
        Specialty specialty = new Specialty(name);
        Specialty check = specialtyController.addNewSpecialty(specialty);
        System.out.println(check == null ? "Not saved" : "Saved!");
    }

    public void updateSpecialty() {
        System.out.println("Enter ID of the specialty you want to update: ");
        int id = s.nextInt();
        System.out.println("Enter new name: ");
        s.nextLine();
        String name = s.nextLine();
        Specialty specialty = new Specialty(name);
        int update = specialtyController.updateSpecialty(specialty, id);
        System.out.println(update == 0 ? "Not updated." : "Updated!");
    }

    public void deleteSpecialtyById() {
        System.out.println("Enter ID: ");
        int id = s.nextInt();
        int update = specialtyController.deleteSpecialtyById(id);
        System.out.println(update == 0 ? "Not deleted." : "Deleted!");
    }

    public void printSpecialtyOptions() {
        System.out.println("\n==========================\n");
        System.out.println("1. Get specialty by ID");
        System.out.println("2. Get all specialties");
        System.out.println("3. Add new specialty");
        System.out.println("4. Update specialty");
        System.out.println("5. Delete specialty by ID");
        System.out.println("Any number to return back");
        System.out.println("\n==========================\n");
    }
}
