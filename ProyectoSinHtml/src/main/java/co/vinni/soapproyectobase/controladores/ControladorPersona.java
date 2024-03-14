
package co.vinni.soapproyectobase.controladores;

import co.vinni.soapproyectobase.servicios.ServiciosPersona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import co.vinni.soapproyectobase.dto.PersonaDto;

import java.util.List;


@RestController


public class ControladorPersona {
    @Autowired
    private ServiciosPersona servicioPersonas;


    public void registrarPersona( PersonaDto personaDto) {
        servicioPersonas.registrarPersona(personaDto);
    }

    public List<PersonaDto> consultarPersonas() {
        return servicioPersonas.obtenerPersonas();
    }


    public List<PersonaDto> consultarPersonasPorNumeroDoc( long numeroDoc) {
        return servicioPersonas.obtenerPersonasPorNumeroDoc(numeroDoc);
    }


    public void modificarPersona(long numeroDoc, PersonaDto personaDto) {
        servicioPersonas.modificarPersona(numeroDoc, personaDto);
    }

    public void eliminarPersona(long numeroDoc) {
        servicioPersonas.eliminarPersona(numeroDoc);
    }

}
