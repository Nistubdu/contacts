package controller;

import domain.Person;
import org.springframework.web.bind.annotation.*;
import service.PersonService;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/person")
public class PersonController {

    @Resource
    PersonService personService;

    @GetMapping(value = "/list")
    public List<Person> getAll() {
        return personService.findAll();
    }

    @PostMapping(value = "/create")
    public void create(@RequestBody Person person) {
        personService.create(person);
    }

    @PutMapping(value = "/update")
    public void update(@RequestBody Person person) {
        personService.update(person);
    }

    @PutMapping(value = "/executeUpdate")
    public void executeUpdate(@RequestBody Person person) {
        personService.executeUpdate(person);
    }

    @DeleteMapping(value = "/delete")
    public void delete(@RequestBody Person person) {
        personService.delete(person);
    }
}