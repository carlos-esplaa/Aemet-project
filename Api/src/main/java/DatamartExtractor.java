import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import static java.sql.DriverManager.getConnection;
public class DatamartExtractor {

    public java.sql.Connection connect() {
        Connection conn = null;
        String url = "jdbc:sqlite:Datamartdir/datamart.db";
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
    public List<EventsFromDatamart> selectMinAll() {
        String sql = "SELECT * FROM MinDegrees";
        List<EventsFromDatamart> minEventsFromDatamartList;
        try {
            java.sql.Connection conn = this.connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            EventsFromDatamart eventsFromDatamart = new EventsFromDatamart();
            minEventsFromDatamartList = new ArrayList<>();
            while (rs.next()) {
                EventsFromDatamart eventsFromDatamart1 = ExtractFromDatamartToList(rs, eventsFromDatamart);
                minEventsFromDatamartList.add(eventsFromDatamart1);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return minEventsFromDatamartList;
    }
    public List<EventsFromDatamart> selectMaxAll() {
        String sql = "SELECT * FROM MaxDegrees";
        List<EventsFromDatamart> maxEventsFromDatamartList;
        try {
            java.sql.Connection conn = this.connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            EventsFromDatamart eventsFromDatamart = new EventsFromDatamart();
            maxEventsFromDatamartList = new ArrayList<>();
            while (rs.next()) {
                EventsFromDatamart eventsFromDatamart1 = ExtractFromDatamartToList(rs, eventsFromDatamart);
                maxEventsFromDatamartList.add(eventsFromDatamart1);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return maxEventsFromDatamartList;
    }
    private EventsFromDatamart ExtractFromDatamartToList(ResultSet rs, EventsFromDatamart eventsFromDatamart) throws SQLException {
        eventsFromDatamart.setMoment(rs.getString("Moment"));
        eventsFromDatamart.setLocation(rs.getString("Location"));
        eventsFromDatamart.setStation(rs.getString("Station"));
        eventsFromDatamart.setDegree(rs.getDouble("Degree"));
        return eventsFromDatamart;
    }
}
