# Spring Boot con Database H2 #

## Panoramica ##
<p> In questo tutorial, esploreremo l'uso di H2 con Spring Boot.<br>
 Come altri DB, c'è pieno supporto intrinseco nell'ecosistema di Spring Boot </p>

## Dipendenze ##
<p> Iniziamo con le dipendenze <em> h2 </em> e <em> spring-boot-starter-data-jpa </em></p>
<code>
<pre>
&lt;dependency> <br>
	&lt;groupId>org.springframework.boot</groupId> <br>
	&lt;artifactId>spring-boot-starter-data-jpa</artifactId> <br>
	&lt;version>2.1.4.RELEASE</version> <br>
&lt;/dependency> <br>
&lt;dependency> <br>
	&lt;groupId>com.h2database</groupId> <br>
	&lt;artifactId>h2</artifactId> <br>
	&lt;scope>runtime</scope> <br>
	&lt;version>1.4.199</version> <br>
&lt;/dependency>
</pre>
</code>

## Configurazione del DB ##
<p> Di default, Spring Boot configura l'applicazione con una connessione ad uno store in memory </p>
<p> con username "sa" e password vuota. In ogni caso, possiamo cambiare questi parametri aggiungendo</p>
<p> proprietà al file <em> application.properties </em>
<code>
<pre>
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
</pre>
</code>

<p> Per come è disegnato, il database in-memory è volatire e i dati saranno persi quando riavviamo l'applicazione </p>
<p> Possiamo cambiare questo comportamento usando uno storage basato su file. Per fare questo dobbiamo aggiornare </p>
<p> il file <em> spring.datasource.url </em><br>
<code><pre>spring.datasource.url=jdbc:h2:file:/data/demo</pre></code>

<b> Il percorso ad esempio <em> /data/demo </em> deve essere assoluto </b>

## Operazioni con il DB ##
<p> Portare a termine le operazioni CRUD con H2 all'interno di Spring Boot è lo stesso che con altri DB SQL e il </p>
<p> nostro tutorial nella serie <a href=https://www.baeldung.com/persistence-with-spring-series>Spring Persistence</a> </p>
<p> fa un buon lavoro nel coprire questo caso.</p>
<p> Nel frattempo, aggiungiamo un file <em> data.sql </em> all'interno della cartella <em> src/main/resources </em>
<code>
<pre>
DROP TABLE IF EXISTS billionaires; <br>
  <br>
CREATE TABLE billionaires ( <br>
  id INT AUTO_INCREMENT  PRIMARY KEY, <br>
  first_name VARCHAR(250) NOT NULL, <br>
  last_name VARCHAR(250) NOT NULL, <br>
  career VARCHAR(250) DEFAULT NULL <br>
); <br>
  <br>
INSERT INTO billionaires (first_name, last_name, career) VALUES <br>
  ('Aliko', 'Dangote', 'Billionaire Industrialist'), <br>
  ('Bill', 'Gates', 'Billionaire Tech Entrepreneur'), <br>
  ('Folrunsho', 'Alakija', 'Billionaire Oil Magnate'); <br>
</pre>
</code>

<p> Spring Boot recupererà automaticamente il file <em> data.sql </em> e lo lancerà sul nostro DB H2 durante lo startup </p>
<p> della nostra applicazione. Questo è un buon modo di seminare il nostro DB  per testing o altri scopi. </p>

## Accesso alla console H2 ##
<p> Il DB H2 ha già internamente una GUI console per navigare nel contenuto di un Db e per lanciare query SQL. Di default, </p>
<p> la console H2 non è abilitata in Spring. Per abilitarla, dobbiamo aggiungere le seguenti proprietà al file <em> application.properties </em>

<code>
<pre>
spring.h2.console.enabled=true <br>
</pre>
</code>

<p> Quindi, dopo aver lanciato l'applicazione, possiamo navigare verso <em> http://localhost:8080/h2-console </em> che ci presenterà </p>
<p> una pagina di login. In questa pagina, useremo le credenziali precedentemente definite nel file <em> application.properties </em>
<p> Una volta connessi, vedremo una pagina web comprensiva delle tabelle sul lato sinistro e di una textbox per le query SQL </p>
<p> La console ha una funzione di autocompletamento per le keyword SQL. Il fatto che la console sia leggera la rende maneggevole per </p>
<p> ispezionare visivamente il DB oppure per eseguire SQL crudo direttamente. </p>
<p> Andando avanti, possiamo configurare la console per specificare le seguenti proprietà nel <em> application.properties </em> con i valori desiderati </p>
<code>
<pre>
spring.h2.console.path=/h2-console <br>
spring.h2.console.settings.trace=false <br>
spring.h2.console.settings.web-allow-others=false <br>
</pre>
</code>

<p> Con lo snippet riportato, settiamo il path della console a <em> /h2-console </em> relativa all'indirizzo e la porta della nostra applicazione che gira. </p>
<p> Quindi se la nostra applicazione gira su <em> http://localhsot:9001 </em> la nostra console sarà disponibile all'indirizzo <em> http://localhost:9001/h2-console </em>
<p> Inoltre settiamo <em> spring.h2.console.setting.trace </em> a <em> false </em> per prevenire il trace dell'output e possiamo disabilitare l'accesso remoto </p>
<p> settando <em> spring.h2.console.settings.web-allow-others </em> a <em> false </em>

