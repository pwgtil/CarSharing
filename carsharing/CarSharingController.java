package carsharing;

import carsharing.persistance.Car;
import carsharing.persistance.Company;
import carsharing.persistance.Customer;

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
        menu.addItem(new MenuItem("Log in as a manager", this, "mgrSubMenuA"));
        menu.addItem(new MenuItem("Log in as a customer", this, "cusSelectCustomer"));
        menu.addItem(new MenuItem("Create a customer", this, "allCreateCustomer"));
        menu.execute();
    }

    public void mgrSubMenuA() {
        Menu menu = new Menu();
        menu.addItem(new MenuItem("Company list", this, "mgrSelectCompany"));
        menu.addItem(new MenuItem("Create a company", this, "mgrCreateCompany"));
        menu.execute();
    }

    public void mgrSelectCompany() {
        List<Company> companies = service.getCompanies();
        if (!companies.isEmpty()) {
            Menu menu = new Menu();
            menu.setTitle("\nChoose a company");
            companies.stream().forEach(company -> menu.addItem(new MenuItem( company.name(), this,"mgrSubMenuB", company.name())));
            menu.execute();
        } else {
            System.out.print("\nThe company list is empty!");
        }
        System.out.print("\n");
    }

    public void mgrCreateCompany() {
        System.out.print("\nEnter the company name:");
        System.out.print("\n");
        Scanner sc = new Scanner(System.in);
        String companyName = sc.nextLine();
        service.addCompany(companyName);
        System.out.print("The company was created!");
        System.out.print("\n");
    }

    public void mgrSubMenuB(String companyName) {
        Menu menu = new Menu();
        menu.setTitle("\n" + companyName + " company");
        menu.addItem(new MenuItem("Car list", this, "mgrCarList", companyName));
        menu.addItem(new MenuItem("Create a car", this, "mgrCreateCar", companyName));
        menu.execute();
        Menu.setMenuPassCounter(1);
    }

    public void mgrCarList(String companyName) {
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

    public void mgrCreateCar(String companyName) {
        System.out.print("\nEnter the car name:");
        System.out.print("\n");
        Scanner sc = new Scanner(System.in);
        String carName = sc.nextLine();
        service.addCar(carName, companyName);
        System.out.print("The car was added!");
        System.out.print("\n");
    }

    public void allCreateCustomer() {
        System.out.print("\nEnter the customer name:");
        System.out.print("\n");
        Scanner sc = new Scanner(System.in);
        String customerName = sc.nextLine();
        service.addCustomer(customerName);
        System.out.print("The customer was added!");
        System.out.print("\n");
    }

    public void cusSelectCustomer() {
        List<Customer> customers = service.getCustomers();
        if (!customers.isEmpty()) {
            Menu menu = new Menu();
            menu.setTitle("\nCustomer list");
            customers.stream().forEach(customer -> menu.addItem(new MenuItem( customer.name(), this,"cusSubMenuA", customer.name())));
            menu.execute();
        } else {
            System.out.print("\nThe customer list is empty!");
        }
        System.out.print("\n");
    }

    public void cusSubMenuA(String customerName) {
        service.setCurrentCustomer(customerName);
        Menu menu = new Menu();
//        menu.setTitle("\n" + customerName + " menu");
        menu.addItem(new MenuItem("Rent a car", this, "cusSelectCompany"));
        menu.addItem(new MenuItem("Return a rented car", this, "cusReturnCar"));
        menu.addItem(new MenuItem("My rented car", this, "cusMyCar"));
        menu.execute();
        Menu.setMenuPassCounter(1);
    }

    public void cusReturnCar() {
        boolean isReturned = service.carReturnedByCustomer(customerName);
        if (isReturned) {
            System.out.print("\nYou've returned a rented car!");
        } else {
            System.out.print("\nYou didn't rent a car!");
        }
        System.out.print("\n");
    }

    public void cusMyCar() {
        if (service.getRentedCarByCurrentCustomer() != null) {
            System.out.print("\nYour rented car:");
            System.out.print("\n" + service.getRentedCarByCurrentCustomer());
            System.out.print("\nCompany:");
            System.out.print("\n" + service.getRentedCarCompanyByCurrentCustomer());
        } else {
            System.out.print("\nYou didn't rent a car!");
        }
        System.out.print("\n");
    }

    public void cusSelectCompany() {
        List<Company> companies = service.getCompanies();
        if (!companies.isEmpty()) {
            Menu menu = new Menu();
            menu.setTitle("\nChoose a company");
            companies.stream().forEach(company -> menu.addItem(new MenuItem( company.name(), this,"cusSelectCar", company.name())));
            menu.execute();
        } else {
            System.out.print("\nThe company list is empty!");
        }
        System.out.print("\n");
    }

    public void cusSelectCar(String companyName) {
        List<Car> cars = service.getCars(companyName);
        if (!cars.isEmpty()) {
            Menu menu = new Menu();
            menu.setTitle("\nChoose a car");
            cars.stream().forEach(car -> menu.addItem(new MenuItem( car.name(), this,"cusRentCar", car.name())));
            menu.execute();
        } else {
            System.out.print("\nThe car list is empty!");
        }
        System.out.print("\n");
    }

    public void cusRentCar(String carName) {
        service.setRentedCarByCurrentCustomer(carName);
        System.out.print("\nYou rented " + carName);
        System.out.print("\n");
        Menu.setMenuPassCounter(2);
    }
}
