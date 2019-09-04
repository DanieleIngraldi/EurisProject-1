package com.example.EurisProject.controller;

import com.example.EurisProject.model.Module;
import com.example.EurisProject.exception.ModuleNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.example.EurisProject.repositories.ModuleRepository;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/modules")
public class ModuleController {

    @Autowired
    private ModuleRepository moduleRepository;

    @GetMapping("/api/modules")
    public List<Module> retrieveAllModules() {
        return moduleRepository.findAll();
    }

    @GetMapping("/api/modules/{id}")
    public Module retrieveModule(@PathVariable long id) {
        Optional<Module> module = moduleRepository.findById(id);
        if (!module.isPresent())
            throw new ModuleNotFoundException("id: " + id);
        return module.get();

    }

    @DeleteMapping("/api/modules/{id}")
    public void deleteModuleById(@PathVariable long id) {
        moduleRepository.deleteById(id);
    }

    @PostMapping("/api/modules")
    public ResponseEntity<Module> createModule(@RequestBody Module module) {
        Module savedModule = moduleRepository.save(module);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(module.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/api/modules/{id}")
    public ResponseEntity<Module> updateModule(@RequestBody Module module, @PathVariable long id) {
        Optional<Module> moduleOptional = moduleRepository.findById(id);
        if (!moduleOptional.isPresent())
            return ResponseEntity.notFound().build();

        module.setId(id);
        moduleRepository.save(module);

        return ResponseEntity.noContent().build();
    }


}
