package com.example.EurisProject;

import model.Module;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = EurisProjectApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class EurisProjectApplicationTests {


    @Autowired
    TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    private String getRootUrl() {
        return "http://localhost:" + port;
    }

    @Test
    public void getAllModules() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/api/module",
                HttpMethod.GET, entity, String.class);
        assertNotNull(response);
    }

    @Test
    public void testGetModuleById() {
        Module module = restTemplate.getForObject(getRootUrl() + "/api/module", Module.class);
        System.out.println(module.getName());
        assertNotNull(module);
    }

    @Test
    public void testCreateModule() {
        Module module = new Module();
        module.setName("Daniele");
        module.setSurname("Ingraldi");
        ResponseEntity<Module> postResponse = restTemplate.postForEntity(getRootUrl() + "/api/module", module, Module.class);
        assertNotNull(module);
        assertNotNull(postResponse.getBody());
    }

    @Test
    public void testUpdateModule() {
        int id = 1;
        Module module = restTemplate.getForObject(getRootUrl() + "/api/module" + id, Module.class);
        module.setName("Matthew");
        module.setSurname("Salveenee");
        restTemplate.put(getRootUrl() + "/api/module" + id, module);
        Module updatedModule = restTemplate.getForObject(getRootUrl() + "/api/module" + id, Module.class);
        assertNotNull(updatedModule);
    }

    @Test
    public void testDeleteModule() {
        int id = 2;
        Module module = restTemplate.getForObject(getRootUrl() + "api/module" + id, Module.class);
        assertNotNull(module);
        restTemplate.delete(getRootUrl() + "api/module" + id);
        try {
            module = restTemplate.getForObject(getRootUrl() + "api/module" + id, Module.class);
        } catch (HttpClientErrorException e) {
            assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
        }
    }


}
