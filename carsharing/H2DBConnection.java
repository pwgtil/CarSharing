package carsharing;

import carsharing.persistance.Company;
import carsharing.persistance.CompanyDao;
import carsharing.persistance.DbClient;

import java.sql.SQLException;

public class H2DBConnection {

    public H2DBConnection(String dbName) {
        if (!dbName.isEmpty()) {
            DbClient.DB_NAME = dbName;
        }
        try {
            init();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void init() throws ClassNotFoundException, SQLException {
        Class.forName(DbClient.JDBC_DRIVER);
        CompanyDao companyDao = new CompanyDao();
        companyDao.add(new Company(1, "My fantastic Company"));
        companyDao.add(new Company(1, "My fantastic Srompany"));
        companyDao.add(new Company(1, "My fantastic Death"));
        companyDao.add(new Company(1, "My fantastic Inne"));
    }
}
