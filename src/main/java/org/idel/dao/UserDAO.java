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
public class UserDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<User> getUsers() {
        return jdbcTemplate.query("SELECT * FROM users", new BeanPropertyRowMapper<>(User.class));
    }

    public User getUserById(int id) {
        return jdbcTemplate.query("SELECT * FROM users WHERE id=?", new Object[]{id},
                new BeanPropertyRowMapper<>(User.class)).stream().findAny().orElse(null);
    }

    public void save(User user) {
        jdbcTemplate.update("INSERT INTO users(name, birth_year) VALUES(?, ?)", user.getName(), user.getBirthYear());
    }

    public void update(int id, User user) {
        jdbcTemplate.update("UPDATE users SET name=?, birth_year=? WHERE id=?", user.getName(), user.getBirthYear(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM users WHERE id=?", id);
    }

    public List<Book> getBooksByUserId(int id) {
        return jdbcTemplate.query("SELECT * FROM books WHERE user_id=?", new Object[]{id},
                new BeanPropertyRowMapper<>(Book.class));
    }

    public Optional<User> getUserByName(String name) {
        return jdbcTemplate.query("SELECT * FROM users WHERE name = ?", new Object[]{name},
                new BeanPropertyRowMapper<>(User.class)).stream().findAny();
    }
}
