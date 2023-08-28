package carsharing;

import carsharing.persistance.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CarSharingService {
    static public String DB_NAME;

    private EntityDao<Company> companyDao;
    private List<Company> companies = new ArrayList<>();
    private EntityDao<Car> carDao;
    private List<Car> cars = new ArrayList<>();

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
        carDao = new CarDao();
        new CarSharingController(this).init();
    }


    public List<Company> getCompanies() {
        companies = companyDao.findAll();
        return companies;
    }

    public void addCompany(String companyName) {
        companyDao.add(new Company(0, companyName));
    }

    public List<Car> getCars(String companyName) {
        Company company = companies.stream().filter(c -> Objects.equals(c.name(), companyName)).findFirst().get();
        List<Car> list = carDao.findAll();
        List<Car> list1 = list.stream().filter(c -> c.companyId() == company.id()).toList();
        return list1;
    }

    public void addCar(String carName, String companyName) {
        Company company = companies.stream().filter(c -> Objects.equals(c.name(), companyName)).findFirst().get();
        carDao.add(new Car(0, carName, company.id()));
    }
}
