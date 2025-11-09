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

public class ODBCMSAccess {

  public static void main(String[] args) {
    final List<String> listFields =
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
    // Map<String, String> map = new HashMap<>();
    Connection connection =
        connectDb(
            "C:"
                + Constants.DELIMETER_PATH
                + "Temp"
                + Constants.DELIMETER_PATH
                + "qatoolsweb"
                + IExtension.MDB);
    final ODBCSQLite odbcSQLite = new ODBCSQLite();
    final Connection connectionSQLite =
        ODBCSQLite.connectDb(
            "C:"
                + Constants.DELIMETER_PATH
                + "Automation"
                + Constants.DELIMETER_PATH
                + "BTS.sqlite");
    String sSQL = JDBCConstants.DELETE_FROM + "[Core];";
    odbcSQLite.executeUpdate(connectionSQLite, sSQL);
    int iRecords = 0;
    try {
      final Statement statement = connection.createStatement();
      final ResultSet resultSet =
          statement.executeQuery(
              JDBCConstants.SELECT_ALL_FROM
                  + "[tblSubmissionLog] "
                  + JDBCConstants.ORDER_BY
                  + "[SubmissionID]");
      while (resultSet.next()) {
        // System.out.println(oResultSet.getString(1));
        final StringBuilder oStringBuilderPre = new StringBuilder();
        oStringBuilderPre.append(JDBCConstants.INSERT_INTO + "[Core] (");
        final StringBuilder oStringBuilderValues = new StringBuilder();
        for (int iField = 0; iField < listFields.size(); iField++) {
          final String sField = listFields.get(iField);
          final String sValue = resultSet.getString(sField);
          // map.put("SubmissionID",
          // oResultSet.getString("SubmissionID"));
          // map.put(sField, sValue);
          if (sValue != null) {
            if (iField == (listFields.size() - 1)) {
              oStringBuilderPre.append("[" + sField + "]) VALUES (");
              oStringBuilderValues.append("'" + sValue + "');");
            } else {
              oStringBuilderPre.append("[" + sField + "], ");
              oStringBuilderValues.append("'" + sValue + "', ");
            }
          }
        }
        sSQL = oStringBuilderPre.toString() + oStringBuilderValues.toString();
        iRecords += odbcSQLite.executeUpdate(connectionSQLite, sSQL);
        if ((iRecords % 100) == 0) {
          System.out.println(iRecords + " records");
        }
        // System.out.println(map.toString());
        statement.close();
        // oConnection.close();
        // oConnection = null;
      }
      connection = null;
      System.out.println(iRecords + " records");
    } catch (final Exception oException) {
      System.out.println(oException.getMessage());
      oException.printStackTrace();
    }
  }

  public static Connection connectDb(String database) {
    System.out.println("Connecting to [" + database + "]");
    try {
      // String dir = System.getProperty("user.dir");
      Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
      final Connection connection = DriverManager.getConnection("jdbc:ucanaccess://" + database);
      return connection;
    } catch (ClassNotFoundException | SQLException oException) {
      JOptionPane.showMessageDialog(null, "Problem connecting to database [" + database + "]");
      System.out.println(oException.getMessage());
      oException.printStackTrace();
      return null;
    }
  }
}
