package carsharing.persistance;

import carsharing.CarSharingService;
import org.h2.jdbcx.JdbcDataSource;

import java.util.List;

public class CustomerDao extends EntityDao<Customer>{
    private static final String CONNECTION_URL = DataBaseClient.DB_URL + CarSharingService.DB_NAME;

    private static final String CREATE_DB = "CREATE TABLE IF NOT EXISTS CUSTOMER(" +
            "ID INTEGER PRIMARY KEY AUTO_INCREMENT," +
            "NAME VARCHAR(255) UNIQUE NOT NULL, " +
            "RENTED_CAR_ID INTEGER," +
            "FOREIGN KEY (RENTED_CAR_ID) REFERENCES CAR(ID));";
    private static final String SELECT_ALL = "SELECT * FROM CUSTOMER";
    private static final String SELECT = "SELECT * FROM CUSTOMER WHERE ID = %d";
    private static final String INSERT_DATA = "INSERT INTO CUSTOMER (NAME, RENTED_CAR_ID) VALUES ('%s', null)";
    private static final String UPDATE_DATA = "UPDATE CUSTOMER SET NAME = '%s', RENTED_CAR_ID = %d WHERE ID = %d";
    private static final String DELETE_DATA = "DELETE FROM CUSTOMER WHERE id = %d";

    private final DataBaseClient<Customer> dataBaseClient;

    public CustomerDao() {
        JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setURL(CONNECTION_URL);
        dataSource.setUser(DataBaseClient.USER);
        dataSource.setPassword(DataBaseClient.PASS);
        dataBaseClient = new DataBaseClient<>(dataSource, new CustomerRowMapper());
        dataBaseClient.run(CREATE_DB);
    }

    @Override
    public List<Customer> findAll() {
        return dataBaseClient.selectForList(SELECT_ALL);
    }

    @Override
    public Customer findById(int id) {
        return dataBaseClient.select(String.format(SELECT, id));
    }

    @Override
    public void add(Customer entity) {
        dataBaseClient.run(String.format(INSERT_DATA, entity.name()));
    }

    @Override
    public void update(Customer entity) {
        dataBaseClient.run(String.format(UPDATE_DATA, entity.name(), entity.rentedCarId(), entity.id()));
    }

    @Override
    public void deleteById(int id) {
        dataBaseClient.run(String.format(DELETE_DATA, id));
    }
}
