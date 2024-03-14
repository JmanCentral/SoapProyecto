

package co.vinni.soapproyectobase.servicios;

import co.vinni.soapproyectobase.dto.PersonaDto;
import co.vinni.soapproyectobase.entidades.Persona;
import co.vinni.soapproyectobase.repositorios.RepositorioPersona;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;


@AllArgsConstructor
@Service
public class ServiciosPersona implements Serializable {

    private ModelMapper modelMapper;

    private final RepositorioPersona repoPersona;

    public PersonaDto registrarPersona(PersonaDto personaDto) {

        Persona nuevaPersona = repoPersona.save(modelMapper.map(personaDto, Persona.class));
        return modelMapper.map(nuevaPersona, PersonaDto.class);
    }

    public List<PersonaDto> obtenerPersonas() {
        TypeToken<List<PersonaDto>> typeToken = new TypeToken<>() {};
        return modelMapper.map(repoPersona.findAll(), typeToken.getType());
    }

    public PersonaDto obtenerPersonasPorNumeroDoc(long numeroDoc) {
        Persona personasFiltradas = repoPersona.getReferenceById(numeroDoc);
        return modelMapper.map(personasFiltradas, PersonaDto.class);
    }

    public PersonaDto modificarPersona(PersonaDto personaDto) {

        repoPersona.save(modelMapper.map(personaDto, Persona.class));
        return personaDto;
    }

    public void eliminarPersona(long numeroDoc) {

        repoPersona.deleteById(numeroDoc);
    }

}
