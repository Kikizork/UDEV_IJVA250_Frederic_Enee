package com.example.demo.service.export.com.example.demo.service.export;

import java.io.PrintWriter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.service.ArticleService;

@Service
public class ExportArticleCSV {

	@Autowired
	private ArticleService articleService;

	public void exportAll(PrintWriter writer) {

		// Ecritures du nom des colonnes
		writer.println("Libelle;Prix");

		// Ecritures des données au format csv une par une
		articleService.findAll().stream().forEach(art -> writer.println(art.libelle + ";" + art.prix));

	}

}
