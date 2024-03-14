
package co.vinni.soapproyectobase.dto;

import co.vinni.soapproyectobase.entidades.Persona;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class CiudadanoDto extends Persona  {

    private String papa;
    private String mama;
}
