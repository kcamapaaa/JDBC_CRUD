package com.Vladislav.view;

import com.Vladislav.controller.SpecialtyController;
import com.Vladislav.model.Specialty;

import java.util.List;
import java.util.Scanner;

public class SpecialtyView {
    private final Scanner s = new Scanner(System.in);

    private final SpecialtyController specialtyController = new SpecialtyController();

    public void getSpecialtyById() {
        System.out.println("Enter ID: ");
        int id;
        try {
            id = Integer.parseInt(s.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Incorrect typo.");
            return;
        }
        Specialty specialty = specialtyController.getSpecialtyById(id);
        System.out.println(specialty == null ? "No specialty" : specialty);
    }

    public void getAllSpecialties() {
        List<Specialty> allSpecialties = specialtyController.getAllSpecialties();
        if(allSpecialties.isEmpty()) {
            System.out.println("No specialties.");
        } else {
            allSpecialties.forEach(System.out::println);
        }
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
        int id;
        try {
            id = Integer.parseInt(s.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Incorrect typo.");
            return;
        }
        System.out.println("Enter new name: ");
        String name = s.nextLine();
        Specialty specialty = new Specialty(id, name);
        Specialty update = specialtyController.updateSpecialty(specialty);
        System.out.println(update == null ? "Not updated." : "Updated!");
    }

    public void deleteSpecialtyById() {
        System.out.println("Enter ID: ");
        int id;
        try {
            id = Integer.parseInt(s.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Incorrect typo.");
            return;
        }
        boolean update = specialtyController.deleteSpecialtyById(id);
        System.out.println(!update ? "Not deleted." : "Deleted!");
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
