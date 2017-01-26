package com.subtitlor.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.subtitlor.dao.DAOFactory;
import com.subtitlor.dao.InitialiseDAO;

/**
 * Instance qui initialise la base de données. En ajoutant les tables necéssaires au fonctionnement de l'application.
 * 
 * @author Cyril
 */
public class InitialiseBDD extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Factory DAO.
	 */
	private DAOFactory daoFactory;
	
	/**
	 * DAO Initialise.
	 */
	private InitialiseDAO initialiseDAO;
	
	@Override
	public void init() throws ServletException {
		daoFactory = DAOFactory.getInstance();
		
		initialiseDAO = daoFactory.getInitialiseDAO();
		initialiseDAO.init();
	}
	
}
