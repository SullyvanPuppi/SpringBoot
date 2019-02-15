package com.spuppi.apirestfilmes.resource;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.spuppi.apirestfilmes.model.Ator;
import com.spuppi.apirestfilmes.model.Filme;
import com.spuppi.apirestfilmes.model.Genero;
import com.spuppi.apirestfilmes.repository.AtorRepository;
import com.spuppi.apirestfilmes.repository.FilmeRepository;
import com.spuppi.apirestfilmes.repository.GeneroRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author Sullyvan Puppi
 * spuppi.com - spuppi@gmail.com
 * 15 de fev de 2019
 *
 */
@Api(value="API REST Filmes")
@RestController
@RequestMapping("/filmes")
public class FilmesResource {
	
	@Autowired
	private FilmeRepository fr;
	
	@Autowired
	private GeneroRepository gr;
	
	@Autowired
	private AtorRepository ar;

	@ApiOperation(value = "Retorna a lista de filmes cadastrados")
	@GetMapping(produces = "application/json")
	public @ResponseBody Iterable<Filme> listarFilmes(){
		Iterable<Filme> listaFilmes = fr.findAll();
		return listaFilmes;
	}
	
	@ApiOperation(value = "Retorna a lista de genêros cadastrados")
	@GetMapping(value = "/generos", produces = "application/json")
	public @ResponseBody Iterable<Genero> listarGeneros(){
		Iterable<Genero> listaGeneros = gr.findAll();
		return listaGeneros;
	}
	
	@ApiOperation(value = "Retorna a lista de atores cadastrados")
	@GetMapping(value = "/atores", produces = "application/json")
	public @ResponseBody Iterable<Ator> listarAtores(){
		Iterable<Ator> listaAtores = ar.findAll();
		return listaAtores;
	}
	
	@ApiOperation(value = "Cadastrar novo filme")
	@PostMapping
	public Filme adicionarFilme(@Valid @RequestBody Filme filme) {
		return fr.save(filme);
	}
	
	@ApiOperation(value = "Cadastrar novo Gênero")
	@PostMapping(value = "/generos")
	public Genero adicionarGenero(@Valid @RequestBody Genero genero) {
		return gr.save(genero);
	}
	
	@ApiOperation(value = "Cadastrar novo Ator")
	@PostMapping(value = "/atores")
	public Ator adicionarAtor(@Valid @RequestBody Ator ator) {
		return ar.save(ator); 
	}
	
	@ApiOperation(value = "Atualizar filme por Id")
	@PutMapping(value = "/{id}")
	public ResponseEntity<Filme> atualizarFilme(@PathVariable Long id, @Valid @RequestBody Filme filme) {
		Filme filmeExistente = fr.getOne(id);
		
		if(filmeExistente == null) {
			return ResponseEntity.notFound().build();
		}
		
		BeanUtils.copyProperties(filme, filmeExistente, "id");
		
		filmeExistente = fr.save(filme);
		
		return ResponseEntity.ok(filmeExistente);		
	}
	
	@ApiOperation(value = "Atualizar Gênero de Filmes por Id")
	@PutMapping(value = "/generos/{id}")
	public ResponseEntity<Genero> atualizarGenero(@PathVariable Long id, @Valid @RequestBody Genero genero){
		Genero generoExistente = gr.getOne(id);
		
		if(generoExistente == null) {
			return ResponseEntity.notFound().build();
		}
		
		BeanUtils.copyProperties(genero, generoExistente, "id");
		
		return ResponseEntity.ok(generoExistente);
	}
	
	@ApiOperation(value = "Atualizar cadastro de ator por Id")
	@PutMapping(value = "/atores/{id}")
	public ResponseEntity<Ator> atualizarAtor(@PathVariable Long id, @Valid @RequestBody Ator ator){
		Ator atorExistente = ar.getOne(id);
		
		if(atorExistente == null) {
			return ResponseEntity.notFound().build();
		}
		
		BeanUtils.copyProperties(ator, atorExistente, "id");
		
		return ResponseEntity.ok(atorExistente);
	}
	
	@ApiOperation(value = "Deletar filme por Id")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> removerFilme(@PathVariable Long id){
		Filme filmeExistente = fr.getOne(id);
		
		if(filmeExistente == null) {
			return ResponseEntity.notFound().build();
		}

		fr.delete(filmeExistente);
		
		return ResponseEntity.noContent().build();
	}
	
	@ApiOperation(value = "Deletar gênero por Id")
	@DeleteMapping(value = "/generos/{id}")
	public ResponseEntity<Void> removerGenero(@PathVariable Long id){
		Genero generoExistente = gr.getOne(id);
		
		if(generoExistente == null) {
			return ResponseEntity.notFound().build();
		}
		
		gr.delete(generoExistente);
		
		return ResponseEntity.noContent().build();		
	}
	
	@ApiOperation(value = "Remover Ator por Id")
	@DeleteMapping(value = "/atores/{id}")
	public ResponseEntity<Void> removerAtor(@PathVariable Long id){
		Ator atorExistente = ar.getOne(id);
		
		if(atorExistente == null) {
			return ResponseEntity.notFound().build();
		}
		
		ar.delete(atorExistente);
		
		return ResponseEntity.noContent().build();
	}
	
}
