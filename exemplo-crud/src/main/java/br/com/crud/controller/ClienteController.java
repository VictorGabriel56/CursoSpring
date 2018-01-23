package br.com.crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import br.com.crud.model.Cliente;
import br.com.crud.service.ClienteService;

@Controller
@RequestMapping("/")
public class ClienteController {
	
	@Autowired
	private ClienteService service;
	
	@RequestMapping("/formCad")
	public String formulario() {
		return "form";
	}
	
	
	@GetMapping("/listar")
	public ResponseEntity<List<Cliente>> listar(){
		
		List<Cliente> clientes = service.list();
		
		if(clientes.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<List<Cliente>>(clientes, HttpStatus.OK);		
	}
	
	
	@RequestMapping(value="/salvar", method=RequestMethod.PUT)
	public ResponseEntity<List<Cliente>> salvar(@RequestBody Cliente cliente){
		
		if(cliente == null) {			
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);				
		}
		
		
		service.save(cliente);
		
		List<Cliente> clientes = service.list();
		
		return new ResponseEntity<List<Cliente>>(clientes, HttpStatus.OK);	
	}
	
	
	@GetMapping("/buscar/{id}")
	public ResponseEntity<Cliente> buscar(@PathVariable("id")Long id){
		Cliente cliente = service.buscaCliente(id);
		
		if(cliente == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
	}
	
	
	@GetMapping("/deletar/{id}")
	public ResponseEntity<List<Cliente>> delete(@PathVariable("id") Long id){
		Cliente cliente = service.buscaCliente(id);	
		
		if(cliente == null) {			
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
		service.remover(id);
		
		List<Cliente> clientes = service.list();
		
		return new ResponseEntity<List<Cliente>>(clientes, HttpStatus.OK);		
	}
	
	
	@RequestMapping(value="/editar/{id}", method=RequestMethod.PUT)
	public ResponseEntity<List<Cliente>> editar(@PathVariable("id") Long id, @RequestBody Cliente cliente) {
		Cliente verifica = service.buscaCliente(id);
		
		if(verifica == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
		Cliente update = new Cliente();
		
		update.setId(id);
		update.setNome(cliente.getNome());
		update.setEmail(cliente.getEmail());
		
		service.update(update);
		
		List<Cliente> clientes = service.list();
		
		return new ResponseEntity<List<Cliente>>(clientes, HttpStatus.OK);
	}
}
