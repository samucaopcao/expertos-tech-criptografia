package br.com.expertostech.criptografia.criptografiaspring.security;

import br.com.expertostech.criptografia.criptografiaspring.jwt.data.DetalheUsuarioData;
import br.com.expertostech.criptografia.criptografiaspring.model.UsuarioModel;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

/**
 * Classe responsável por
 * /** autenticar o usuário e gerar token JWT
 **/
public class JWTAutenticarFilter extends UsernamePasswordAuthenticationFilter {

    //Ambas variáveis deveriam estar em um arquivo de configuração
    public static final int TOKEN_EXPIRACAO = 600000;
    public static final String TOKEN_SENHA = "9456aeb7-a7f4-4ee9-8581-aed00673e9ae";

    private final AuthenticationManager authenticationManager;

    public JWTAutenticarFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    /**
     * Método que Converte o conteúdo do Json do usuário
     * para uma classe e realiza a validação
     */

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        try {
            UsuarioModel usuario = new ObjectMapper().readValue(request.getInputStream(), UsuarioModel.class);
                    return authenticationManager.authenticate((new UsernamePasswordAuthenticationToken(
                            usuario.getLogin(),
                            usuario.getPassword(),
                            new ArrayList<>())));
        } catch (IOException e) {
            throw new RuntimeException("Falha ao autenticar usuário ", e);
        }
    }

    /**
     * Casa a validação seja positiva utilizamos esse
     * Método. A dependência Auth0 é necessária para
     * criação do token
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

        DetalheUsuarioData usuarioData = (DetalheUsuarioData) authResult.getPrincipal();
        String token = JWT.create().withSubject(usuarioData.getUsername())
                //Adicionamos os milis segundos atuais somados com o tempo de expiração do token (criado na variavel TOKEN_EXPIRACAO)
                .withExpiresAt(new Date(System.currentTimeMillis() + TOKEN_EXPIRACAO))
                .sign(Algorithm.HMAC512(TOKEN_SENHA));

        //Para registrar o token no corpo da nossa página utilizamos:
        response.getWriter().write(token);
        response.getWriter().flush();

    }
}
