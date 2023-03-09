package dwes.juego_palabras.controller;

import dwes.juego_palabras.error.EquipoNotFoundException;
import dwes.juego_palabras.repos.EquipoRepository;
import dwes.juego_palabras.model.Equipo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class EquipoController {

    private final EquipoRepository repositorioEquipos;

    /**
     * Obtención de todos los equipos
     *
     * @return Lista de equipos
     */
    @GetMapping("/equipos")
    public ResponseEntity<List<?>> getAllEquipos(){
        List<Equipo> equipos = repositorioEquipos.findAll();
        if(equipos.isEmpty()){
            return ResponseEntity.noContent().build();
        } else {

            return ResponseEntity.ok(equipos);
        }
    }

    /**
     * Obtención de un equipo según su id
     *
     * @param id Identificador del equipo
     * @return Equipo | Error 404
     */
    @GetMapping("/equipo/{id}")
    public Equipo getEquipoById(@PathVariable Long id){
        return repositorioEquipos.findById(id)
                .orElseThrow(() -> new EquipoNotFoundException(id));
    }

    /**
     * Creación de un equipo nuevo
     *
     * @param nuevo Datos del nuevo equipo
     * @return Equipo nuevo
     */
    @PostMapping("/equipos")
    public ResponseEntity<?> newEquipo(@RequestBody Equipo nuevo){
        return ResponseEntity.status(HttpStatus.CREATED).body(repositorioEquipos.save(nuevo));
    }

    /**
     * Actualización de un equipo según su id
     *
     * @param actualizacion Nuevos datos
     * @param id Identificación correspondiente
     * @return Equipo editado | Error 404
     */
    @PutMapping("/equipo/{id}")
    public Equipo updateEquipo(@RequestBody Equipo actualizacion, @PathVariable Long id){
        return repositorioEquipos.findById(id).map(equipo ->{
            equipo.setNombre_equipo(actualizacion.getNombre_equipo());
            equipo.setLogo(actualizacion.getLogo());
            equipo.setPuntos(actualizacion.getPuntos());
            return repositorioEquipos.save(equipo);
        }).orElseThrow(()-> new EquipoNotFoundException(id));
    }

    /**
     * Eliminación de un equipo según su id
     *
     * @param id Identificador del equipo
     * @return Código 204
     */
    @DeleteMapping("/equipo/{id}")
    public ResponseEntity<Void> deleteEquipoById(@PathVariable Long id){
        Equipo equipo = repositorioEquipos.findById(id).orElseThrow(() -> new EquipoNotFoundException(id));
        repositorioEquipos.delete(equipo);
        return ResponseEntity.noContent().build();
    }
}