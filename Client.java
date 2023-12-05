/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clientserver.studentdb.javaapp;

/**
 *
 * @author eleno
 */
import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) throws ClassNotFoundException {
        try (Socket socket = new Socket("localhost", 3307);
             ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
             Scanner scanner = new Scanner(System.in)) {

            while (true) {
                System.out.println("1. Search for a student");
                System.out.println("2. Add a new student");
                System.out.println("3. Exit");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine();  // Consume the newline character

                switch (choice) {
                    case 1 -> searchForStudent(out, in, scanner);
                    case 2 -> addNewStudent(out, in, scanner);
                    case 3 -> {
                        out.writeObject("EXIT");
                        return;
                    }
                    default -> System.out.println("Invalid choice");
                }
            }
        } catch (IOException e) {
        }
    }

    private static void searchForStudent(ObjectOutputStream out, ObjectInputStream in, Scanner scanner) throws IOException, ClassNotFoundException {
        out.writeObject("SEARCH");
        System.out.print("Enter student's surname: ");
        String surname = scanner.nextLine();
        out.writeObject(surname);
        Student student = (Student) in.readObject();
        if (student != null) {
            System.out.println("Student found:");
            System.out.println(student);
        } else {
            System.out.println("Student not found");
        }
    }

    private static void addNewStudent(ObjectOutputStream out, ObjectInputStream in, Scanner scanner) throws IOException, ClassNotFoundException {
        out.writeObject("ADD");
        System.out.print("Enter student's name: ");
        String Name = scanner.nextLine();
        System.out.print("Enter student's surname: ");
        String surname = scanner.nextLine();
        System.out.print("Enter student's dpt: ");
        String dpt = scanner.nextLine();
        System.out.print("Enter student's semester: ");
        int semester = scanner.nextInt();
        System.out.print("Enter student's passed subjects: ");
        int passedSum = scanner.nextInt();

        Student newStudent = new Student(Name, surname, dpt, semester, passedSum);
        out.writeObject(newStudent);

        String response = (String) in.readObject();
        System.out.println(response);
    }
}
