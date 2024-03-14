
package co.vinni.soapproyectobase.servicios;

import co.vinni.soapproyectobase.dto.CiudadanoDto;
import co.vinni.soapproyectobase.entidades.Ciudadano;
import co.vinni.soapproyectobase.repositorios.RepositorioCiudadano;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@Service
public class ServiciosCiudadano implements Serializable {

    private ModelMapper modelMapper;

    private final RepositorioCiudadano repoCiudadano;


    public CiudadanoDto registrarCiudadano(CiudadanoDto ciudadanoDto) {
        Ciudadano nuevaCiudadano = repoCiudadano.save(modelMapper.map(ciudadanoDto, Ciudadano.class));
        return modelMapper.map(nuevaCiudadano, CiudadanoDto.class);
    }

    public List<CiudadanoDto> obtenerCiudadanos() {
        TypeToken<List<CiudadanoDto>> typeToken = new TypeToken<>() {
        };
        return modelMapper.map(repoCiudadano.findAll(), typeToken.getType());
    }

    public CiudadanoDto obtenerCiudadanosPorserial(long serial) {
        Ciudadano CiudadanosFiltradas = repoCiudadano.getReferenceById(serial);

        return modelMapper.map(CiudadanosFiltradas, CiudadanoDto.class);
    }


    public CiudadanoDto modificarCiudadano(CiudadanoDto ciudadanoDto) {

        repoCiudadano.save(modelMapper.map(ciudadanoDto, Ciudadano.class));
        return ciudadanoDto;
    }

    public void eliminarCiudadano(long serial) {

        repoCiudadano.deleteById(serial);

    }
}

