package com.subtitlor.dao;

import java.util.List;

import com.subtitlor.beans.Fichier;
import com.subtitlor.beans.Ligne;

/**
 * Interface permettant de récupérer des données issues de la table LIGNE.
 * 
 * @author Cyril
 */
public interface LigneDAO {

	/**
	 * Ajoute une ligne.
	 * 
	 * @param ligne Ligne à ajouter.
	 * @throws DAOException Si une {@link DAOException} est lancée.
	 */
	public void ajouter(Ligne ligne) throws DAOException;
	
	/**
	 * Récupère la liste des sous-titres liée au fichier passé en paramètre.
	 * 
	 * @param fichier Le fichier.
	 * 
	 * @return La liste des sous-titres.
	 * @throws DAOException Si une {@link DAOException} est lancée.
	 */
	public List<Ligne> recuperer(Fichier fichier) throws DAOException;
	
	/**
	 * Récupère toutes les lignes du fichier SRT
	 * 
	 * @param fichier Le fichier.
	 * 
	 * @return La liste des sous-titres.
	 * @throws DAOException Si une {@link DAOException} est lancée.
	 */
	public List<Ligne> recupererTout(Fichier fichier) throws DAOException;
	
	/**
	 * Récupère une ligne à partir de son identifiant.
	 * 
	 * @param id L'identifiant de la ligne.
	 * 
	 * @return La ligne récupérée.
	 * @throws DAOException Si une {@link DAOException} est lancée.
	 */
	public Ligne recuperer(int id) throws DAOException;
	
	/**
	 * Sauvegarde la ligne passée en paramètre.
	 * 
	 * @param ligne La ligne à sauvegarder.
	 * @throws DAOException Si une {@link DAOException} est lancée.
	 */
	public void sauvegarder(Ligne ligne) throws DAOException;
}
