/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clientserver.studentdb.javaapp;

/**
 *
 * @author eleno
 */
import java.io.Serializable;

public class Student implements Serializable {
    private final String Name;
    private final String surname;
    private final String dpt;
    private final int semester;
    private final int passedSum;

    public Student(String Name, String surname, String dpt, int semester, int passedSum) {
        this.Name = Name;
        this.surname = surname;
        this.dpt = dpt;
        this.semester = semester;
        this.passedSum = passedSum;
    }

    public String getName() {
        return Name;
    }

    public String getSurname() {
        return surname;
    }

    public String getdpt() {
        return dpt;
    }

    public int getSemester() {
        return semester;
    }

    public int getPassedSum() {
        return passedSum;
    }

    @Override
    public String toString() {
        return "Student{" + Name + ", " + surname + ", " + dpt + ", " + semester 
                + ",  " + passedSum + "}";
    }
}
