package carsharing.persistance;

import java.sql.ResultSet;
import java.sql.SQLException;

class CarRowMapper implements RowMapper<Car>{
    @Override
    public Car mapRow(ResultSet resultSetItem) throws SQLException {
        int id = resultSetItem.getInt("ID");
        String name = resultSetItem.getString("NAME");
        int companyId = resultSetItem.getInt("COMPANY_ID");
        return new Car(id, name, companyId);
    }
}
