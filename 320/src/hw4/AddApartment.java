package hw4;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import helper.SQLHelper;
import helper.connectionObject;
import model.DataAccess;

/**
 * Servlet implementation class AddApartment
 */
@WebServlet("/hw4/AddApartment")
public class AddApartment extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddApartment()
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
		HttpSession session = request.getSession(false);
		if (session != null)
		{
			response.sendRedirect("AddApartment.jsp");
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

		String strApartment = request.getParameter("apartmentid").trim();
		String strApartmentType = request.getParameter("apartmentType").trim();
		String strFacilities = request.getParameter("facilities").trim();
		String strMaxPeople = request.getParameter("maxpeople").trim();
		String strRent = request.getParameter("rent").trim();
		String strDeposit = request.getParameter("deposits").trim();

		try
		{
			DataAccess.setApartment(Integer.parseInt(strApartment),
					strApartmentType, strFacilities,
					Integer.parseInt(strMaxPeople), Integer.parseInt(strRent),
					Integer.parseInt(strDeposit));

			response.sendRedirect("ManagerHome");
		}
		catch (Exception ex)
		{
			// Error Inserting.
			request.setAttribute("errorMessage", ex.getMessage());
			request.getRequestDispatcher("AddApartment.jsp").include(request,
					response);
		}
	}
}
