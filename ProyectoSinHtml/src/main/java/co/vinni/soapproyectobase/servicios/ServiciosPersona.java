

package co.vinni.soapproyectobase.servicios;

import co.vinni.soapproyectobase.repositorios.RepositorioPersona;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import java.io.Serializable;
import java.util.List;
import co.vinni.soapproyectobase.dto.PersonaDto;
import co.vinni.soapproyectobase.entidades.Persona;
import org.springframework.stereotype.Service;

@Service
public class ServiciosPersona implements Serializable {

    private ModelMapper modelMapper;
    final
    private RepositorioPersona repoPersona;

    public ServiciosPersona( RepositorioPersona repoPersona) {

        this.repoPersona = repoPersona;
    }

    public void registrarPersona(PersonaDto nuevaPersonaDto) {
        Persona nuevaPersona = modelMapper.map(nuevaPersonaDto, Persona.class);
        repoPersona.save(nuevaPersona);
    }

    public List<PersonaDto> obtenerPersonas() {
        TypeToken<List<PersonaDto>> typeToken = new TypeToken<>() {};
        return modelMapper.map(repoPersona.findAll(), typeToken.getType());
    }



    public List<PersonaDto> obtenerPersonasPorNumeroDoc(long numeroDoc) {
        List<Persona> personasFiltradas = repoPersona.findByNumeroDoc(numeroDoc);
        TypeToken<List<PersonaDto>> typeToken = new TypeToken<>() {};
        return modelMapper.map(personasFiltradas, typeToken.getType());
    }



    public void modificarPersona(long numeroDoc, PersonaDto personaModificadaDto) {
        Persona personaModificada = modelMapper.map(personaModificadaDto, Persona.class);

        List<Persona> personas = repoPersona.findByNumeroDoc(numeroDoc);
        for (Persona persona : personas) {
            persona.setNombre(personaModificada.getNombre());
            persona.setFechanac(personaModificada.getFechanac());

        }

        repoPersona.saveAll(personas);
    }

    public void eliminarPersona(long numeroDoc) {
        List<Persona> personasAEliminar = repoPersona.findByNumeroDoc(numeroDoc);

        for (Persona persona : personasAEliminar) {
            repoPersona.delete(persona);
        }
    }

}
