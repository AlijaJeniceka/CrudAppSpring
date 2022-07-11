package lv.alija.springcourseCrud.controllers;

import lv.alija.springcourseCrud.DAO.PersonDAO;
import lv.alija.springcourseCrud.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/people")
public class PeopleController {


//    @Autowired
//    private PersonDAO personDAO;

    //or
    private final PersonDAO personDAO;
    @Autowired //can be or can not be
    public PeopleController(PersonDAO personDAO){
        this.personDAO=personDAO;
    }

    @GetMapping()
    public String index(Model model){

        //Received all persons from DAO class un we will show them in html

        model.addAttribute("people", personDAO.index());

        return "people/index";

    }
    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model){
        //We will receive person by concrete id from DAO and we will show this person in html
        model.addAttribute("person", personDAO.show(id));


        return "people/show";

    }

    //@Model Attribute create new object in model, we will not send fields, just create new free object without any dates
    //@ModelAttributes("person") person is the key
    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person){

        return "people/new";
    }
    //@Model Attribute create object, insert dates from the fields and read them and insert it into model with all the dates
    @PostMapping
    public String create(@ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult){

        if(bindingResult.hasErrors()) {
            return "people/new";
        }

        personDAO.save(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id){
        model.addAttribute("person", personDAO.show(id));
        return "people/edit";
    }
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult, @PathVariable("id") int id){
        if(bindingResult.hasErrors()){
            return "people/edit";
        }

        personDAO.update(id, person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        personDAO.delete(id);
        return "redirect:/people";
    }

    }

