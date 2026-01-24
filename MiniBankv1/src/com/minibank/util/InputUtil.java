package com.minibank.util;

import com.minibank.exception.InputValidationException;

import java.util.Scanner;

public class InputUtil {
    public static Scanner scanner = new Scanner(System.in);

    public static String requireName() {
        System.out.println("Enter name: ");
        String name = InputUtil.scanner.nextLine();
        if (name == null || name.isBlank() || name.length() < 3) {
            throw new InputValidationException("Your name cannot be less than 3 letters");
        }
        if (!name.matches("[a-zA-Z]{3,}+")) {
            throw new InputValidationException("Only letters!");
        }
        return name;
    }

    public static String requireSurname() {
        System.out.println("Enter surname: ");
        String surname = InputUtil.scanner.nextLine();
        if (surname == null || surname.isBlank() || surname.length() < 4) {
            throw new InputValidationException("Your surname cannot be less than 3 letters");
        }
        if (!surname.matches("[a-zA-Z]{3,}+")) {
            throw new InputValidationException("Only letters!");
        }
        return surname;
    }

    public static String requireAzeID() {
        System.out.println("Enter AZE ID: ");
        String azeId = InputUtil.scanner.nextLine().toUpperCase().trim();
        String idPattern = "^([A-Z]{2}\\d{8}|AZE\\d{8})$";
        if (!azeId.matches(idPattern)) {
            throw new InputValidationException("incorrectly ID!");
        }

        return azeId;
    }


    public static int requireAge() {
        System.out.println("Enter Age: ");

        if (!InputUtil.scanner.hasNextInt()) {
            throw new InputValidationException("Only number!");
        }
        int age = InputUtil.scanner.nextInt();
        if (age < 0) {
            System.out.println("Cannot enter a negative age!");
            return requireAge();
        }
        if (age < 18) {
            throw new InputValidationException("It is impossible to open an account for veterans under the age of 18!");
        }
        scanner.nextLine();
        return age;
    }
}
