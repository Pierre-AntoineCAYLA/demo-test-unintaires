package dev.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CalculException extends RuntimeException{
	
private static final Logger LOG = LoggerFactory.getLogger(CalculException.class);
 
public CalculException(){
	
	LOG.info("L'expression est non valide"); 
 }
}
