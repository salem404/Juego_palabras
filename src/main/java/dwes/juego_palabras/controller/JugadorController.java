package dwes.juego_palabras.controller;

import dwes.juego_palabras.dto.JugadorDTO;
import dwes.juego_palabras.dto.converter.JugadorDTOConverter;
import dwes.juego_palabras.error.JugadorNotFoundException;
import dwes.juego_palabras.model.Jugador;
import dwes.juego_palabras.repos.JugadorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class JugadorController {
    private final JugadorRepository repositorioJugadores;

    /**
     * Obtención de todos los jugadores
     *
     * @return Lista de jugadores
     */
    @GetMapping("/jugadores")
    public ResponseEntity<List<?>> getAllJugadores(){
        List<Jugador> jugadores = repositorioJugadores.findAll();
        if(jugadores.isEmpty()){
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(jugadores);
        }
    }

    /**
     * Obtención de un jugador según su id
     *
     * @param id Identificador del jugador
     * @return Jugador | Error 404
     */
    @GetMapping("/jugador/{id}")
    public Jugador getJugadorById(@PathVariable Long id){
        return repositorioJugadores.findById(id)
                .orElseThrow(() -> new JugadorNotFoundException(id));
    }

    /**
     * Creación de un jugador nuevo
     *
     * @param nuevo Datos del nuevo jugador
     * @return Jugador nuevo
     */
    @PostMapping("/jugadores")
    public ResponseEntity<?> newJugador(@RequestBody Jugador nuevo){
        return ResponseEntity.status(HttpStatus.CREATED).body(repositorioJugadores.save(nuevo));
    }

    /**
     * Actualización de un jugador según su id
     *
     * @param actualizacion Nuevos datos
     * @param id Identificación correspondiente
     * @return Jugador editado | Error 404
     */
    @PutMapping("/jugador/{id}")
    public Jugador updateJugador(@RequestBody Jugador actualizacion, @PathVariable Long id){
        return repositorioJugadores.findById(id).map(jugador ->{
            jugador.setNombre(actualizacion.getNombre());
            jugador.setPuntos(actualizacion.getPuntos());
            jugador.setAvatar(actualizacion.getAvatar());
            jugador.setEquipo(actualizacion.getEquipo()); // TODO: id_equipo
            return repositorioJugadores.save(jugador);
        }).orElseThrow(()-> new JugadorNotFoundException(id));
    }

    /**
     * Eliminación de un jugador según su id
     *
     * @param id Identificador del jugador
     * @return Código 204
     */
    @DeleteMapping("/jugador/{id}")
    public ResponseEntity<Void> deleteJugadorById(@PathVariable Long id){
        Jugador jugador = repositorioJugadores.findById(id).orElseThrow(() -> new JugadorNotFoundException(id));
        repositorioJugadores.delete(jugador);
        return ResponseEntity.noContent().build();
    }
}
