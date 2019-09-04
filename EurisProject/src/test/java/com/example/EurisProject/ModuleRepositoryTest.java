package com.example.EurisProject;

import com.example.EurisProject.repositories.ModuleRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;
import java.sql.Timestamp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith( SpringRunner.class )
@DataJpaTest
class ModuleRepositoryTest {

    @Autowired(required = false)
    private ModuleRepository repo;

    @Test
    void findByName() {
        Module module = new Module(1L, "Jason", "Statham", Date.valueOf("1967-07-26"), Timestamp.valueOf("2019-09-02 16:57:47.361"), 52 , 'C');
        repo.save(module);
        Module module2 = repo.findByName("admin").get(0);
        assertNotNull(module);
        assertEquals(module2.getName(), module.getName());

    }

    @Test
    void findBySurname() {
    }

    @Test
    void findByAge() {
    }
}