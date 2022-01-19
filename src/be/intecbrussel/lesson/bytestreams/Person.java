package be.intecbrussel.lesson.bytestreams;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.time.Duration;

public class Person implements Serializable {
    // Serializable is needed to allow writing objects like an instance of
    // this Person class to bytes - CHECK FURTHER SERIALIZABLE
    public static final long serialVersionUID = 1; // CHECK

    private String name;
//    private int age; // we change age into birthday - but that means if we
//    read the file after just changing a variable in our Person class, we
//    will not be able anymore to read our file anymore
    private int birthDay; // normally this should be a local date, but hey..
    private String mood;
    private transient Duration timeLoggedOn = null;
    // new keyword: transient - with transient we indicate that a certain
    // variable should NOT be written to the file / database / location where
    // we want to write / read the data of an object of this class to.

    public Person(String name, int birthDay, String mood) {
        this.name = name;
        this.birthDay = birthDay;
        this.mood = mood;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBirthDay() {
        return birthDay;
    }

    public void setAge(int birthDay) {
        this.birthDay = birthDay;
    }

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }

    public Duration getTimeLoggedOn() {
        return timeLoggedOn;
    }

    public void setTimeLoggedOn(Duration timeLoggedOn) {
        this.timeLoggedOn = timeLoggedOn;
    }

    // implementation of our own serialization via writing custom readObject
    // method
    private void readObject(ObjectInputStream ois) {
        try {
            ois.defaultReadObject();
            timeLoggedOn = Duration.ZERO;
        } catch (IOException | ClassNotFoundException e) { // exception
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", birthDay=" + birthDay +
                ", mood='" + mood + '\'' +
                '}';
    }
}
