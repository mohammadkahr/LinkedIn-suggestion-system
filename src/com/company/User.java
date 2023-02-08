package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class User {
    static Scanner input = new Scanner(System.in);
    String name ;
    String id ;
    ArrayList<String> specialties ;
    String field ;
    ArrayList<String> connectionId;
    String dateOfBirth;
    String universityLocation;
    String workplace;
    String email;
    String pas = "1234";
    int score = 0;

    //constructors
    public User(String name, String id, ArrayList<String> specialties,
                String field, ArrayList<String> connectionId, String dateOfBirth,
                String universityLocation, String workplace, String email) {
        this.name = name;
        this.id = id;
        this.specialties = specialties;
        this.field = field;
        this.connectionId = connectionId;
        this.dateOfBirth = dateOfBirth;
        this.universityLocation = universityLocation;
        this.workplace = workplace;
        this.email = email;
    }
    public User() {
    }

    //getters and setters
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public ArrayList<String> getSpecialties() {
        return specialties;
    }
    public void setSpecialties(ArrayList<String> specialties) {
        this.specialties = specialties;
    }
    public String getField() {
        return field;
    }
    public void setField(String field) {
        this.field = field;
    }
    public ArrayList<String> getConnectionId() {
        return connectionId;
    }
    public void setConnectionId(ArrayList<String> connectionId) {
        this.connectionId = connectionId;
    }
    public String getDateOfBirth() {
        return dateOfBirth;
    }
    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    public String getUniversityLocation() {
        return universityLocation;
    }
    public void setUniversityLocation(String universityLocation) {
        this.universityLocation = universityLocation;
    }
    public String getWorkplace() {
        return workplace;
    }
    public void setWorkplace(String workplace) {
        this.workplace = workplace;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPas() {
        return pas;
    }
    public void setPas(String pas) {
        this.pas = pas;
    }
    //methods
    static User logIn(String email,String pass ,Graph graph){
        User user = null;
        for (int i = 0; i < graph.vertices().size(); i++) {
            if (email.equals(graph.vertices().get(i).getElement().getEmail()) &&
                    pass.equals(graph.vertices().get(i).getElement().getPas())){
                user = graph.vertices().get(i).getElement();
            }
        }

        return user;
    }
    static void menu(User user , Graph graph , ArrayList<User> users){
        while (true){
            short select ;
            System.out.println("\n\n\n\n1.Details of your account");
            System.out.println("2.All users");
            System.out.println("3.Search a users");
            System.out.println("4.Suggest users ");
            System.out.println("5.Connect to a user");
            System.out.println("6.Change password");
            System.out.println("9.Exit");
            select = input.nextShort();
            if (select==1){
                System.out.println(graph.getVertex(user.getId()).getElement().toString());
            }//Details - done
            else if (select==2){
                System.out.print("Enter the number of users you want to ses (max=2000): ");
                int numberOfUsers = input.nextInt();
                if (numberOfUsers >= graph.vertices().size()) numberOfUsers = graph.vertices().size();
                for (int i = 0; i < numberOfUsers; i++) {
                    System.out.println(graph.vertices().get(i).getElement().toString() + "\n");
                }

            }//print all users - done
            else if (select==3){
                System.out.println("Do you want to search with(1.id,2.name,3.e-mail)");
                select = input.nextShort();
                String str ;
                if (select==1){
                    System.out.println("Enter id : ");
                    str = input.next();
                    searchWithId(str,graph);
                }//id
                else if (select==2) {
                    System.out.println("Enter name : ");
                    str = input.nextLine();
                    searchWithName(str,graph);
                }//name
                else if (select==3){
                    System.out.println("Enter e-mail : ");
                    str = input.next();
                    searchWithEmail(str,graph);
                }//email
                else if (select==9){
                    menu(user,graph,users);
                }//menu
            }//search a user - done
            else if (select==4){
                ArrayList<Vertex> arr = setScoreWithVertex(graph,user.getId());
                for (int i = 0; i < 25; i++) {
                    System.out.println(arr.get(i).getElement().toString());
                }
            }//suggest users
            else if (select==5){
                System.out.println("Enter the id of who you want to connect that : ");
                String str = input.next();
                User.connectToUser(graph, user.getId(), str);
            }//connect to user - done
            else if (select==6){
                changePass(graph,user);
            }//change pass - done
            else if (select==9){
                System.out.println("\n\t\tyou back to login page");
                Main.menu(graph,users);
            }//exit - done
        }
    }
    static void searchWithId(String id ,Graph graph){
        for (int i = 0; i < graph.vertices().size() ; i++) {
            if (graph.vertices().get(i).getElement().getId().equals(id)){
                System.out.println(graph.vertices().get(i).getElement().toString());
                return;
            }
        }
        System.out.println("There isn't any user with this id");
    }
    static void searchWithName(String name,Graph graph){
        for (int i = 0; i < graph.vertices().size() ; i++) {
            if (graph.vertices().get(i).getElement().getName().equals(name)){
                System.out.println(graph.vertices().get(i).getElement().toString());
                return;
            }
        }
        System.out.println("There isn't any user with this name");
    }
    static void searchWithEmail(String email,Graph graph){
        for (int i = 0; i < graph.vertices().size() ; i++) {
            if (graph.vertices().get(i).getElement().getEmail().equals(email)){
                System.out.println(graph.vertices().get(i).getElement().toString());
                return;
            }
        }
        System.out.println("There isn't any user with this email");
    }
    static void connectToUser(Graph graph,String connectionId1,String connectionId2){
        graph.insertEdge(graph.getVertex(connectionId1),graph.getVertex(connectionId2),
                connectionId1+"-"+connectionId2);
        graph.getVertex(connectionId1).getElement().connectionId.add(connectionId2);
        System.out.println("Now you are connect to "+connectionId2);
    }
    static ArrayList<Vertex> setScoreWithVertex(Graph graph, String id){
        User user = graph.getVertex(id).getElement();
        List<List<Vertex>> rec = new ArrayList<>();
        rec = graph.bfsLevels(graph.getVertex(id),6);
        ArrayList<Vertex>  arr = new ArrayList<>();
        for (int i = 0; i < rec.size(); i++) {
            for (int j = 0; j < rec.get(i).size(); j++) {
                rec.get(i).get(j).getElement().score = 0 ;
                if (rec.get(i).get(j).getElement().getField().equals(user.getField())){
                    rec.get(i).get(j).getElement().score+=10;
                }
                if (rec.get(i).get(j).getElement().getUniversityLocation().equals(user.getUniversityLocation())){
                    rec.get(i).get(j).getElement().score+=10;
                }
                if (rec.get(i).get(j).getElement().getWorkplace().equals(user.getWorkplace())){
                    rec.get(i).get(j).getElement().score+=10;
                }
                for (int k = 0; k < rec.get(i).get(j).getElement().specialties.size(); k++) {
                    for (int l = 0; l < user.specialties.size(); l++) {
                        int x = (k+1) + (l+1);
                        if (rec.get(i).get(j).getElement().specialties.get(k).
                                equals(user.specialties.get(l))){
                            rec.get(i).get(j).getElement().score+=10*(1/x);
                        }
                    }
                }
                if (i==1) rec.get(i).get(j).getElement().score+=25 ;
                if (i==2) rec.get(i).get(j).getElement().score+=20 ;
                if (i==3) rec.get(i).get(j).getElement().score+=15 ;
                if (i==4) rec.get(i).get(j).getElement().score+=10 ;
                if (i==5) rec.get(i).get(j).getElement().score+=5 ;
                arr.add(rec.get(i).get(j));
            }
        }
        for (int i = 0; i < arr.size(); i++) {
            for (int j = i; j < arr.size()-1; j++) {
                if (arr.get(j).getElement().score > arr.get(j+1).getElement().score){
                    Vertex temp = arr.get(j);
                    arr.set(j,arr.get(j+1)) ;
                    arr.set(j+1,temp);

                }
            }
        }
        System.out.println();
        return arr;
    }
    static void changePass(Graph graph,User user){
        System.out.println("Your password is : "
                + graph.getVertex(user.getId()).getElement().pas);
        System.out.println("Enter new password : ");
        graph.getVertex(user.getId()).getElement().setPas(input.next());
    }

    @Override
    public String toString() {
        return "\n\nName : " + name + '\t' +
                "|| Id : " + id + '\t' +
                "|| Field : " + field + '\n' +
                "DateOfBirth : " + dateOfBirth + '\t' +
                "|| UniversityLocation : " + universityLocation + '\t' +
                "|| Workplace : " + workplace + '\n' +
                "Email : " + email + '\n' +
                "Specialties : " + specialties +
                "\nConnectionId=" + connectionId ;
    }
}
