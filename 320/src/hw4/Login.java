package hw4;

import java.io.IOException;
import java.sql.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import helper.*;
import model.DataAccess;
import model.Months;
import model.Occupation;
import model.Roles;
import model.Status;
import model.UserDetail;

/**
 * Servlet implementation class Login
 */
@WebServlet("/hw4/Login")
public class Login extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException
	{
		// TODO Auto-generated method stub
		super.init(config);

		getServletContext().setAttribute("Occupation", Occupation.values());
		getServletContext().setAttribute("Roles", Roles.values());
		getServletContext().setAttribute("Status", Status.values());
		getServletContext().setAttribute("Months", Months.values());
		
		try
		{
			SQLHelper.getSQLClass();
		}
		catch (Exception e)
		{
			throw new ServletException(e);
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
		response.sendRedirect("Login.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
		// doGet(request, response);

		String strEmail = request.getParameter("email").trim();
		String strPassword = request.getParameter("password").trim();

		try
		{
			UserDetail User = DataAccess.tryLogin(strEmail, strPassword);
			if (User != null)
			{
				HttpSession session = request.getSession();
				session.setAttribute("User", User);

				if (User.getRole().equals(Roles.Manager))
				{
					response.sendRedirect("ManagerHome");
					return;
				}
				else
				{
					response.sendRedirect("LookUpApartment");
					return;
				}
			}
			else
			{
				// Failed to find the User.
				request.setAttribute("errorMessage",
						"Invalid E-mail or Password. Please try again.");
				request.getRequestDispatcher("Login.jsp").include(request,
						response);
			}
		}
		catch (Exception e)
		{
			request.setAttribute("errorMessage", e.getMessage());
			request.getRequestDispatcher("Login.jsp").include(request,
					response);
		}
	}
}
