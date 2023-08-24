package carsharing;

import carsharing.persistance.Company;
import carsharing.persistance.CompanyDao;
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
//        companyDao.add(new Company(1, "My fantastic Srompany"));
//        companyDao.add(new Company(1, "My fantastic Death"));
//        companyDao.add(new Company(1, "My fantastic Inne"));

        mainMenu();
    }

    private void mainMenu() {
        Menu menu = new Menu();
        menu.setTitle("*** My Main Menu ***");
        menu.addItem(new MenuItem("Option A", this, "subMenuA"));
        menu.addItem(new MenuItem("Option B", this, "subMenuB"));
        menu.addItem(new MenuItem("Option C", this, "performOptionC"));
        menu.execute();
    }

    public void subMenuA() {
        Menu menu = new Menu();
        menu.setTitle("*** Sub Menu A ***");
        menu.addItem(new MenuItem("Option Aa"));
        menu.addItem(new MenuItem("Option Ab"));
        menu.execute();
    }

    /* This menu has no menu items but will still generate a single exit option. */
    public void subMenuB() {
        Menu menu = new Menu();
        menu.setTitle("*** Sub Menu B ***");
        menu.execute();
    }

    /* Added a confirmation request here to demonstrate how it could be used. */
    public void performOptionC() {
        boolean confirm = ConsoleUtils.requestConfirmation();
        if (confirm)
            System.out.println("\nDo Option C...");
        else System.out.println("\nOption C cancelled!");
        ConsoleUtils.pauseExecution();
    }
}
