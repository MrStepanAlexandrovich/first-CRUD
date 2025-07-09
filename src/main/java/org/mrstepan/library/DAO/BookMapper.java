package org.mrstepan.library.DAO;

import org.mrstepan.library.models.Book;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMapper implements RowMapper<Book> {
    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        Book book = new Book();
        book.setId(rs.getInt(1));
        if (rs.getInt(2) == 0) {
            book.setPersonId(null);
        } else {
            book.setPersonId((rs.getInt(2)));
        }
        book.setName(rs.getString(3));
        book.setAuthor(rs.getString(4));
        book.setReleaseYear(rs.getInt(5));

        return book;
    }
}
