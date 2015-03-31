package dbConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class dbConnection {

	public static void main(String[] args) {

		Connection con;
		Statement stmt;
		ResultSet rs;
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/java_music_project?user=root&password=");
			stmt = con.createStatement();
			rs = stmt.executeQuery("select * from interpret");
			while(rs.next()){
				System.out.println(rs.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
