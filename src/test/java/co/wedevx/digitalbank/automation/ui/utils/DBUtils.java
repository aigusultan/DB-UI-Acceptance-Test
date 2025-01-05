package co.wedevx.digitalbank.automation.ui.utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static co.wedevx.digitalbank.automation.ui.utils.ConfigReader.getPropertiesValue;

public class DBUtils {
    private static Connection connection = null;
    private static Statement statement;
    private static ResultSet resultSet;
    //all methods in this class will use this connection, so it needs to be declared on a class level

    //method to establish connection with DB
    public static void establishConnection() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection(
                    getPropertiesValue("digitalbank.db.url"),
                    getPropertiesValue("digitalbank.db.username"),
                    getPropertiesValue("digitalbank.db.password"));

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }


    //method that can dynamically send select statement and return a List<Map<>> of all columns
    //List will be the rows (user) and Map will be columns and values (account info for the given user)
    public static List<Map<String, Object>> runSqlSelectQuery(String sqlQuery) {
        //Object as a value because it can be a String, wrapper class int, double etc. any object

        List<Map<String, Object>> dbResultList = new ArrayList<>();

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlQuery);

            //getMetaData method returns info about your info.
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            //this class and its objects have useful methods
            int columnCount = resultSetMetaData.getColumnCount();

            while (resultSet.next()) {
                Map<String, Object> rowMap = new HashMap<>();

                for (int col = 1; col <= columnCount; col++) {
                    rowMap.put(resultSetMetaData.getColumnName(col), resultSet.getObject(col));
                }
                dbResultList.add(rowMap);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return dbResultList;
    }


    //method that inserts into db and returns the number of rows updated or 0 when the action was not performed

    //run update query
    public static int runSqlUpdateQuery(String sqlQuery) {
        int rowsUpdated = 0;

        try {
            statement = connection.createStatement();
            rowsUpdated = statement.executeUpdate(sqlQuery);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return rowsUpdated;
    }

    //delete or truncate the table


    //Return the first row from the table as a list of maps:
    public static List<Map<String, Object>> returnFirstRow(String sqlQuery) {
        List<Map<String, Object>> firstRowList = new ArrayList<>();

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlQuery);
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int columnCount = resultSetMetaData.getColumnCount();

            if (resultSet.next()) {
                Map<String, Object> firstRowMap = new HashMap<>();

                for (int col = 1; col <= columnCount; col++) {
                    firstRowMap.put(resultSetMetaData.getColumnName(col), resultSet.getObject(col));
                }
                firstRowList.add(firstRowMap);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return firstRowList;
    }

    //Return the last row from the table as a list of maps:
    public static List<Map<String, Object>> returnLastRow(String sqlQuery, String tableName) {
        List<Map<String, Object>> lastRowList = new ArrayList<>();

        if (tableName == null || tableName.isEmpty()) {
            return lastRowList;
        }

        String getTotalRowsQuery = String.format("SELECT COUNT(*) AS total_rows FROM %s", tableName);
        int lastRow = 0;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(getTotalRowsQuery);

            if (resultSet.next()) {
                lastRow = resultSet.getInt("total_rows");
            }

            resultSet = statement.executeQuery(sqlQuery);
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int columnCount = resultSetMetaData.getColumnCount();

            if (resultSet.absolute(lastRow)) {
                Map<String, Object> lastRowMap = new HashMap<>();

                for (int col = 1; col <= columnCount; col++) {
                    lastRowMap.put(resultSetMetaData.getColumnName(col), resultSet.getObject(col));
                }
                lastRowList.add(lastRowMap);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return lastRowList;
    }

    //Return the nth row from the table as a list of maps:
    public static List<Map<String, Object>> returnNthRow(String sqlQuery, int rowNumber, String tableName) {
        List<Map<String, Object>> nthRowList = new ArrayList<>();

        if (rowNumber < 1 || tableName == null || tableName.isEmpty()) {
            return nthRowList;
        }

        String getTotalRowsQuery = String.format("SELECT COUNT(*) AS total_rows FROM %s", tableName);
        int lastRow = 0;

        try (Statement statement = connection.createStatement()) {
            resultSet = statement.executeQuery(getTotalRowsQuery);

            if (resultSet.next()) {
                lastRow = resultSet.getInt("total_rows");
            }

            if (rowNumber <= lastRow) {
                resultSet = statement.executeQuery(sqlQuery);
                ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
                int columnCount = resultSetMetaData.getColumnCount();

                if (resultSet.absolute(rowNumber)) {
                    Map<String, Object> nthRowMap = new HashMap<>();

                    for (int col = 1; col <= columnCount; col++) {
                        nthRowMap.put(resultSetMetaData.getColumnName(col), resultSet.getObject(col));
                    }
                    nthRowList.add(nthRowMap);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return nthRowList;
    }

    //close connection method
    public static void closeConnection() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }

            if (statement != null) {
                statement.close();
            }

            if (connection != null) {
                connection.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
