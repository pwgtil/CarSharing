package carsharing.persistance;

import carsharing.CarSharingService;
import org.h2.jdbcx.JdbcDataSource;

import java.util.List;

public class CarDao implements EntityDao<Car> {

    private static final String CONNECTION_URL = DataBaseClient.DB_URL + CarSharingService.DB_NAME;

    private static final String CREATE_DB = "CREATE TABLE IF NOT EXISTS CAR(" +
            "ID INTEGER PRIMARY KEY AUTO_INCREMENT," +
            "NAME VARCHAR(255) UNIQUE NOT NULL, " +
            "COMPANY_ID INTEGER NOT NULL," +
            "FOREIGN KEY (COMPANY_ID) REFERENCES COMPANY(ID));";
//            "COMPANY_ID INTEGER UNIQUE " +
//            "FOREIGN KEY REFERENCES COMPANY(ID));";
    private static final String SELECT_ALL = "SELECT * FROM CAR";
    private static final String SELECT = "SELECT * FROM CAR WHERE ID = %d";
    private static final String INSERT_DATA = "INSERT INTO CAR (NAME, COMPANY_ID) VALUES ('%s', %d)";
    private static final String UPDATE_DATA = "UPDATE CAR SET NAME = '%s', COMPANY_ID = %d WHERE ID = %d";
    private static final String DELETE_DATA = "DELETE FROM CAR WHERE id = %d";

    private final DataBaseClient<Car> dataBaseClient;

    public CarDao() {
        JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setURL(CONNECTION_URL);
        dataSource.setUser(DataBaseClient.USER);
        dataSource.setPassword(DataBaseClient.PASS);
        dataBaseClient = new DataBaseClient<>(dataSource, new CarRowMapper());
        dataBaseClient.run(CREATE_DB);
    }

    @Override
    public List<Car> findAll() {
        return dataBaseClient.selectForList(SELECT_ALL);
    }

    @Override
    public Car findById(int id) {
        return dataBaseClient.select(String.format(SELECT, id));
    }

    @Override
    public void add(Car car) {
        dataBaseClient.run(String.format(INSERT_DATA, car.name(), car.companyId()));
    }

    @Override
    public void update(Car car) {
        dataBaseClient.run(String.format(
                UPDATE_DATA, car.name(), car.companyId(), car.id()
        ));
    }

    @Override
    public void deleteById(int id) {
        dataBaseClient.run(String.format(
                DELETE_DATA, id)
        );
    }
}
