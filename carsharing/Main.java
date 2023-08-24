package carsharing;

public class Main {

    public static void main(String[] args) {
        if (args.length > 1) {
            CarSharingService db = new CarSharingService(args[1]);
        } else {
            CarSharingService db = new CarSharingService("");
        }
    }
}