package org.example.com.minibank.entity;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Person {

    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "surname")
    private String surname;
    @Basic(optional = false)
    @Column(name = "aze_id")
    private String azeID;
    @Basic(optional = false)
    @Column(name = "age")
    private int age;

    public Person() {
    }

    public Person(String name, String surname, int age, String azeID) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.azeID = azeID;
    }

    public String getName() {
        return name;
    }

    public String getazeID() {
        return azeID;
    }

    public String getSurname() {
        return surname;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "name='" + name + '\''
                + ", surname='" + surname + '\''
                + ", azeID='" + azeID + '\''
                + ", age=" + age;
    }
}
