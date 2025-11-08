package com.cjs.qa.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import com.cjs.qa.core.Environment;
import com.cjs.qa.jdbc.JDBCConstants;

public class ODBCMSAccess_64
{

	public void main(String[] args)
	{
		Connection oConnection = ConnectDb(
				"C:" + Constants.DELIMETER_PATH + "Temp" + Constants.DELIMETER_PATH + "qatoolsweb" + IExtension.MDB);
		try
		{
			final Statement oStatement = oConnection.createStatement();
			final ResultSet oResultSet = oStatement.executeQuery(JDBCConstants.SELECT_ALL_FROM + "[tblSubmissionLog]");
			while (oResultSet.next())
			{
				// Environment.sysOut(oResultSet.getString(1));
				Environment.sysOut("SubmissionID:" + oResultSet.getString("SubmissionID"));
			}
			oStatement.close();
			oConnection.close();
			oConnection = null;
		} catch (final Exception oException)
		{
			Environment.sysOut(oException.getMessage());
			oException.printStackTrace();
		}
	}

	public Connection ConnectDb(String database)
	{
		Environment.sysOut("Connecting to [" + database + "]");
		try
		{
			// String dir = System.getProperty("user.dir");
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			final Connection oConnection = DriverManager.getConnection("jdbc:odbc:Driver={Microsoft Access Driver (*"
					+ IExtension.MDB + ")};DBQ=" + database + Constants.DELIMETER_LIST, "", "");
			return oConnection;
		} catch (ClassNotFoundException | SQLException oException)
		{
			JOptionPane.showMessageDialog(null, "Problem connecting to database [" + database + "]");
			Environment.sysOut(oException.getMessage());
			oException.printStackTrace();
			return null;
		}
	}
}