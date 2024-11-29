package util;

public class InputValidator {
    public static boolean isValidId(String id) {
        return id != null && id.matches("S-\\d{4}");
    }

    public static boolean isValidGPA(double gpa) {
        return gpa >= 0.0 && gpa <= 4.0;
    }
}
