package carsharing.persistance;

import org.h2.jdbcx.JdbcDataSource;

import java.util.List;

public class CompanyDao implements EntityDao<Company> {

    private static final String CONNECTION_URL = DataBaseClient.DB_URL + DataBaseClient.DB_NAME;

    private static final String CREATE_DB = "CREATE TABLE IF NOT EXISTS COMPANY(" +
            "ID INTEGER PRIMARY KEY AUTO_INCREMENT," +
            "NAME TEXT NOT NULL);";
    private static final String SELECT_ALL = "SELECT * FROM COMPANY";
    private static final String SELECT = "SELECT * FROM COMPANY WHERE ID = %d";
//    private static final String INSERT_DATA = "INSERT INTO COMPANY VALUES (%d , '%s')";
    private static final String INSERT_DATA = "INSERT INTO COMPANY (NAME) VALUES '%s'";
    private static final String UPDATE_DATA = "UPDATE COMPANY SET NAME = '%s' WHERE ID = %d";
    private static final String DELETE_DATA = "DELETE FROM COMPANY WHERE id = %d";

    private final DataBaseClient dataBaseClient;

    public CompanyDao() {
        JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setURL(CONNECTION_URL);
        dataSource.setUser(DataBaseClient.USER);
        dataSource.setPassword(DataBaseClient.PASS);
        dataBaseClient = new DataBaseClient(dataSource);
        dataBaseClient.run(CREATE_DB);
    }

    @Override
    public List<Company> findAll() {
        return dataBaseClient.selectForList(SELECT_ALL);
    }

    @Override
    public Company findById(int id) {
        return dataBaseClient.select(String.format(SELECT, id));
    }

    @Override
    public void add(Company company) {
        dataBaseClient.run(String.format(INSERT_DATA, company.name()));
//        dbClient.run(String.format(INSERT_DATA, company.id(), company.name()));
    }

    @Override
    public void update(Company company) {
        dataBaseClient.run(String.format(
                UPDATE_DATA, company.name(), company.id()
        ));
    }

    @Override
    public void deleteById(int id) {
        dataBaseClient.run(String.format(
                DELETE_DATA, id)
        );
    }
}
