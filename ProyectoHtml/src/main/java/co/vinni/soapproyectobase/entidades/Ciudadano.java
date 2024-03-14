

package co.vinni.soapproyectobase.entidades;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;


@Data

@Table(name = "CIUDADANOS")
@Entity
public class Ciudadano  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CIUDADANOS")
    @SequenceGenerator(name = "SEQ_CIUDADANOS", sequenceName = "SEQ_CIUDADANOS", allocationSize = 1)

    @Column(name = "ID", nullable = false)
    private long serial;

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


