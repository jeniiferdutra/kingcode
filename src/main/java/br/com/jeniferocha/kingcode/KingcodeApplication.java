package br.com.jeniferocha.kingcode;

import br.com.jeniferocha.kingcode.main.Main;
import br.com.jeniferocha.kingcode.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KingcodeApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(KingcodeApplication.class, args);
	}

	@Autowired
	private LivroService service;

	@Override
	public void run(String... args) throws Exception {
		Main main = new Main(service);
		main.buscarLivro();
	}
}
