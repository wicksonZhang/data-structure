package com.wickson.hash;

import java.util.Objects;

public class Person {

    private int age;

    private float height;

    private String name;

    public Person(int age, float height, String name) {
        this.age = age;
        this.height = height;
        this.name = name;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Person person = (Person) o;
//        return age == person.age && Float.compare(person.height, height) == 0 && Objects.equals(name, person.name);
//    }

    @Override
    public int hashCode() {
        int hashCode = Integer.hashCode(age);
        hashCode = hashCode + Float.hashCode(height);
        hashCode = hashCode + name.hashCode();
        return hashCode;
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", height=" + height +
                ", name='" + name + '\'' +
                '}';
    }
}
