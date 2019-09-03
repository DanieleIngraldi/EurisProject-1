package repositories;

import model.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ModuleRepository extends JpaRepository<Module, Long> {
    @Repository
    public interface PersonsRepository extends JpaRepository<Module, Long> {
        List<Module> findByName(String name);

        List<Module> findBySurname(String surname);

        List<Module> findByAge(int age);
    }
}
