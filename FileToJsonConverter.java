package ua.opnu;

import java.io.*;
import java.util.*;
import com.google.gson.*;

class User {
    private String name;
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }
}

public class FileToJsonConverter {
    public static void convertToJson(String inputFileName, String outputFileName) {
        List<User> users = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(inputFileName))) {
            String header = br.readLine(); // Пропускаємо заголовок
            if (header == null) {
                throw new IllegalArgumentException("Файл порожній.");
            }

            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\s+");
                if (parts.length != 2) continue;

                String name = parts[0];
                int age = Integer.parseInt(parts[1]);
                users.add(new User(name, age));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        try (FileWriter writer = new FileWriter(outputFileName)) {
            Gson gson = new Gson();
            gson.toJson(users, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        convertToJson("fileV2.txt", "user.json");
    }
}