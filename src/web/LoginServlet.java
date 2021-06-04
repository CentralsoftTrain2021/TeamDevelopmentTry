package web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Tashizan;
import dao.DBUtil;
import dao.EmployeesDao;
import dao.EmployeesVo;
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	protected void doPost(
			HttpServletRequest request,
			HttpServletResponse response
			) throws ServletException, IOException
	{
		//画面から入力したデータを取得する
		String idStr = request.getParameter("ID");
		String peStr = request.getParameter("PW");

		int id = Integer.parseInt(idStr);

		EmployeesVo  emp = getEmployeesVo( id );

		request.getSession().setAttribute("EmployeesVo", emp);

		Tashizan bean = new Tashizan();
		bean.setShainName( emp.getEmployeename() );

		request.setAttribute("bean", bean);

		//JSPに遷移する
		RequestDispatcher disp = request.getRequestDispatcher("/main.jsp");
		disp.forward(request, response);

	}

	//DBから従業員を取得する
	private static EmployeesVo getEmployeesVo( int id )
	{
		EmployeesVo emp = null;
		DBUtil dbUtil = new  DBUtil();

		//コネクションを取得
		try( Connection  con = dbUtil.getConection(); )
		{
			EmployeesDao edao = new EmployeesDao( con );

			//DBから従業員を取得
			emp = edao.getEmployee( id );

			//取得したデータを表示する
			System.out.println( emp );

		}
		catch( SQLException e )
		{
			throw new RuntimeException( e );//ランタイム例外に載せ替えて再スロー
		}

		return emp;
	}

}
