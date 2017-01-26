package com.subtitlor.dao;

/**
 * Initialise la base de données.
 * 
 * @author Cyril
 */
public interface InitialiseDAO {

	/**
	 * Lance l'initialisation de la base de données.
	 * 
	 * @throws DAOException Si une {@link DAOException} est lancée.
	 */
	public void init() throws DAOException;
	
}
