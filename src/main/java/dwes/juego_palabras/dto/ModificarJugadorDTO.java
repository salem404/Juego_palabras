package dwes.juego_palabras.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ModificarJugadorDTO {

    private String nombre;

    private String avatar;

    private Long id_equipo;

    private Long puntos;
}
