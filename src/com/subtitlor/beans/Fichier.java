package com.subtitlor.beans;

/**
 * Représente un fichier avec son nom et son chemin.
 * 
 * @author Cyril
 */
public class Fichier {

	/**
	 * Identifiant du fichier.
	 */
	private int id;
	
	/**
	 * Nom du fichier.
	 */
	private String nom;
	
	/**
	 * Chemin du fichier.
	 */
	private String path;

	/**
	 * Retourne le nom.
	 * 
	 * @return {@link #nom}
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Modifie {@link #nom}.
	 * 
	 * @param nom Le nom.
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * Retourne le chemin.
	 * 
	 * @return {@link #path}
	 */
	public String getPath() {
		return path;
	}

	/**
	 * Modifie {@link #path}.
	 * 
	 * @param path Le chemin.
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * Retourne l'identifiant.
	 * 
	 * @return {@link #id}
	 */
	public int getId() {
		return id;
	}

	/**
	 * Modifie {@link #id}.
	 * 
	 * @param id L'identifiant.
	 */
	public void setId(int id) {
		this.id = id;
	}
}
