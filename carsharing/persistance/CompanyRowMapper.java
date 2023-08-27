package carsharing.persistance;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CompanyRowMapper implements RowMapper<Company>{
    @Override
    public Company mapRow(ResultSet resultSetItem) throws SQLException {
        int id = resultSetItem.getInt("ID");
        String name = resultSetItem.getString("NAME");
        return new Company(id, name);
    }
}
