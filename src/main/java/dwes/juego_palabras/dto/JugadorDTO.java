package dwes.juego_palabras.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JugadorDTO {

    private Long id;

    private String nombre;

    private String avatar;

    private Long id_equipo;

    private Long puntos;


}
