package finals;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DataLink;
import model.Meeting;

/**
 * Servlet implementation class AddMeeting
 */
@WebServlet("/AddMeeting")
public class AddMeeting extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddMeeting()
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
			request.setAttribute("Days", DataLink.getDays());
			request.setAttribute("TimeSlots", DataLink.getTimeSlots());
			request.setAttribute("Meetings", DataLink.getMeetings());
			request.getRequestDispatcher("AddMeeting.jsp").include(request,
					response);
		}
		catch (Exception e)
		{
			request.setAttribute("errorMessage", e.getMessage());
			request.getRequestDispatcher("AddMeeting.jsp").include(request,
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

			boolean a = true;
			String day = request.getParameter("day");
			String time = request.getParameter("time");
			String note = request.getParameter("note");

			int iday = Integer.parseInt(day);
			int itime = Integer.parseInt(time);

			ArrayList<Meeting> Meetings = DataLink.getMeetings();

			for (Meeting temp : Meetings)
			{
				if (temp.getDayID() == iday && temp.getTimeID() == itime)
				{
					a = false;
				}
			}

			if (time != null && a)
			{
				DataLink.insertMeeting(iday, itime, note);
				response.sendRedirect("Final");
			}
			else
			{
				request.setAttribute("errorMessage",
						"A meeting for same time  already exists. Please Choose another time.");
				request.setAttribute("Days", DataLink.getDays());
				request.setAttribute("TimeSlots", DataLink.getTimeSlots());
				request.getRequestDispatcher("AddMeeting.jsp").include(request,
						response);
			}
		}
		catch (Exception e)
		{
			request.setAttribute("errorMessage", e.getMessage());
			request.getRequestDispatcher("AddMeeting.jsp").include(request,
					response);
		}
	}

}
