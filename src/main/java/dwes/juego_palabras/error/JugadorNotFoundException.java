package dwes.juego_palabras.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class JugadorNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 43876691117560211L;
    public JugadorNotFoundException (Long id){
        super("Jugador inexistente. ID=" + id);
    }
}
