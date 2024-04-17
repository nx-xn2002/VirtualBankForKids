package com.group20.backend.dao;

import lombok.AllArgsConstructor;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Base Dao
 *
 * @author Ni Xiang
 */
@AllArgsConstructor
public class BaseDao<T> {
    private String dataType;
    private String path;

    public List<Object> getAll() {
        Class<?> dataClass = null;
        try {
            dataClass = Class.forName(dataType);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        List<String> allStrings = getAllString();
        List<Object> res = new ArrayList<>();
        for (String str : allStrings) {
            List<String> list = Arrays.stream(str.split(" ")).toList();
            try {
                Object object = dataClass.getConstructor(List.class).newInstance(list);
                res.add(object);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return res;
    }

    public void saveAll(List<T> list) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            for (T o : list) {
                writer.write(o.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<String> getAllString() {
        List<String> lines = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Failed to read" + path);
        }
        return lines;
    }
}
