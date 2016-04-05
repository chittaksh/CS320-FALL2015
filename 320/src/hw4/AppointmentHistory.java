package hw4;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.AppointmentDetail;
import model.DataAccess;

/**
 * Servlet implementation class AppointmentHistory
 */
@WebServlet("/hw4/AppointmentHistory")
public class AppointmentHistory extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AppointmentHistory()
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
					ArrayList<AppointmentDetail> AppointDetails = DataAccess
							.getAppointmentHistoryForApartment(Integer
									.parseInt(request.getParameter("aptID")));

					request.setAttribute("AppointDetails", AppointDetails);
					request.getRequestDispatcher("AppointmentHistory.jsp?aptID="
							+ request.getParameter("aptID"))
							.include(request, response);
				}
				catch (Exception ex)
				{
					request.setAttribute("errorMessage", ex.getMessage());
					request.getRequestDispatcher("RescheduleApplication.jsp")
							.include(request, response);
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
		doGet(request, response);
	}

}
