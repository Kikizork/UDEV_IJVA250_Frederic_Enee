package com.example.demo.controller.export;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.service.export.com.example.demo.service.export.ExportFactureXSLX;

/**
 * Controller pour réaliser l'export des articles.
 */
@Controller
@RequestMapping("export")
public class ExportFactureController {

	@Autowired
	private ExportFactureXSLX exportFactureXSLX;

	/**
	 * Export des articles au format CSV, déclenché sur l'url
	 * http://.../export/articles/csv
	 *
	 * @param request  objet reprensantant la requête http
	 * @param response objet reprensantant la réponse http
	 */
	@GetMapping("/factures/xlsx")
	public void facturesXLSX(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition", "attachment; filename=\"export-facture.xlsx\"");

		OutputStream outputStream = response.getOutputStream();

		exportFactureXSLX.exportAll(outputStream);
	}

}
