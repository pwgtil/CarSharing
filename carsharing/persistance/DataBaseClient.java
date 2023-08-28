package carsharing.persistance;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBaseClient<T> {

    // JDBC driver name and database URL
    static public final String JDBC_DRIVER = "org.h2.Driver";
    static public final String DB_URL = "jdbc:h2:./src/carsharing/db/";


    //  Database credentials
    static final String USER = "";
    static final String PASS = "";

    private final DataSource dataSource;
    private final RowMapper<T> rowMapper;

    public DataBaseClient(DataSource dataSource, RowMapper<T> rowMapper) {
        this.dataSource = dataSource;
        this.rowMapper = rowMapper;
    }

    public void run(String str) {
        try (Connection con = dataSource.getConnection();
             Statement statement = con.createStatement()
        ) {
            con.setAutoCommit(true);
            statement.executeUpdate(str);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public T select(String query) {
        List<T> results = selectForList(query);
        if (results.size() == 1) {
            return results.get(0);
        } else if (results.isEmpty()) {
            return null;
        } else {
            throw new IllegalStateException("Query returned more than one entry");
        }
    }

    public List<T> selectForList(String query) {
        List<T> results = new ArrayList<>();

        try (Connection con = dataSource.getConnection();
             Statement statement = con.createStatement()
        ) {
            ResultSet resultSetItem = statement.executeQuery(query);
            con.setAutoCommit(true);
            while (resultSetItem.next()) {
                results.add(rowMapper.mapRow(resultSetItem));
            }
            return results;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }
}
