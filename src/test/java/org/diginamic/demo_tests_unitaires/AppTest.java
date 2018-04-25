package org.diginamic.demo_tests_unitaires;

import static org.junit.Assert.assertTrue;


import dev.utils.StringUtils;

public class AppTest  {
	
	@org.junit.Test
	public void chat() {
		assertTrue(StringUtils.levenshteinDistance("chat", "chats") == 1);
	}
	@org.junit.Test
	public void machin() {
		assertTrue(StringUtils.levenshteinDistance("machins", "machine") == 1);
	}
	@org.junit.Test
	public void avion() {
		assertTrue(StringUtils.levenshteinDistance("avion", "aviron") == 1);
	}
	@org.junit.Test
	public void distance() {
		assertTrue(StringUtils.levenshteinDistance("distance", "instance") == 2);
	}
	@org.junit.Test
	public void chien() {
		assertTrue(StringUtils.levenshteinDistance("chien", "chine") == 2);
	}
	@org.junit.Test
	public void nullsDroit() {
		assertTrue(StringUtils.levenshteinDistance(null, "chien") == -1);
	}
	@org.junit.Test
	public void nullsGauche() {
		assertTrue(StringUtils.levenshteinDistance("chien", null) == -1);
	}
	@org.junit.Test
	public void nullsDouble() {
		assertTrue(StringUtils.levenshteinDistance(null, null) == -1);
		
	}
}
