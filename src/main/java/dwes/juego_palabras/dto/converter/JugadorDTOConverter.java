package dwes.juego_palabras.dto.converter;

import dwes.juego_palabras.dto.JugadorDTO;
import dwes.juego_palabras.model.Jugador;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JugadorDTOConverter {
    private final ModelMapper modelMapper;

    public JugadorDTO convertToDTO (Jugador jugador){
        return modelMapper.map(jugador, JugadorDTO.class);
    }
}
