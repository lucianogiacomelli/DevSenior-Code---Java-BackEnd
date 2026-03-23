package arg.mza.dev.lgiacomelli.reservas.Exception.Generics;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String mensaje) {
        super(mensaje);
    }
}
