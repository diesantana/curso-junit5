package com.example.finans.infra;

import static com.example.finans.domain.builders.UsuarioBuilderByMaster.umUsuario;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.example.finans.domain.Usuario;
import com.example.finans.domain.exception.ValidationException;
import com.example.finans.service.UsuarioService;

@Tag("infra")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserServiceComUserMemoryRepositoryTest {

	private static UsuarioService service = new UsuarioService(new UsuarioMemoryRepository());
	
	
	@Test
	@Order(1)
	void deveSalvarUsuarioValido() {
		Usuario user = service.salvar(umUsuario().comId(null).agora());
		assertNotNull(user.getId());
	}
	
	@Test
	@Order(2)
	void deveRejeitarUsuarioExistente() {
		ValidationException exception = assertThrows(ValidationException.class, () -> {
			service.salvar(umUsuario().comId(null).agora());
		});
		assertEquals("Usuário bob@email.com já cadastrado!", exception.getMessage());
	}
}
