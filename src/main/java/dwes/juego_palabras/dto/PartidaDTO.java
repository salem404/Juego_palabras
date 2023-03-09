package dwes.juego_palabras.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PartidaDTO {
    private Long id;
    private LocalDateTime fecha_hora;
    private Long intentos;
    private String dificultad;
    private Long puntos;
    private Long id_jugador;
    private Long id_juego;

}