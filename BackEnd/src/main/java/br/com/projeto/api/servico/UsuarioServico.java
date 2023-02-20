package br.com.projeto.api.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.projeto.api.modelo.Mensagem;
import br.com.projeto.api.modelo.Usuario;
import br.com.projeto.api.repositorio.UsuarioRepositorio;


@Service
public class UsuarioServico {

    @Autowired
    private Mensagem mensagem;

    @Autowired
    private UsuarioRepositorio acao;

    @Autowired
    private PasswordEncoder encoder;
    

    // Método para cadastrar usuarios
    public ResponseEntity<?> cadastrar(Usuario objeto) {
        Usuario emailCadastro = acao.findByEmail(objeto.getEmail());

        if (emailCadastro != null) {
            mensagem.setMensagem("Conflito: e-mail já cadastrado.");
            return new ResponseEntity<>(mensagem, HttpStatus.CONFLICT);

        }else {
            if (objeto.getNome().equals("")) {
                mensagem.setMensagem("O nome precisa ser preenchido.");
                return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);

            }else if(objeto.getSobrenome().equals("")) {
                mensagem.setMensagem("O sobrenome precisa ser preenchido.");
                return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
    
            }else if(objeto.getEmail().equals("")) {
                mensagem.setMensagem("O e-mail precisa ser preenchido.");
                return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
    
            }else if(objeto.getSenha().equals("")) {
                mensagem.setMensagem("A senha precisa ser preenchida.");
                return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
    
            } else {
                return new ResponseEntity<>(acao.save(objeto), HttpStatus.CREATED);
            }
        }
    }

    // Método para editar dados
    public ResponseEntity<?> editarUsuario(Usuario objeto) {

        if(acao.countByCodigo(objeto.getCodigo()) == 0) {
            mensagem.setMensagem(("O cógido informado não existe."));
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);

        }else if(objeto.getNome().equals("")) {
            mensagem.setMensagem("É necessário informar um nome");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);

        }else if(objeto.getSobrenome().equals("")) {
            mensagem.setMensagem("O sobrenome precisa ser preenchido");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);

        }else if(objeto.getEmail().equals("")) {
            mensagem.setMensagem("O e-mail precisa ser preenchido");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
            
        }else if(objeto.getSenha().equals("")) {
            mensagem.setMensagem("A senha precisa ser preenchida");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);

        }else {
            mensagem.setMensagem("Novas informações salvas com sucesso!");
            return new ResponseEntity<>(acao.save(objeto), HttpStatus.OK);
        }
    }

    // Método listar usuarios
    public ResponseEntity<?> selecionar() {
        return new ResponseEntity<>(acao.findAll(), HttpStatus.OK);
    }

    // Método para validar senha
    public Boolean validarSenha(Usuario objeto) {
    
        Usuario emailCadastro = acao.findByEmail(objeto.getEmail());
    
        if (emailCadastro != null) {

            String senha = acao.findByEmail(objeto.getEmail()).getSenha();
            Boolean valid = encoder.matches(objeto.getSenha(), senha);
            
            return valid;

        }else {
            return false;
        }
    }
    // Método para validar usuário com e-mail e senha
    public ResponseEntity<?> validarUsuario(Usuario objeto) {
        
        Usuario emailUsuario = acao.findByEmail(objeto.getEmail());
        String senhaEmail = acao.findByEmail(objeto.getEmail()).getSenha();
        Boolean valid = encoder.matches(objeto.getSenha(), senhaEmail);

        if (emailUsuario.equals(null)) {

            mensagem.setMensagem("E-mail não encontrado!");
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
        }
        
        if (!valid) {

            mensagem.setMensagem("E-mail ou senha incorretos!");
            return new ResponseEntity<>(mensagem, HttpStatus.UNAUTHORIZED);
        } 

        mensagem.setMensagem("Carregado com sucesso!");
        return new ResponseEntity<>(mensagem, HttpStatus.OK);
    }
}
