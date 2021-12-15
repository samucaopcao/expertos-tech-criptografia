package br.com.expertostech.criptografia.criptografiaspring.security;

import br.com.expertostech.criptografia.criptografiaspring.service.DetalheUsuariosServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@EnableWebSecurity
public class JWTConfiguracao extends WebSecurityConfigurerAdapter {

    //Neste ponto temos a busca do nosso usuário
    private final DetalheUsuariosServiceImpl usuariosService;
    private final PasswordEncoder passwordEncoder;

    public JWTConfiguracao(DetalheUsuariosServiceImpl usuariosService, PasswordEncoder passwordEncoder) {
        this.usuariosService = usuariosService;
        this.passwordEncoder = passwordEncoder;
    }
    //Como entender meus serviços
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(usuariosService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /**
         *  Essa configuração "http.csrf().disable()" é utilizada
         *  contra ataques, em ambiente de produção deve estar ativada,
         *  prosseguimos ".authorizeRequests()" como queremos que sejam feitas as autorizações
         *  das minhas requisições.
         *  Toda vez que colocamos o Spring Security como dependência
         *  ele cria uma url "/login" que não deve ter restrições por isso usamos
         *  ".antMatchers(HttpMethod.POST, "/login").permitAll()" não será pedido senha
         *  nessa solicitação de login , para qualquer outra solicitação usaremos
         *  ".anyRequest().authenticated()" para dizer que desejamos que esteja
         *  autenticado.
         *  Por fim usamos os dois filtros para validar a autenticação e outro
         *  para efetuar a validação
         */

        http.csrf().disable().authorizeRequests()
                .antMatchers(HttpMethod.POST, "/login").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilter(new JWTAutenticarFilter(authenticationManager()))
                .addFilter(new JWTValidarFilter(authenticationManager()))
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    /**
     * Tal configuração serve para que a
     * aplicação possa receber requisições
     * de outros dominios que não sejam o dela,
     * como podemos ter solicitações de mobile
     * por exemplo, é interessante ter essa config
     */
    @Bean
    CorsConfigurationSource corsConfigurationSource(){
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        CorsConfiguration corsConfiguration = new CorsConfiguration().applyPermitDefaultValues();
        source.registerCorsConfiguration("/**", corsConfiguration);

        return source;
    }

}
