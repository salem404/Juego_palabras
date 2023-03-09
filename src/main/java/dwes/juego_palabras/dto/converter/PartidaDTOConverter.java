package dwes.juego_palabras.dto.converter;


import dwes.juego_palabras.dto.PartidaDTO;
import dwes.juego_palabras.model.Partida;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PartidaDTOConverter {
    private final ModelMapper modelMapper;
    public PartidaDTO convertToDTO (Partida partida){
        return modelMapper.map(partida, PartidaDTO.class);
    }
}
