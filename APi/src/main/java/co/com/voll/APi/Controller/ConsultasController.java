package co.com.voll.APi.Controller;

import co.com.voll.APi.domain.Consultas.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consultas")
@SecurityRequirement(name = "bearer-key")
public class ConsultasController {
    @Autowired
    private ConsultasService consultasService;

    @Autowired
    private ConsultaRepository consultaRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<RespuestaConsulta> agendarCita(@RequestBody @Valid DatosRegistroConsulta datosRegistroConsulta){
        RespuestaConsulta respuestaConsulta = consultasService.agendar(datosRegistroConsulta);
        return ResponseEntity.ok(respuestaConsulta);
    }
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity CancelarCita(@NotNull @PathVariable long id){
        Consulta consulta =consultaRepository.getReferenceById(id);
        consulta.cancelarConsulta();
        return ResponseEntity.noContent().build();
    }
}
