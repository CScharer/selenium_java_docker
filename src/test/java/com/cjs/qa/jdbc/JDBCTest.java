package com.cjs.qa.jdbc;

import org.junit.Test;

import com.cjs.qa.vivit.VivitData;

public class JDBCTest
{

	@Test
	public void getDatabaseTablesViewsFields() throws Throwable
	{
		JDBC.exportTableViewSchemaSQLite("TablesViews", VivitData.DATABASE_DEFINITION, true);
	}
}