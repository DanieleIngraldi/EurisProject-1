package exception;

public class ModuleNotFoundException extends RuntimeException {

    public ModuleNotFoundException(String message) {
        super("modulo non trovato!");
    }
}
