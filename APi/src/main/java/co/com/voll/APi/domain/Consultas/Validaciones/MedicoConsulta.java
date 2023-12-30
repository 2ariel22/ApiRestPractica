package co.com.voll.APi.domain.Consultas.Validaciones;

import co.com.voll.APi.domain.Consultas.ConsultaRepository;
import co.com.voll.APi.domain.Consultas.DatosRegistroConsulta;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MedicoConsulta implements ValidadorDeConsultas{

    @Autowired
    private ConsultaRepository consultaRepository;

    public void validar(DatosRegistroConsulta datosRegistroConsulta){

        boolean vali = consultaRepository.existsByMedico_IdAndFecha(datosRegistroConsulta.idMedico(),datosRegistroConsulta.fecha());

        if(vali){
            throw new ValidationException("medico ocupado en ese horario");
        }
    }
}
