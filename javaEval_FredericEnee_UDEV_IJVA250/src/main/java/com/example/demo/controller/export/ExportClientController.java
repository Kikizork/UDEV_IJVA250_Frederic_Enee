package com.example.demo.controller.export;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.service.export.com.example.demo.service.export.ExportClientCSV;
import com.example.demo.service.export.com.example.demo.service.export.ExportClientXSLX;

/**
 * Controller pour réaliser l'export des clients.
 */
@Controller
@RequestMapping("export")
public class ExportClientController {

	@Autowired
	private ExportClientCSV exportClientCSV;

	@Autowired
	private ExportClientXSLX exportClientXSLX;

	/**
	 * 
	 * Réalise l'export des clients au format csv
	 * 
	 * @param request  objet reprensantant la requête http
	 * @param response objet reprensantant la réponse http
	 */
	@GetMapping("/clients/csv")
	public void clientsCSV(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// positionne de metadata sur la réponse afin d'informer le navigateur que la
		// réponse correspond à un fichier téléchargeable.
		response.setContentType("text/csv");
		response.setHeader("Content-Disposition", "attachment; filename=\"export-client.csv\"");

		// Le writter est un objet provenant de la response dans lequel on va pouvoir
		// écrire pour générer le contenu de l'export CSV.
		PrintWriter writer = response.getWriter();

		exportClientCSV.exportAll(writer);
	}

	@GetMapping("/clients/xlsx")
	public void clientsXLSX(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition", "attachment; filename=\"export-client.xlsx\"");

		OutputStream outputStream = response.getOutputStream();

		exportClientXSLX.exportAll(outputStream);
	}

}
