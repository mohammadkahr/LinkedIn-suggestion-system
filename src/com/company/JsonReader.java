package com.company;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.*;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import java.io.*;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.Iterator;

public class JsonReader {
    static Object fileReader(){
        Object obj = null;
        ArrayList<User> users = new ArrayList<>();
        JSONParser jsonParser = new JSONParser();
        try {
            FileReader reader = new FileReader(".\\users.json");
            obj = jsonParser.parse(reader);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return obj;
    }
    static ArrayList<User> fileReader2(){
        ArrayList<User> users = new ArrayList<>();
        Object object = fileReader();
        JSONArray jsonArray = (JSONArray) object;
//        jsonArray.add(object);
        Iterator<JSONObject> jsonObjectIterator = jsonArray.iterator();
        while (jsonObjectIterator.hasNext()){
            JSONObject jsonObject = jsonObjectIterator.next();
            String id = (String) jsonObject.get("id");
            String name = (String) jsonObject.get("name");
            String dateOfBirth = (String) jsonObject.get("dateOfBirth");
            String universityLocaition = (String) jsonObject.get("universityLocaition");
            String field = (String) jsonObject.get("field");
            String workPlace = (String) jsonObject.get("workPlace");
            String email = (String) jsonObject.get("email");

            JSONArray specialties = (JSONArray) jsonObject.get("specialties");
            JSONArray connectionId = (JSONArray) jsonObject.get("connectionId");

            User user = new User(name,id,specialties,field,
                    connectionId,dateOfBirth,universityLocaition,
                    workPlace,email);

            users.add(user);
        }
        return users;
    }
    static ArrayList<User> fileReader3() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        User [] users = objectMapper.readValue(new File(".\\users.json"),User[].class);
        ArrayList<User> users55 = new ArrayList<>();
        for (int i = 0; i < users.length; i++) {
            users55.add(users[i]);
        }
        return users55;
    }

}
