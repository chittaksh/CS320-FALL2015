package hw4;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

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
import model.UserDetail;

/**
 * Servlet implementation class RequestMapping
 */
@WebServlet("/hw4/RequestMapping")
public class RequestMapping extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RequestMapping()
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
			if (request.getParameter("aptID") != null)
			{
				try
				{

					DataAccess.setAppointment(User.getId(),
							Integer.parseInt(request.getParameter("aptID")));

					response.sendRedirect("LookUpApartment");
				}
				catch (Exception ex)
				{
					request.setAttribute("errorMessage", ex.getMessage());
					request.getRequestDispatcher("LookUpApartment.jsp")
							.include(request, response);
				}
			}
		}
		else
		{
			response.sendRedirect("Logout");
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
