package dev.console;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dev.service.CalculService;

public class App {
	private static final Logger LOG = LoggerFactory.getLogger(App.class);
	private Scanner scanner;
	private CalculService calculatrice;

	public App(Scanner scanner, CalculService calculatrice) {
		this.scanner = scanner;
		this.calculatrice = calculatrice;
	}


	public void demarrer() {
		afficherTitre();
	}

	protected void evaluer(String expression) {
		LOG.info(expression+"="+ calculatrice.additionner(expression));
	}

	public void afficherTitre() {
		LOG.info("**** Application Calculatrice ****");
		
	}
}
