package hw4;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import helper.SQLHelper;
import helper.connectionObject;
import model.ApartmentDetail;
import model.AppointmentDetail;
import model.DataAccess;
import model.Status;
import model.UserDetail;

/**
 * Servlet implementation class LookUpApartment
 */
@WebServlet("/hw4/LookUpApartment")
public class LookUpApartment extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LookUpApartment()
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
			UserDetail User = (UserDetail) session.getAttribute("User");
			
			try
			{
				if (request.getParameter("appointID") != null)
				{
					DataAccess.updateAppointmentStatus(Status.Accepted, null, Integer.parseInt(request.getParameter("appointID")));
				}
				
				ArrayList<ApartmentDetail> ApartDetails = DataAccess.getVacantApartments();
				ArrayList<AppointmentDetail> AppointDetails =  DataAccess.getAppointmentsForUser(User.getId());

				request.setAttribute("ApartDetails", ApartDetails);
				request.setAttribute("AppointDetails", AppointDetails);
				request.getRequestDispatcher("LookUpApartment.jsp").include(request, response);
			}
			catch (Exception ex)
			{
				request.setAttribute("errorMessage", ex.getMessage());
				request.getRequestDispatcher("LookUpApartment.jsp").include(request, response);
			}
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
		doGet(request, response);
	}
}
