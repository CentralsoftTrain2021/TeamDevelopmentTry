package web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ShainListBean;
import dao.DBUtil;
import dao.EmployeesDao;
import dao.EmployeesVo;

@WebServlet("/ShainListServlet")
public class ShainListServlet extends HttpServlet {

	protected void doPost(
			HttpServletRequest request,
			HttpServletResponse response
			) throws ServletException, IOException
	{

		List<EmployeesVo>  shainList = getEmployeesVoList();

		ShainListBean bean = new ShainListBean();

		bean.setMsg(			"社員リストを表示します");
		bean.setShainList( 		shainList );

		EmployeesVo emp = (EmployeesVo)request.getSession().getAttribute("EmployeesVo");
		bean.setLoginShainName(	emp.getEmployeename());

		request.setAttribute("bean", bean);
		//JSPに遷移する
		RequestDispatcher disp = request.getRequestDispatcher("/shainlist.jsp");
		disp.forward(request, response);
	}

	//DBから従業員を取得する
	private static List<EmployeesVo> getEmployeesVoList()
	{
		List<EmployeesVo> empList = null;
		DBUtil dbUtil = new  DBUtil();

		//コネクションを取得
		try( Connection  con = dbUtil.getConection(); )
		{
			EmployeesDao edao = new EmployeesDao( con );

			//DBから従業員を取得
			empList = edao.getEmployeelist();

			//取得したデータを表示する
			//System.out.println( emp );

		}
		catch( SQLException e )
		{
			throw new RuntimeException( e );//ランタイム例外に載せ替えて再スロー
		}

		return empList;
	}


}
