package com.galarza.gestorlogistica;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GestorlogisticaApplicationTests {

	@Test
	void contextLoads() {
		// Test de contexto de Spring Boot
	}

	public static void main(String[] args) {
		String[] contraseñas = {"1234", "logi123", "clave123"};
		for (String pass : contraseñas) {
			String hash = org.mindrot.jbcrypt.BCrypt.hashpw(pass, org.mindrot.jbcrypt.BCrypt.gensalt());
			System.out.println(pass + " -> " + hash);
		}
	}
}