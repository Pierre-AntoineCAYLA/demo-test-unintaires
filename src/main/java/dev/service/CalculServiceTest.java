package dev.service;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dev.exception.CalculException;



public class CalculServiceTest {
private static final Logger LOG = LoggerFactory.getLogger(CalculServiceTest.class
);

@org.junit.Test
public void testAdditionner() throws Exception {
LOG.info("Etant donné, une instance de la classe CalculService"); 
CalculService cs =new CalculService();
LOG.info("Lorsque j'évalue l'addition de l'expression 1+3+4"); 
int somme = cs.additionner("1+3+4");
LOG.info("Alors j'obtiens le résultat 8");
assertTrue(somme == 8);
}

@org.junit.Test(expected = CalculException.class)
public void testAdditionnerInvalid() throws Exception {
LOG.info("Etant donné, une instance de la classe CalculService"); 
CalculService cs =new CalculService();
LOG.info("Lorsque j'évalue l'addition de l'expression 1+3++4"); 
LOG.info("Alors j'obtiens le résultat 8");
cs.additionner("1+3++4");
}

@org.junit.Test
public void testAdditionnerAssertj() throws Exception {
LOG.info("Etant donné, une instance de la classe CalculService"); 
CalculService cs =new CalculService();
LOG.info("Lorsque j'évalue l'addition de l'expression 1+3+4"); 
int somme = cs.additionner("1+3+4");
LOG.info("Alors j'obtiens le résultat 8");
assertThat(somme).isEqualTo(8);
}


}
