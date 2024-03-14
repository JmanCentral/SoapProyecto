
package co.vinni.soapproyectobase.controladores;

import co.vinni.soapproyectobase.dto.CiudadanoDto;
import co.vinni.soapproyectobase.servicios.ServiciosCiudadano;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController

public class ControladorCiudadano {
    @Autowired
    private ServiciosCiudadano servicioCiudadanos;


    public void registrarCiudadano(CiudadanoDto ciudadano) {
        servicioCiudadanos.registrarCiudadano(ciudadano);
    }


    public List<CiudadanoDto> consultarPersonas() {
        return servicioCiudadanos.obtenerCiudadanos();
    }


    public List<CiudadanoDto> consultarCiudadanosPorNumeroDoc(long numeroDoc) {
        return servicioCiudadanos.obtenerCiudadanosPorNumeroDoc(numeroDoc);
    }


    public void modificarCiudadano(long numeroDoc, CiudadanoDto ciudadanoDto) {
        servicioCiudadanos.modificarCiudadano(numeroDoc, ciudadanoDto);
    }

    public void eliminarCiudadano(long numeroDoc) {
        servicioCiudadanos.eliminarCiudadano(numeroDoc);
    }
}
