package org.exceptions;

public class ErrorHandler {

    public static void handleException(Exception e) {
        System.out.println("Ошибка: " + e.getMessage());
    }
}
