package co.com.voll.APi.domain.Consultas;



import co.com.voll.APi.domain.Medicos.Especialidad;

import java.time.LocalDateTime;

public record RespuestaConsulta(Long idMedico, LocalDateTime fecha, Especialidad especialidad) {

    public RespuestaConsulta(Consulta consulta) {
        this(consulta.getMedico().getId(),consulta.getFecha(),consulta.getMedico().getEspecialida() );
    }
}
