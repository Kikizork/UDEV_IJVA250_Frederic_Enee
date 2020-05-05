package com.example.demo.dto;

import java.util.List;

/**
 * Classe permettant d'exposer des données au format JSON d'une facture.
 */
public class FactureDto {

	public Long id;

	public ClientDto client;

	public List<LigneFactureDto> ligneFactures;

	public FactureDto(Long id, ClientDto client, List<LigneFactureDto> ligneFactures) {
		this.id = id;
		this.client = client;
		this.ligneFactures = ligneFactures;
	}

	@Override
	public String toString() {
		return "FactureDto [id=" + id + ", client=" + client + ", ligneFactures=" + ligneFactures + "]";
	}

}
