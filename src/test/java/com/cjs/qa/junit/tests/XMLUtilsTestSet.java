package com.cjs.qa.junit.tests;

import org.junit.Test;

import com.cjs.qa.core.Environment;
import com.cjs.qa.core.QAException;
import com.cjs.qa.utilities.Constants;
import com.cjs.qa.utilities.FSO;
import com.cjs.qa.utilities.IExtension;
import com.cjs.qa.utilities.XML;

public class XMLUtilsTestSet
{

	@Test
	public void assertXMLEqualFail() throws QAException
	{
		XML.xmlAssertEqual(getXML1(), getXML2());
	}

	@Test
	public void assertXMLEqualPass() throws QAException
	{
		XML.xmlAssertEqual(getXML1(), getXML1());
	}

	@Test
	public void assertXMLEqualsFail() throws QAException
	{
		try
		{
			XML.xmlAssertEquals(getXML1(), getXML2());
		} catch (final Exception e)
		{
			throw new QAException("Error trying to assertXMLEquals", e);
		}
	}

	@Test
	public void assertXMLEqualsPass() throws QAException
	{
		try
		{
			XML.xmlAssertEquals(getXML1(), getXML1());
		} catch (final Exception e)
		{
			throw new QAException("Error trying to assertXMLEquals", e);
		}
	}

	@Test
	public void formatPretty() throws QAException
	{
		Environment.sysOut(XML.formatPretty(getXML1()));
		Environment.sysOut(XML.formatPretty(getXML2()));
	}

	@Test
	public void fromStringToCanonicalXML() throws QAException
	{
		Environment.sysOut(XML.fromStringToCanonical(getXML1()));
		Environment.sysOut(XML.fromStringToCanonical(getXML2()));
	}

	private String getXML1()
	{
		return FSO.fileReadAll(Constants.PATH_FILES_XML + "xml1" + IExtension.XML);
	}

	private String getXML2()
	{
		return FSO.fileReadAll(Constants.PATH_FILES_XML + "xml2" + IExtension.XML);
	}
}