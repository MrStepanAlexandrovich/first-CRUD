package org.mrstepan.library.controllers;

import jakarta.jws.WebParam;
import org.mrstepan.library.DAO.BooksDAO;
import org.mrstepan.library.DAO.PeopleDAO;
import org.mrstepan.library.models.Book;
import org.mrstepan.library.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books")
public class BooksController {
    private final PeopleDAO peopleDAO;
    private BooksDAO booksDAO;

    @Autowired
    public BooksController(BooksDAO booksDAO, PeopleDAO peopleDAO) {
        this.booksDAO = booksDAO;
        this.peopleDAO = peopleDAO;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("books", booksDAO.showList());
        return "books";
    }

    @GetMapping("/new")
    public String addNewBook(Model model) {
        Book book = new Book();
        model.addAttribute("newBook", book);
        return "newBook";
    }

    @GetMapping("/{id}")
    public String showBook(@PathVariable int id,
                           Model model) {
        model.addAttribute("book", booksDAO.showBook(id));
        model.addAttribute("people", peopleDAO.showList());
        model.addAttribute("newPerson", new Person());
        model.addAttribute("bookIsFree", (booksDAO.showBook(id).getPersonId()) == null);
        model.addAttribute("person", peopleDAO.show(id));
        return "book";
    }

    @PostMapping
    public String saveBook(@ModelAttribute("newBook") Book book) {
        booksDAO.add(book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable int id) {
        booksDAO.delete(id);
        return "redirect:/books";
    }

    @PostMapping("/{id}")
    public String assignBook(@PathVariable int id,
                             @ModelAttribute("person") Person person) {
        booksDAO.assignBookToAPerson(person.getId(), id);
        return "redirect:/books";
    }
}
