package org.example.com.minibank.model.user;

public abstract class Person {
    private String name;
    private String surname;
    private String azeID;
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
        return "Person{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", azeID='" + azeID + '\'' +
                ", age=" + age +
                '}';
    }
}
