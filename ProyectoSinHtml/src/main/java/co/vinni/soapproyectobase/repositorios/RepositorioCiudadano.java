
package co.vinni.soapproyectobase.repositorios;

import co.vinni.soapproyectobase.entidades.Ciudadano;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RepositorioCiudadano extends JpaRepository<Ciudadano, Long> {

    List<Ciudadano> findByNumeroDoc(long numeroDoc);

}


