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
    final List<String> lFields =
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
    Connection oConnection =
        connectDb(
            "C:"
                + Constants.DELIMETER_PATH
                + "Automation"
                + Constants.DELIMETER_PATH
                + "BTS"
                + IExtension.SQLITE);
    try {
      final Statement oStatement = oConnection.createStatement();
      final ResultSet oResultSet =
          oStatement.executeQuery(
              JDBCConstants.SELECT_ALL_FROM
                  + "Core "
                  + JDBCConstants.WHERE
                  + "SubmissionTime > '2016-04-15 12:00:00.000000' "
                  + JDBCConstants.ORDER_BY
                  + "SubmissionTime DESC");
      while (oResultSet.next()) {
        for (int iField = 0; iField < lFields.size(); iField++) {
          final String sField = lFields.get(iField);
          if (iField < (lFields.size() - 1)) {
            System.out.print(sField + ":[" + oResultSet.getString(sField) + "]");
          } else {
            System.out.println(sField + ":[" + oResultSet.getString(sField) + "]");
          }
        }
      }
      oConnection = null;
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
      final Connection oConnection = DriverManager.getConnection("jdbc:sqlite:" + database);
      return oConnection;
    } catch (ClassNotFoundException | SQLException oException) {
      JOptionPane.showMessageDialog(null, "Problem connecting to database [" + database + "]");
      System.out.println(oException.getMessage());
      oException.printStackTrace();
      return null;
    }
  }

  public boolean execute(Connection oConnection, String sSQL) {
    try {
      final Statement oStatement = oConnection.createStatement();
      final ResultSet oResultSet = oStatement.executeQuery(sSQL);
      System.out.println(oResultSet.toString());
      oStatement.close();
      oConnection.close();
      oConnection = null;
    } catch (final Exception oException) {
      System.out.println(oException.getMessage());
      oException.printStackTrace();
      return false;
    }
    return true;
  }

  public int executeUpdate(Connection oConnection, String sSQL) {
    int iReturn = 0;
    try {
      final Statement oStatement = oConnection.createStatement();
      iReturn = oStatement.executeUpdate(sSQL);
      // if (iReturn == 1){
      // System.out.println(String.valueOf(iReturn) + " record updated");
      // } else {
      // System.out.println(String.valueOf(iReturn) + " records updated");
      // }
      // oConnection.commit();
      oStatement.close();
      return iReturn;
    } catch (final Exception oException) {
      System.out.println(oException.getMessage());
      oException.printStackTrace();
      System.out.println(sSQL);
      return 0;
    }
  }
}
