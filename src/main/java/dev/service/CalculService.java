package dev.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dev.exception.CalculException;

public class CalculService {
	private static final Logger LOG = LoggerFactory.getLogger(CalculService.class);

	public float additionner(String expression) {
		LOG.debug("Evaluation de l'epression" + expression);

		char valeur[] = new char[150];
		float[][] numerique = new float[2][150];
		int[][] operateur = new int[2][150];
		int verif = 0;
		int compteur = 0;
		int indiceErreur = 0;
		int i = 0;
		int j = 0;
		int asci;
		int puissance = 0;
		int nbnombre = 0;
		int compare = 0;
		int v = 0;
		float operation = 0;
		float[] nombre = new float[150];

		for (i = 0; i < 150; i++) {
			for (j = 0; j < 2; j++) {
				numerique[j][i] = 0;
				operateur[j][i] = 0;
			}
			valeur[i] = 0;
			nombre[i] = 0;
		}
		i = 0;
		j = 0;

		for (i = 0; i < expression.length(); i++) {
			valeur[i] = expression.charAt(i);
		}

		System.out.print("\n");

		for (j = 0; j <= i-1; j++) {
			asci = (int) valeur[j];
			if (asci > 47 && asci <= 58) {
				numerique[0][compteur] = valeur[j] - 48;
				numerique[1][compteur] = j + 1;
				compteur = compteur + 1;
				indiceErreur = 0;
			} else if (asci == 42 || asci == 43 || asci == 45 || asci == 47) {
				operateur[0][compteur] = valeur[j];
				operateur[1][compteur] = j + 1;
				compteur = compteur + 1;
				indiceErreur = indiceErreur + 1;
				if (indiceErreur == 2) {				
					throw  new CalculException();				
				}
				
			}
			else {
				throw new  CalculException();
			}
		}

		compteur = 0;
		if (verif == 0) 
		{
			for (j = compare; j <= i; j++) {
				while (numerique[1][j] == compare + 1 && numerique[1][j] != 0) {
					puissance = puissance + 1;
					j = j + 1;
					compare = compare + 1;
				}

				for (v = puissance; v > 0; v--) {
					nombre[nbnombre] = nombre[nbnombre] + numerique[0][compteur] * (float) Math.pow(10, v - 1);
					compteur = compteur + 1;
				}
				nbnombre = nbnombre + 1;
				puissance = 0;
				compare = compare + 1;
				compteur = compteur + 1;
			}
		}
		compare = 0;
		compteur = 0;
		nbnombre = 0;
		operation = nombre[nbnombre];
		for (j = compare; j <= i; j++) 
		{
			if (operateur[1][j] != 0) {
				if (operateur[0][j] == 42)
					operation = operation * nombre[nbnombre + 1];
				else if (operateur[0][j] == 43)
					operation = operation + nombre[nbnombre + 1];
				else if (operateur[0][j] == 45)
					operation = operation - nombre[nbnombre + 1];
				else if (operateur[0][j] == 47)
					operation = operation / nombre[nbnombre + 1];
				else
					System.out.println("je ne sais pas");
				nbnombre = nbnombre + 1;
			}
		}
		System.out.println(operation);

		return operation;
	}
}
