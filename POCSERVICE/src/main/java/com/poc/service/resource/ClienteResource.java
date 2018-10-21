package com.poc.service.resource;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poc.service.api.Cliente;
import com.poc.service.repository.ClienteRepository;

@RestController
@RequestMapping("/cliente")
public class ClienteResource {

	@Autowired
	private ClienteRepository clienteRepository;

	@CrossOrigin
	@PostMapping("/cadastrar")
	public ResponseEntity<Long> cadastrarCliente(@Valid @RequestBody Cliente cliente) {
		Cliente clienteCadastrado = clienteRepository.save(cliente);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Access-Control-Allow-Headers", "*");
		headers.add("Access-Control-Allow-Credentials", "true");
		headers.add("Access-Control-Allow-Methods", "GET,POST");
		headers.add("Content-Type", "application/json");
		return ResponseEntity.status(HttpStatus.OK).headers(headers).body(clienteCadastrado.getCodigo());
	}

	@CrossOrigin
	@GetMapping("/{codigo}")
	public ResponseEntity<Cliente> consultarCliente(@PathVariable Long codigo) {
		Optional<Cliente> cliente = clienteRepository.findById(codigo);
		if (cliente.isPresent()) {
			HttpHeaders headers = new HttpHeaders();
			headers.add("Access-Control-Allow-Headers", "*");
			headers.add("Access-Control-Allow-Credentials", "true");
			headers.add("Access-Control-Allow-Methods", "GET,POST");
			headers.add("Content-Type", "application/json");
			return ResponseEntity.status(HttpStatus.OK).headers(headers).body(cliente.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
