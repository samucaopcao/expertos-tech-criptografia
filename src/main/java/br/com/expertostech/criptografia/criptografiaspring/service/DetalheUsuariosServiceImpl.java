package br.com.expertostech.criptografia.criptografiaspring.service;

import br.com.expertostech.criptografia.criptografiaspring.jwt.data.DetalheUsuarioData;
import br.com.expertostech.criptografia.criptografiaspring.model.UsuarioModel;
import br.com.expertostech.criptografia.criptografiaspring.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Serviço responsável pela consulta
 * e validação da senha do usuário
 */
@Component
public class DetalheUsuariosServiceImpl implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public DetalheUsuariosServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    /**
     * Método responsável pela consulta
     * do usuário
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       Optional<UsuarioModel> usuario = usuarioRepository.findByLogin(username);
       if(usuario.isEmpty()){
           throw new UsernameNotFoundException("Usuário [" + username + "] não encontrado");
       }

        return new DetalheUsuarioData(usuario);
    }
}
