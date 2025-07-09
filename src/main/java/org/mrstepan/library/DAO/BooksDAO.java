package org.mrstepan.library.DAO;

import org.mrstepan.library.models.Book;
import org.mrstepan.library.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BooksDAO {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public BooksDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> showList() {
       return jdbcTemplate.query("SELECT * FROM book", new BeanPropertyRowMapper<>(Book.class));
    }

    public void add(Book book) {
        jdbcTemplate.update("INSERT INTO book(name, author, release_year) VALUES (?, ?, ?)", new Object[] {book.getName(), book.getAuthor(), book.getReleaseYear()});
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM book WHERE id=?", new Object[]{id});
    }

    public Book showBook(int id) {
        return jdbcTemplate.query("SELECT * FROM book WHERE id=?", new Object[]{id}, new BookMapper())
                .stream()
                .findAny()
                .orElse(null);
    }

    public void assignBookToAPerson(int personId, int bookId) {
        jdbcTemplate.update("UPDATE book SET person_id=? WHERE id=?", new Object[]{personId, bookId});
    }

    public List<Book> showBooksOfPerson(int personId) {
        return jdbcTemplate.query("SELECT * FROM book WHERE person_id=?",
                new Object[]{personId}, new BeanPropertyRowMapper<>(Book.class));
    }
}
