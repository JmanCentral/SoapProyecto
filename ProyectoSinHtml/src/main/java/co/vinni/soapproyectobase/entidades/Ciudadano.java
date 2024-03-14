

package co.vinni.soapproyectobase.entidades;

import jakarta.persistence.*;
import lombok.Data;


@Data

@Table(name = "CIUDADANOS")
@Entity
public class Ciudadano extends Persona {


    @Column(name = "PADRE", nullable = false)
    private String papa;

    @Column(name = "MADRE", nullable = false)
    private String mama;

    public String getPadre() {
        return papa;
    }

    public void setPadre(String papa) {
        this.papa = papa;
    }

    public String getMadre() {
        return mama;
    }

    public void setmadre(String mama) {
        this.mama = mama;
    }

}


