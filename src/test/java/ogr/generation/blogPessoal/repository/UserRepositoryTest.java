package ogr.generation.blogPessoal.repository;

import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import javax.validation.Validator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import ogr.generation.blogPessoal.model.Usuario;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(Lifecycle.PER_CLASS)
public class UserRepositoryTest {

	private @Autowired UsuarioRepository repository;
	
	@Autowired
    private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    Validator validator = factory.getValidator();
	
    @BeforeAll
	void start() {
		repository.save(new Usuario("Camila","camibaza","326987"));
		repository.save(new Usuario("Julia","juju","123455"));
		repository.save(new Usuario("Gabriel","bielbaza","54367"));
		repository.save(new Usuario("Martina","marta","875643"));	
	}
	
    @Test
    @DisplayName("Retorna 1 Usuario")
    void deveRetornaUmUsuario() {
        Usuario user = repository.findByUsuario("camibaza").get();
        assertTrue(user.getUsuario().equals("camibaza"));
    }

	
	
}