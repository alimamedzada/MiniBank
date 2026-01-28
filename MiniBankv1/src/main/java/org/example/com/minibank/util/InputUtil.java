package org.example.com.minibank.util;

import org.example.com.minibank.exception.InputValidationException;

import java.util.Scanner;

public class InputUtil {

    public static String requireText(String question) {
        System.out.println(question);
        return new Scanner(System.in).nextLine();
    }

    public static double requireDouble(String question) {
        System.out.println(question);
        return new Scanner(System.in).nextDouble();
    }

    public static int requireNumber(String question) {
        System.out.println(question);
        return new Scanner(System.in).nextInt();
    }

    public static String requireUsername() {
        String username = requireText("Enter username: ");
        if (username == null || username.isBlank() || username.length() < 6) {
            System.out.println("Your username cannot be less than 6 letters");
            requireUsername();
        }
        if (!username.matches("^(?!.*\\.{2})[a-zA-Z0-9._]{1,30}$")) {
            System.out.println("Please, wouldn't write (..) or more than 30 characters");
            return requireUsername();
        }
        return username;
    }

    public static String requirePassword() {
        String password = requireText(("Enter password: "));
        if (password == null || password.isBlank() || password.length() < 8) {
            throw new InputValidationException("Your username cannot be less than 8 letters");
        }
        if (!password.matches("^(?!.*\\.{2})[a-zA-Z0-9._]{1,30}$")) {
            System.out.println("Please, wouldn't write (..) or more than 30 characters");
            return requireUsername();
        }
        return password;
    }

    public static String requireName() {
        String name = requireText(("Enter name: "));
        if (name == null || name.isBlank() || name.length() < 3) {
            throw new InputValidationException("Your name cannot be less than 3 letters");
        }
        if (!name.matches("[a-zA-Z]{3,}+")) {
            throw new InputValidationException("Only letters!");
        }
        return name;
    }

    public static String requireSurname() {
        String surname = requireText(("Enter surname: "));
        if (surname == null || surname.isBlank() || surname.length() < 4) {
            throw new InputValidationException("Your surname cannot be less than 3 letters");
        }
        if (!surname.matches("[a-zA-Z]{3,}+")) {
            throw new InputValidationException("Only letters!");
        }
        return surname;
    }

    public static String requireAzeID() {
        String azeId = requireText(("Enter AZE ID: ")).toUpperCase().trim();
        String idPattern = "^([A-Z]{2}\\d{8}|AZE\\d{8})$";
        if (!azeId.matches(idPattern)) {
            throw new InputValidationException("incorrectly ID!");
        }

        return azeId;
    }


    public static int requireAge() {
//        if (!InputUtil.scanner.hasNextInt()) {
//            scanner.nextLine();
//            throw new InputValidationException("Only number!");
//        }
        int age = requireNumber("Enter Age: ");
        if (age < 0) {
            System.out.println("Cannot enter a negative age!");
            return requireAge();
        }
        if (age < 18) {
            throw new InputValidationException("It is impossible to open an account for minor under the age of 18!");
        }

        return age;
    }
}
