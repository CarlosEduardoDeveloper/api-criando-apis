package br.com.projeto.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.projeto.api.model.Mensagem;
import br.com.projeto.api.model.Pessoa;
import br.com.projeto.api.repository.PessoaRepository;

@Service
public class Servico {
	
	@Autowired
	Mensagem mensagem;
	
	@Autowired
	private PessoaRepository repository;
	
	public ResponseEntity<?> cadastrar(Pessoa p){
		if(p.getNome().equals("")) {
			mensagem.setMensagem("O nome precisa ser preenchido");
			return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
		}else if(p.getIdade() < 0) {
			mensagem.setMensagem("Informe uma idade válida!");
			return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
		}else{
			repository.save(p);
			return new ResponseEntity<>(mensagem, HttpStatus.CREATED);
		}
	}
	
	public ResponseEntity<?> selecionar(){
		return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
	}
	
	public ResponseEntity<?> selecionarPeloId(Long id){
		if(repository.countById(id) == 0) {
			mensagem.setMensagem("Não foi encontrada nenhuma pessoa.");
			return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
		}else {
			return new ResponseEntity<>(repository.findById(id), HttpStatus.CREATED);
		}
	}
	
	public ResponseEntity<?> editar(Pessoa p){
		if(repository.countById(p.getId()) == 0) {
			mensagem.setMensagem("O id informado não existe.");
			return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
		}else if(p.getNome().equals("")) {
			mensagem.setMensagem("É necessário informar um nome.");
			return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
		}else if(p.getIdade() < 0) {
			mensagem.setMensagem("Informe uma idade válida.");
			return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
		}else {
			repository.save(p);
			return new ResponseEntity<>(repository.save(p), HttpStatus.CREATED);
		}
	}
	
	public ResponseEntity<?> remover(Long id){
		if(repository.countById(id) == 0) {
			mensagem.setMensagem("O id informado não existe.");
			return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
		} else {
			Optional<Pessoa> p = repository.findById(id);
			repository.deleteById(id);
			
			mensagem.setMensagem("Pessoa removida com sucesso.");
			return new ResponseEntity<>(repository.save(p), HttpStatus.OK);
		}
	}

}
