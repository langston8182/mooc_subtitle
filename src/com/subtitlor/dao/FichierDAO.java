package com.subtitlor.dao;

import java.util.List;

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
	 */
	public void ajouter(Fichier fichier);
	
	/**
	 * Récupère la liste des fichiers existants.
	 * 
	 * @return La liste des fichiers.
	 */
	public List<Fichier> lister();
	
	/**
	 * Récupère un fichier à partir de son identifiant.
	 * 
	 * @param id L'identifiant du fichier.
	 * 
	 * @return Le fichier récupéré.
	 */
	public Fichier recuperer(int id);
	
}
