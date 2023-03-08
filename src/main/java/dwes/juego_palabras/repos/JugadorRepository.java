package dwes.juego_palabras.repos;

import dwes.juego_palabras.model.Jugador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JugadorRepository extends JpaRepository<Jugador,Long> {
}