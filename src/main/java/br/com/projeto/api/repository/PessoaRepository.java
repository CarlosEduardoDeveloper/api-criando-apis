package br.com.projeto.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.projeto.api.model.Pessoa;

@Repository
public interface PessoaRepository extends CrudRepository<Pessoa, Long> {
	
	List<Pessoa> findAll();
	
	Optional<Pessoa> findById(Long id);
	
	List<Pessoa> findByOrderByNomeDesc();

	List<Pessoa> findByNomeOrderByIdadeAsc(String nome);
	
	List<Pessoa> findByNomeContaining(String termo);
	
	List<Pessoa> findByNomeStartsWith(String termo);
	
	List<Pessoa> findByNomeEndsWith(String termo);
	
	@Query(value = "SELECT SUM(idade) FROM pessoa", nativeQuery = true)
	int somaIdades();
	
	@Query(value = "SELECT * FROM pessoa WHERE idade >= :idade", nativeQuery =  true)
	List<Pessoa> idadeMaiorIgual(int idade);
	
	int countById(Long id);

	Object save(Optional<Pessoa> p);
}
