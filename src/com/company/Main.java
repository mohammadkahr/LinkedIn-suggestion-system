package com.company;
import java.io.IOException;
import java.util.*;

import static java.lang.System.exit;

public class Main {
    static Scanner input = new Scanner(System.in);
    public static void main(String[] args) throws IOException {
        // Mohammad._.hr
        //4003623039

        //create graph
        ArrayList<User> users = JsonReader.fileReader3();
        Graph graph = new Graph();
        for (User value : users) {
            graph.insertVertex(value);
        }
        for (Vertex vertex : graph.vertices()) {
            for (String connectionId : vertex.getElement().connectionId){
                graph.insertEdge(vertex,graph.getVertex(connectionId),
                        vertex.getElement().getId() + "-" + connectionId);
            }
        }
        graph.identifyComponentsDFS();
        //end of creation graph
        menu(graph,users);


    }
    //methods
    static void logIn(Graph graph,ArrayList<User> users) {
        User user = null;
        while (user == null) {
            System.out.println("Enter your Email: ");
            String email = input.next();
//            String email = "behdadLevine1997@webster.org";
            System.out.println("Enter your password: ");
            String pass = input.next();
//            String pass = "1234";

            user = User.logIn(email, pass, graph);
            if (user == null) {
                System.out.println("there isn't any account with this user and pass" +
                        "\nYou move to login page again!");
            } else
                User.menu(user,graph,users);
        }
    }
    static Graph singUp(Graph graph,ArrayList<User> users){
        ArrayList<String> specialist = new ArrayList<>();
        ArrayList<String> connected = new ArrayList<>();
        System.out.println("Enter your name : ");
        String name = input.next();
        specialist.add("Front-End");
        specialist.add("Database");
        specialist.add("Crypto");
        connected.add("906");
        connected.add("746");
        connected.add("1109");
        User user = new User(name,users.size()+1+"",specialist,"AA",connected,
                "1997/12/23","Isfahan","Intel",
                "H@gmail.com");
        user.setPas("1234");
        graph.insertVertex(user);
        for (int i = 0; i < user.connectionId.size(); i++) {
            graph.insertEdge(graph.getVertex(user.getId()),graph.getVertex(user.connectionId.get(i)),
                    user.getId()+"-"+graph.getVertex(user.connectionId.get(i)));
        }

        return graph;
    }
    static void menu(Graph graph,ArrayList<User> users){
        short select ;
        while (true){
            System.out.println("Enter the number");
            System.out.println("1.sing up");
            System.out.println("2.log in");
            select = input.nextShort();
            if (select==1){
                singUp(graph,users);
            }
            else if(select==2){
                logIn(graph,users);
            }
            else if(select==9){
                System.out.println("\n\n\t\tGOOD LUCK");
                exit(0);
            }
        }
    }
}
