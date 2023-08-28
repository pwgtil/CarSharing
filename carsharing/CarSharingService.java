package carsharing;

import carsharing.persistance.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CarSharingService {
    static public String DB_NAME;

    private EntityDao<Company> companyDao;
    private EntityDao<Car> carDao;
    private EntityDao<Customer> customerDao;
    private List<Company> companies = new ArrayList<>();
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
        customerDao = new CustomerDao();
        new CarSharingController(this).init();
        /*
        * Test
        * */
//        companyDao.add(new Company(0, "New rentals"));
//        carDao.add(new Car(0, "Fiat panda", 1));
//        customerDao.add(new Customer(0, "Test customer 1", null));
//        customerDao.add(new Customer(0, "Test customer 2", null));
//        customerDao.add(new Customer(0, "Test customer 3", null));
//        customerDao.update(new Customer(1, "Test customer 1", 1));
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
        return carDao.findAll().stream().filter(c -> c.companyId() == company.id()).toList();
    }

    public void addCar(String carName, String companyName) {
        Company company = companies.stream().filter(c -> Objects.equals(c.name(), companyName)).findFirst().get();
        carDao.add(new Car(0, carName, company.id()));
    }
}
