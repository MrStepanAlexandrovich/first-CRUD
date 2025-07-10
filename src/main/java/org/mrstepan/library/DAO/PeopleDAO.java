package org.mrstepan.library.DAO;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.mrstepan.library.models.Person;

import java.util.List;
import java.util.Optional;

@Component
public class PeopleDAO {
    private JdbcTemplate jdbcTemplate;

    public PeopleDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> showList() {
        return jdbcTemplate.query("SELECT * FROM person", new BeanPropertyRowMapper<>(Person.class));
    }

    public void add(Person person) {
        jdbcTemplate.update("INSERT INTO person(name, birthyear) VALUES(?, ?)", new Object[]{person.getName(), person.getBirthYear()});
    }

    public Person show(int id) {
        return jdbcTemplate.query("SELECT * FROM person WHERE id=?", new Object[]{id}, new PersonMapper())
                .stream()
                .findAny()
                .orElse(null);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM person WHERE id=?", new Object[]{id});
    }
}
