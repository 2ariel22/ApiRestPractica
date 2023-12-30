package co.com.voll.APi.infra.security;

import co.com.voll.APi.domain.Usuarios.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenGenerate {
    @Value("${api.security.secret]")
    private String valorSecret;

    public String generarToken(Usuario usuario){
        try {
            Algorithm algorithm = Algorithm.HMAC256(valorSecret);
            return JWT.create()
                    .withIssuer("Voll Med")
                    .withSubject(usuario.getUser())
                    .withClaim("id",usuario.getId())
                    .withExpiresAt(generarFechaExpiracion())
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new RuntimeException();
        }
    }
    public String getSujet(String Token){
        if(Token == null){
            throw new RuntimeException("TOken nulo");
        }
        String user = "";
        try {
            Algorithm algorithm = Algorithm.HMAC256(valorSecret);
            DecodedJWT verifier = JWT.require(algorithm)
                    .withIssuer("Voll Med")
                    .build().verify(Token);

            user = verifier.getSubject();
        } catch (JWTVerificationException exception){
            // Invalid signature/claims
        }
        if(user == "" || user == null){
            throw new RuntimeException("user no valido");
        }
        return user;
    }
    private Instant generarFechaExpiracion(){
        return LocalDateTime.now().plusHours(3).toInstant(ZoneOffset.of("-05:00"));
    }
}
