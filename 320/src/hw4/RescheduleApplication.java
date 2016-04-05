package hw4;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import model.AppointmentDetail;
import model.DataAccess;
import model.Occupation;
import model.Status;

/**
 * Servlet implementation class RescheduleApplication
 */
@WebServlet("/hw4/RescheduleApplication")
public class RescheduleApplication extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RescheduleApplication()
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
			if (request.getParameter("aptID") != null)
			{
				try
				{
					ArrayList<AppointmentDetail> AppointDetails = DataAccess.getAppointmentForApartment(Integer.parseInt(request.getParameter("aptID")));
					
					request.setAttribute("AppointDetails", AppointDetails);
					request.getRequestDispatcher("RescheduleApplication.jsp?aptID="+request.getParameter("aptID"))
					.include(request, response);
				}
				catch (Exception ex)
				{
					request.setAttribute("errorMessage", ex.getMessage());
					request.getRequestDispatcher("RescheduleApplication.jsp").include(request, response);
				}
			}
			else
			{
				response.sendRedirect("ManagerHome");
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
		// doGet(request, response);
		try
		{

			String Date = request.getParameter("date");
			String appointmentID = request.getParameter("appointID");
			String aptID = request.getParameter("aptID");
			// Formatter Settings
			SimpleDateFormat formatter = new SimpleDateFormat(
					"MM/dd/yyyy hh:mm:ss");

			DataAccess.updateAppointmentStatus(Status.Waiting, formatter.parse(Date), Integer.parseInt(appointmentID));
			
			doGet(request, response);
		}
		catch (Exception ex)
		{
			request.setAttribute("errorMessage", ex.getMessage());
			request.getRequestDispatcher("RescheduleApplication.jsp").include(request, response);
		}
	}
}
