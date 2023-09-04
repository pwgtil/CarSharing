package carsharing;

import java.util.Arrays;
import java.util.List;

public class Main {
    private static final String DB_FILE_NAME_PARAM = "-databaseFileName";
    private static final String DB_FILE_NAME_DEFAULT = "carsharing";

    public static void main(String[] args) {
        List<String> argsList = Arrays.stream(args).toList();
        String dbName = DB_FILE_NAME_DEFAULT;

        if (argsList.contains(DB_FILE_NAME_PARAM)) {
            try {
                dbName = argsList.get(argsList.indexOf(DB_FILE_NAME_PARAM) + 1);
            } catch (Exception e) {
                System.out.println("Parameter " + DB_FILE_NAME_PARAM + " does not have a right value!");
            }
        }

        new CarSharingService(dbName);
    }
}