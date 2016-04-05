package hw4;

import java.io.IOException;
import java.time.Month;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.DataAccess;
import model.Months;

/**
 * Servlet implementation class PayRent
 */
@WebServlet("/hw4/PayRent")
public class PayRent extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PayRent()
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
			try
			{
				request.setAttribute("RentedAparts",
						DataAccess.getRentDetails());
				request.getRequestDispatcher("PayRent.jsp").include(request,
						response);

			}
			catch (Exception ex)
			{
				request.setAttribute("errorMessage", ex.getMessage());
				request.getRequestDispatcher("PayRent.jsp").include(request,
						response);
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

		try
		{
			int ids = Integer.parseInt(request.getParameter("rentDetailID"));
			int rentAmt = Integer.parseInt(request.getParameter("rentAmt"));
			String rentMonth = request.getParameter("rentMonth");
			int rentYear = Integer.parseInt(request.getParameter("rentYear"));

			int a = DataAccess.setRentLog(ids, rentAmt, Months.valueOf(rentMonth),
					rentYear);
			
			response.setContentType("text/plain");
	        response.getWriter().write(String.valueOf(a));
		}
		catch (Exception e)
		{
			request.setAttribute("errorMessage", e.getMessage());
			request.getRequestDispatcher("PayRent.jsp").include(request,
					response);
		}
	}

}
