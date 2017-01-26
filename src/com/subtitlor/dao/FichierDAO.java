package com.subtitlor.dao;

import java.util.List;

import com.subtitlor.beans.BeanException;
import com.subtitlor.beans.Fichier;

/**
 * Interface permettant de récupérer des données issues de la table FICHIER.
 * 
 * @author Cyril
 */
public interface FichierDAO {

	/**
	 * Ajouter un fichier.
	 * 
	 * @param fichier Fichier à ajouter.
	 * @throws DAOException Si une {@link DAOException} est lancée.
	 */
	public void ajouter(Fichier fichier) throws DAOException;
	
	/**
	 * Récupère la liste des fichiers existants.
	 * 
	 * @return La liste des fichiers.
	 * @throws DAOException Si une {@link DAOException} est lancée.
	 * @throws BeanException Si une {@link BeanException} est lancée.
	 */
	public List<Fichier> lister() throws DAOException, BeanException;
	
	/**
	 * Récupère un fichier à partir de son identifiant.
	 * 
	 * @param id L'identifiant du fichier.
	 * 
	 * @return Le fichier récupéré.
	 * @throws DAOException Si une {@link DAOException} est lancée.
	 * @throws BeanException Si une {@link BeanException} est lancée.
	 */
	public Fichier recuperer(int id) throws DAOException, BeanException;
	
}
