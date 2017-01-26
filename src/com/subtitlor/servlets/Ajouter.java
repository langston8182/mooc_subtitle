package com.subtitlor.servlets;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.subtitlor.beans.Fichier;
import com.subtitlor.beans.Ligne;
import com.subtitlor.dao.DAOFactory;
import com.subtitlor.dao.FichierDAO;
import com.subtitlor.dao.LigneDAO;
import com.subtitlor.utilities.SubtitlesHandler;

/**
 * Servlet AJouter.
 * 
 * @author Cyril
 */
public class Ajouter extends HttpServlet{

	/**
	 * Taille de la mémoire tampon .
	 */
	public static final int TAILLE_TAMPON = 10240;
	
	/**
	 * Le chemin du fichier.
	 */
	private static final String CHEMIN_FICHIER = "/WEB-INF/";
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * DAO Fichier.
	 */
	private FichierDAO fichierDAO;
	
	/**
	 * DAO Ligne.
	 */
	private LigneDAO ligneDAO;
	
	@Override
	public void init() throws ServletException {
		DAOFactory daoFactory = DAOFactory.getInstance();
		fichierDAO = daoFactory.getFichierDAO();
		ligneDAO = daoFactory.getLigneDAO();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/WEB-INF/ajouter.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Part part = req.getPart("fichier");
		String  nomFichier = getNomFichier(part);
		
		if (nomFichier != null && !nomFichier.isEmpty()) {
			// Corrige un bug du fonctionnement d'Internet Explorer
             nomFichier = nomFichier.substring(nomFichier.lastIndexOf('/') + 1)
                    .substring(nomFichier.lastIndexOf('\\') + 1);

            // On écrit définitivement le fichier sur le disque
            String path = getServletContext().getRealPath(CHEMIN_FICHIER + nomFichier);
            ecrireFichier(part, path);
            
            Fichier fichier = new Fichier();
    		fichier.setNom(nomFichier);
    		fichier.setPath(path);
    		
    		fichierDAO.ajouter(fichier);
    		
    		SubtitlesHandler handler = new SubtitlesHandler(fichier.getPath());
    		List<String> sousTitres = handler.getAllSubtitles();
    		
    		for (String sousTitre : sousTitres) {
    			Ligne ligne = new Ligne();
    			ligne.setFichier(fichier);
    			ligne.setOriginal(sousTitre);
    			
    			ligneDAO.ajouter(ligne);
    		}
		}
		this.getServletContext().getRequestDispatcher("/WEB-INF/accueil.jsp").forward(req, resp);
	}
	
	/**
	 * Sauvegarde un fichier sur le serveur.
	 * 
	 * @param part Le champ description du fichier.
	 * @param chemin La chemin complet du fichier.
	 * 
	 * @throws IOException Lance une IOException en cas d'erreur.
	 */
	private void ecrireFichier(Part part, String chemin) throws IOException {
        BufferedInputStream entree = null;
        BufferedOutputStream sortie = null;
        try {
            entree = new BufferedInputStream(part.getInputStream(), TAILLE_TAMPON);
            sortie = new BufferedOutputStream(new FileOutputStream(new File(chemin)), TAILLE_TAMPON);

            byte[] tampon = new byte[TAILLE_TAMPON];
            int longueur;
            while ((longueur = entree.read(tampon)) > 0) {
                sortie.write(tampon, 0, longueur);
            }
        } finally {
            try {
                sortie.close();
            } catch (IOException ignore) {
            }
            try {
                entree.close();
            } catch (IOException ignore) {
            }
        }
    }
    
	/**
	 * Récupère le nom du fichier.
	 * 
	 * @param part Le champ descrption du fichier.
	 * 
	 * @return Le nom du fichier.
	 */
    private static String getNomFichier(Part part) {
        for ( String contentDisposition : part.getHeader( "content-disposition" ).split( ";" ) ) {
            if ( contentDisposition.trim().startsWith( "filename" ) ) {
                return contentDisposition.substring( contentDisposition.indexOf( '=' ) + 1 ).trim().replace( "\"", "" );
            }
        }
        return null;
    }   
}
