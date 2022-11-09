package org.springcourse.dao;

import org.springcourse.models.Person;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {

    private final List<Person> people = new ArrayList<>();
    private int PEOPLE_COUNT = 0;

    public PersonDAO() {

        people.add(new Person(++PEOPLE_COUNT, "Tom", 20, "ytutdfg@hg"));
        people.add(new Person(++PEOPLE_COUNT, "Mike", 30, "ghewrtetjg@bv"));
        people.add(new Person(++PEOPLE_COUNT, "Katy", 22, "skjhkafdsdf@xc"));
        people.add(new Person(++PEOPLE_COUNT, "Mark", 31, "bcsdfgvb@sd"));
        people.add(new Person(++PEOPLE_COUNT, "Amber", 38, "qweertq@sd"));

    }

    public List<Person> index() {
        return new ArrayList<>(people);
    }

    public Person show(int id) {

        return people.stream()
                .filter(person -> person.getId() == id)
                .findAny()
                .orElse(null);

    }

    public void save(Person person) {
        person.setId(++PEOPLE_COUNT);
        people.add(person);
    }

    public void update(int id, Person person) {

        Person personToBeUpdated = show(id);

        personToBeUpdated.setName(person.getName());
        personToBeUpdated.setAge(person.getAge());
        personToBeUpdated.setEmail(person.getEmail());

    }

    public void delete(int id) {
        people.removeIf(person -> person.getId() == id);
    }

}
