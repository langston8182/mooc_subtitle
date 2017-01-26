package com.subtitlor.beans;

/**
 * Représente une ligne à traduire.
 * 
 * @author Cyril
 */
public class Ligne {

	/**
	 * Identifiant de la ligne.
	 */
	private int id;
	
	/**
	 * Texte original.
	 */
	private String original;
	
	/**
	 * Texte traduit.
	 */
	private String traduit;
	
	/**
	 * Fichier appartenant à la ligne.
	 */
	private Fichier fichier;

	/**
	 * Retourne le texte original.
	 * 
	 * @return {@link #original}
	 */
	public String getOriginal() {
		return original;
	}

	/**
	 * Modifie {@link #original}.
	 * 
	 * @param original Le texte original.
	 */
	public void setOriginal(String original) {
		this.original = original;
	}

	/**
	 * Retourne le texte traduit.
	 * 
	 * @return {@link #traduit}
	 */
	public String getTraduit() {
		return traduit;
	}

	/**
	 * Modifie {@link #traduit}.
	 * 
	 * @param traduit Le texte traduit.
	 */
	public void setTraduit(String traduit) {
		this.traduit = traduit;
	}

	/**
	 * Retourne le fichier.
	 * 
	 * @return {@link #fichier}
	 */
	public Fichier getFichier() {
		return fichier;
	}

	/**
	 * Modifie {@link #fichier}.
	 * 
	 * @param fichier Le fichier.
	 */
	public void setFichier(Fichier fichier) {
		this.fichier = fichier;
	}

	/**
	 * Retourne l'identiant de la ligne.
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
