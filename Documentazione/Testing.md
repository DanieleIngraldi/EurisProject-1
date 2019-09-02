# Spring Testing #
Questa sezione (ed la maggior parte del seguente capitolo) copre l'integrazione dei test per le applicazioni Spring. Include i seguenti argomenti:
#
<b>3.1 Panoramica </b><br>
 E' importante riuscire ad eseguire alcune integrazioni di test senza effettuare il deploy della tua applicazione server o connettersi ad altre infrastrutture enterprise. Fare questo di permette di testare parti come:<br>
    <li> La corretta connessione del contesto del tuo Spring IoC container
    <li> Accesso ai dati usando JDBC o un tool ORM. Questo può includere la correttezza delle righe SQL, le query Hibernate, mapping delle entità JPA e così via.<br><br>
Il Framework Spring fornisce supporto di prima classe per l'integrazione dei test nel modulo spring-test. Il nome dell'attuale file JAR deve includere la versione di rilascio e può inoltre essere nel form org.springframework.test, dipendentemente da dove lo recuperi.
 Questa libreria include il package org.springframework.test, che contiene preziose classi per l'integrazione dei test con uno Spring container. Questi test non fanno riferimento ad applicazioni server o altri ambienti di deployment. Questi test sono molto più lenti ad eseguire degli unit test, ma molto più veloci rispetto agli equivalenti test Selenium o test remoti che fanno affidamento ad applicazioni server.
 Unità ed integrazione del supporto dei test sono fornite nella forma dalle annotazioni Sprin TestContext Framework. Il TextContext framework è ignaro del framework di test in uso, il che permette la strumentazione dei test in vari ambienti come JUnit, TestNG ed altri.
 #
<b>3.2 Gli obiettivi dell'integrazione dei Test </b> <br>
 L'integrazione del supporto dei test ha i seguenti obiettivi:
* 3.2.1 Gestione del contesto e caching
 Lo Spring TestContext Framework fornisce il caricamento consistente di istanze di Spring ApplicationContext e istanze di WebApplicationContext così come del caching di questi contesti. Il supporto per il caching del caricamento del contesto è importante, perché il tempo di avvio può essere un problema - non per colpa di Spring, ma perché gli oggetti istanziati dallo Spring container hanno bisogno di tempo per essere istanziati. 
 Le classi di test generalmente dichiarano un array di locazioni di risorse per XML o Groovy configuration metadata - spesso nel classpath - o un array di classi annotate usate per configurare l'applicazione
Queste locazioni o classi sono lo stesso o simili a quanto specificato in web.xml o altri file di configurazione per il production deploy.
 Di default, una volta caricato, l'ApplicationContext configurato, viene riutilizzato per ogni test. Così il costo di setup è solamente uno per ogni suite di test e l'esecuzione risulta più veloce. In questo contesto il termine "test suite" indica tutti i test eseguiti nella stessa JVM. Nel raro caso in cui un test corrompa il contesto dell'applicazione e neccessita di un reload, il framework TestContext può essere configurato affinché ricarichi la configurazione e ricostruisca il contesto dell'applicaizone prima di eseguire il test successivo. 
* 3.2.2 Dependency Injection delle Fixture di test
 Quando il framework TestContext carica il contesto dell'applicazione, può opzionalmente configurare istanze delle tue classi di test usando la Dependency Injection. Questo fornisce un meccanismo conveniente per fare il setup di fixture di test usando bean preconfigurati dal contesto della tua applicazione. Un grosso beneficio è quello che puoi riutilizzare contesti dell'applicazione in diversi scenari di test, evitando di dover duplicare complessi setup di fixture di test per casi indivuduali. 
* 3.2.3 Gestione delle transazioni
 Un problema comune dei test con accesso ad un database reale è l'effetto sullo stato della persistenza. Anche quando usiamo un database di sviluppo, i cambiamenti sullo stato possono influenzare diversi test. Inoltre molte operazioni - come l'inserimento o la modifica di dati persistenti - non possono essere eseguiti (o verificati) al di fuori della transazione. 
 Il framework TestContext indirizza questo problema. Di default, il framework crea e riavvolge una transazione per ogni test. Puoi scrivere codice che presuppone l'esistenza di una transazione. Se richiami oggetti transazionalmente proxati, si comportano correttamente, in accordo alla semantica della transazione. In aggiunta se un metodo di test elimina il contenuto di una tabella, una volta terminato viene eseguito un roll-back in modo da riportare il DB allo stato originale per il prossimo test da eseguire. Il supporto alle transazioni è fornito ai test dall'uso del bean PlatformTransactionalManager definito nell'application context del test. 
 Se vuoi che una transazione effettui un commit (inusuale ma occasionalmente utile nel caso in cui si voglia che un test popoli o modifichi il DB) si può dire al framework TestContext di effettuare un commit invece che un roll-back usando l'annotazione @Commit.
* 3.2.4 Classi di supporto per l'integrazione dei test
    * Lo Spring TestContext Framework fornisce diverse classi di supporto astratte che semplificano la scrittura di test di integrazione. Queste classi di test di base forniscono agganci ben definiti all'interno del framework così come utili istanze di variabili e metodi, che permettono l'accesso a:
    * L'ApplicationContex, per effettuare esplicite ricerche sui bean oppure per testare lo stato del contesto nel suo intero. 
    * Un JdbcTemplate, per eseguire comandi SQL per richieste al DB. Puoi usare queste query per confermare lo stato del DB prima o dopo dell'esecuzione del codice relativo al DB e Spring garantisce che queste query vengano eseguite nello scope della transazione. Usato insieme ad un tool ORM, assicurati di evitare i falsi positivi.
 In aggiunta potresti voler creare una tua superclasse personale che copra l'applicazione con variabili d'istanza e metodi specifici per il tuo progetto.
 #
<b>3.3 Supporto ai test JDBC </b><br>
 Il package org.springframework.test.jdbc contiene JdbcTestUtils che è una collezione di funzioni di utilità relative a JDBC intese a semplificare gli scenari di test di database standard. Nello specifico JdbcTestUtils fornisce i seguenti metodi statici:
* countRowsInTable(..); Conta il numero di righe nella tabella.
* countRowsInTableWhere(..) Conta il numero di righe nella tabella usando la clausola WHERE.
* deleteFromTables(..) Elimina tutte le righe da una specifica tabella.
* deleteFromTablesWhere(..) Elimina tutte le righe da una specifica tabella usando la clausola WHERE.
* dropTables(..) Elimina la tabella specificata.
#

<b>3.4 Annotazioni </b><br>
Il Framework Spring fornisce il seguente set di annotazioni specifiche per Spring che puoi usare nelle unità ed integrazioni di test in congiunzione con il TestContext framework. Vedi iil relativo javadoc per ulteriore documentazione, inclusi i valori di default, alias di attributi e altri dettagli.<br>
Le annotazioni Spring includono le seguenti:
* @BootstrapWith<br>
@BootstrapWith è un'annotazione per classi che puoi usare per configurare come viene fatto partire il Framework Spring TestContext. Nello specifico puoi usare @BootstrapWith per specificare un TestContextBootstrapper personalizzato. Vedere la sezione [bootstrapping the TestContext framework](https%3A%2F%2Fdocs.spring.io%2Fspring%2Fdocs%2Fcurrent%2Fspring-framework-reference%2Ftesting.html#testcontext-bootstrapping)
* @ContextConfiguration<br>
@ContextConfiguration definisce metadata a livello di classe usati per determinare come caricare e configurare un ApplicationContext per l'integrazione di test. Nello specifico @ContextConfiguration dichiara le risorse dell'application context <em>locations</em> o le <em>classi</em> annotate usate per caricare il contesto.<br>
Le locazioni di risorse sono tipicamente file di configurazione XML o script Groovy posizionati nel classpath, mentre le classi annotate sono tipicamente classi @Configuration. Comunque le resource location possono riferirsi a file e script nel filesystem mentre le classi annotate possono essere classi componente e così via.
* WebAppConfiguration<br>
@WebAppConfiguration è un'annotazione di classe che si può usare per dichiarare che l'ApplicationContext caricato per l'integrazione del test debba essere una WebApplicationContext. La mera presenza di questa annotazione assicura che venga caricata una WebApplicationContext per il test, usando il valore di default "file:src/main/webapp". come path per la radice della web application. Il path della risorsa di base viene usata dietro le scene per creare un MockServletContext, che serve come ServletContext per il test WebApplicationContext.
* @ContextHierarchy<br>
@ContextHierarchy è un annotazione di classe usata per definire la gerarchia di istanze di ApplicationContext per l'integrazione dei test. @ContentHierarchy dovrebbe essere dichiarata con una lista di uno o più istanze di @ContextConfiguration, ciascuna delle quali definiscce il livello nella gerarchia dei contesti. Se si ha bisogno di unire o sovrascrivere la configurazione per un dato livello di gerarchia di contesto all'interno di una classe gerarchica, devi esplicitare il nome di quel livello fornendo lo stesso valore dell'attributo name nel @ContextConfiguration ad ogni livello nella classe gerarchica.
* @ActiveProfiles<br>
@ActiveProfiles è un annotazione di classe usata per dichiarare quale profilo di definizione dei bean deve essere attivo quando viene caricato un ApplicationContext per l'integrazione del test.
*TestPropertySource è un annotazione di classe che puoi usare per configurare la locazione del file di proprietà e delle proprietà in linea per essere aggiunti al set di PropertySource nell'Enviroment per un ApplicationContext caricato per un integrazione di test. Le sorgenti delle proprietà hanno precedenza più alta rispetto a quelli caricati dal sistema operativo o dalle proprietà di Java cosi come delle sorgenti delle proprietà aggiunte dalle applicazioni dichiarative attraverso @PropertySource o programmaticamente. Così le sorgenti delle proprietà dei test possono essere usati per sovrascrivere le proprietà definite dal sistema e dalle proprietà sorgenti dell'applicazione.
* @DirtiesContext<br>
@DirtiesContext indica che l'ApplicationContext di Spring sottointeso, è stato sporcato durante l'esecuzione del test e dovrebbe essere chiuso. Quando il contesto di un applicazione viene marchiato come Dirty, viene rimosso dalla cache del framework di test e chiuso. Come conseguenza, il contenitore di Spring sottointeso viene ricostruito per tutti i successivi test che richiedono un contesto con gli stessi metadata di configurazione. Si può usare @DirtiesContext assieme ad annotazione di classe o di metodo all'interno della stessa classe o gerarchia di classe. In questo scenario l'ApplicationContext viene marchiato come dirty prima e dopo qualsiasi metodo annotato cosi come prima e dopo qualsiasi classe di test, a seconda di come sono configurati methodMode e classMode.
* @Commit<br>
@Commit indica che le transazioni effettuate in un metodo di un test transazionale, devono essere committate dopo che il metodo del test è completato. Si può utilizzare @Commit come alternativa a @Rollback(false) per essere più precisi nel definire l'intento del codice. Analogamente a @Rollback, @Commit può essere dichiarata come annotazione di classe o di metodo.
* @Rollback<br>
@Rollback indica che le transazioni dei test con metodi che lavorano sulle transazioni, devono essere riavvolte, dopo che il metodo ha terminato. Se impostato con il valore true (valore di default), la transazione viene riavvolta, altrimenti abbiamo lo stesso comportamento di @Commit. Quando dichiarata come annotazione per classe, definisce il comportamento per tutti i metodi al suo interno. Quando dichiarata per metodo, definisce il comportamento per quel specifico metodo, sovrascrivendo eventuali dichiarazioni per la classe che lo contiene.
* @BeforeTransaction<br>
@BeforeTransaction indica che il metodo void annotato deve essere eseguito prima che vengano eseguite transazioni, per i metodi che hanno la configurazione di esecuzione con l'annotazione @Transactional. Dal Framework Spring 4.3 @BeforeTransaction non richiede che i metodi siano dichiarti pubblici e possono essere dichiarati sui metodi di default delle interfacce basate su Java 8.
* @AfterTransaction<br>
@AfterTransaction indica che il metodo annotato void deve essere eseguito quando la transazione è terminata per tutti i metodi di test annotati come @Transactional. Dal Framework Spring 4.3 @AfterTransaction non richiede che i metodi siano dichiarti pubblici e possono essere dichiarati sui metodi di default delle interfacce basate su Java 8.
* @Sql<br>
@Sql è usata per annotare un classe di test o un metodo per configurare gli script SQL da eseguire su un dato DB durante i test.
* @SqlConfig<br>
@Sqlconfig definisce i metadata usati per determinare come parsare 