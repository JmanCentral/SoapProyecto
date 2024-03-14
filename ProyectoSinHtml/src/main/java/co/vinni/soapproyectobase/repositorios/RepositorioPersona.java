

package co.vinni.soapproyectobase.repositorios;

import co.vinni.soapproyectobase.entidades.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RepositorioPersona extends JpaRepository<Persona, Long> {

    List<Persona> findByNumeroDoc(long numeroDoc);


}
