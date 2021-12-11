package br.com.expertostech.criptografia.criptografiaspring.repository;

import br.com.expertostech.criptografia.criptografiaspring.model.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<UsuarioModel, Integer> {

    public Optional<UsuarioModel> findByLogin(String login);

}
