package hw4;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
 * Servlet implementation class RentOut
 */
@WebServlet("/hw4/RentOut")
public class RentOut extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RentOut()
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
			response.sendRedirect(
					"RentOut.jsp?AppID=" + request.getParameter("AppID"));
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

			SimpleDateFormat formatter = new SimpleDateFormat(
					"MM/dd/yyyy hh:mm:ss");
			Date LeaseTerminationDate = formatter
					.parse(request.getParameter("LeaseTerminationDate"));
			String LeaseHolderName = request.getParameter("LeaseHolderName");
			String IDProof = request.getParameter("IDProof");
			String Rent = request.getParameter("Rent");
			String Deposit = request.getParameter("Deposite");
			int appointID = Integer.parseInt(request.getParameter("txtAppID"));
			
			boolean isStudent = false;
			
			DataAccess.setRentDetails(appointID, LeaseTerminationDate, LeaseHolderName, IDProof, Integer.parseInt(Rent), Integer.parseInt(Deposit));

			response.sendRedirect("ManagerHome");
		}
		catch (Exception e)
		{
			// Failed to Parse the Date.
			request.setAttribute("errorMessage",
					e.getMessage());
			request.getRequestDispatcher("RentOut.jsp").include(request,
					response);
		}
	}

}
