package org.example.com.minibank.util;

import org.example.com.minibank.exception.InputValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class InputUtil {
    private static final Logger logger = LoggerFactory.getLogger(InputUtil.class);

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
        while (true) {
            String username = requireText("Enter username: ");

            if (username == null || username.isBlank() || username.length() < 6) {
                logger.warn("Your username cannot be less than 6 letters");
                continue;
            }

            if (!username.matches("^(?!.*\\.{2})[a-zA-Z0-9._]{1,30}$")) {
                logger.warn("Please, wouldn't write (..) or more than 30 characters in username");
                continue;
            }

            return username;
        }
    }

    public static String requirePassword() {
        while (true) {
            String password = requireText(("Enter password: "));
            if (password == null || password.isBlank() || password.length() < 8) {
                logger.warn("Your password cannot be less than 8 letters");
                throw new InputValidationException("Your password cannot be less than 8 letters");
            }
            if (!password.matches("^(?!.*\\.{2})[a-zA-Z0-9._]{1,30}$")) {
                logger.warn("Please, wouldn't write (..) or more than 30 characters in password");
                continue;
            }
            return password;
        }
    }

    public static String requireName() {
        String name = requireText(("Enter name: "));
        if (name == null || name.isBlank() || name.length() < 3) {
            logger.warn("Your name cannot be less than 3 letters");
            throw new InputValidationException("Your name cannot be less than 3 letters");
        }
        if (!name.matches("[a-zA-Z]{3,20}+")) {
            logger.warn("Only letters in name!");
            throw new InputValidationException("Only letters in name!");
        }
        return name;
    }

    public static String requireSurname() {
        String surname = requireText(("Enter surname: "));
        if (surname == null || surname.isBlank() || surname.length() < 4) {
            logger.warn("Your surname cannot be less than 3 letters");
            throw new InputValidationException("Your surname cannot be less than 3 letters");
        }
        if (!surname.matches("[a-zA-Z]{3,}+")) {
            logger.warn("Only letters in surname!");
            throw new InputValidationException("Only letters in surname!");
        }
        return surname;
    }

    public static String requireAzeID() {
        String azeId = requireText(("Enter AZE ID: ")).toUpperCase().trim();
        String idPattern = "^([A-Z]{2}\\d{8}|AZE\\d{8})$";
        if (!azeId.matches(idPattern)) {
            logger.warn("incorrectly AzeID!");
            throw new InputValidationException("incorrectly AzeID!");
        }

        return azeId;
    }

    public static int requireAge() {
        while (true) {
            int age = requireNumber("Enter Age: ");
            if (age < 0) {
                logger.warn("Cannot enter a negative age!");
                continue;
            }
            if (age < 18) {
                logger.warn("It is impossible to open an account for minor under the age of 18!");
                continue;
            }

            return age;
        }
//        if (!InputUtil.scanner.hasNextInt()) {
//            scanner.nextLine();
//            throw new InputValidationException("Only number!");
//        }
    }
}
