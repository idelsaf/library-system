package org.idel.controllers;

import jakarta.validation.Valid;
import org.idel.dao.BookDAO;
import org.idel.dao.UserDAO;
import org.idel.entity.Book;
import org.idel.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BooksController {
    private final BookDAO bookDAO;
    private final UserDAO userDAO;

    public BooksController(BookDAO bookDAO, UserDAO userDAO) {
        this.bookDAO = bookDAO;
        this.userDAO = userDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("books", bookDAO.getAllBooks());
        return "books/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model, @ModelAttribute("user") User user) {
        model.addAttribute("book", bookDAO.getBookById(id));

        Optional<User> owner = bookDAO.getBookOwner(id);

        if (owner.isPresent()) {
            model.addAttribute("owner", owner.get());
        } else {
            model.addAttribute("users", userDAO.getUsers());
        }

        return "books/show";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {
        return "books/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "books/new";
        }

        bookDAO.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", bookDAO.getBookById(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors()) {
            return "books/edit";
        }

        bookDAO.update(id, book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        bookDAO.delete(id);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/assign")
    public String assign(@PathVariable("id") int id, @ModelAttribute("user") User user) {
        bookDAO.assign(id,user);

        return "redirect:/books/" + id;
    }

    @PatchMapping("/{id}/release")
    public String release(@PathVariable("id") int id) {
        bookDAO.release(id);

        return "redirect:/books/" + id;
    }
}
