package org.springcourse.dao;

import org.springcourse.models.Person;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO_PureJDBC {

    private static final String URL = "jdbc:postgresql://localhost:5432/first-spring-mvc";
    private static final String USER = "postgres";
    private static final String PASSWORD = "root";

    private static final Connection connection;

    static {

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<Person> index() {

        List<Person> people = new ArrayList<>();

        try {

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM person");

            while (resultSet.next()) {

                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                int age = resultSet.getInt(3);
                String email = resultSet.getString(4);

                people.add(new Person(id, name, age, email));

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return people;

    }

    public Person show(int id) {

        try {

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM person WHERE id=?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                String name = resultSet.getString(2);
                int age = resultSet.getInt(3);
                String email = resultSet.getString(4);

                Person person = new Person(id, name, age, email);

                return person;

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;

    }

    public void save(Person person) {

        try {

            PreparedStatement statement = connection.prepareStatement("INSERT INTO person(name, age, email) VALUES(?, ?, ?)");
            statement.setString(1, person.getName());
            statement.setInt(2, person.getAge());
            statement.setString(3, person.getEmail());

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void update(int id, Person person) {

        try {

            PreparedStatement statement = connection.prepareStatement("UPDATE person SET name=?, age=?, email=? WHERE id=?");
            statement.setString(1, person.getName());
            statement.setInt(2, person.getAge());
            statement.setString(3, person.getEmail());
            statement.setInt(4, id);

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void delete(int id) {

        try {

            PreparedStatement statement = connection.prepareStatement("DELETE FROM person WHERE id=?");
            statement.setInt(1, id);

            statement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}