package com.example.demo.dto;

import java.time.LocalDate;

/**
 * Classe permettant d'exposer des données au format JSON d'un client.
 */
public class ClientDto {

	public Long id;

	public String nom;

	public String prenom;

	public Long ageClient;

	public LocalDate dateDeNaissance;

	public ClientDto(Long id, String nom, String prenom, Long ageClient, LocalDate dateDeNaissance) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.ageClient = ageClient;
		this.dateDeNaissance = dateDeNaissance;
	}

	@Override
	public String toString() {
		return "ClientDto [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", ageClient=" + ageClient
				+ ", dateDeNaissance=" + dateDeNaissance + "]";
	}

}
