package com.cjs.qa.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;

import javax.swing.JOptionPane;

import com.cjs.qa.jdbc.JDBCConstants;

public class ODBCExcel
{
	public static void main(String[] args)
	{
		final String sDatabase = System.getProperty("user.dir") + Constants.DELIMETER_PATH + "target"
				+ Constants.DELIMETER_PATH + "Data" + Constants.DELIMETER_PATH + "urls" + IExtension.XLS;
		final List<String> lFields = Arrays.asList("company", "abbreviation", "environment", "url");
		Connection oConnection = ConnectDb(sDatabase);
		try
		{
			final Statement oStatement = oConnection.createStatement();
			final ResultSet oResultSet = oStatement.executeQuery(JDBCConstants.SELECT_ALL_FROM + "urls "
					+ JDBCConstants.WHERE + "([company] = 'Acadia' " + JDBCConstants.OR
					+ "[companyAbbreviation] = 'AIC') " + JDBCConstants.AND + "[environment] = 'INT'");
			while (oResultSet.next())
			{
				for (int iField = 0; iField < lFields.size(); iField++)
				{
					final String sField = lFields.get(iField);
					if (iField < (lFields.size() - 1))
					{
						System.out.print(sField + ":[" + oResultSet.getString(sField) + "]");
					} else
					{
						System.out.println(sField + ":[" + oResultSet.getString(sField) + "]");
					}
				}
			}
			oConnection = null;
		} catch (final Exception oException)
		{
			System.out.println(oException.getMessage());
			oException.printStackTrace();
		}
	}

	public static Connection ConnectDb(String database)
	{
		System.out.println("Connecting to [" + database + "]");
		try
		{
			// String dir = System.getProperty("user.dir");
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			final Connection oConnection = DriverManager.getConnection(
					"jdbc:odbc:Driver={Microsoft Excel Driver (*" + IExtension.XLS + ", *" + IExtension.XLSX + ", *"
							+ IExtension.XLSM + ", *" + IExtension.XLSB + ")};Dbq=" + database);
			return oConnection;
		} catch (ClassNotFoundException | SQLException oException)
		{
			JOptionPane.showMessageDialog(null, "Problem connecting to database [" + database + "]");
			System.out.println(oException.getMessage());
			oException.printStackTrace();
			return null;
		}
	}

	public boolean execute(Connection oConnection, String sSQL)
	{
		try
		{
			final Statement oStatement = oConnection.createStatement();
			final ResultSet oResultSet = oStatement.executeQuery(sSQL);
			System.out.println(oResultSet.toString());
			oStatement.close();
			oConnection.close();
			oConnection = null;
		} catch (final Exception oException)
		{
			System.out.println(oException.getMessage());
			oException.printStackTrace();
			return false;
		}
		return true;
	}

	public int executeUpdate(Connection oConnection, String sSQL)
	{
		int iReturn = 0;
		try
		{
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
		} catch (final Exception oException)
		{
			System.out.println(oException.getMessage());
			oException.printStackTrace();
			System.out.println(sSQL);
			return 0;
		}
	}
}