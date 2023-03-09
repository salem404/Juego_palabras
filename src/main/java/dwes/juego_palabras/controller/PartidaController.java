package dwes.juego_palabras.controller;

import dwes.juego_palabras.dto.*;
import dwes.juego_palabras.dto.converter.PartidaDTOConverter;
import dwes.juego_palabras.error.JuegoNotFoundException;
import dwes.juego_palabras.error.JugadorNotFoundException;
import dwes.juego_palabras.error.PartidaNotFoundException;
import dwes.juego_palabras.model.*;
import dwes.juego_palabras.repos.JuegoRepository;
import dwes.juego_palabras.repos.JugadorRepository;
import dwes.juego_palabras.repos.PartidaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class PartidaController {
    private final PartidaRepository repositorioPartidas;
    private final JugadorRepository repositorioJugadores;
    private final JuegoRepository repositorioJuegos;
    private final PartidaDTOConverter partidaDTOConverter;

    /**
     * Obtención de todos las partidas
     *
     * @return Lista de partidas
     */
    @GetMapping("/partidas")
    public ResponseEntity<List<?>> getAllJugadores(){
        List<Partida> partidas = repositorioPartidas.findAll();
        if(partidas.isEmpty()){
            return ResponseEntity.noContent().build();
        } else {
            List<PartidaDTO> dtoList =
                    partidas.stream().map(partidaDTOConverter::convertToDTO).collect(Collectors.toList());
            return ResponseEntity.ok(partidas);
        }
    }

    /**
     * Obtención de una partida según su id
     *
     * @param id Identificador de la partida
     * @return Partida | Error 404
     */
    @GetMapping("/partida/{id}")
    public Partida getPartida(@PathVariable Long id){
        return repositorioPartidas.findById(id)
                .orElseThrow(() -> new PartidaNotFoundException(id));
    }

    /**
     * Creación de una partida nueva
     *
     * @param nueva Datos de la nueva partida
     * @return Partida nueva
     */
    @PostMapping("/partidas")
    public ResponseEntity<?> newPartida(@RequestBody CrearPartidaDTO nueva){
        Partida partida = new Partida();
        Jugador jugador = repositorioJugadores.findById(nueva.getId_jugador()).orElseThrow(()->
                new JugadorNotFoundException(nueva.getId_jugador()));
        Juego juego = repositorioJuegos.findById(nueva.getId_juego()).orElseThrow(()->
                new JuegoNotFoundException(nueva.getId_juego()));;
        partida.setFecha_hora(LocalDateTime.now());
        partida.setIntentos(nueva.getIntentos());
        partida.setPuntos(nueva.getPuntos());
        partida.setJugador(jugador);
        partida.setJuego(juego);
        partida.setDificultad(Dificultad.valueOf(nueva.getDificultad()));
        partida.setIntentos(nueva.getIntentos());

        return ResponseEntity.status(HttpStatus.CREATED).body(repositorioPartidas.save(partida));
    }

    /**
     * Actualización de una partida según su id
     *
     * @param actualizacion Nuevos datos
     * @param id Identificación correspondiente
     * @return Partida editada | Error 404
     */
    @PutMapping("/partida/{id}")
    public Partida updatePartida(@RequestBody ModificarPartidaDTO actualizacion, @PathVariable Long id){
        final Jugador jugador = repositorioJugadores.findById(actualizacion.getId_jugador()).orElseThrow(()->
                new JugadorNotFoundException(actualizacion.getId_jugador()));
        final Juego juego = repositorioJuegos.findById(actualizacion.getId_juego()).orElseThrow(()->
                new JuegoNotFoundException(actualizacion.getId_juego()));
        return repositorioPartidas.findById(id).map(p -> {
            p.setIntentos(actualizacion.getIntentos());
            p.setDificultad(Dificultad.valueOf(actualizacion.getDificultad()));
            p.setPuntos(actualizacion.getPuntos());
            p.setJugador(jugador);
            p.setJuego(juego);
            return repositorioPartidas.save(p);
        }).orElseThrow(() -> new PartidaNotFoundException(id));
    }

    /**
     * Eliminación de una partida según su id
     *
     * @param id Identificador de la partida
     * @return Código 204
     */
    @DeleteMapping("/partida/{id}")
    public ResponseEntity<Void> deletePartidaById(@PathVariable Long id){
        Partida partida = repositorioPartidas.findById(id).orElseThrow(() -> new PartidaNotFoundException(id));
        repositorioPartidas.delete(partida);
        return ResponseEntity.noContent().build();
    }

}
