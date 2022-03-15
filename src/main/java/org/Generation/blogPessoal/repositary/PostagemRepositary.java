package org.Generation.blogPessoal.repositary;
import java.util.List;

import org.Generation.blogPessoal.model.Postagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PostagemRepositary extends JpaRepository<Postagem, Long> {
	public List<Postagem>findAllByTituloContainingIgnoreCase(String Titulo);
	
}

