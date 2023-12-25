package co.com.voll.APi.Controller;

import co.com.voll.APi.Medicos.DatosRegistroMedicos;
import co.com.voll.APi.Medicos.Medico;
import co.com.voll.APi.Medicos.MedicoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Medicos")
public class MedicoController {
    @Autowired
    private MedicoRepository medicoRepository;


    @PostMapping
    public void RegistrarMedico(@RequestBody @Valid DatosRegistroMedicos DatosMedico){
        medicoRepository.save(new Medico(DatosMedico));
    }
}
