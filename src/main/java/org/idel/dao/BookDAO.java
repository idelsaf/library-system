package org.idel.dao;

import org.idel.entity.Book;
import org.idel.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> getAllBooks() {
        return jdbcTemplate.query("SELECT * FROM books", new BeanPropertyRowMapper<>(Book.class));
    }

    public Book getBookById(int id) {
        return jdbcTemplate.query("SELECT * FROM books WHERE id=?", new Object[]{id},
                new BeanPropertyRowMapper<>(Book.class)).stream().findAny().orElse(null);
    }

    public void save(Book book) {
        jdbcTemplate.update("INSERT INTO books(name, author, writing_year) VALUES(?, ?, ?)",
                book.getName(), book.getAuthor(), book.getWritingYear());
    }

    public void update(int id, Book book) {
        jdbcTemplate.update("UPDATE books SET name=?, author=?, writing_year=? WHERE id=?",
                book.getName(), book.getAuthor(), book.getWritingYear(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM books WHERE id=?", id);
    }

    public Optional<User> getBookOwner(int id) {
        String sql = "SELECT u.*" +
                     " FROM books b " +
                     "JOIN users u ON b.id = u.id " +
                     "WHERE b.id = ?";

        return jdbcTemplate.query(sql,
                        new Object[]{id},
                        new BeanPropertyRowMapper(User.class))
                .stream()
                .findAny();
    }

    public void assign(int id, User user) {
        jdbcTemplate.update("UPDATE books SET user_id=? WHERE books.id=?", user.getId(), id);
    }

    public void release(int id) {
        jdbcTemplate.update("UPDATE books SET user_id=NULL WHERE id=?", id);
    }
}
