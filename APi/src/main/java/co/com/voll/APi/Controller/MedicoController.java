package co.com.voll.APi.Controller;

import co.com.voll.APi.domain.Medicos.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;


@RestController
@RequestMapping("/Medicos")
@SecurityRequirement(name = "bearer-key")
public class MedicoController {
    @Autowired
    private MedicoRepository medicoRepository;


    @Operation(
            summary = "registrar medico",
            description = "",
            tags = {"consulta","post"}
    )
    @PostMapping
    public ResponseEntity<RespuestaMedico> RegistrarMedico(@RequestBody @Valid DatosRegistroMedicos DatosMedico, UriComponentsBuilder uriComponentsBuilder){
        Medico medico = medicoRepository.save(new Medico(DatosMedico));
        URI url = uriComponentsBuilder.path("/Medicos/{Id}").buildAndExpand(medico.getId()).toUri();
        return ResponseEntity.created(url).body(new RespuestaMedico(medico));

    }
    @GetMapping
    public ResponseEntity<Page<DatosListadoMedico>> ListaMedicos(@PageableDefault(size = 5) Pageable paginacion){
        //return medicoRepository.findAll(paginacion).map(DatosListadoMedico::new);
        return ResponseEntity.ok(medicoRepository.findByActivoTrue(paginacion).map(DatosListadoMedico::new));//esta nomenclatura sirve
        // para crear querys personalizadas
    }
    @PutMapping
    @Transactional // esto es para que actualice la base de datos
    public ResponseEntity ActualizarMedico(@RequestBody @Valid DatosActualizarMedico datosActualizarMedico){
        Medico medico = medicoRepository.getReferenceById(datosActualizarMedico.Id());
        medico.ActualizarMedico(datosActualizarMedico);
        return ResponseEntity.ok(new RespuestaMedico(medico));
    }

    @DeleteMapping("/{Id}")
    @Transactional
    public ResponseEntity DeleteMedico(@PathVariable long Id){
        Medico medico = medicoRepository.getReferenceById(Id);
        //medicoRepository.delete(medico); DELETE BASE DE DATOS
        medico.DesactivarMedico();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{Id}")
    public ResponseEntity<RespuestaMedico> BuscarMedico(@PathVariable long Id){
        Medico medico = medicoRepository.getReferenceById(Id);

        return ResponseEntity.ok(new RespuestaMedico(medico));
    }
}
