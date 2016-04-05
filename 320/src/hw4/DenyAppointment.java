package hw4;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
import model.DataAccess;
import model.Status;

/**
 * Servlet implementation class DenyAppointment
 */
@WebServlet("/hw4/DenyAppointment")
public class DenyAppointment extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DenyAppointment()
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
			try
			{
				if (request.getParameter("appointID") != null)
				{
					DataAccess.updateAppointmentStatus(Status.Denied, null,
							Integer.parseInt(
									request.getParameter("appointID")));

					response.sendRedirect("LookUpApartment");
				}
			}
			catch (Exception ex)
			{
				request.setAttribute("errorMessage", ex.getMessage());
				request.getRequestDispatcher("LookUpApartment.jsp")
						.include(request, response);
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
