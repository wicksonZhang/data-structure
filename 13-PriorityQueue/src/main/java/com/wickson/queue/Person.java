package com.wickson.queue;

/**
 * @author ZhangZiHeng
 * @date 2024-02-06
 */
public class Person implements Comparable<Person> {

    private String name;

    private int boneBreak;

    public Person(String name, int boneBreak) {
        this.name = name;
        this.boneBreak = boneBreak;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", boneBreak=" + boneBreak +
                '}';
    }

    @Override
    public int compareTo(Person person) {
        return this.boneBreak - person.boneBreak;
    }
}
