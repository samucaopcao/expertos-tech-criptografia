package br.com.expertostech.criptografia.criptografiaspring.jwt.data;

import br.com.expertostech.criptografia.criptografiaspring.model.UsuarioModel;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public class DetalheUsuarioData implements UserDetails {

    private final Optional<UsuarioModel> usuario;

    public DetalheUsuarioData(Optional<UsuarioModel> usuario) {
        this.usuario = usuario;
    }

    /**
     * Método responsável pelas autorizações de acesso do nosso usuário
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
    }

    @Override
    public String getPassword() {
        return usuario.orElse(new UsuarioModel()).getPassword();
    }

    @Override
    public String getUsername() {
        return usuario.orElse(new UsuarioModel()).getLogin();
    }

    /**
     * Método responsável por verificar se a conta
     * do usuário não está expirada
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Método responsável por verificar se a conta
     * do usuário não está bloqueada
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Método responsável por verificar se a conta
     * do usuário não está com a credencial expirada
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    /**
     * Método responsável por verificar se a conta
     * do usuário  está ativa
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
