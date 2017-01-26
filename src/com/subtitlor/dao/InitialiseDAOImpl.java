package com.subtitlor.dao;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Instance de classe qui permet l'initialisation de la base de données.
 * 
 * @author Cyril
 */
public class InitialiseDAOImpl implements InitialiseDAO {

	/**
	 * Fichier d'initialisation SQL.
	 */
	private static final String SQL_FILE = "/com/subtitlor/bdd/initialise.sql";
	/**
	 * Factory DAO.
	 */
	private DAOFactory daoFactory;
	
	InitialiseDAOImpl(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}
	
	@Override
	public void init() throws DAOException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		try {
			connexion = daoFactory.getConnection();
			Path sqlPath = Paths.get(this.getClass().getResource(SQL_FILE).toURI());
			byte[] bytes = Files.readAllBytes(sqlPath);
			String sql = new String(bytes);

			preparedStatement = connexion.prepareStatement(sql);
			preparedStatement.executeUpdate();
			connexion.commit();
		} catch (SQLException | URISyntaxException | IOException ex) {
			if (connexion != null) {
				try {
					connexion.rollback();
				} catch (SQLException e) {
				}
			}
			throw new DAOException("Impossible de communiquer avec la basse de données.");
		}
	}
}
