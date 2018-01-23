package br.com.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.crud.model.Cliente;
import br.com.crud.repository.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repository;
	
	public void save(Cliente cliente) {
		repository.save(cliente);	
	}
	
	public void remover(Long idCliente) {
		repository.delete(idCliente);
	}
	
	public List<Cliente> list (){
		return repository.findAll();
	}
	
	public Cliente buscaCliente(Long idCliente) {
		return repository.findOne(idCliente);
	}
	
	
	public void update(Cliente cliente) {
		repository.saveAndFlush(cliente);	
	}

}
