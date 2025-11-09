package com.cjs.qa.jdbc;

import com.cjs.qa.vivit.VivitData;
import org.junit.Test;

public class JDBCTest {

  @Test
  public void getDatabaseTablesViewsFields() throws Throwable {
    JDBC.exportTableViewSchemaSQLite("TablesViews", VivitData.DATABASE_DEFINITION, true);
  }
}
