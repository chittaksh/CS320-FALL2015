package hw4;

import java.io.IOException;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ApartmentDetail;
import model.AppointmentDetail;
import model.DataAccess;
import model.Months;
import model.RentDetail;
import model.RentLog;

/**
 * Servlet implementation class Reports
 */
@WebServlet("/hw4/Reports")
public class Reports extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Reports()
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
		try
		{
			List<ApartmentDetail> apartments =  DataAccess.getApartments();
			List<RentDetail> rentDetail = DataAccess.getRentDetails();
			List<RentLog> rentLog = DataAccess.getRentLog();
			
			List<RentLog> rentLogSorted = new ArrayList<RentLog>();
			
			for (RentLog log : rentLog)
			{
				if(log.getPayDate().getMonth()== (new Date()).getMonth() && log.getPayDate().getYear() == (new Date()).getYear()){
					rentLogSorted.add(log);
				}
			}
			
			request.setAttribute("apartmentDetails", apartments);
		    request.setAttribute("rentDetail", rentDetail);
			request.setAttribute("rentLogs", rentLogSorted);
			request.setAttribute("selMonth", Months.getMonths(((new Date()).getMonth()+1)).toString());
			request.setAttribute("selYear", (new Date()).getYear());
		    
			request.getRequestDispatcher("Reports.jsp").include(request,
					response);
		}
		catch (Exception ex)
		{
			request.setAttribute("errorMessage", ex.getMessage());
			request.getRequestDispatcher("Reports.jsp").include(request,
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
		
		try
		{
			String strMonth = request.getParameter("rentMonth").trim();
			int intYear = Integer.parseInt(request.getParameter("rentYear"));
			
			int intMonth = Months.getValue(strMonth)-1;
			
			List<ApartmentDetail> apartments =  DataAccess.getApartments();
			List<RentDetail> rentDetail = DataAccess.getRentDetails();
			List<RentLog> rentLog = DataAccess.getRentLog();
			
			List<RentLog> rentLogSorted = new ArrayList<RentLog>();
			
			for (RentLog log : rentLog)
			{
				if(log.getPayDate().getMonth()== intMonth && log.getPayDate().getYear() == intYear){
					rentLogSorted.add(log);
				}
			}
			
			request.setAttribute("apartmentDetails", apartments);
		    request.setAttribute("rentDetail", rentDetail);
			request.setAttribute("rentLogs", rentLogSorted);
			
			request.setAttribute("selMonth", strMonth);
			request.setAttribute("selYear", intYear);
		    
			request.getRequestDispatcher("Reports.jsp").include(request,
					response);
		}
		catch (Exception ex)
		{
			request.setAttribute("errorMessage", ex.getMessage());
			request.getRequestDispatcher("Reports.jsp").include(request,
					response);
		}

	}

}
