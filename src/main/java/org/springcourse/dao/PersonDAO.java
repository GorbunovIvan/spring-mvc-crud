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

        people.add(new Person(++PEOPLE_COUNT, "Tom"));
        people.add(new Person(++PEOPLE_COUNT, "Mike"));
        people.add(new Person(++PEOPLE_COUNT, "Katy"));
        people.add(new Person(++PEOPLE_COUNT, "Mark"));
        people.add(new Person(++PEOPLE_COUNT, "Amber"));

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

    }

    public void delete(int id) {
        people.removeIf(person -> person.getId() == id);
    }

}
