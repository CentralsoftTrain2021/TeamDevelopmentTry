package web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ShainListBean;
import dao.EmployeesVo;
import service.EmployeesService;

@WebServlet("/ShainListServlet")
public class ShainListServlet extends HttpServlet {

	protected void doPost(
			HttpServletRequest request,
			HttpServletResponse response
			) throws ServletException, IOException
	{

		EmployeesService service = new EmployeesService();
		List<EmployeesVo>  shainList = service.getEmployeesVoList();

		//List<EmployeesVo>  shainList  =new ArrayList<EmployeesVo>();

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




}
