package finals;

import java.io.IOException;
import java.time.DayOfWeek;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.*;
import helper.SQLHelper;

/**
 * Servlet implementation class Final
 */
@WebServlet("/Final")
public class Final extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Final()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	public void init() throws ServletException
	{
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
		// response.getWriter().append("Served at:
		// ").append(request.getContextPath());
		try
		{
			request.setAttribute("Days", DataLink.getDays());
			request.setAttribute("TimeSlots", DataLink.getTimeSlots());
			request.setAttribute("Meetings", DataLink.getMeetings());
			request.getRequestDispatcher("Final.jsp").include(request,
					response);
		}
		catch (Exception e)
		{
			request.setAttribute("errorMessage", e.getMessage());
			request.getRequestDispatcher("Final.jsp").include(request,
					response);
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
	}

}
