package com.subtitlor.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.subtitlor.beans.Fichier;
import com.subtitlor.beans.Ligne;

/**
 * Classe permettant de récupérer des données issues de la table LIGNE.
 * 
 * @author Cyril
 */
public class LigneDAOImpl implements LigneDAO {

	/**
	 * La DAO Factory.
	 */
	private DAOFactory daoFactory;
	
	/**
	 * Créer une instance de LigneDAOImpl.
	 * 
	 * @param factory La DAO Factory.
	 */
	LigneDAOImpl(DAOFactory factory) {
		this.daoFactory = factory;
	}
	
	@Override
	public void ajouter(Ligne ligne) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement("INSERT INTO ligne(fk_fichier, traduit, original) VALUES(?, ?, ?);");
			preparedStatement.setInt(1, ligne.getFichier().getId());
			preparedStatement.setString(2, ligne.getTraduit());
			preparedStatement.setString(3, ligne.getOriginal());
			
			preparedStatement.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public List<Ligne> recupererTout(Fichier fichier) {
		List<Ligne> lignes = new ArrayList<>();
		
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;
		
		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			resultat = statement.executeQuery("SELECT * FROM ligne WHERE fk_fichier='" + fichier.getId() + "';");
			
			while (resultat.next()) {
				int id = resultat.getInt("id");
				String original = resultat.getString("original");
				String traduit = resultat.getString("traduit");
				
				Ligne ligne = new Ligne();
				lignes.add(ligne);
				ligne.setId(id);
				ligne.setFichier(fichier);
				ligne.setOriginal(original);
				ligne.setTraduit(traduit);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		
		return lignes;
	}

	@Override
	public Ligne recuperer(int id) {
		Ligne ligne = null;
		
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;
		
		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			resultat = statement.executeQuery("SELECT * FROM ligne WHERE id='" + id + "';");
			
			if (resultat.next()) {
				String original = resultat.getString("original");
				String traduit = resultat.getString("traduit");
				
				ligne = new Ligne();
				ligne.setId(id);
				ligne.setOriginal(original);
				ligne.setTraduit(traduit);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		
		return ligne;
	}

	@Override
	public void sauvegarder(Ligne ligne) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement("UPDATE ligne SET traduit='" + ligne.getTraduit() + "' WHERE id=?;");
			preparedStatement.setInt(1, ligne.getId());
			
			preparedStatement.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public List<Ligne> recuperer(Fichier fichier) {
		List<Ligne> lignes = new ArrayList<>();
		
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;
		
		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			resultat = statement.executeQuery("SELECT * FROM ligne WHERE fk_fichier='" + fichier.getId() + "';");
			
			boolean retrieve = false;
			while (resultat.next()) {
				int id = resultat.getInt("id");
				String original = resultat.getString("original");
				String traduit = resultat.getString("traduit");
				
				if (original.contains("-->")) {
					retrieve = true;
					continue;
				}
				if ("".equals(original)) {
					retrieve = false;
					continue;
				}
				if (retrieve) {
					Ligne ligne = new Ligne();
					lignes.add(ligne);
					ligne.setId(id);
					ligne.setFichier(fichier);
					ligne.setOriginal(original);
					ligne.setTraduit(traduit);
				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		
		return lignes;
	}
}
