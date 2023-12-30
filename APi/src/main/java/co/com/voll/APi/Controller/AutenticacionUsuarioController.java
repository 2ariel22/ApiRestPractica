package co.com.voll.APi.Controller;

import co.com.voll.APi.domain.Usuarios.DatosAutenticacionUser;
import co.com.voll.APi.domain.Usuarios.Usuario;
import co.com.voll.APi.infra.security.AutenticationService;
import co.com.voll.APi.infra.security.DatosJWT;
import co.com.voll.APi.infra.security.TokenGenerate;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Login")
public class AutenticacionUsuarioController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenGenerate tokenGenerate;

    @PostMapping
    public ResponseEntity AutenticarUsurio(@RequestBody @Valid DatosAutenticacionUser datosAutenticacionUser){
        Authentication Authtoken = new UsernamePasswordAuthenticationToken(datosAutenticacionUser.user()
        ,datosAutenticacionUser.pass());
        var usuarioAuth = authenticationManager.authenticate(Authtoken);
        String JWTtoken = tokenGenerate.generarToken((Usuario) usuarioAuth.getPrincipal());//el getprincipal sirve para traer el usuario ya autenticado
        return ResponseEntity.ok(new DatosJWT(JWTtoken));

    }
}
