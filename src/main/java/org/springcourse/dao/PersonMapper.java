package org.springcourse.dao;

import org.springcourse.models.Person;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonMapper implements RowMapper<Person> {

    @Override
    public Person mapRow(ResultSet rs, int rowNum) throws SQLException {

        int id = rs.getInt(1);
        String name = rs.getString(2);
        int age = rs.getInt(3);
        String email = rs.getString(4);

        Person person = new Person(id, name, age, email);

        return person;

    }
}
