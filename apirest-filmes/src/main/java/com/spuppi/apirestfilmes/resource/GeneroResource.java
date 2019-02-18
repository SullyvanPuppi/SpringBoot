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

import com.spuppi.apirestfilmes.model.Genero;
import com.spuppi.apirestfilmes.repository.GeneroRepository;

import io.swagger.annotations.ApiOperation;

/**
 * @author Sullyvan Puppi
 * spuppi.com - spuppi@gmail.com
 * 16 de fev de 2019
 *
 */
@RestController
@RequestMapping("/generos")
public class GeneroResource {

	@Autowired
	private GeneroRepository gr;
	
	@ApiOperation(value = "Retorna a lista de genêros cadastrados")
	@GetMapping(produces = "application/json")
	public @ResponseBody Iterable<Genero> listarGeneros(){
		Iterable<Genero> listaGeneros = gr.findAll();
		return listaGeneros;
	}
	
	@ApiOperation(value = "Cadastrar novo Gênero")
	@PostMapping
	public Genero adicionarGenero(@Valid @RequestBody Genero genero) {
		return gr.save(genero);
	}
	
	@ApiOperation(value = "Atualizar Gênero de Filmes por Id")
	@PutMapping(value = "/{id}")
	public ResponseEntity<Genero> atualizarGenero(@PathVariable Long id, @Valid @RequestBody Genero genero){
		Genero generoExistente = gr.getOne(id);
		
		if(generoExistente == null) {
			return ResponseEntity.notFound().build();
		}
		
		BeanUtils.copyProperties(genero, generoExistente, "id");
		
		generoExistente = gr.save(generoExistente);
		
		return ResponseEntity.ok(generoExistente);
	}
	
	@ApiOperation(value = "Deletar gênero por Id")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> removerGenero(@PathVariable Long id){
		Genero generoExistente = gr.getOne(id);
		
		if(generoExistente == null) {
			return ResponseEntity.notFound().build();
		}
		
		gr.delete(generoExistente);
		
		return ResponseEntity.noContent().build();		
	}
	
}
