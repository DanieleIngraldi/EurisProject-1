# TESTING THE WEB LAYER #
## @SpringBootApplication ##
<p> Questa annotazione aggiunge le seguenti: 
<li> @Configuration: tagga la classe come sorgente della definizione del Bean per il contesto dell'applicazione
<li> @EnableAutoConfiguration: dice a Spring Boot di partire aggiungendo beans basati sulle impostazioni del classpath, altri bean e varie proprietà
<li> @EnableWebMvc: Spring Boot lo aggiunge automaticamente quando trova spring-webmvc nel classpath. Segnala l'applicazione come web based e attiva <br>il comportamento delle chiavi come impostando DispatcherServlet
<li> @ComponentScan: dice a Spring di cercare altri componenti, configurazioni e servizi nel package del progetto, permettendo di trovare il suo controller.

<p> Il metodo main() usa lo SpringApplication.run() di Spring Boot per lanciare l'applicazione. 

<p> L'annotazione @SpringBootTest dice a Spring Boot di cercare una classe main di configurazione e di usarla per lanciare il contesto dell'applicazione Spring.

<p> L'annotazione @Autowired viene interpretata da Spring e il controller è iniettato prima che i metodi di test vengano eseguiti. 

<p> @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT) in questo caso RANDOM.PORT viene usato per lanciare il server su una porta casuale (utile per evitare conflitti di utilizzo in fase di test) e inietta la porta su @LocalServerPort.

<p> Un altro tipo di approccio è quello di non lanciare il server ma testare solo il layer al di sotto, lasciando gestire a Spring le richieste http e consegnandole al controller. In questo modo, quasi tutto lo stack viene utilizzato ed il codice sarà chiamato come se stesse processando le richieste HTTP senza però far partire il server. Per fare questo utilizziamo l'annotazione @MockMvc e possiamo chiedere per questa che venga iniettata usando @AutoConfigureMockMvc nel test.

