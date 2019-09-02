package com.example.EurisProject.Repositories;

import com.example.EurisProject.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long> {
    List<Person> findByName(String name);
    List<Person> findBySurname(String surname);
}
