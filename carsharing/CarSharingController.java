package carsharing;

import carsharing.persistance.Car;
import carsharing.persistance.Company;

import java.util.List;
import java.util.Scanner;

public class CarSharingController {

    private CarSharingService service;

    public CarSharingController(CarSharingService service) {
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
//        System.out.print("\nCompany list:");
        List<Company> companies = service.getCompanies();
        if (!companies.isEmpty()) {
            Menu menu = new Menu();
            menu.setTitle("\nChoose a company");
            companies.stream().forEach(company -> menu.addItem(new MenuItem( company.name(), this,"subMenuB", company.name())));
            menu.execute();
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

    public void subMenuB(String companyName) {
        Menu menu = new Menu();
        menu.setTitle("\n" + companyName + " company");
        menu.addItem(new MenuItem("Car list", this, "getCarList", companyName));
        menu.addItem(new MenuItem("Create a car", this, "createCar", companyName));
        menu.execute();
        Menu.setMenuPassCounter(1);
    }

    public void getCarList(String companyName) {
        List<Car> cars = service.getCars(companyName);
        if (!cars.isEmpty()) {
            System.out.print("\n" + companyName + " cars:");
            for (int i = 0; i < cars.size(); i++) {
                System.out.print("\n" + (i + 1) + ". " + cars.get(i).name());
            }
        } else {
            System.out.print("\nThe car list is empty!");
        }
        System.out.print("\n");
    }

    public void createCar(String companyName) {
        System.out.print("\nEnter the car name:");
        System.out.print("\n");
        Scanner sc = new Scanner(System.in);
        String carName = sc.nextLine();
        service.addCar(carName, companyName);
        System.out.print("The car was added!");
        System.out.print("\n");
    }
}
