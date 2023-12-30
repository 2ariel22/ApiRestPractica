package co.com.voll.APi.domain.Consultas.Validaciones;

import co.com.voll.APi.domain.Consultas.DatosRegistroConsulta;
import jakarta.validation.ValidationException;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

@Component
public class HorarioClinica implements ValidadorDeConsultas{

    public void validar(DatosRegistroConsulta datosRegistroConsulta){
        boolean domingo = DayOfWeek.SUNDAY.equals(datosRegistroConsulta.fecha().getDayOfWeek());
        boolean despuesDeApertura = datosRegistroConsulta.fecha().getHour() > 19;
        boolean antesDeApertura = datosRegistroConsulta.fecha().getHour() < 7;//local datetime maneja un forma
        //to de 24 horas

        if(domingo || despuesDeApertura || antesDeApertura){
            throw new ValidationException("no se atiende en este horario");
        }

    }
}
