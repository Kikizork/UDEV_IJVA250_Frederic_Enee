package com.example.demo.service.export.com.example.demo.service.export;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ClientDto;
import com.example.demo.service.ClientService;

@Service
public class ExportClientXSLX {

	@Autowired
	private ClientService clientService;

	public void exportAll(OutputStream outputStream) throws IOException {

		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet("Clients");

		Font font = workbook.createFont();

		List<ClientDto> listeClientDto = clientService.findAllClients();

		HashMap<String, Object> properties = new HashMap<String, Object>();

		Row headerRow = sheet.createRow(0);

		Cell cellNomHeader = headerRow.createCell(0);
		Cell cellPrenomHeader = headerRow.createCell(1);
		Cell cellAgeHeader = headerRow.createCell(2);

		cellNomHeader.setCellValue("Nom");
		cellPrenomHeader.setCellValue("Prénom");
		cellAgeHeader.setCellValue("Age");

		properties.put(CellUtil.BORDER_TOP, BorderStyle.THICK);
		properties.put(CellUtil.BORDER_BOTTOM, BorderStyle.THICK);
		properties.put(CellUtil.BORDER_LEFT, BorderStyle.THICK);
		properties.put(CellUtil.BORDER_RIGHT, BorderStyle.THICK);

		properties.put(CellUtil.TOP_BORDER_COLOR, IndexedColors.BLUE.getIndex());
		properties.put(CellUtil.BOTTOM_BORDER_COLOR, IndexedColors.BLUE.getIndex());
		properties.put(CellUtil.LEFT_BORDER_COLOR, IndexedColors.BLUE.getIndex());
		properties.put(CellUtil.RIGHT_BORDER_COLOR, IndexedColors.BLUE.getIndex());

		font.setColor(IndexedColors.PINK.getIndex());
		font.setBold(true);

		CellUtil.setCellStyleProperties(cellNomHeader, properties);
		CellUtil.setCellStyleProperties(cellPrenomHeader, properties);
		CellUtil.setCellStyleProperties(cellAgeHeader, properties);

		CellUtil.setFont(cellNomHeader, font);
		CellUtil.setFont(cellPrenomHeader, font);
		CellUtil.setFont(cellAgeHeader, font);

		for (int i = 0; i < listeClientDto.size(); i++) {
			Row clientRow = sheet.createRow(i + 1);
			Cell cellNomClient = clientRow.createCell(0);
			Cell cellPrenomClient = clientRow.createCell(1);
			Cell cellAgeClient = clientRow.createCell(2);

			cellNomClient.setCellValue(listeClientDto.get(i).nom);
			cellPrenomClient.setCellValue(listeClientDto.get(i).prenom);
			cellAgeClient.setCellValue(listeClientDto.get(i).ageClient + " ans");

			CellUtil.setCellStyleProperties(cellNomClient, properties);
			CellUtil.setCellStyleProperties(cellPrenomClient, properties);
			CellUtil.setCellStyleProperties(cellAgeClient, properties);

		}
		workbook.write(outputStream);
		workbook.close();
	}

}
