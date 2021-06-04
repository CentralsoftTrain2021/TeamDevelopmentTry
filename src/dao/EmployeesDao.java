package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeesDao
{
	private Connection con;

	 private static final String SELECT_ALL_SQL = "select "
		+ "   EMPLOYEEID"
		+ "  ,EMPLOYEENAME"
		+ "  ,HEIGHT"
		+ "  ,EMAIL"
		+ "  ,WEIGHT"
		+ "  ,HIREFISCALYEAR"
		+ "  ,BIRTHDAY"
		+ "  ,BLOODTYPE"
		+ " from "
		+ "   EMPLOYEES ";

	 private static final String SELECT_ONE_SQL =
			 "select "
		+ "   EMPLOYEEID"
		+ "  ,EMPLOYEENAME"
		+ "  ,HEIGHT"
		+ "  ,EMAIL"
		+ "  ,WEIGHT"
		+ "  ,HIREFISCALYEAR"
		+ "  ,BIRTHDAY"
		+ "  ,BLOODTYPE"
		+ " from "
		+ "   EMPLOYEES "
		+ " where"
		+ "   EMPLOYEEID=?";


	public EmployeesDao(Connection con) {
		super();
		this.con = con;
	}

	//SQL実行処理　
	public List<EmployeesVo> getEmployeelist() throws SQLException
	{
		//Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rset = null;
		List<EmployeesVo> list = new ArrayList<EmployeesVo>();

		//共通化した、コネクション取得メソッド
		//con = getConection();

		try
		{
			stmt = con.prepareStatement( SELECT_ALL_SQL );

			/*④ ｓｑｌ実行 */
			rset = stmt.executeQuery();

			/*⑤ 取得したデータを表示します。 */
			while (rset.next())
			{
				EmployeesVo em = new EmployeesVo();

				//int eid = rset.getInt("EMPLOYEEID");
				int eid =rset.getInt(1);
				em.setEmployeeid( eid );

				em.setEmployeename( 	rset.getString(2));
				em.setHeight( 			rset.getBigDecimal(3));
				em.setEmail(			rset.getString(4));
				em.setWeight(			rset.getBigDecimal(5));
				em.setHirefiscalyear(	rset.getInt(6));
				em.setBirthday(			rset.getDate(7));
				em.setBloodtype(		rset.getString(8));

				list.add(em);
			}
		}

		finally {
			try {
				if(stmt != null){
				  stmt.close();
				  stmt = null;
				}
				if(rset != null){
				  rset.close();
				  rset = null;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			return list;
		}
	}

	//SQL実行処理　
	// try-with-resouceでの書き方
	public List<EmployeesVo> getEmployeelist2() throws SQLException
	{
		List<EmployeesVo> list = new ArrayList<EmployeesVo>();

		try(
			 PreparedStatement 	stmt = con.prepareStatement( SELECT_ALL_SQL );
			 ResultSet 		 	rset = stmt.executeQuery();
			)
		{
			//更新の場合は以下のメソッドを使う ( insert, delete, update)
			//int numOfUpdateRecords = stmt.executeUpdate();		/*取得したデータを取り出す */

			while (rset.next())
			{
				EmployeesVo em = new EmployeesVo();

				//int eid = rset.getInt("EMPLOYEEID");
				int eid =rset.getInt(1);
				em.setEmployeeid( eid );

				em.setEmployeename( 	rset.getString(2));
				em.setHeight( 			rset.getBigDecimal(3));
				em.setEmail(			rset.getString(4));
				em.setWeight(			rset.getBigDecimal(5));
				em.setHirefiscalyear(	rset.getInt(6));
				em.setBirthday(			rset.getDate(7));
				em.setBloodtype(		rset.getString(8));

				list.add(em);
			}
		}

		return list;
	}

	// 一個従業員を取得
	public EmployeesVo getEmployee(int id) throws SQLException
	{
		EmployeesVo em = new EmployeesVo();

		try(
			 PreparedStatement 	stmt = con.prepareStatement( SELECT_ONE_SQL );
			)
		{
			stmt.setInt(1 ,id );
			ResultSet 		 	rset = stmt.executeQuery();

			while (rset.next())
			{
				int eid =rset.getInt(1);
				em.setEmployeeid( eid );

				em.setEmployeename( 	rset.getString(2));
				em.setHeight( 			rset.getBigDecimal(3));
				em.setEmail(			rset.getString(4));
				em.setWeight(			rset.getBigDecimal(5));
				em.setHirefiscalyear(	rset.getInt(6));
				em.setBirthday(			rset.getDate(7));
				em.setBloodtype(		rset.getString(8));
			}
		}

		return em;
	}
}
