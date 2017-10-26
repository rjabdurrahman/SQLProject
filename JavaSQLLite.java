package javasqllite;
import java.sql.*;

public class JavaSQLLite {
    public static Connection Connector(){
        try{
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:resultmanagement.sqlite");
            return conn;
        } catch (Exception e){
            return null;
        }
    }
    public static boolean isDbConnected(){
        try {
            return !Connector().isClosed();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public static void main(String[] args) throws SQLException {
        if(isDbConnected())
            System.out.println("Connected!");
        else
            System.out.println("Not Connected!");
        //Data
        ResultSet rs = null;
        String query = "SELECT * FROM students";
        System.out.println("Roll" + "Name");
        try {
            rs = Connector().createStatement().executeQuery(query);
            while(rs.next()){
                System.out.println(rs.getString(1) + "  " + rs.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            rs.close();
        }
    }
    
}
