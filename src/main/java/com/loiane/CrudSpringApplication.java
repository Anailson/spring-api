package com.loiane;

import com.loiane.model.Curso;
import com.loiane.repository.CursoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CrudSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudSpringApplication.class, args);
	}

	@Bean
	CommandLineRunner initDataBase(CursoRepository cursoRepository){
		return  args -> {
				cursoRepository.deleteAll();;

				Curso c = new Curso();
				c.setNome("Angular com Spring");
				c.setCategoria("Front-end"); //Front-end

				cursoRepository.save(c);
		};
	}


}
