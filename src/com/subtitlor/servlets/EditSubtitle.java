package com.subtitlor.servlets;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.subtitlor.beans.BeanException;
import com.subtitlor.beans.Fichier;
import com.subtitlor.beans.Ligne;
import com.subtitlor.dao.DAOException;
import com.subtitlor.dao.DAOFactory;
import com.subtitlor.dao.FichierDAO;
import com.subtitlor.dao.LigneDAO;

@WebServlet("/EditSubtitle")
public class EditSubtitle extends HttpServlet {
	
	/**
	 * Attribut en cas d'erreur.
	 */
	private static final String ERREUR_ATTRIBUT = "erreur";
	
	/**
	 * UTF8 encodage.
	 */
	private static final String UTF_8 = "UTF-8";

	/**
	 * Délimiteur d'identifiant.
	 */
	private static final String DELIMITER = "#";

	/**
	 * Prefixe d'identifiant.
	 */
	private static final String LIGNE_PREFIXE = "ligne";

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * DAO Ligne.
	 */
	private LigneDAO ligneDAO;
	
	/**
	 * DAO Fichier.
	 */
	private FichierDAO fichierDAO;
	
	/**
	 * Initialise la servlet.
	 */
	public void init() throws ServletException {
		DAOFactory daoFactory = DAOFactory.getInstance();
		this.ligneDAO = daoFactory.getLigneDAO();
		this.fichierDAO = daoFactory.getFichierDAO();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = 0;
		if (request.getParameter("id") != null) {
			id = Integer.parseInt(request.getParameter("id"));
		}
	
		if (id != 0) {
			Fichier fichier = null;
			try {
				fichier = fichierDAO.recuperer(id);
			} catch (DAOException | BeanException ex) {
				request.setAttribute(ERREUR_ATTRIBUT, ex.getMessage());
			}
			List<Ligne> lignes = new ArrayList<>();
			try {
				lignes = ligneDAO.recuperer(fichier);
			} catch (DAOException ex) {
				request.setAttribute(ERREUR_ATTRIBUT, ex.getMessage());
			}
			
			request.setAttribute("lignes", lignes);
			request.setAttribute("id", id);
			request.setAttribute("fichier", fichier);
		}
		
		List<Fichier> fichiers = new ArrayList<>();
		try {
			fichiers = fichierDAO.lister();
		} catch (DAOException | BeanException ex) {
			request.setAttribute(ERREUR_ATTRIBUT, ex.getMessage());
		}
		request.setAttribute("fichiers", fichiers);
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/edit_subtitle.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding(UTF_8);
		Map<String, String[]> map = request.getParameterMap();
		for (Map.Entry<String, String[]> entry : map.entrySet()) {
			String key = entry.getKey();
			String[] values = entry.getValue();
			
			if (key.startsWith(LIGNE_PREFIXE) && !values[0].equals("")) {
				byte[] bytes = values[0].getBytes(StandardCharsets.UTF_8);
				String traduit = new String(bytes, StandardCharsets.UTF_8);
				int idLigne = Integer.parseInt(key.substring(key.lastIndexOf(DELIMITER) + 1));
				
				// Récupération de la ligne concerné.
				Ligne ligne = null;
				try {
					ligne = ligneDAO.recuperer(idLigne);
				} catch (DAOException ex) {
					request.setAttribute(ERREUR_ATTRIBUT, ex.getMessage());
				}
				ligne.setTraduit(traduit);
				try {
					ligneDAO.sauvegarder(ligne);
				} catch (DAOException ex) {
					request.setAttribute(ERREUR_ATTRIBUT, ex.getMessage());
				}
			}
		}
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/accueil.jsp").forward(request, response);
	}

}
