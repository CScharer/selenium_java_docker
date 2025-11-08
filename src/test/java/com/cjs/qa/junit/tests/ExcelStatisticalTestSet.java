package com.cjs.qa.junit.tests;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;

import org.junit.Test;

import com.cjs.qa.core.Environment;
import com.cjs.qa.core.QAException;
import com.cjs.qa.microsoft.excel.xls.XLS;
import com.cjs.qa.utilities.Constants;
import com.cjs.qa.utilities.Convert;
import com.cjs.qa.utilities.DateHelpers;
import com.cjs.qa.utilities.FSO;
import com.cjs.qa.utilities.IExtension;

public class ExcelStatisticalTestSet
{
	// public static final String TIMINGS_PATH = Constants.DELIMETER_PATH +
	// Constants.DELIMETER_PATH + "wrbts" + Constants.DELIMETER_PATH + "shared"
	// + Constants.DELIMETER_PATH + "bts QA" + Constants.DELIMETER_PATH + "QA
	// Tools" + Constants.DELIMETER_PATH + "Timings" + Constants.DELIMETER_PATH
	public static final String			PATH_TIMINGS		= "C:" + Constants.DELIMETER_PATH + "Temp"
			+ Constants.DELIMETER_PATH + "Excel_Timings" + Constants.DELIMETER_PATH;
	protected static final List<String>	PATH_LIST			= Arrays.asList("VALA", "VALA-LP");
	private ExcelTiming					excelTimingControl	= new ExcelTiming();
	private ExcelTiming					excelTimingTest		= new ExcelTiming();

	@Test
	public void getStatisticalTimings() throws IOException, QAException
	{
		for (final String path : PATH_LIST)
		{
			final String timingPath = PATH_TIMINGS + path + Constants.DELIMETER_PATH;
			switch (path)
			{
				case "VALA":
					getData(timingPath, excelTimingControl);
					break;
				case "VALA-LP":
					getData(timingPath, excelTimingTest);
					break;
				default:
					break;
			}
		}
		createStatisticalReport();
	}

	private void getData(String timingPath, ExcelTiming excelTiming) throws QAException, IOException
	{
		final String SHEET_NAME = "Sheet1";
		excelTiming.setFileList(FSO.filesList(timingPath, IExtension.XLS));
		for (final String filePathName : excelTiming.getFileList())
		{
			Environment.sysOut("filePathName:[" + filePathName + "]");
			final XLS excel = new XLS(filePathName, SHEET_NAME);
			final int rows = excel.getRowCount(SHEET_NAME);
			for (int row = 1; row < rows; row++)
			{
				String methodName = excel.readCell(SHEET_NAME, 0, row);
				methodName = methodName.toLowerCase();
				final double timing = Double.parseDouble(excel.readCell(SHEET_NAME, 1, row));
				Environment.sysOut("methodName:[" + methodName + "], timing:[" + timing + "]");
				if (excelTiming.getMethodCallsMap().get(methodName) == null)
				{
					excelTiming.getMethodCallsMap().put(methodName, 1);
					excelTiming.getMethodTimingMap().put(methodName, timing);
				} else
				{
					excelTiming.getMethodCallsMap().put(methodName,
							excelTiming.getMethodCallsMap().get(methodName) + 1);
					excelTiming.getMethodTimingMap().put(methodName,
							excelTiming.getMethodTimingMap().get(methodName) + timing);
				}
			}
			excel.close();
		}
	}

	private void createStatisticalReport() throws QAException, IOException
	{
		final String SHEET_NAME = "Statistical Timings";
		final String filePathName = PATH_TIMINGS + SHEET_NAME + "_"
				+ DateHelpers.getCurrentDateTime(DateHelpers.FORMAT_DATE_TIME_STAMP) + IExtension.XLS;
		final XLS excel = new XLS(filePathName, SHEET_NAME);
		int row = 1;
		// excel.writeCell(SHEET_NAME, 0, 0, "Method")
		excel.writeCell(SHEET_NAME, 1, 0, PATH_LIST.get(0));
		excel.writeCell(SHEET_NAME, 2, 0, "Files");
		excel.writeCell(SHEET_NAME, 3, 0, getExcelTimingControl().getFileList().size());
		excel.writeCell(SHEET_NAME, 4, 0, PATH_LIST.get(1));
		excel.writeCell(SHEET_NAME, 5, 0, "Files");
		excel.writeCell(SHEET_NAME, 6, 0, getExcelTimingTest().getFileList().size());
		// excel.writeCell(SHEET_NAME, 7, 0, "Difference")
		excel.writeCell(SHEET_NAME, 0, row, "Method");
		excel.writeCell(SHEET_NAME, 1, row, "Calls");
		excel.writeCell(SHEET_NAME, 2, row, "Time");
		excel.writeCell(SHEET_NAME, 3, row, "Average");
		excel.writeCell(SHEET_NAME, 4, row, "Calls");
		excel.writeCell(SHEET_NAME, 5, row, "Time");
		excel.writeCell(SHEET_NAME, 6, row, "Average");
		excel.writeCell(SHEET_NAME, 7, row, "Difference");
		for (int column = 0; column < 8; column++)
		{
			excel.setFormatHeading(SHEET_NAME, column, 0);
			excel.setFormatHeading(SHEET_NAME, column, row);
		}
		for (final Entry entry : getExcelTimingControl().getMethodCallsMap().entrySet())
		{
			row++;
			final String methodNameControl = (String) entry.getKey();
			final Integer methodCallsControl = (Integer) entry.getValue();
			final Double methodTimeControl = getExcelTimingControl().getMethodTimingMap().get(methodNameControl);
			final Double methodTimeControlAverage = (methodTimeControl / methodCallsControl);
			excel.writeCell(SHEET_NAME, 0, row, methodNameControl);
			excel.writeCell(SHEET_NAME, 1, row, methodCallsControl);
			excel.writeCell(SHEET_NAME, 2, row, methodTimeControl);
			excel.writeCell(SHEET_NAME, 3, row, methodTimeControlAverage);
			Environment.sysOut("methodNameControl:[" + methodNameControl + "], methodCallsControl:["
					+ methodCallsControl + "], methodTimeControl:[" + methodTimeControl
					+ "], methodTimeControlAverage:[" + methodTimeControlAverage + "]");
			final Integer methodCallsTest = getExcelTimingTest().getMethodCallsMap().get(methodNameControl);
			final Double methodTimeTest = getExcelTimingTest().getMethodTimingMap().get(methodNameControl);
			final Double methodTimeTestAverage = (methodTimeTest / methodCallsTest);
			Environment.sysOut("methodNameTest:[" + methodNameControl + "], methodCallsTest:[" + methodCallsTest
					+ "], methodTimeTest:[" + methodTimeTest + "], methodTimeTestAverage:[" + methodTimeTestAverage
					+ "]");
			excel.writeCell(SHEET_NAME, 4, row, methodCallsTest);
			excel.writeCell(SHEET_NAME, 5, row, methodTimeTest);
			excel.writeCell(SHEET_NAME, 6, row, methodTimeTestAverage);
			excel.writeCell(SHEET_NAME, 7, row, (methodTimeTestAverage - methodTimeControlAverage));
			// getExcelTimingControl().getMethodCallsMap().remove(methodNameControl)
			// getExcelTimingControl().getMethodTimingMap().remove(methodNameControl)
			// getExcelTimingTest().getMethodCallsMap().remove(methodNameControl)
			// getExcelTimingTest().getMethodTimingMap().remove(methodNameControl)
		}
		row++;
		excel.writeCell(SHEET_NAME, 0, row, "Totals");
		for (int column = 0; column < 8; column++)
		{
			if (column >= 1)
			{
				excel.writeCellFormula(SHEET_NAME, column, row, "Sum(" + Convert.fromNumberToLetterExcel(column) + "3:"
						+ Convert.fromNumberToLetterExcel(column) + row + ")");
			}
			excel.setFormatHeading(SHEET_NAME, column, row);
		}
		excel.autoSizeColumns(SHEET_NAME);
		excel.save();
		excel.close();
	}

	public ExcelTiming getExcelTimingControl()
	{
		return excelTimingControl;
	}

	public void setExcelTimingControl(ExcelTiming excelTimingControl)
	{
		this.excelTimingControl = excelTimingControl;
	}

	public ExcelTiming getExcelTimingTest()
	{
		return excelTimingTest;
	}

	public void setExcelTimingTest(ExcelTiming excelTimingTest)
	{
		this.excelTimingTest = excelTimingTest;
	}
}