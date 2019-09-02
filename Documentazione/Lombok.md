# Project Lombok #

<p>Project Lombok è un progetto che si prefigge il compito di ridurre la quantità di codice 
scritto nei progetti Java tramite l'uso di annotazioni.</p>
Project Lombok è capace di iniettare codice immediatamente disponibile allo sviluppatore

## Installazione ##
<p>Scaricando dal sito http://projectlombok.org/ il file JAR, quasto include le API per l'integrazione con l'IDE</p>
<p>Con MAVEN è possibile includere Lombok come dipendenza aggiungendo il seguente codice al file POM</p>
<code>

    <dependencies>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>1.18.8</version>
            <scope>provided</scope>
        </dependency>
    </dependencies>
</code>

## Annotazioni ##

### @Getter & @Setter ###
<p>Generano getter e setter per un campo, seguendo la convenzione per i tipi boolean, usando il metodo isFoo invece che getFoo</p>
<code>
@Getter @Setter private boolean employed = true;<br>
@Setter(AccessLevel.PROTECTED) private String name;
</code><br>

### @NonNull ###
<p> E' utilizzato per indicare la necessità di un controllo sul membro affinché non sia NULL</p>
<p> In questo caso viene lanciata una NullPointerException</p>
<code>
@Getter @Setter @NonNull<br>
private List&lt;Person> members;
</code>

### @ToString ###
<p> Genera l'implementazione del metodo toString()</p>
<code>
    @ToString(callSuper=true,exclude="someExcludedField")<br>
    public class Foo extends Bar {<br>
        private boolean someBoolean = true;<br>
        private String someStringField;<br>
        private float someExcludedField;<br>
    }
	</code>

### @EqualsAndHashCode ###
<p> Genera i metodi equals() e hashCode()</p>
<code>
@EqualsAndHashCode(callSuper=true,exclude={"address","city","state","zip"}) <br> 
public class Person extends SentientBeing { <br>
    enum Gender { Male, Female } <br> 
    @NonNull private String name; <br>
    @NonNull private Gender gender; <br>
    private String ssn; <br>
    private String address; <br>
    private String city; <br>
    private String state; <br>
    private String zip; <br>
}
</code>

### @Data ###
<p>Combina le funzionalità di:</p>
<li> @ToString
<li> @EqualsAndHashCode
<li> @Getter
<li> @Setter </li>
<code>
    @Data(staticConstructor="of") <br>
    public class Company { <br> 
        private final Person founder; <br>
        private String name; <br>
        private List<Person> employees; <br>
    }</code>

### @Cleanup ###
<p> Serve per assicurarsi che le risorse allocate siano rilasciate al termine del loro utilizzo </p>
<code>
    public void testCleanUp() { <br>
        try { <br>
            @Cleanup ByteArrayOutputStream baos = new ByteArrayOutputStream(); <br>
            baos.write(new byte[] {'Y','e','s'}); <br>
            System.out.println(baos.toString()); <br>
        } catch (IOException e) { <br>
            e.printStackTrace(); <br>
        } <br>
    }</code>

### @Synchronized ###
<p> Blocca l'accesso dell'oggetto corrente (this) in caso di multithreading </p>
<code>
    private DateFormat format = new SimpleDateFormat("MM-dd-YYYY"); <br>
      <br>
    @Synchronized <br>
    public String synchronizedFormat(Date date) { <br>
        return format.format(date); <br>
    }</code>

### @SneakyThrows ###
<p> Permette alle eccezioni checked di essere lanciate senza dichiarare la clausola throws </p>
<code>
    @SneakyThrows <br>
    public void testSneakyThrows() { <br>
        throw new IllegalAccessException(); <br>
    }</code>
