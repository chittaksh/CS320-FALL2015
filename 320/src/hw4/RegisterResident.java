package hw4;

import java.io.IOException;
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import helper.SQLHelper;
import helper.connectionObject;
import model.DataAccess;
import model.Occupation;

/**
 * Servlet implementation class RegisterResident
 */
@WebServlet("/hw4/RegisterResident")
public class RegisterResident extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterResident()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at:
		// ").append(request.getContextPath());

		HttpSession session = request.getSession();
		if (session != null)
		{
			response.sendRedirect("RegisterResident.jsp");
		}
		else
		{
			response.sendRedirect("Login");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub

		String strUsername = request.getParameter("username").trim();
		String strEmail = request.getParameter("email").trim();
		String strPassword = request.getParameter("password").trim();
		String strConPassword = request.getParameter("conpassword").trim();
		String strContact = request.getParameter("contact").trim();
		String strPeople = request.getParameter("people").trim();
		String strOccupation = request.getParameter("occupation").trim();
		String strApartmentType = request.getParameter("apartmentType").trim();
		String strPreferences = request.getParameter("preferences").trim();
		String strNeedFrom = request.getParameter("needFrom").trim();

		// Check password and Confirm Password
		if (strPassword.equals(strConPassword))
		{
			try
			{
				SimpleDateFormat formatter = new SimpleDateFormat(
						"MM/dd/yyyy HH:mm:ss");

				DataAccess.setUser(strUsername, strPassword, strEmail,
						strContact, Integer.parseInt(strPeople),
						Occupation.getOccupation(
								Occupation.getValue(strOccupation)),
						strApartmentType, strPreferences,
						formatter.parse(strNeedFrom));

				response.sendRedirect("Login");
			}
			catch (Exception ex)
			{
				// Failed to Insert Record.
				request.setAttribute("errorMessage", ex.getMessage());
				request.getRequestDispatcher("RegisterResident.jsp")
						.include(request, response);
			}
		}
		else
		{
			// Password and Confirm Password are different.
			request.setAttribute("errorMessage",
					"Password and confirm Password did not match.");
			request.getRequestDispatcher("RegisterResident.jsp")
					.include(request, response);
		}
	}
}
