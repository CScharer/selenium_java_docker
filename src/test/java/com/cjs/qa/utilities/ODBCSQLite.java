package com.cjs.qa.utilities;

import com.cjs.qa.jdbc.JDBCConstants;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;
import javax.swing.JOptionPane;

public class ODBCSQLite {

  public static void main(String[] args) {
    final List<String> fields =
        Arrays.asList(
            "SubmissionID",
            "Company",
            "Environment",
            "Status",
            "SubmitType",
            "Submitter",
            "SubmissionTime",
            "StartTime",
            "CompletionTime",
            "FileName",
            "ResultsFile",
            "ResultsSent",
            "EmailAddress",
            "FileNameNew",
            "MachineName",
            "SubmitCompany");
    try (Connection oConnection =
        connectDb(
            "C:"
                + Constants.DELIMETER_PATH
                + "Automation"
                + Constants.DELIMETER_PATH
                + "BTS"
                + IExtension.SQLITE)) {
      if (oConnection != null) {
        try (Statement statement = oConnection.createStatement();
            ResultSet resultSet =
                statement.executeQuery(
                    JDBCConstants.SELECT_ALL_FROM
                        + "Core "
                        + JDBCConstants.WHERE
                        + "SubmissionTime > '2016-04-15 12:00:00.000000' "
                        + JDBCConstants.ORDER_BY
                        + "SubmissionTime DESC")) {
          while (resultSet.next()) {
            for (int fieldIndex = 0; fieldIndex < fields.size(); fieldIndex++) {
              final String field = fields.get(fieldIndex);
              if (fieldIndex < (fields.size() - 1)) {
                System.out.print(field + ":[" + resultSet.getString(field) + "]");
              } else {
                System.out.println(field + ":[" + resultSet.getString(field) + "]");
              }
            }
          }
        }
      }
    } catch (final Exception oException) {
      System.out.println(oException.getMessage());
      oException.printStackTrace();
    }
  }

  public static Connection connectDb(String database) {
    System.out.println("Connecting to [" + database + "]");

    try {
      // String dir = System.getProperty("user.dir");
      Class.forName("org" + IExtension.SQLITE + IExtension.JDBC);
      return DriverManager.getConnection("jdbc:sqlite:" + database);
    } catch (ClassNotFoundException | SQLException oException) {
      JOptionPane.showMessageDialog(null, "Problem connecting to database [" + database + "]");
      System.out.println(oException.getMessage());
      oException.printStackTrace();
      return null;
    }
  }

  public boolean execute(Connection oConnection, String sSQL) {
    try (Statement statement = oConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sSQL)) {
      System.out.println(resultSet.toString());
    } catch (final Exception oException) {
      System.out.println(oException.getMessage());
      oException.printStackTrace();
      return false;
    } finally {
      try {
        if (oConnection != null) {
          oConnection.close();
        }
      } catch (final SQLException e) {
        System.out.println(e.getMessage());
      }
    }
    return true;
  }

  public int executeUpdate(Connection oConnection, String sSQL) {
    int iReturn = 0;
    try (Statement oStatement = oConnection.createStatement()) {
      iReturn = oStatement.executeUpdate(sSQL);
      // if (iReturn == 1){
      // System.out.println(String.valueOf(iReturn) + " record updated");
      // } else {
      // System.out.println(String.valueOf(iReturn) + " records updated");
      // }
      // oConnection.commit();
      return iReturn;
    } catch (final Exception oException) {
      System.out.println(oException.getMessage());
      oException.printStackTrace();
      System.out.println(sSQL);
      return 0;
    }
  }
}
