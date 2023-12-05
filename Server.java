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
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Server {

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(3307)) {
            System.out.println("Server is running. Waiting for clients...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket);

                new Thread(() -> handleClient(clientSocket)).start();
            }
        } catch (IOException e) {
        }
    }

    private static void handleClient(Socket clientSocket) {
        try (
            ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream())
        ) {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mariadb://localhost:3306/askisi1_db", "christofidou", "password");
            System.out.println("Database Connection Successful.");

            while (true) {
                String action = (String) in.readObject();

                switch (action) {
                    case "SEARCH" -> {
                        String surname = (String) in.readObject();
                        searchStudent(connection, out, surname);
                    }
                    case "ADD" -> {
                        Student newStudent = (Student) in.readObject();
                        addStudent(connection, out, newStudent);
                    }
                    case "EXIT" -> {
                        clientSocket.close();
                        return;
                    }
                    default -> System.out.println("Invalid action");
                }
            }
        } catch (IOException | SQLException | ClassNotFoundException e) {
        }
    }

    private static void searchStudent(Connection connection, ObjectOutputStream out, String surname) throws SQLException, IOException {
        String query = "SELECT * FROM students WHERE surname = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, surname);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Student student = new Student(
                        resultSet.getString("name"),
                        resultSet.getString("surname"),
                        resultSet.getString("dpt"),
                        resultSet.getInt("semester"),
                        resultSet.getInt("passed_sum")
                );
                out.writeObject(student);
            } else {
                out.writeObject(null);  // Send null if not found
            }
        }
    }

    private static void addStudent(Connection connection, ObjectOutputStream out, Student newStudent) throws SQLException, IOException {
        String insertQuery = "INSERT INTO students (name, surname, dpt, semester, passed_sum) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement insertStatement = connection.prepareStatement(insertQuery)) {
            insertStatement.setString(1, newStudent.getName());
            insertStatement.setString(2, newStudent.getSurname());
            insertStatement.setString(3, newStudent.getdpt());
            insertStatement.setInt(4, newStudent.getSemester());
            insertStatement.setInt(5, newStudent.getPassedSum());

            int rowsAffected = insertStatement.executeUpdate();

            if (rowsAffected > 0) {
                out.writeObject("Student added successfully");
            } else {
                out.writeObject("Failed to add student");
            }
        }
    }
}
