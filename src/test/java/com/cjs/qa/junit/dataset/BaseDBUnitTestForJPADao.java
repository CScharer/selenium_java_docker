package com.cjs.qa.junit.dataset;

import java.io.FileReader;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.h2.Driver;
import org.h2.tools.RunScript;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import com.cjs.qa.core.Environment;
import com.cjs.qa.utilities.Constants;
import com.cjs.qa.utilities.IExtension;
import com.cjs.qa.utilities.JavaHelpers;

public class BaseDBUnitTestForJPADao
{
	protected static EntityManagerFactory	entityManagerFactory	= null;
	protected static IDatabaseConnection	iDatabaseConnection		= null;

	protected EntityManager					entityManager			= null;

	@BeforeClass
	public static void setupTestClass() throws Exception
	{
		Environment.sysOut("Setup-Class Method:[" + JavaHelpers.getCurrentClassName() + "]");
		final Properties properties = new Properties();
		properties.put("user", DBInfo.USER);
		properties.put("password", DBInfo.PASSWORD);

		final Connection connection = Driver.load().connect(DBInfo.URL, properties);
		iDatabaseConnection = new DatabaseConnection(connection);
		String dbLocation = Constants.PATH_PROJECT + "b2csite.dll" + IExtension.SQL;
		dbLocation = "src/test/resources/TableDef/b2csite.dll" + IExtension.SQL;
		RunScript.execute(iDatabaseConnection.getConnection(), new FileReader(dbLocation));

		final Map<Object, Object> mapProperties = new HashMap<>();
		mapProperties.put("javax.persistence.jdbc.url", DBInfo.URL);
		// mapProperties.put("hibernate.hbm2dll.auto", "create-drop");
		entityManagerFactory = Persistence.createEntityManagerFactory("orderPersistenceUnit", mapProperties);
	}

	@AfterClass
	public static void teardownTestClass()
	{
		Environment.sysOut("TearDown-Class Method:[" + JavaHelpers.getCurrentClassName() + "]");
		try
		{
			if (entityManagerFactory != null)
			{
				if (entityManagerFactory.isOpen())
				{
					entityManagerFactory.close();
				}
				entityManagerFactory = null;
			}
		} catch (final Exception e)
		{
		}
	}

	@Before
	public void baseSetup() throws Exception
	{
		entityManager = entityManagerFactory.createEntityManager();
	}

	@After
	public void baseTeardown() throws Exception
	{
		if (entityManager != null)
		{
			if (entityManager.isOpen())
			{
				entityManager.close();
			}
			entityManager = null;
		}
	}
}