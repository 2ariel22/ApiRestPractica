package co.com.voll.APi.domain.Consultas;

import co.com.voll.APi.domain.Medicos.Medico;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "consultas")
@Entity(name = "Consulta")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id") // esto es para comprar con otros medicos
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "medico_id")
    private Medico medico;

    private LocalDateTime fecha;
    @Enumerated(EnumType.STRING)
    private Estado estado;

    public Consulta(Medico medico, LocalDateTime fecha) {
        this.medico = medico;
        this.fecha = fecha;
        this.estado = Estado.ACTIVA;
    }

    public void cancelarConsulta() {
        this.estado = Estado.CANCELADA;
    }
}
