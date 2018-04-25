package dev.console;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Scanner;

import org.junit.Before;
import org.junit.Rule;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dev.exception.CalculException;
import dev.service.CalculService;

public class AppTest {

	private static final Logger LOG = LoggerFactory.getLogger(AppTest.class);

	@Rule
	public TextFromStandardInputStream systemInMock = emptyStandardInputStream();
	@Rule
	public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

	private App app;
	private CalculService calculService;

	@Before
	public void setUp() throws Exception {
		Scanner sc = new Scanner(System.in);
		this.calculService = mock(CalculService.class);
		this.app = new App(sc, calculService);
	}

	@org.junit.Test
	public void testAfficherTitre() throws Exception {
		this.app.afficherTitre();
		String logConsole = systemOutRule.getLog();
		assertThat(logConsole).contains("**** Application Calculatrice ****");
	}

	@org.junit.Test
	public void testEvaluer() throws Exception {
		LOG.info("Etant donné, un service CalculService qui retourne 35 à l'évaluation de l'expression 1+34");
		String expression = "1+34";
		when(calculService.additionner(expression)).thenReturn(35);
		LOG.info("Lorsque la méthode evaluer est invoquée");
		this.app.evaluer(expression);
		LOG.info("Alors le service est invoqué avec l'expression {}", expression);
		verify(calculService).additionner(expression);
		LOG.info("Alors dans la console, s'affiche 1+34=35");
		assertThat(systemOutRule.getLog()).contains("1+34=35");
	}

	@org.junit.Test(expected = CalculException.class)
	public void testEvaluerInvalide() throws Exception {
		LOG.info("Etant donné, un service CalculService qui retourne erreur à l'évaluation de l'expression 1++34");
		String expression = "1++34";
		when(calculService.additionner(expression)).thenThrow(new CalculException());
		LOG.info("Lorsque la méthode evaluer est invoquée");
		this.app.evaluer(expression);
		LOG.info("Alors le service est invoqué avec l'expression {}", expression);
		verify(calculService).additionner(expression);
		LOG.info("Alors dans la console, s'affiche L’expression 1++34 est invalide");
		assertThat(systemOutRule.getLog()).contains("L’expression 1++34 est invalide");
	}
	
	@org.junit.Test
	public void testEvaluerFin() throws Exception {
		String expression = "fin";
		this.app = new App(new Scanner(System.in),calculService);
		this.app.demarrer();
		
		String logConsole = systemOutRule.getLog();
		
		assertThat(logConsole).contains("**** Application Calculatrice ****"
		+ "Veuillez saisir une expression"
		+"au revoir :(");
	}

	@org.junit.Test
	public void testEvaluerCalculeFin() throws Exception {
		String expression = "1+2 fin";
		systemInMock.provideLines(expression,"fin");
		this.app = new App(new Scanner(System.in),calculService);
		
		when(calculService.additionner(expression)).thenReturn(3);
		this.app.demarrer();
		verify(calculService).additionner(expression);
		
		String logConsole = systemOutRule.getLog();
		
		assertThat(systemOutRule.getLog()).contains("**** Application Calculatrice ****"
		+ "Veuillez saisir une expression"
		+"1+2=3\r\n Veuillez saisir une expression"
		+"au revoir :(");
		
	}
	
	@org.junit.Test
	public void testEvaluerAAAFin() throws Exception {
		String expression = "AAA fin";
		systemInMock.provideLines(expression,"fin");
		this.app = new App(new Scanner(System.in),calculService);
		
		when(calculService.additionner(expression)).thenThrow(new CalculException());		
		verify(calculService).additionner(expression);
		
		String logConsole = systemOutRule.getLog();
		
		assertThat(logConsole).contains("**** Application Calculatrice ****\n\r"
				+ "Veuillez saisir une expression\n\r"
				+"l'expresion AAA est non valide\r\n Veuillez saisir une expression\n\r"
				+"au revoir :(");
	}
	
	@org.junit.Test
	public void testEvaluerCaclculCalculFin() throws Exception {
			String expression1 = "1+2";
			String expression2 = "34+1";
			systemInMock.provideLines(expression1,expression2,"fin");
			this.app = new App(new Scanner(System.in),calculService);
			when(calculService.additionner(expression1)).thenReturn(3);
			when(calculService.additionner(expression2)).thenReturn(35);
			this.app.demarrer();
			verify(calculService).additionner(expression1);
			verify(calculService).additionner(expression2);
			
			
			String logConsole = systemOutRule.getLog();
			
			assertThat(logConsole).contains("**** Application Calculatrice ****\n\r"
			+ "Veuillez saisir une expression\n\r"
			+"1+2=3\r\n Veuillez saisir une expression\n\r"
			+"34+1=35\r\n Veuillez saisir une expression\n\r"
			+"au revoir :(");
	}
}
