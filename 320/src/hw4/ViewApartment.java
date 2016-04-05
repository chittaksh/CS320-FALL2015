package hw4;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import helper.*;
import model.*;

/**
 * Servlet implementation class ViewApartment
 */
@WebServlet("/hw4/ViewApartment")
public class ViewApartment extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ViewApartment()
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
		HttpSession session = request.getSession(false);
		if (session != null)
		{
			try
			{
				if (request.getParameter("aptID") != null)
				{
					DataAccess.changeVacancy(true, Integer.parseInt(request.getParameter("aptID")));				
				}
				
				request.setAttribute("ApartDetails", DataAccess.getApartments());
				request.setAttribute("AppointmentDetails", DataAccess.getAppointments());
				request.setAttribute("AppointmentHistory", DataAccess.getAppointmentsHistory());
			}
			catch (Exception ex)
			{
				request.setAttribute("errorMessage", ex.getMessage());
			}

			request.getRequestDispatcher("ViewApartment.jsp").include(request, response);
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

	}
}
