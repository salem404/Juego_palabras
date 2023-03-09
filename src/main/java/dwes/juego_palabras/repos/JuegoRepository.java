package dwes.juego_palabras.repos;

import dwes.juego_palabras.model.Juego;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JuegoRepository extends JpaRepository<Juego,Long> {
}
