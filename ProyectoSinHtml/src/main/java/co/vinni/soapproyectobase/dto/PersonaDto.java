package co.vinni.soapproyectobase.dto;

import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString


public class PersonaDto implements Serializable {
    private long numeroDoc;
    private String nombre;
    private Date fechanac;
    private String tipoDoc;
    private int edad;
    private String apellidoPadre;
    private String apellidoMadre;

}
