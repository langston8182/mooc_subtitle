package com.subtitlor.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Créer une instancer de DAO.
 * 
 * @author Cyril
 */
public class DAOFactory {
	
	/**
	 * URL de connexion à la BDD.
	 */
	private String url;
	
	/**
	 * Utilisateur de la BDD.
	 */
	private String username;
	
	/**
	 * Mot de passe de la BDD.
	 */
	private String password;

	/**
	 * Constructeur de DAOFactory.
	 * 
	 * @param url URL de connexion à la BDD.
	 * @param username Utilisateur de la BDD.
	 * @param password Motr de passe de la BDD.
	 */
	private DAOFactory(String url, String username, String password) {
		super();
		this.url = url;
		this.username = username;
		this.password = password;
	}
	
	/**
	 * Créer une instance de DAOFactory.
	 * 
	 * @return Une instance de DAOFactory.
	 */
	public static DAOFactory getInstance() {
		try {
			Class.forName("org.h2.Driver");
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		
		//DAOFactory instance = new DAOFactory("jdbc:h2:~/javaee", "sa", "");
		DAOFactory instance = new DAOFactory("jdbc:h2:mem:subtitle", "sa", "");
		
		return instance;
	}
	
	/**
	 * Retourne la connexion à la BDD.
	 * 
	 * @return La connexion à la BDD.
	 * 
	 * @throws SQLException Si une SQLException est lancée.
	 */
	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, username, password);
	}
	
	/**
	 * Retourne le DAO de Fichier.
	 * 
	 * @return une instance de FicheirDAO.
	 */
	public FichierDAO getFichierDAO() {
		return new FichierDAOImpl(this);
	}
	
	/**
	 * Retourne le DAO de Ligne.
	 * 
	 * @return une instance de LigneDAO.
	 */
	public LigneDAO getLigneDAO() {
		return new LigneDAOImpl(this);
	}
	
	/**
	 * Retourne le DAO Initialise.
	 * 
	 * @return une instance de InitialiseDAO.
	 */
	public InitialiseDAO getInitialiseDAO() {
		return new InitialiseDAOImpl(this);
	}
}
