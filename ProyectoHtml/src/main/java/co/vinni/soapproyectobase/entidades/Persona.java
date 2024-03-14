

package co.vinni.soapproyectobase.entidades;


import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "PERSONAS")

public class Persona implements Serializable {

    @Id
    @Column(name = "Numero_Documento")
    private long numeroDoc;

    @Column(name = "NOMBRE")
    private String nombre;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Column(name = "FECHA_NACIMIENTO")
    private Date fechanac;

    @Column(name = "TIPO_DOC")
    private String tipodoc;


    @Column(name = "EDAD")
    private int edad;

    @Column(name = "APELLIDO_PADRE")
    private String apellidoPadre;

    @Column(name = "APELLIDO_MADRE")
    private String apellidoMadre;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public Date getFechanac() {
        return fechanac;
    }

    public void setFechanac(Date fechanac) {
        this.fechanac = fechanac;
    }

    public String getTipodoc() {
        return tipodoc;
    }

    public void setTipodoc(String tipodoc) {
        this.tipodoc = tipodoc;
    }

    public long getnumeroDoc() {
        return numeroDoc;
    }

    public void setnumeroDoc(long numeroDoc) {
        this.numeroDoc = numeroDoc;
    }



    public String getPadreApellido() {
        return apellidoPadre;
    }

    public void setPadreApellido(String apellidoPadre) {
        this.apellidoPadre = apellidoPadre;
    }

    public String getMadreApellido() {
        return apellidoMadre;
    }

    public void setMadreApellido(String apellidoMadre) {
        this.apellidoMadre = apellidoMadre;
    }






    public void actualizarInformacion(Persona nuevaInformacion) {
        this.nombre = nuevaInformacion.getNombre();
        this.fechanac = nuevaInformacion.getFechanac();
        this.tipodoc = nuevaInformacion.getTipodoc();
    }


}
