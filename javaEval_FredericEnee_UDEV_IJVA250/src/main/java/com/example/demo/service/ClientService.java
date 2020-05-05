package com.example.demo.service;

import static java.util.stream.Collectors.toList;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.ClientDto;
import com.example.demo.repository.ClientRepository;

/**
 * Service contenant les actions métiers liées aux clients.
 */
@Service
@Transactional
public class ClientService {

	private ClientRepository clientRepository;

	public ClientService(ClientRepository clientRepository) {
		this.clientRepository = clientRepository;
	}

	public List<ClientDto> findAllClients() {
		// Transformation d'une liste de Client en ClientDto
		return clientRepository.findAll().stream().map(c -> new ClientDto(c.getId(), c.getNom(), c.getPrenom(),
				getAgeClient(c.getDateNaissance()), c.getDateNaissance())).collect(toList());
	}

	private Long getAgeClient(LocalDate dateNaissance) {
		return ChronoUnit.YEARS.between(dateNaissance, LocalDate.now());
	}
}
