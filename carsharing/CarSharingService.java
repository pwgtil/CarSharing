package carsharing;

import carsharing.persistance.DataBaseClient;

import java.sql.SQLException;

public class CarSharingService {

    public CarSharingService(String dbName) {
        if (!dbName.isEmpty()) {
            DataBaseClient.DB_NAME = dbName;
        }
        try {
            init();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void init() throws ClassNotFoundException, SQLException {
//        Class.forName(DataBaseClient.JDBC_DRIVER);
//        CompanyDao companyDao = new CompanyDao();
//        companyDao.add(new Company(1, "My fantastic Company"));
//        companyDao.add(new Company(1, "My fantastic Company 2"));
//        companyDao.add(new Company(1, "My fantastic Company 3"));

        mainMenu();
    }

    private void mainMenu() {
        Menu menu = new Menu();
        menu.addItem(new MenuItem("Log in as a manager", this, "subMenuA"));
        menu.execute();
    }

    public void subMenuA() {
        Menu menu = new Menu();
        menu.addItem(new MenuItem("Company List", this, "getCompanyList"));
        menu.addItem(new MenuItem("Create a company", this, "createCompany"));
        menu.execute();
    }

    public void getCompanyList() {
        System.out.println("\nCompany list:");
        System.out.println("\nList of companies (TODO)");
    }

    public void createCompany() {
        System.out.println("\nEnter the company name:");
        System.out.println("\nCompany creation (TODO)");
    }
}
