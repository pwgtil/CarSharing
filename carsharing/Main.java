package carsharing;

public class Main {

    public static void main(String[] args) {
        if (args.length > 1) {
            H2DBConnection db = new H2DBConnection(args[1]);
        } else {
            H2DBConnection db = new H2DBConnection("");
        }
    }
}