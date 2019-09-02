package com.euris.mg.MGApp;

import com.euris.mg.MGApp.models.Person;
import com.euris.mg.MGApp.repositories.PersonsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;
import java.time.LocalDate;

/*
When BeanPostProcessor Methods are Called
Typically spring’s DI container does following things to create a bean, you request for:

Create the bean instance either by a constructor or by a factory method
Set the values and bean references to the bean properties
Call the setter methods defined in the all the aware interfaces
Pass the bean instance to the postProcessBeforeInitialization() method of each bean post processor
Call the initialization callback methods
Pass the bean instance to the postProcessAfterInitialization() method of each bean post processor
The bean is ready to be used
When the container is shut down, call the destruction callback methods
How to Register BeanPostProcessor
To register a bean post processor in your application context file, declare an instance of the processor in the bean configuration file (e.g. beans.xml), and then it will get registered automatically.

Reference: https://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/beans/factory/config/BeanPostProcessor.html

 */


@SpringBootApplication
public class MgAppApplication implements BeanPostProcessor {
	public static void main(String[] args) {
		SpringApplication.run(MgAppApplication.class, args);
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException
	{
		System.out.println("Called postProcessBeforeInitialization() for :" + beanName);
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException
	{
		System.out.println("Called postProcessAfterInitialization() for :" + beanName);
		return bean;
	}

//	@Bean
//	public CommandLineRunner demo(PersonsRepository repository) {
//		return (args) -> {
//			// save a couple of Persons
//			repository.save(new Person("FAKECFMOCK","Jack", "Bauer", LocalDate.of(1980, 01, 30)));
//			repository.save(new Person("FAKECFMOCK","Chloe", "O'Brian", LocalDate.of(1987, 05, 15)));
//			repository.save(new Person("FAKECFMOCK","Kim", "Bauer", LocalDate.of(1990, 02, 14)));
//			repository.save(new Person("FAKECFMOCK","David", "Palmer", LocalDate.of(1955, 07, 06)));
//			repository.save(new Person("FAKECFMOCK","Michelle", "Dessler", LocalDate.of(1954, 04, 23)));
//		};
//	}

	@Autowired
	private PersonsRepository _repo;

	/*
		@PostConstruct

		This annotation is a convenient way of declaring a postProcessAfterInitialization method,
		and Spring becomes aware of it when you either by registerCommonAnnotationBeanPostProcessor
		or specify the <context:annotation-config /> in bean configuration file.
		Whether the @PostConstruct method will execute before or after any other postProcessAfterInitialization
		depends on the order property.
		You can configure multiple BeanPostProcessor instances, and you can control
		the order in which these BeanPostProcessors execute by setting the order property.

		https://docs.spring.io/spring/docs/3.1.x/spring-framework-reference/htmlsingle/spring-framework-reference.html#beans-factory-lifecycle-combined-effects
	 */
	@PostConstruct
	public void insertMockDatas(){
			this._repo.save(new Person("FAKECFMOCK","Jack", "Bauer", LocalDate.of(1980, 01, 30)));
			this._repo.save(new Person("FAKECFMOCK","Chloe", "O'Brian", LocalDate.of(1987, 05, 15)));
			this._repo.save(new Person("FAKECFMOCK","Kim", "Bauer", LocalDate.of(1990, 02, 14)));
			this._repo.save(new Person("FAKECFMOCK","David", "Palmer", LocalDate.of(1955, 07, 06)));
			this._repo.save(new Person("FAKECFMOCK","Michelle", "Dessler", LocalDate.of(1954, 04, 23)));
	}
}
