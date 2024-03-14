

package co.vinni.soapproyectobase.servicios;

import co.vinni.soapproyectobase.dto.CiudadanoDto;
import co.vinni.soapproyectobase.entidades.Ciudadano;
import co.vinni.soapproyectobase.repositorios.RepositorioCiudadano;


import org.modelmapper.ModelMapper;


import org.modelmapper.TypeToken;



import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ServiciosCiudadano implements Serializable {

    private ModelMapper modelMapper;
    final
    private RepositorioCiudadano repoCiudadano;

    public ServiciosCiudadano(RepositorioCiudadano repoCiudadano) {

        this. repoCiudadano =  repoCiudadano;
    }

    public void registrarCiudadano(CiudadanoDto nuevaCiudadanoDto) {
        Ciudadano nuevaCiudadano = modelMapper.map(nuevaCiudadanoDto, Ciudadano.class);
        repoCiudadano.save(nuevaCiudadano);
    }

    public List<CiudadanoDto>obtenerCiudadanos() {
        TypeToken<List<CiudadanoDto>> typeToken = new TypeToken<>() {};
        return modelMapper.map(repoCiudadano.findAll(), typeToken.getType());
    }

    public List<CiudadanoDto> obtenerCiudadanosPorNumeroDoc(long numeroDoc) {
        List<Ciudadano> CiudadanosFiltradas = repoCiudadano.findByNumeroDoc((numeroDoc));
        TypeToken<List<CiudadanoDto>> typeToken = new TypeToken<>() {};
        return modelMapper.map(CiudadanosFiltradas, typeToken.getType());
    }



    public void modificarCiudadano(long numeroDoc, CiudadanoDto ciudadanoModificadaDto) {
        Ciudadano ciudadanoModificada = modelMapper.map(ciudadanoModificadaDto, Ciudadano.class);

        List<Ciudadano> ciudadanos = repoCiudadano.findByNumeroDoc((numeroDoc));
        for (Ciudadano ciudadano : ciudadanos) {
            ciudadano.setNombre(ciudadanoModificada.getNombre());
            ciudadano.setFechanac(ciudadanoModificada.getFechanac());

        }

        repoCiudadano.saveAll(ciudadanos);
    }

    public void eliminarCiudadano(long numeroDoc) {
        List<Ciudadano> ciudadanosAEliminar = repoCiudadano.findByNumeroDoc(numeroDoc);

        for (Ciudadano ciudadano : ciudadanosAEliminar) {
            repoCiudadano.delete(ciudadano);
        }
    }

}
