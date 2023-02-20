package br.com.projeto.api.controle;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.projeto.api.modelo.Usuario;
import br.com.projeto.api.servico.UsuarioServico;
import br.com.projeto.api.repositorio.UsuarioRepositorio;
import jakarta.validation.Valid;


@RestController
@CrossOrigin("*")
@RequestMapping("/math-school/")
public class UsuarioControle {

    @Autowired
    private UsuarioServico servico;

    @Autowired
    private PasswordEncoder encoder;


    // Cadastrar dados do usuario no database ok
    @PostMapping("cadastrar")
    public ResponseEntity<?> cadastrar(@Valid @RequestBody Usuario objeto) {
        objeto.setSenha(encoder.encode(objeto.getSenha()));
        return servico.cadastrar(objeto);
    }

    // Atualizar dados do usuário ok
    @PutMapping("/usuarios")
    public ResponseEntity<?> editarUsuario(@Valid @RequestBody Usuario objeto) {
        objeto.setSenha(encoder.encode(objeto.getSenha()));
        return servico.editarUsuario(objeto);
    }

    // Retorna dados dos usuários ok
    @GetMapping("/usuarios")
    public ResponseEntity<?> selecionar() {
        return servico.selecionar();
    }

    
    // Validar senha e e-mail
    @PostMapping("login")
    public ResponseEntity<?> validarUsuario(@Valid @RequestBody Usuario objeto) {
        //objeto.setSenha(encoder.encode(objeto.getSenha()));
        return servico.validarUsuario(objeto);
    }
    



    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
            
        });
        return errors;
    }
    
}
