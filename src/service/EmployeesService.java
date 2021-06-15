package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dao.DBUtil;
import dao.EmployeesDao;
import dao.EmployeesVo;

public class EmployeesService 
{
	//DBから従業員を取得する
		public EmployeesVo getEmployeesVo( int id )
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
		
		//DBから従業員を取得する
		public List<EmployeesVo> getEmployeesVoList()
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
