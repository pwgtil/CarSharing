package carsharing;

import carsharing.persistance.DataBaseClient;

import java.util.Arrays;
import java.util.List;

public class Main {
    private static final String DB_FILE_NAME_PARAM = "-databaseFileName";
    private static final String DB_FILE_NAME_DEFAULT = "carsharing";


    public static void main(String[] args) {
        List<String> argsList = Arrays.stream(args).toList();
        String dbName;

        if (argsList.contains(DB_FILE_NAME_PARAM)) {
            int dbNameIndex = argsList.indexOf(DB_FILE_NAME_PARAM) + 1;
            try {
                dbName = argsList.get(dbNameIndex);
            } catch (IndexOutOfBoundsException e) {
                dbName = DB_FILE_NAME_DEFAULT;
            }
        } else {
            dbName = DB_FILE_NAME_DEFAULT;
        }

        new CarSharingService(dbName);

    }
}