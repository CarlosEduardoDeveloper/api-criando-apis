package br.com.projeto.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.projeto.api.model.Cliente;
import br.com.projeto.api.model.Pessoa;
import br.com.projeto.api.repository.PessoaRepository;
import br.com.projeto.api.service.Servico;

@RestController
public class Controller {
	
	@Autowired
	private PessoaRepository repository;
	
	@Autowired
	private Servico service;
	
	@PostMapping("/api")
	public ResponseEntity<?> cadastrar(@RequestBody Pessoa p) {
		return service.cadastrar(p);
	}
	
	@GetMapping("/api")
	public ResponseEntity<?> selecionar(){
		return service.selecionar();
	}
	
	@GetMapping("/api/{id}")
	public ResponseEntity<?> selecionarPeloId(@PathVariable Long id) {
		return service.selecionarPeloId(id);
	}
	
	@PutMapping("/api")
	public ResponseEntity<?> editar(@RequestBody Pessoa p) {
		return service.editar(p);
	}
	
	@DeleteMapping("/api/{id}")
	public ResponseEntity<?> remover(@PathVariable Long id) {
		return service.remover(id);
	}
	
	@GetMapping("/api/contador")
	public long contator() {
		return repository.count();
	}
	
	@GetMapping("/api/ordenarNomes")
	public List<Pessoa> ordenarNomes(){
		return repository.findByOrderByNomeDesc();
	}
	
	@GetMapping("/api/ordenarNomes2")
	public List<Pessoa> ordenarNomes2(){
		return repository.findByNomeOrderByIdadeAsc("Eduardo");
	}
	
	@GetMapping("/api/nomeContem")
	public List<Pessoa> nomeContem(){
		return repository.findByNomeContaining("A");
	}
	
	@GetMapping("/api/iniciaCom")
	public List<Pessoa> iniciaCom(){
		return repository.findByNomeStartsWith("A");
	}
	
	@GetMapping("/api/terminaCom")
	public List<Pessoa> terminaCom(){
		return repository.findByNomeEndsWith("a");
	}
	
	@GetMapping("/api/somaIdades")
	public int somaIdades() {
		return repository.somaIdades();
	}
	
	@GetMapping("/api/idadeMaiorIgual")
	public List<Pessoa> idadeMaiorIgual() {
		return repository.idadeMaiorIgual(30);
	}
	
	
	
	@GetMapping("/")
	public String mensagem() {
		return "Hello world";
	}
	
	@GetMapping("/boasVindas/{nome}")
	public String boasVindas(@PathVariable String nome) {
		return "Seja bem vindo(a)" +nome+ "!";
	}
	
	@PostMapping("/pessoa")
	public Pessoa pessoa(@RequestBody Pessoa p) {
		return p;
	} 
	
	@GetMapping("/status")
	public ResponseEntity<?> status(){
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@PostMapping("/cliente")
	public void cliente(@Valid @RequestBody Cliente c) {
		
	}

}
