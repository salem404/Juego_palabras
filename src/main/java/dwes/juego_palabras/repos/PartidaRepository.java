package dwes.juego_palabras.repos;

import dwes.juego_palabras.model.Partida;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartidaRepository extends JpaRepository<Partida,Long> {
}
