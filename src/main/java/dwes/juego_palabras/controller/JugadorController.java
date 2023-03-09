package dwes.juego_palabras.controller;

import dwes.juego_palabras.dto.CrearJugadorDTO;
import dwes.juego_palabras.dto.JugadorDTO;
import dwes.juego_palabras.dto.ModificarJugadorDTO;
import dwes.juego_palabras.dto.converter.JugadorDTOConverter;
import dwes.juego_palabras.error.JugadorNotFoundException;
import dwes.juego_palabras.model.Equipo;
import dwes.juego_palabras.model.Jugador;
import dwes.juego_palabras.repos.EquipoRepository;
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
    private final JugadorDTOConverter jugadorDTOConverter;

    private final EquipoRepository repositorioEquipos;

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
            List<JugadorDTO> dtoList =
                    jugadores.stream().map(jugadorDTOConverter::convertToDTO).collect(Collectors.toList());
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
    public ResponseEntity<?> newJugador(@RequestBody CrearJugadorDTO nuevo){
        Jugador jugador = new Jugador();
        Equipo equipo = null;
        jugador.setNombre(nuevo.getNombre());
        jugador.setAvatar(nuevo.getAvatar());
        if(nuevo.getId_equipo() != null){
            equipo = repositorioEquipos.findById(nuevo.getId_equipo()).orElse(null);
        }
        jugador.setEquipo(equipo);
        jugador.setPuntos(nuevo.getPuntos());
        return ResponseEntity.status(HttpStatus.CREATED).body(repositorioJugadores.save(jugador));
    }

    /**
     * Actualización de un jugador según su id
     *
     * @param actualizacion Nuevos datos
     * @param id Identificación correspondiente
     * @return Jugador editado | Error 404
     */
    @PutMapping("/jugador/{id}")
    public Jugador updateJugador(@RequestBody ModificarJugadorDTO actualizacion, @PathVariable Long id){
        Equipo equipo = null;
        if(actualizacion.getId_equipo() != null){
            equipo = repositorioEquipos.findById(actualizacion.getId_equipo()).orElse(null);
        }
        final Equipo equipoFinal = equipo;
        return repositorioJugadores.findById(id).map(jugador ->{
            jugador.setNombre(actualizacion.getNombre());
            jugador.setPuntos(actualizacion.getPuntos());
            jugador.setAvatar(actualizacion.getAvatar());
            jugador.setEquipo(equipoFinal);
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
