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
	 */
	public void ajouter(Ligne ligne);
	
	/**
	 * Récupère la liste des sous-titres liée au fichier passé en paramètre.
	 * 
	 * @param fichier Le fichier.
	 * 
	 * @return La liste des sous-titres.
	 */
	public List<Ligne> recuperer(Fichier fichier);
	
	/**
	 * Récupère toutes les lignes du fichier SRT
	 * 
	 * @param fichier Le fichier.
	 * 
	 * @return La liste des sous-titres.
	 */
	public List<Ligne> recupererTout(Fichier fichier);
	
	/**
	 * Récupère une ligne à partir de son identifiant.
	 * 
	 * @param id L'identifiant de la ligne.
	 * 
	 * @return La ligne récupérée.
	 */
	public Ligne recuperer(int id);
	
	/**
	 * Sauvegarde la ligne passée en paramètre.
	 * 
	 * @param ligne La ligne à sauvegarder.
	 */
	public void sauvegarder(Ligne ligne);
}
