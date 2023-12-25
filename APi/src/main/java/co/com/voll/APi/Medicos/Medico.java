package co.com.voll.APi.Medicos;

import co.com.voll.APi.direccion.Direccion;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    @Enumerated(EnumType.STRING)
    private Especialidad Especialida;
    @Embedded
    private Direccion direccion;

    public Medico(DatosRegistroMedicos datosMedico) {
        this.Nombre = datosMedico.Nombre();
        this.Email = datosMedico.Email();
        this.Telefono = datosMedico.Telefono();
        this.Documento = datosMedico.Documento();
        this.Especialida = datosMedico.Especialida();
        this.direccion = new Direccion(datosMedico.Direccion());
    }
}
