package co.com.voll.APi.domain.Medicos;

import co.com.voll.APi.domain.direccion.Direccion;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "medicos")
@Entity(name = "Medico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "Id") // esto es para comprar con otros medicos
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;
    private String Nombre;
    private String Email;
    private String Telefono;
    private String Documento;
    private Boolean activo;
    @Enumerated(EnumType.STRING)
    private Especialidad Especialida;
    @Embedded
    private Direccion direccion;
    private LocalDateTime date;

    public Medico(DatosRegistroMedicos datosMedico) {
        this.activo = true;
        this.Nombre = datosMedico.Nombre();
        this.Email = datosMedico.Email();
        this.Telefono = datosMedico.Telefono();
        this.Documento = datosMedico.Documento();
        this.Especialida = datosMedico.Especialida();
        this.direccion = new Direccion(datosMedico.Direccion());
    }

    public void ActualizarMedico(DatosActualizarMedico datosActualizarMedico) {
        if(datosActualizarMedico.Nombre() != null){
            this.Nombre = datosActualizarMedico.Nombre();
        }
        if(datosActualizarMedico.Documento() != null){
            this.Documento = datosActualizarMedico.Documento();
        }
        if(datosActualizarMedico.Direccion() != null){
            this.direccion.ActualizarDatos(datosActualizarMedico.Direccion());
        }
    }

    public void DesactivarMedico() {
        this.activo=false;
    }
}
