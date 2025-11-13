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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ODBCExcel {
  private static final Logger log = LogManager.getLogger(ODBCExcel.class);

  public static void main(String[] args) {
    final String database =
        System.getProperty("user.dir")
            + Constants.DELIMETER_PATH
            + "target"
            + Constants.DELIMETER_PATH
            + "Data"
            + Constants.DELIMETER_PATH
            + "urls"
            + IExtension.XLS;
    final List<String> fields = Arrays.asList("company", "abbreviation", "environment", "url");
    try (Connection connection = connectDb(database)) {
      if (connection != null) {
        try (Statement statement = connection.createStatement();
            ResultSet resultSet =
                statement.executeQuery(
                    JDBCConstants.SELECT_ALL_FROM
                        + "urls "
                        + JDBCConstants.WHERE
                        + "([company] = 'Acadia' "
                        + JDBCConstants.OR
                        + "[companyAbbreviation] = 'AIC') "
                        + JDBCConstants.AND
                        + "[environment] = 'INT'")) {
          while (resultSet.next()) {
            for (int fieldIndex = 0; fieldIndex < fields.size(); fieldIndex++) {
              final String field = fields.get(fieldIndex);
              if (fieldIndex < (fields.size() - 1)) {
                log.debug(field + ":[" + resultSet.getString(field) + "]");
              } else {
                log.info(field + ":[" + resultSet.getString(field) + "]");
              }
            }
          }
        }
      }
    } catch (final Exception oException) {
      log.info(oException.getMessage());
      oException.printStackTrace();
    }
  }

  public static Connection connectDb(String database) {
    log.info("Connecting to [" + database + "]");
    try {
      // String dir = System.getProperty("user.dir");
      Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
      return DriverManager.getConnection(
          "jdbc:odbc:Driver={Microsoft Excel Driver (*"
              + IExtension.XLS
              + ", *"
              + IExtension.XLSX
              + ", *"
              + IExtension.XLSM
              + ", *"
              + IExtension.XLSB
              + ")};Dbq="
              + database);
    } catch (ClassNotFoundException | SQLException oException) {
      JOptionPane.showMessageDialog(null, "Problem connecting to database [" + database + "]");
      log.info(oException.getMessage());
      oException.printStackTrace();
      return null;
    }
  }

  public boolean execute(Connection oConnection, String sSQL) {
    try (Statement statement = oConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sSQL)) {
      log.info(resultSet.toString());
    } catch (final Exception oException) {
      log.info(oException.getMessage());
      oException.printStackTrace();
      return false;
    } finally {
      try {
        if (oConnection != null) {
          oConnection.close();
        }
      } catch (final SQLException e) {
        log.info(e.getMessage());
      }
    }
    return true;
  }

  public int executeUpdate(Connection oConnection, String sSQL) {
    int iReturn = 0;
    try (Statement oStatement = oConnection.createStatement()) {
      iReturn = oStatement.executeUpdate(sSQL);
      // if (iReturn == 1){
      // log.info(String.valueOf(iReturn) + " record updated");
      // } else {
      // log.info(String.valueOf(iReturn) + " records updated");
      // }
      // oConnection.commit();
      return iReturn;
    } catch (final Exception oException) {
      log.info(oException.getMessage());
      oException.printStackTrace();
      log.info(sSQL);
      return 0;
    }
  }
}
