package org.mrstepan.library.DAO;

import org.mrstepan.library.models.Person;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonMapper implements RowMapper<Person> {
    @Override
    public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
        Person person = new Person();

        person.setId(rs.getInt(1));
        person.setName(rs.getString(2));
        person.setBirthYear(rs.getInt(3));

        return person;
    }
}
