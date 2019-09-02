package com.euris.mg.MGApp.repositories;

import com.euris.mg.MGApp.models.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
/* By extending CrudRepository, PersonsRepository inherits several methods for working with Customer
  persistence, including methods for saving, deleting, and finding Customer entities.

  Spring lets exception translation be applied transparently through the @Repository annotation.
  https://docs.spring.io/spring/docs/current/spring-framework-reference/data-access.html#orm-exception-translation
*/
@Repository
public interface  PersonsRepository extends CrudRepository<Person, Long> {
    List<Person> findByName(String name);
    List<Person> findBySurname(String surname);
}
