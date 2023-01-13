package view;
import java.sql.*;
public class DatamartDegreesCreator {
    public Connection connect(String datamart) {
        Connection conn = null;
        String url = "jdbc:sqlite:" + datamart;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public void createNewDatabase(String fileName) {

        String url = "jdbc:sqlite:" + fileName;
        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void createNewTable(){
        String url = "jdbc:sqlite:C:Datamartdir/datamart.db";
        String maxDegrees = "CREATE TABLE IF NOT EXISTS MaxDegrees (\n"
                + "	Moment text,\n"
                + "	Location text,\n"
                + "	Station text,\n"
                + "	Degree number\n"
                + ");";
        String minDegrees = "CREATE TABLE IF NOT EXISTS MinDegrees (\n"
                + "	Moment text,\n"
                + "	Location text,\n"
                + "	Station text,\n"
                + "	Degree number\n"
                + ");";
        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            stmt.execute(maxDegrees);
            stmt.execute(minDegrees);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void insertTomaxDegrees(String Moment, String Location, String Station, Double Degree,String dbPath) {
        String sql = "INSERT INTO MaxDegrees(Moment,Location,Station,Degree) VALUES(?,?,?,?)";
        try (Connection conn = this.connect(dbPath);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, Moment);
            pstmt.setString(2, Location);
            pstmt.setString(3, Station);
            pstmt.setDouble(4, Degree);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
    public void insertTominDegrees(String Moment, String Location, String Station, Double Degree,String dbPath) {
        String sql = "INSERT INTO MinDegrees(Moment,Location,Station,Degree) VALUES(?,?,?,?)";
        try (Connection conn = this.connect(dbPath);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, Moment);
            pstmt.setString(2, Location);
            pstmt.setString(3, Station);
            pstmt.setDouble(4, Degree);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
