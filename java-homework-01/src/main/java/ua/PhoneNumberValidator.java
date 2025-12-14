package ua;

import java.io.*;
import java.util.regex.*;

public class PhoneNumberValidator {
    public static void validatePhoneNumbers(String fileName) {
        String validPattern1 = "^\\(\\d{3}\\) \\d{3}-\\d{4}$";
        String validPattern2 = "^\\d{3}-\\d{3}-\\d{4}$";

        Pattern pattern1 = Pattern.compile(validPattern1);
        Pattern pattern2 = Pattern.compile(validPattern2);

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (pattern1.matcher(line).matches() || pattern2.matcher(line).matches()) {
                    System.out.println(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        validatePhoneNumbers("file.txt");
    }
}