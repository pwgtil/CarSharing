package carsharing;

import carsharing.persistance.Company;
import carsharing.persistance.CompanyDao;
import carsharing.persistance.DataBaseClient;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarSharingService {
    static public String DB_NAME;

    private CompanyDao companyDao;
    private List<Company> companies = new ArrayList<>();

    public CarSharingService(String dbName) {
        CarSharingService.DB_NAME = dbName;
        try {
            init();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void init() throws ClassNotFoundException, SQLException {
        Class.forName(DataBaseClient.JDBC_DRIVER);
        companyDao = new CompanyDao();
        new MenuController(this).init();
//        companyDao.add(new Company(1, "My fantastic Company"));
//        companyDao.add(new Company(1, "My fantastic Company 2"));
//        companyDao.add(new Company(1, "My fantastic Company 3"));
    }


    public List<Company> getCompanies() {
        companies = companyDao.findAll();
        return companies;
    }

    public void addCompany(String companyName) {
        companyDao.add(new Company(0, companyName));
    }
}
