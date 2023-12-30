package co.com.voll.APi.infra.security;

import co.com.voll.APi.domain.Usuarios.Usuario;
import co.com.voll.APi.domain.Usuarios.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component //esto es lo mas generico en spring
public class SecurityFilter extends OncePerRequestFilter {
    @Autowired
    private TokenGenerate tokenGenerate;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String Auth = request.getHeader("Authorization");
        if(Auth!=null){
            Auth = Auth.replace("Bearer ", "");
            if (Auth !=""){
                UserDetails usuario =  usuarioRepository.findByUser(tokenGenerate.getSujet(Auth));
                var Authenticado = new UsernamePasswordAuthenticationToken(usuario,null,
                        usuario.getAuthorities());//esto se hace para forzar un inicio de sesion debido a que nuestra api
                //es stateless
                SecurityContextHolder.getContext().setAuthentication(Authenticado);
            }
        }

        filterChain.doFilter(request,response);//sino se llama a la cadena de filtro se queda aqui atrapado
    }
}
