# Create a WebApp using Maven - Spring Boot and scaffolding Angular

Our goal is to create our first project using Intellij Idea for IDE.

It will be based on maven (avoid using the built-in version that comes with the ide we will use), spring boot and including Angular(scaffolding).

We will ensure that all works as expected doing a basic applicationcontext test (assertj).

## Environment setup

### Java Development Kit

The JDK is available [here](https://www.oracle.com/technetwork/java/javase/downloads/index.html).

Accept the contract and download the package, install as usual.

The `JAVA_HOME` environment variable should be automatically configured by the wizard, if not then proceed to add it manually.

### Maven-cli

First, go to [download](https://maven.apache.org/download.html) section and grab the .zip file

Follow the instructions written [here](https://maven.apache.org/install.html) to set it as an environment variable.

To test if it works, open a command prompt and type `mvn --version`

### Intellij IDEA Community

Go to [this page](https://www.jetbrains.com/idea/download/#section=windows) and download the community executable file.

Install it as usual.

To enforce the IDE to use our preferred Maven version,go to Perferences –> Maven –> Maven home directory.

Insert the path into the field.

### NodeJS (and npm)

Download the reccomended version from [this page](https://nodejs.org/en/) .

Install as usual.

### Angular-cli

From a command prompt, we can use **npm** to quickly install the cli for global usage.

Open a command prompt and type `npm install -g @angular/cli` (internet connection required).

----------

### Create our basic project

A good way to get ready is by creating the base project on Spring Initializr website: [go here](https://start.spring.io/) and compile the form:

- **Project:** Maven Project
- **Language:** Java
- **Sping Boot:** (depends on needs). In this example we are going to use 2.1.7 version
- **Project Metadata:** arbitrary datas
    - Group
    - Artifact
    - (Options)
- **Packaging:** we are going to use Jar packaging    
- **Java:** depends on project. We are going to use version 12
- **Dependencies:** Spring Web Starter

----------


## Work on project - structure

The first thing we need to do, is create two folders on our root project folder:
- **backend** (this will contain the spring-related contents - java classes)
- **frontend** (this will contain the Angular stuff for frontend)

### Backend stuff
Now move the `src` folder from the root to backend directory.
We also need to copy the `pom.xml` from the root to backend directory.

In pom.xml reserved for the Spring Boot module we will leave all required maven dependencies unchanged – the application will look for them here.

However, we will completely alter the content of the `<parent>` element:

to specify which artifact is the parent for this pom we will use the fully qualified artifact name of the parent pom – spring-boot-angular-scaffolding;
to keep the groupId and the version of the module the same as its parent, we will enclose those fields here.
Furthermore, there are some metadata that require to be updated, namely:

`<artifactId>` – we will put backend here,
`<name>` – we will put backend here,
`<description>` – we will put The backend module built with Spring Boot here,
`<properties>` – remove it,
`<packaging>` – remove it.
Thanks to those changes the backend can be later recognised by the parent application as its module.
Leave the rest of the properties unaltered.

This is a sample of how it should looks like

```language
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<parent>
		<groupId>com.euris.mg</groupId>
		<artifactId>MGApp</artifactId>
		<version>0.0.1-SNAPSHOT</version>
	</parent>

	<groupId>com.euris.mg</groupId>
	<artifactId>backend</artifactId>
	<name>backend</name>
	<description>The backend module built with Spring Boot</description>

	<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>

		<dependency>
			<groupId>com.euris.mg</groupId>
			<artifactId>frontend</artifactId>
			<version>${project.version}</version>
			<scope>runtime</scope>
   		</dependency>

		<dependency>
			<groupId>org.assertj</groupId>
			<artifactId>assertj-core</artifactId>
			<!-- use 2.9.1 for Java 7 projects -->
			<version>3.11.1</version>
			<scope>test</scope>
		</dependency>
	</dependencies>

	<build>
		<plugins>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
			</plugin>
		</plugins>
	</build>

</project>

```

### Frontend stuff

In the parent project create the frontend/src/main/ folder for the web application sources – the src and main directories are here to follow the Maven standard directory layout.

Now copy `pom.xml` from the backend directory to the frontend directory.

Update the following elements:

`<artifactId>` – we will put frontend here,
`<name>` – we will putfrontend here,
`<description>` – we will put The frontend module built with Angular here,
`<dependencies>` – remove it,
`<plugins>` – remove the following plugin:

```language
 <plugin>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-maven-plugin</artifactId>
 </plugin>
```
This is a sample of how it should look like

```language
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<parent>
		<groupId>com.euris.mg</groupId>
		<artifactId>MGApp</artifactId>
		<version>0.0.1-SNAPSHOT</version>
	</parent>

	<groupId>com.euris.mg</groupId>
	<artifactId>frontend</artifactId>
	<name>frontend</name>
	<description>The frontend module built with Angular</description>

	<build>
		<plugins>
			<plugin>
				<groupId>com.github.eirslett</groupId>
				<artifactId>frontend-maven-plugin</artifactId>
				<version>1.8.0</version>
				<configuration>
					<workingDirectory>src/main/angular</workingDirectory>
					<nodeVersion>v10.16.0</nodeVersion>
					<npmVersion>6.9.0</npmVersion>
				</configuration>
				<executions>
					<execution>
						<id>install node and npm</id>
						<goals>
							<goal>install-node-and-npm</goal>
						</goals>
					</execution>
					<execution>
						<id>npm install</id>
						<goals>
							<goal>npm</goal>
						</goals>
					</execution>
					<execution>
						<id>npm run build</id>
						<goals>
							<goal>npm</goal>
						</goals>
						<configuration>
							<arguments>run build</arguments>
						</configuration>
					</execution>
				</executions>
			</plugin>
		</plugins>

		<resources>
			<resource>
				<directory>target/frontend</directory>
				<targetPath>static</targetPath>
			</resource>
		</resources>
	</build>

</project>

```

### Tie modules together

The backend and frontend pom.xml files contains the `<parent>` field so the modules know from which project they inherit. Now the main application requires data to identify which submodules should be aggregated during the build. We will provide them by updating the main pom.xml file by adding a new field – `<modules>` – with directories of its modules specified: backend and fronted.

Update the following properties:

`<packaging>` – we will put pom here, <br />
`<dependencies>` – remove it, <br />
`<build>` – remove it. <br />

Finally, we have a multi-module application. The backend module is the Spring Boot project created with Spring Initializr that will implement in our example a restful service, what we need now is to set up an Angular project in the frontend module.

### Angular

Move to the frontend folder from a command prompt, then type `ng new --skip-git angular` to create the angular project.

The cli will ask if you want to add the *routing module*, type y, then the style type (select *CSS*).

Now wait (be patient, it will depends on your internet connection speed and pc specs).

To keep up with the Maven standards we need to alterate the outputPath option for our Angular project, instead of "dist/angular" use "../../../target/frontend" (frontend/src/main/angular/angular.json):

```language
 "projects": {
    "angular": {
      "architect": {
        "build": {
          …
          "options": {
            "outputPath": "../../../target/frontend",
…
```

And in frontend/pom.xml :

```language
 <build>
…
   <resources>
      <resource>
         <directory>target/frontend</directory>
         <targetPath>static</targetPath>
      </resource>
   </resources>
</build>
```

Add the frontend dependency to the Spring Boot backend (backend/pom.xml):

```language
  <dependencies>
…
   <dependency>
      <groupId>groupId</groupId>
      <artifactId>frontend</artifactId>
      <version>${project.version}</version>
      <scope>runtime</scope>
   </dependency>
<dependencies>
```

## Messing with CORS issues on localhost and allow routing to Angular

If you run your app locally and want to separate concerns (run an instance for backend on port 8080 and one for frontend on port 4200) you are going to encounter this error:

```language
No 'Access-Control-Allow-Origin' header is present on the requested resource. Origin 'http://localhost:4200' is therefore not allowed access.
```

Pointing any uri resource different that the root will give a 404 error.

To fix that situations and allow Angular to manage routing requests, create a package named `config` under the root package of the backend.

1. Inside it, create a new class `MvcConfiguration`:

```language
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

import java.io.IOException;

@Configuration
public class MvcConfiguration implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/")
                .resourceChain(true)
                .addResolver(new PathResourceResolver() {
                    @Override
                    protected Resource getResource(String resourcePath, Resource location) throws IOException {
                        Resource requestedResource = location.createRelative(resourcePath);

                        return requestedResource.exists() && requestedResource.isReadable() ? requestedResource
                                : new ClassPathResource("/static/index.html");
                    }
                });
    }
}
```
The "/**" path pattern will be matched by AntPathMatcher to zero or more directories in a path, so this configuration will be applied to all routes in our project. Next, a resolver – created with dependency to PathResourceResolver – will try to find a resource under the given location and all requests that are not handled by Spring Boot will be redirected to index.html allowing Angular to take care of them.

2. Create a class `DevCorsConfiguration`:

```import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@Profile("development")
public class DevCorsConfiguration implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**").allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE");
    }
}

```
Doing this we ensure that the routing from Angular is working (if the uri given is invalid, it will return the index.html page as fallback) and that the backend can serve our restful service using `/api/***` as the template uri. We have allowed all the http methods needed.

----------

## Build the project with Maven

We have 2 options.

1. From command line
    Move to the root directory of the project, then run `mvn clean install`. Wait the process to be finished (it could take a while).

2. Use [frontend-maven-plugin](https://github.com/eirslett/frontend-maven-plugin) to build directly from our IDE.
   This is a sample configuration that need to be added to `frontend/pom.xml`:


```language
<build>
		<plugins>
			<plugin>
				<groupId>com.github.eirslett</groupId>
				<artifactId>frontend-maven-plugin</artifactId>
				<version>1.8.0</version>
				<configuration>
					<workingDirectory>src/main/angular</workingDirectory>
					<nodeVersion>v10.16.0</nodeVersion>
					<npmVersion>6.9.0</npmVersion>
				</configuration>
				<executions>
					<execution>
						<id>install node and npm</id>
						<goals>
							<goal>install-node-and-npm</goal>
						</goals>
					</execution>
					<execution>
						<id>npm install</id>
						<goals>
							<goal>npm</goal>
						</goals>
					</execution>
					<execution>
						<id>npm run build</id>
						<goals>
							<goal>npm</goal>
						</goals>
						<configuration>
							<arguments>run build</arguments>
						</configuration>
					</execution>
				</executions>
			</plugin>
		</plugins>

		<resources>
			<resource>
				<directory>target/frontend</directory>
				<targetPath>static</targetPath>
			</resource>
		</resources>
	</build>

```


   To build the project, look on the right side of the IDE: there is a label for `Maven`. Click to expand the relative menu, then under the `<root>` double click **clean** then **install**

   ----------
   
Independently of the way you choose to (re)build the app, you will see that a folder called `target` have been created both on backend and frontend.

Inside it we have the core of our application:

- backend: compiled classes, maven stuff, **the final jar to be launched**
- frontend: we will see the static webpages that will be served (the datas are compressed as much as possible because we are using the --prod flag in the build process), maven stuff and again a jar file related to frontend.


## Simple tests

### From cli
Open a command prompt, navigate to the root folder of the project and type `mvn test`.

### From the ide
We can use [AssertJ](https://joel-costigliola.github.io/assertj/index.html).

First, ensure to add the dependency to the `pom.xml` of backend. In this case, we will use version 3.11.
```language
<dependency>
   ....
  <groupId>org.assertj</groupId>
  <artifactId>assertj-core</artifactId>
  <!-- use 2.9.1 for Java 7 projects -->
  <version>3.11.1</version>
  <scope>test</scope>
  ....
</dependency>
```

Now create a context bean simple test.
There is a default test class in backend/src/test/java/*yourAppName*/*yourAppNameTests*.java

Sample test:
```
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MgAppApplicationTests {

	@LocalServerPort
	int port;

	@Before
	public void setUp() throws Exception {
		RestAssured.port = port;
	}

	@Autowired
	public ApplicationContext context;

						// GENERIC BEAN TEST
	@Test
	public void contextLoads() {
		assertThat(context).isNotNull();
	}

```
Now debug as usal (JUnit configuration, classpath of backend, tests application class).

### JSON test from restful backend service

We will use `RestAssured` to make it more simple.


Add this dependency to `backend/pom.xml`

```		<dependency>
			<groupId>io.rest-assured</groupId>
			<artifactId>rest-assured</artifactId>
			<version>3.3.0</version>
			<scope>test</scope>
		</dependency>

```

Here are some JSON examples

```language
		// check if rest service works as expected (Json returned)
		@Test
		//@DisplayName("Rest call returns a JSON")
		public void restApiReturnsJson(){
			RestAssured.baseURI = "http://localhost";
			RequestSpecification httpRequest = RestAssured.given();
			// Set HTTP Headers
			httpRequest.header("Content-Type", "application/json");
			Response response = httpRequest.get("/api/getall");
			// Get JSON Representation from Response Body
			assertTrue(response.getContentType().equals("application/json;charset=UTF-8"));
		}

		// check if some values are present in Json returned
		@Test
		//@DisplayName("Search Jack and Chloe in H2 DB")
		public void matchNamesOnJson(){
			get("/api/getall").then().body("name", hasItems("Jack", "Chloe"));
		}
```

### Test REST API

Before implementing the frontend, it's recommended to test the behaviour of the rest controller (http status codes, resources returned, correct functionality).

You can do it easily using the software [postman](https://www.getpostman.com/)

Once installed, you can save you preset-requests to be launched to query the api service deployed.

----------

## Add an in memory database (backend)

If you want to use an on-the-fly solution for storing datas for your webapp, then you should consider [h2 db](https://www.h2database.com/html/main.html)

First of all, add the dependency on `backend/pom.xml`
```language
		<!-- database -->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>
		<dependency>
			<groupId>com.h2database</groupId>
			<artifactId>h2</artifactId>
			<scope>runtime</scope>
		</dependency>
```

Then edit `backend/src/main/resources/application.properties`

```language
###
#   Database Settings
###
spring.datasource.url=jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE;
spring.datasource.platform=h2
spring.datasource.username =sa
spring.datasource.password =
spring.datasource.driverClassName =org.h2.Driver
spring.datasource.initialize=false
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect

###
#   H2 Settings
###
spring.h2.console.enabled=true
spring.h2.console.path=/console
spring.h2.console.settings.trace=false
spring.h2.console.settings.web-allow-others=false

###
#   Hibernate Settings
###
spring.jpa.hibernate.ddl-auto = update
spring.jpa.properties.hibernate.show_sql=true
spring.jpa.properties.hibernate.use_sql_comments=false
spring.jpa.properties.hibernate.format_sql=false
```

Using this configuration, we are telling that a web console will be available to manage the db under `http://localhost:8080/console`.

Ensure that you log into the system using this jdbc url: `jdbc:h2:mem:testdb` instead of the default one. 

The easiest way to implement basic crud operations is declaring an interface that extends the CrudRepository class, that offer us all the basic method implementation:

```language
public interface  PersonsRepository extends CrudRepository<Person, Long> {
}
```
We can also declare inside the repo interface our custom methods:

```language
public interface  PersonsRepository extends CrudRepository<Person, Long> {
    List<Person> findBySurname(String surname);
}
```
The entities doesn't need too much efforts to be translated as tables in our database.

For example if we want to model persons

```language
package com.euris.mg.MGApp.models;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "persons")
@Getter
@Setter
@ToString
public class Person {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String fiscalCode;
    private String name;
    private String surname;
    LocalDate birthDate;

    public Person() {}

    public Person(Long id, String fiscalCode, String name, String surname, LocalDate birthDate) {
        this.id = id;
        this.fiscalCode = fiscalCode;
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
    }

    public Person(String fiscalCode, String name, String surname, LocalDate birthDate) {
        this.fiscalCode = fiscalCode;
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
    }
}
```

Make sure that the constructors method are declared, otherwhise you are going to face errors at runtime execution when calling the api.

Finally, here is a basic rest controller example that returns all rows formatted as json objects
```// First basic test
@RestController
public class MGController {
    private PersonsRepository _repo;

    public MGController(PersonsRepository repo) {
        _repo = repo;
    }

    @RequestMapping(value="/api/getall", method=GET, produces = "application/json")
    public Iterable<Person> getAll(@RequestParam(value="name", required=false) String namePart) throws InterruptedException {
        return _repo.findAll();
    }
}

```
### Mock datas auto insert

We can add a bean that will be called in our application class (where is also the main method with run call).

Here is an example

```language
	@Bean
	public CommandLineRunner demo(PersonsRepository repository) {
		return (args) -> {
			// save a couple of Persons
			repository.save(new Person("FAKECFMOCK","Jack", "Bauer", LocalDate.of(1980, 01, 30)));
			repository.save(new Person("FAKECFMOCK","Chloe", "O'Brian", LocalDate.of(1987, 05, 15)));
			repository.save(new Person("FAKECFMOCK","Kim", "Bauer", LocalDate.of(1990, 02, 14)));
			repository.save(new Person("FAKECFMOCK","David", "Palmer", LocalDate.of(1955, 07, 06)));
			repository.save(new Person("FAKECFMOCK","Michelle", "Dessler", LocalDate.of(1954, 04, 23)));
		};
	}
```
Everytime we launch the app, these persons will be added in the database person's table. 

A second possible way to insert mock datas on our db is creating a file named `data.sql` that needs to be placed in `backend/src/resources` with the usual dialect syntax *INSERT INTO tableName (columns) VALUES (...)*

----------

## Run the application

Open a command prompt, navigate to `backend\target`, then type 

```language
java -jar backend-version.jar
```

This will run the spring boot webserver, and if all is done correctly if you open a web browser and navigate to `http://localhost:8080` you will see the angular default index.html homepage.

Our basic webapp is ready.

![running](http://esempivari.altervista.org/spring_angular_scaffolding.JPG)

![angular](http://esempivari.altervista.org/serving_angular_on_8080.JPG)

----------

## Frontend development

To work easily on the frontend part once the backend features are done (or at least, the necessary) we can serve angular manually pointing the backend api using a `proxy configuration`.

To do that, these are the steps:

1. create a new file named `proxy.conf.json` and place it in the root folder of angular (in our case it will be `frontend/src/main/angular`). This is it's content

		```language
			{
				"/api/*": {
					"target": "http://localhost:8080",
					"secure": false,
					"changeOrigin": true
				}
			}
		```
2. Open a command prompt and, from the root folder written previously, type `ng serve --proxy-config proxy.conf.json`. 
3. Once the build is ready, an instance will run on `http://localhost:4200/<something>`. 
Everytime we change something on the angular side, we can see immediately the effects.
4. Open our IDE Intellij IDEA, make a new configuration (top right section -> edit configurations -> + button -> Application). Configuration:
	- **use classpath of module** backend
	- **main class** select the class that cointains our *SpringApplication.run* bean
	- **JRE** use default (version 12)
   
   	Apply the settings, then launch the backend pressing the "play" button.


Once we have finished our changes on the frontend side, we must compile all the stuffs as usal from Intellij IDEA.
