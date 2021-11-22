package ogr.generation.blogPessoal.Controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ogr.generation.blogPessoal.model.UserLogin;
import ogr.generation.blogPessoal.model.Usuario;
import ogr.generation.blogPessoal.repository.UsuarioRepository;
import ogr.generation.blogPessoal.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private UsuarioRepository repository;
	
	@GetMapping
	public ResponseEntity <List<Usuario>> getAll() {
	        return ResponseEntity.ok(repository.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Usuario> getById(@PathVariable long id) {
	        return repository.findById(id)
		        .map(resp -> ResponseEntity.ok(resp))
		        .orElse(ResponseEntity.notFound().build());
	}
	
	@PutMapping("/atualizar")
	public ResponseEntity<Usuario> putUsuario(@Valid @RequestBody Usuario usuario){		
	        return usuarioService.atualizarUsuario(usuario)
	                .map(resp -> ResponseEntity.status(HttpStatus.OK).body(resp))
	                .orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
	}


	@PostMapping("/logar")
	public ResponseEntity<UserLogin> Autentication(@Valid @RequestBody Optional<UserLogin> user) {
		return usuarioService.UserLogin(user).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}

	@PostMapping("/cadastrar")
	public ResponseEntity<Optional<Usuario>> Post(@Valid @RequestBody Usuario usuario) {
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.cadastrarUsuario(usuario));
	}

}
