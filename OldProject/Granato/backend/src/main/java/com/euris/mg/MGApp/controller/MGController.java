package com.euris.mg.MGApp.controller;

import com.euris.mg.MGApp.models.Person;
import com.euris.mg.MGApp.repositories.PersonsRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.reflect.InvocationTargetException;

import static java.util.stream.Collectors.toList;
import static org.springframework.web.bind.annotation.RequestMethod.*;


@RestController
public class MGController {
    private PersonsRepository _repo;

    public MGController(PersonsRepository repo) {
        _repo = repo;
    }

    // get all rows (persons) present on db
    @RequestMapping(value="/api/getall", method=GET, produces = "application/json")
    public Iterable<Person> getAll() throws InterruptedException {
        return _repo.findAll();
    }

    // get all rows (persons) present on db that matches a passed surname
    @RequestMapping(value="/api/search", method=GET, produces = "application/json")
    public Iterable<Person> search(@RequestParam(value="field", required=true) String field, @RequestParam(value="searchstring", required=false) String searchStringPart) throws InterruptedException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String searchString = searchStringPart != null ? searchStringPart : "";
        switch(field) {
            case "surname": return _repo.findBySurname(searchString);
            case "name":    return _repo.findByName(searchString);
            default:        return null;
        }
    }

    // add a new person
    @RequestMapping(value="/api/newperson", method=POST)
    public ResponseEntity post(@Valid @RequestBody Person viewModel, BindingResult br) throws InterruptedException {
        if (!br.hasErrors()) {
            _repo.save(viewModel);
            return new ResponseEntity(HttpStatus.CREATED);
        } else {
            var errors = br.getAllErrors().stream().map(e -> e.getDefaultMessage()).collect(toList());
            return new ResponseEntity(errors, HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    // delete a person(if present) from table passing the id
    @RequestMapping(value="/api/delete/{id}", method=DELETE)
    public ResponseEntity delete(@PathVariable("id") long id) throws InterruptedException {
        try {
            _repo.deleteById(id);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch(IllegalArgumentException ex) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } catch(EmptyResultDataAccessException ex){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
    //modify an existing person values
    @RequestMapping(value="/api/editperson", method=PUT)
    public ResponseEntity put(@Valid @RequestBody Person viewModel, BindingResult br) throws InterruptedException {
        if (!br.hasErrors()) {
            try {
                _repo.save(viewModel);
                return new ResponseEntity(HttpStatus.NO_CONTENT);
            } catch(IllegalArgumentException ex) {
                return new ResponseEntity(HttpStatus.NOT_FOUND);
            } catch(EmptyResultDataAccessException ex){
                return new ResponseEntity(HttpStatus.NOT_FOUND);
            }
        } else {
            var errors = br.getAllErrors().stream().map(e -> e.getDefaultMessage()).collect(toList());
            return new ResponseEntity(errors, HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
}