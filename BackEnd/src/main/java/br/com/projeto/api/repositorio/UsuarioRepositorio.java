package br.com.projeto.api.repositorio;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.projeto.api.modelo.Usuario;
  

@Repository
public interface UsuarioRepositorio extends CrudRepository<Usuario, UUID> {

    List<Usuario> findAll();

    Usuario findByCodigo(UUID codigo);

    Usuario findByEmail(String email); 

    Usuario findBySenha(String senha);

    int countByCodigo(UUID codigo);
}
