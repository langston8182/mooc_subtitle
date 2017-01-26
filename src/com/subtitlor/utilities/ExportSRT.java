package com.subtitlor.utilities;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import com.subtitlor.beans.Ligne;

/**
 * Exporte un fichier SRT
 * 
 * @author Cyril
 */
public class ExportSRT {

	/**
	 * L'ensemble des lignes à exporter.
	 */
	private List<Ligne> lignes;

	/**
	 * Construit un objet ExportSRT en prenant en paramètre la liste dew lignes.
	 * 
	 * @param cheminFichier Le chemin d'export du nouveau fichier.
	 * @param lignes L'ensemble des lignes à exporter.
	 */
	public ExportSRT(List<Ligne> lignes) {
		this.lignes = lignes;
	}
	
	/**
	 * Export un fichier SRT sous forme de flux.
	 */
	public byte[] export() {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			for (Ligne ligne : lignes) {
				if (ligne.getTraduit() != null) {
					bos.write(ligne.getTraduit().getBytes());
				} else {
					bos.write(ligne.getOriginal().getBytes());
				}
				bos.write("\r\n".getBytes());
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		return bos.toByteArray();
	}
}
