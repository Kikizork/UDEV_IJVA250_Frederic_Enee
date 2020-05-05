package com.example.demo.service.export.com.example.demo.service.export;

import java.io.PrintWriter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.service.ClientService;

@Service
public class ExportClientCSV {

	@Autowired
	private ClientService clientService;

	public void exportAll(PrintWriter writer) {

		// Ecritures du nom des colonnes
		writer.println("Nom;Prenom;Age");

		// Ecritures des données au format csv une par une
		clientService.findAllClients().stream()
				.forEach(client -> writer.println(client.nom + ";" + client.prenom + ";" + client.ageClient + " ans"));

	}

}
