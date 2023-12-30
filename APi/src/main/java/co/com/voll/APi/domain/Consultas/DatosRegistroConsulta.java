package co.com.voll.APi.domain.Consultas;


import co.com.voll.APi.domain.Medicos.Especialidad;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DatosRegistroConsulta(Long idMedico, @NotNull LocalDateTime fecha, @NotNull Especialidad especialidad) {

}
