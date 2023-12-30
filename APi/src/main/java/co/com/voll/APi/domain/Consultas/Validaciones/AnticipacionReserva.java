package co.com.voll.APi.domain.Consultas.Validaciones;

import co.com.voll.APi.domain.Consultas.DatosRegistroConsulta;
import jakarta.validation.ValidationException;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class AnticipacionReserva implements ValidadorDeConsultas{

    public void validar(DatosRegistroConsulta datosRegistroConsulta){

        LocalDateTime ahora = LocalDateTime.now();
        LocalDateTime horaRecerva = datosRegistroConsulta.fecha();
        System.out.println(ahora);
        System.out.println(horaRecerva);
        System.out.println(Duration.between(ahora,horaRecerva).toMinutes());
        boolean diff = Duration.between(ahora,horaRecerva).toMinutes() <30;

        if(diff){
            throw new ValidationException("debes apartar la cita con 30 min de anticipacion");
        }

    }
}
