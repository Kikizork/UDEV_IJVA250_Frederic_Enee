package com.example.demo.service.export.com.example.demo.service.export;

import java.io.IOException;
import java.io.OutputStream;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Client;
import com.example.demo.entity.Facture;
import com.example.demo.entity.LigneFacture;
import com.example.demo.repository.ClientRepository;
import com.example.demo.service.FactureService;

@Service
public class ExportFactureXSLX {

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private FactureService factureService;

	public void exportAll(OutputStream outputStream) throws IOException {

		Workbook workbook = new XSSFWorkbook();

		// Je suis parti du principe qu'un client sans facture ne devait pas être
		// affiché. Je sais que j'aurais pu le faire autrement, mais le moins je tripote
		// un modèle sans base de donnée mieux je me portes
		List<Client> listAllClient = clientRepository.findAll().stream().filter(s -> !s.getListFacture().isEmpty())
				.collect(Collectors.toList());

		for (Client client : listAllClient) {
			Sheet clientSheet = workbook.createSheet(client.getNom() + " " + client.getPrenom());
			Font font = workbook.createFont();
			font.setBold(true);
			Row nomRow = clientSheet.createRow(0);
			Row prenomRow = clientSheet.createRow(1);
			Row dateDeNaissanceRow = clientSheet.createRow(2);
			Cell cellNomHeader = nomRow.createCell(0);
			Cell cellPrenomHeader = prenomRow.createCell(0);
			Cell cellDateDeNaissanceHeader = dateDeNaissanceRow.createCell(0);
			cellNomHeader.setCellValue("Nom");
			cellPrenomHeader.setCellValue("Prénom");
			cellDateDeNaissanceHeader.setCellValue("Date de naissance");
			Cell cellNomValue = nomRow.createCell(1);
			Cell cellPrenomValue = prenomRow.createCell(1);
			Cell cellDateDeNaissanceValue = dateDeNaissanceRow.createCell(1);
			cellNomValue.setCellValue(client.getNom());
			cellPrenomValue.setCellValue(client.getPrenom());
			cellDateDeNaissanceValue
					.setCellValue(client.getDateNaissance().format(DateTimeFormatter.ofPattern("dd/MM/yy")));
			for (Facture facture : client.getListFacture()) {

				Sheet factureSheet = workbook.createSheet("Facture n° " + facture.getId());

				Row headerRow = factureSheet.createRow(0);

				Cell designationCell = headerRow.createCell(0);
				Cell quantiteCell = headerRow.createCell(1);
				Cell prixUnitaireCell = headerRow.createCell(2);

				designationCell.setCellValue("Désignation");
				quantiteCell.setCellValue("Quantité");
				prixUnitaireCell.setCellValue("Prix unitaire");

				CellUtil.setFont(designationCell, font);
				CellUtil.setFont(quantiteCell, font);
				CellUtil.setFont(prixUnitaireCell, font);

				List<LigneFacture> listLigneFactures = new ArrayList<>(facture.getLigneFactures());

				for (int i = 0; i < listLigneFactures.size(); i++) {
					Row articleRow = factureSheet.createRow(i + 1);

					Cell designationCellValue = articleRow.createCell(0);
					Cell quantiteCellValue = articleRow.createCell(1);
					Cell prixUnitaireCellValue = articleRow.createCell(2);

					designationCellValue.setCellValue(listLigneFactures.get(i).getArticle().getLibelle());
					quantiteCellValue.setCellValue(listLigneFactures.get(i).getQuantite());
					prixUnitaireCellValue.setCellValue(listLigneFactures.get(i).getArticle().getPrix());

				}
			}
		}
		workbook.write(outputStream);
		workbook.close();
	}

}
