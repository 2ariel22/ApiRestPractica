package co.com.voll.APi.infra.Errores;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice //esto intercepta los errores lanzados
public class TratadorErrores {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity TratarError404(){
        return ResponseEntity.notFound().build();
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity TratarError400(MethodArgumentNotValidException e){
        var erorres = e.getFieldErrors().stream().map(DatosErrorValidacion::new).toList();
        return ResponseEntity.badRequest().body(erorres);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity TratarError400(ValidationException e){

        return ResponseEntity.badRequest().body(e.getMessage());
    }



    private record DatosErrorValidacion(String campo, String error){//Esto se hace para retornar un mensaje
        //de error mas amigable para el usuario

        public DatosErrorValidacion(FieldError objectError) {
            this(objectError.getField(),objectError.getDefaultMessage());
        }


    }
}


