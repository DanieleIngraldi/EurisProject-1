package com.euris.dbexample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    /*@Bean
    public CommandLineRunner demo(CustomerRepository repository) {
        return (args) -> {
            // save a couple of customers
            repository.save(new Customer("Mario", "Rossi", "pifferaio"));
            repository.save(new Customer("Paolo", "Rossi", "fratello del pifferaio"));
            repository.save(new Customer("Fabio", "Neri", "idraultico"));
            repository.save(new Customer("Luigi", "Bianchi", "omino bianco"));
            repository.save(new Customer("Wario", "Browser", "villain"));

            // fetch all customers
            log.info("Customers found with findAll():");
            log.info("-------------------------------");
            for (Customer customer : repository.findAll()) {
                log.info(customer.toString());
            }
            log.info("");

            // fetch an individual customer by ID
            repository.findById(1L)
                    .ifPresent(customer -> {
                        log.info("Customer found with findById(1L):");
                        log.info("---------------------------------");
                        log.info(customer.toString());
                        log.info("");
                    });

            //fetch customers by last name
            log.info("Customer found with findByLastName('Rossi'):");
            log.info("--------------------------------------------");
            repository.findByLastName("Rossi").forEach(rossi -> {
                log.info(rossi.toString());
            });
            log.info("");
        };
    }*/
}