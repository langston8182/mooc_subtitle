package com.subtitlor.servlets;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.subtitlor.beans.Fichier;
import com.subtitlor.beans.Ligne;
import com.subtitlor.dao.DAOFactory;
import com.subtitlor.dao.FichierDAO;
import com.subtitlor.dao.LigneDAO;
import com.subtitlor.utilities.ExportSRT;

/**
 * Servlet Export.
 * 
 * @author Cyril
 */
public class Export extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Factory DAO.
	 */
	private DAOFactory daoFactory;
	
	/**
	 * DAO Ligne.
	 */
	private LigneDAO ligneDAO;
	
	/**
	 * DAO Fichier.
	 */
	private FichierDAO fichierDAO;

	@Override
	public void init() throws ServletException {
		daoFactory = DAOFactory.getInstance();
		ligneDAO = daoFactory.getLigneDAO();
		fichierDAO = daoFactory.getFichierDAO();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		Fichier fichier = fichierDAO.recuperer(id);
		List<Ligne> lignes = ligneDAO.recupererTout(fichier);
		
		ExportSRT exportSRT = new ExportSRT(lignes);
		byte[] file = exportSRT.export();
		
		resp.setContentType("application/octet-stream");
		resp.addHeader("Content-disposition", "attachment; filename=" + fichier.getNom());
		resp.setContentLength(file.length);
		
		OutputStream os = resp.getOutputStream();
		os.write(file);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doPost(req, resp);
	}
}
