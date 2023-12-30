package co.com.voll.APi.domain.Consultas;

import co.com.voll.APi.domain.Consultas.Validaciones.ValidadorDeConsultas;
import co.com.voll.APi.domain.Medicos.Medico;
import co.com.voll.APi.domain.Medicos.MedicoRepository;
import co.com.voll.APi.infra.Errores.ValidacionIntegridad;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultasService {

    @Autowired
    private MedicoRepository medicoRepository;


    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private List<ValidadorDeConsultas> validadorDeConsultas;

    public RespuestaConsulta agendar(DatosRegistroConsulta datosRegistroConsulta){
        //if(!medicoRepository.findById(datosRegistroConsulta.idMedico()).isPresent()){//ispresent arroja un optional con true o false
         //   throw new ValidacionIntegridad("este Id de medico no fue encontrado");
       // }

        if(datosRegistroConsulta.idMedico() != null &&
                !medicoRepository.existsById(datosRegistroConsulta.idMedico())){//este me arroja el true o false directos
            throw new ValidationException("Este Id no fue encontrado");
        }

        validadorDeConsultas.forEach(v-> v.validar(datosRegistroConsulta));


        Medico medico = seleccionarMedico(datosRegistroConsulta);

        if(medico == null){
            throw new ValidationException("no hay medicos dispobles para esta especialidad y hora");
        }

        Consulta consulta = new Consulta(medico, datosRegistroConsulta.fecha());
        consultaRepository.save(consulta);
        return new RespuestaConsulta(consulta);

    }



    private Medico seleccionarMedico(DatosRegistroConsulta datosRegistroConsulta){
        if(datosRegistroConsulta.idMedico() != null){
            return medicoRepository.getReferenceById(datosRegistroConsulta.idMedico());
        }
        if(datosRegistroConsulta.especialidad() == null){
            throw new ValidacionIntegridad("Debes seleccionar una especialidad");
        }
        return medicoRepository.medicoRandomPorEspecialidad(datosRegistroConsulta.especialidad(),datosRegistroConsulta.fecha());
    }
}
