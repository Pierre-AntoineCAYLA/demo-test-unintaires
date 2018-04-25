package dev.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AurevoirException extends RuntimeException {
	private static final Logger LOG = LoggerFactory.getLogger(AurevoirException.class);
	 
	public AurevoirException(){
		
		LOG.info("Au revoir :("); 
	 }
}
