package co.com.voll.APi.domain.Consultas.Validaciones;

import co.com.voll.APi.domain.Consultas.DatosRegistroConsulta;
import co.com.voll.APi.domain.Medicos.MedicoRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class MedicoActivo implements ValidadorDeConsultas{

    @Autowired
    private MedicoRepository medicoRepository;

    public void validar(DatosRegistroConsulta datosRegistroConsulta){
        if(datosRegistroConsulta.idMedico() == null){
            return;
        }
        boolean state = medicoRepository.findActivoById(datosRegistroConsulta.idMedico());
        if(!state){
            throw new ValidationException("medico inactivo");
        }
    }


}
