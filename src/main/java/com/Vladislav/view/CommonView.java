package com.Vladislav.view;

import java.util.Scanner;

public class CommonView {
    private final Scanner s = new Scanner(System.in);
    DeveloperView developerView = new DeveloperView();
    SkillView skillView = new SkillView();
    SpecialtyView specialtyView = new SpecialtyView();


    public void start() {
        options();
        while (true) {
            int choice;
            try{
                choice = Integer.parseInt(s.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Incorrect number.");
                continue;
            }
            switch (choice) {
                case 1 -> {
                    developerView.printDeveloperOptions();
                    int developerChoice;
                    try {
                        developerChoice = Integer.parseInt(s.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Incorrect input!");
                        continue;
                    }
                    switch (developerChoice) {
                        case 1 -> developerView.getDeveloperById();
                        case 2 -> developerView.getAllDevelopers();
                        case 3 -> developerView.addNewDeveloper();
                        case 4 -> developerView.updateDeveloper();
                        case 5 -> developerView.deleteDeveloperById();
                        default -> options();
                    }
                }
                case 2 -> {
                    skillView.printSkillOptions();
                    int skillChoice;
                    try {
                        skillChoice = Integer.parseInt(s.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Incorrect input!");
                        continue;
                    }
                    switch (skillChoice) {
                        case 1 -> skillView.getSkillById();
                        case 2 -> skillView.getAllSkills();
                        case 3 -> skillView.addNewSkill();
                        case 4 -> skillView.updateSkill();
                        case 5 -> skillView.deleteSkillByID();
                        default -> options();
                    }
                }
                case 3 -> {
                    specialtyView.printSpecialtyOptions();
                    int specialtyChoice;
                    try {
                        specialtyChoice = Integer.parseInt(s.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Incorrect input.");
                        return;
                    }
                    switch (specialtyChoice) {
                        case 1 -> specialtyView.getSpecialtyById();
                        case 2 -> specialtyView.getAllSpecialties();
                        case 3 -> specialtyView.addNewSpecialty();
                        case 4 -> specialtyView.updateSpecialty();
                        case 5 -> specialtyView.deleteSpecialtyById();
                        default -> options();
                    }
                }
                case 4 -> options();
                case 5 -> {
                    System.out.println("Good bye!");
                    return;
                }
                default -> System.out.println("Enter correct number!");
            }
        }
    }

    public void options() {
        System.out.println("\n=========================\n");
        System.out.println("Options: ");
        System.out.println("1. Developers");
        System.out.println("2. Skills");
        System.out.println("3. Specialties");
        System.out.println("4. Show options");
        System.out.println("5. Finish the program");
        System.out.println("\n=========================\n");
    }
}
