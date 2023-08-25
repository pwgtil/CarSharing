package carsharing;

import carsharing.persistance.Company;

import java.util.List;
import java.util.Scanner;

public class MenuController {

    private CarSharingService service;

    public MenuController(CarSharingService service) {
        this.service = service;
    }

    public void init() {
        mainMenu();
    }


    private void mainMenu() {
        Menu menu = new Menu();
        menu.addItem(new MenuItem("Log in as a manager", this, "subMenuA"));
        menu.execute();
    }

    public void subMenuA() {
        Menu menu = new Menu();
        menu.addItem(new MenuItem("Company list", this, "getCompanyList"));
        menu.addItem(new MenuItem("Create a company", this, "createCompany"));
        menu.execute();
    }

    public void getCompanyList() {
        System.out.print("\nCompany list:");
        List<Company> companies = service.getCompanies();
        if (!companies.isEmpty()) {
            companies.stream().forEach(company -> System.out.print("\n" + company.id() + ". " + company.name()));
        } else {
            System.out.print("\nThe company list is empty!");
        }
        System.out.print("\n");
    }

    public void createCompany() {
        System.out.print("\nEnter the company name:");
        System.out.print("\n");
        Scanner sc = new Scanner(System.in);
        String companyName = sc.nextLine();
        service.addCompany(companyName);
        System.out.print("The company was created!");
        System.out.print("\n");
    }
}
