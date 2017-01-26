package com.subtitlor.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.subtitlor.beans.Fichier;

/**
 * Classe permettant de récupérer des données issues de la table FICHIER.
 * 
 * @author Cyril
 */
public class FichierDAOImpl implements FichierDAO {

	/**
	 * Factory DAO.
	 */
	private DAOFactory daoFactory;
	
	/**
	 * Créer une instance de FicheirDAOImpl.
	 * 
	 * @param factory La DAO Factory.
	 */
	FichierDAOImpl(DAOFactory factory) {
		this.daoFactory = factory;
	}
	
	@Override
	public void ajouter(Fichier fichier) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement("INSERT INTO fichier(nom, path) VALUES(?, ?);");
			preparedStatement.setString(1, fichier.getNom());
			preparedStatement.setString(2, fichier.getPath());
			
			preparedStatement.executeUpdate();
			
			ResultSet cles = preparedStatement.getGeneratedKeys();
			if (cles.next()) {
				fichier.setId((int) cles.getObject(1));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public List<Fichier> lister() {
		List<Fichier> fichiers = new ArrayList<>();
		
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;
		
		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			resultat = statement.executeQuery("SELECT * FROM fichier;");
			
			while (resultat.next()) {
				int id = resultat.getInt("id");
				String nom = resultat.getString("nom");
				String path = resultat.getString("path");
				
				Fichier fichier = new Fichier();
				fichiers.add(fichier);
				fichier.setId(id);
				fichier.setNom(nom);
				fichier.setPath(path);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		
		return fichiers;
	}

	@Override
	public Fichier recuperer(int id) {
		Fichier fichier = null;
		
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;
		
		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			resultat = statement.executeQuery("SELECT * FROM fichier WHERE id='" + id + "';");
			
			if (resultat.next()) {
				String nom = resultat.getString("nom");
				String path = resultat.getString("path");
				
				fichier = new Fichier();
				fichier.setId(id);
				fichier.setNom(nom);
				fichier.setPath(path);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		
		return fichier;
	}
}
