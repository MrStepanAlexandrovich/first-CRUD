package org.mrstepan.library.controllers;

import org.mrstepan.library.DAO.BooksDAO;
import org.mrstepan.library.DAO.PeopleDAO;
import org.mrstepan.library.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/people")
public class PeopleController {
    private final BooksDAO booksDAO;
    private PeopleDAO peopleDAO;

    @Autowired
    public PeopleController(PeopleDAO peopleDAO, BooksDAO booksDAO) {
        this.peopleDAO = peopleDAO;
        this.booksDAO = booksDAO;
    }

    @GetMapping
    public String people(Model model) {
        model.addAttribute("people", peopleDAO.showList());
        return "people";
    }

    @GetMapping("/{id}")
    public String showPerson(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", peopleDAO.show(id));
        model.addAttribute("books", booksDAO.showBooksOfPerson(id));
        return "person";
    }

    @GetMapping("/new")
    public String addNewPerson(Model model) {
        Person person = new Person();
        model.addAttribute("newPerson", person);
        return "newPerson";
    }

    @PostMapping
    public String save(@ModelAttribute("newPerson") Person person) {
        peopleDAO.add(person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String deletePerson(@PathVariable int id) {
        peopleDAO.delete(id);
        return "redirect:/people";
    }
}
