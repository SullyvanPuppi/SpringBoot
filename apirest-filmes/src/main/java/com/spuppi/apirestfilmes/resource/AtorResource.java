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
import com.spuppi.apirestfilmes.repository.AtorRepository;

import io.swagger.annotations.ApiOperation;

/**
 * @author Sullyvan Puppi
 * spuppi.com - spuppi@gmail.com
 * 16 de fev de 2019
 *
 */
@RestController
@RequestMapping("/atores")
public class AtorResource {

	@Autowired
	private AtorRepository ar;
	
	@ApiOperation(value = "Retorna a lista de atores cadastrados")
	@GetMapping(produces = "application/json")
	public @ResponseBody Iterable<Ator> listarAtores(){
		Iterable<Ator> listaAtores = ar.findAll();
		return listaAtores;
	}
	
	@ApiOperation(value = "Cadastrar novo Ator")
	@PostMapping
	public Ator adicionarAtor(@Valid @RequestBody Ator ator) {
		return ar.save(ator); 
	}
	
	@ApiOperation(value = "Atualizar cadastro de ator por Id")
	@PutMapping(value = "/{id}")
	public ResponseEntity<Ator> atualizarAtor(@PathVariable Long id, @Valid @RequestBody Ator ator){
		Ator atorExistente = ar.getOne(id);
		
		if(atorExistente == null) {
			return ResponseEntity.notFound().build();
		}
		
		BeanUtils.copyProperties(ator, atorExistente, "id");
		
		atorExistente = ar.save(atorExistente);
		
		return ResponseEntity.ok(atorExistente);
	}
	
	@ApiOperation(value = "Remover Ator por Id")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> removerAtor(@PathVariable Long id){
		Ator atorExistente = ar.getOne(id);
		
		if(atorExistente == null) {
			return ResponseEntity.notFound().build();
		}
		
		ar.delete(atorExistente);
		
		return ResponseEntity.noContent().build();
	}
	
}
