package be.intecbrussel.exercises.exercise6and8;

import java.time.LocalDate;
import java.io.*;
import java.util.Timer;
import java.util.TimerTask;

public class Person implements Serializable {

    private String firstname;
    private String lastName;
    private LocalDate birthDay;
    private transient Timer heartbeat;

    public Person(String firstname, String lastName, LocalDate birthDay) {
        this.firstname = firstname;
        this.lastName = lastName;
        this.birthDay = birthDay;
        heartbeat = new Timer(true);
        heartbeat.scheduleAtFixedRate(new Heartbeat(), 0, 1000);
    }

    private class Heartbeat extends TimerTask {
        @Override
        public void run() {
            System.out.println("*");
        }
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(LocalDate birthDay) {
        this.birthDay = birthDay;
    }
}
