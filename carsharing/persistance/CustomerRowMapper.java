package carsharing.persistance;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerRowMapper implements RowMapper<Customer> {

    @Override
    public Customer mapRow(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("ID");
        String name = resultSet.getString("NAME");
        int rentedCarId = resultSet.getInt("RENTED_CAR_ID");
        return new Customer(id, name, rentedCarId);
    }
}
