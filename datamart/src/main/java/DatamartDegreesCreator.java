import java.sql.Connection;
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
        String url = "jdbc:sqlite:C:\\Users\\fenix\\IdeaProjects\\Aemet-project\\Datamart.db";
        String maxDegrees = "CREATE TABLE IF NOT EXISTS MaxDegrees (\n"
                + "	day text,\n"
                + "	moment text,\n"
                + "	place text,\n"
                + "	station text,\n"
                + "	worth integer PRIMARY KEY\n"
                + ");";
        String minDegrees = "CREATE TABLE IF NOT EXISTS MinDegrees (\n"
                + "	day text,\n"
                + "	date text,\n"
                + "	place text,\n"
                + "	station text,\n"
                + "	worth integer PRIMARY KEY\n"
                + ");";
        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            stmt.execute(maxDegrees);
            stmt.execute(minDegrees);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void insertToMaxDegree(String Moment, String Day, String Locate, String Station, Double Degree,String dbPath) {
        String sql = "INSERT INTO MaxDegrees(Moment,Day,Locate,Station,Degree) VALUES(?,?,?,?,?)";
        try (Connection conn = this.connect(dbPath);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, Moment);
            pstmt.setString(2, Day);
            pstmt.setString(3, Locate);
            pstmt.setString(4, Station);
            pstmt.setDouble(5, Degree);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
    public void insertToMinDegree(String Moment, String Day, String Locate, String Station, Double Degree,String dbPath) {
        String sql = "INSERT INTO MinDegrees(Moment,Day,Locate,Station,Degree) VALUES(?,?,?,?,?)";
        try (Connection conn = this.connect(dbPath);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, Moment);
            pstmt.setString(2, Day);
            pstmt.setString(3, Locate);
            pstmt.setString(4, Station);
            pstmt.setDouble(5, Degree);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
}
