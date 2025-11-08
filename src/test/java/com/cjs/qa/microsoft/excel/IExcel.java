package com.cjs.qa.microsoft.excel;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.DataFormatter;

import com.cjs.qa.core.Environment;
import com.cjs.qa.core.QAException;
import com.cjs.qa.microsoft.excel.xls.XLS;
import com.cjs.qa.microsoft.excel.xlsx.XLSX;
import com.cjs.qa.utilities.IExtension;

public interface IExcel
{
	public static final String			FORMAT_NAME_BOLD			= "Bold";
	public static final String			FORMAT_NAME_HEADING			= "Heading";
	public static final String			FORMAT_NAME_HYPERLINK		= "Hyperlink";
	public static final String			FORMAT_NAME_NORMAL			= "Normal";
	public static final String			FORMAT_NAME_PASS			= "Pass";
	public static final String			FORMAT_NAME_FAIL			= "Fail";
	public static final String			FORMAT_NAME_SECTION			= "Section";
	public static final String			FORMAT_NAME_STATUS			= "Status";
	//
	public static final String			FORMAT_NAME_NUMBER			= "Number";
	public static final String			FORMAT_NAME_NUMBERPERCENT	= "NumberPercent";
	public static final String			FORMAT_NAME_PERCENT			= "Percent";
	//
	public static final String			PREFIX_CREATE_CELL_FONT		= "createCellFont";
	public static final String			PREFIX_CREATE_CELL_STYLE	= "createCellStyle";
	// EExcelConstants.CELL_STYLES_XLS.getValue()
	public static final int				CELL_STYLE_DEFINED_COUNT	= 11;
	// 255
	public static final int				MAX_COLUMNS_XLS				= EExcelConstants.MAX_COLUMNS_XLS.getValue();
	// 16383
	public static final int				MAX_COLUMNS_XLSX			= EExcelConstants.MAX_COLUMNS_XLSX.getValue();
	// 65536
	public static final int				MAX_ROWS_XLS				= EExcelConstants.MAX_ROWS_XLS.getValue();
	// 1048576
	public static final int				MAX_ROWS_XLSX				= EExcelConstants.MAX_ROWS_XLSX.getValue();
	public static final List<String>	CELL_STYLE_LIST				= Arrays.asList("Bold", "Heading", "Hyperlink",
			"Normal", "Pass", "Fail", "Section", "Status");
	public static final List<String>	COLORS_ALLOWED				= Arrays.asList("aqua", "black", "blue",
			"blue grey", "bright green", "brown", "coral", "cornflower blue", "dark blue", "dark green", "dark red",
			"dark teal", "dark yellow", "gold", "green", "grey", "grey_25", "grey_40", "grey_50", "grey_80", "indigo",
			"lavender", "lemon chiffon", "light blue", "light cornflower", "light green", "light orange",
			"light turquoise", "light yellow", "lime", "maroon", "olive green", "orange", "orchid", "pale blue", "pink",
			"plum", "red", "rose", "royal blue", "sea green", "sky blue", "tan", "teal", "turquoise", "violet", "white",
			"yellow");
	public static final String			SHEET_COUNTS				= "Counts";
	public static final String			SHEET_DEFAULT				= "Sheet1";
	public static final String			SHEET_SUMMARY				= "Summary";
	public static final int				DEFAULT_INDEX				= 0;
	public static final int				MAX_CELL_HEIGHT				= 409;
	public static final int				MAX_CELL_WIDTH				= 255;
	public static final int				MAX_SHEET_NAME_LENGTH		= 31;
	public static final DataFormatter	dataFormatter				= new DataFormatter();

	public static String getFileType(String fileName)
	{
		Environment.sysOut("fileCheck:[" + fileName + "]");
		final String fileNameExtension = "." + FilenameUtils.getExtension(fileName);
		if (fileNameExtension.equalsIgnoreCase(IExtension.XLS))
		{
			return IExtension.XLS;
		}
		if (fileNameExtension.equalsIgnoreCase(IExtension.XLSX))
		{
			return IExtension.XLSX;
		}
		return null;
	}

	public static short getFontColorIndex(String color)
	{
		switch (color.toLowerCase())
		{
			case "aqua":
				return HSSFColor.HSSFColorPredefined.AQUA.getIndex();
			case "black":
				return HSSFColor.HSSFColorPredefined.BLACK.getIndex();
			case "blue":
				return HSSFColor.HSSFColorPredefined.BLUE.getIndex();
			case "blue grey":
				return HSSFColor.HSSFColorPredefined.BLUE_GREY.getIndex();
			case "bright green":
				return HSSFColor.HSSFColorPredefined.BRIGHT_GREEN.getIndex();
			case "brown":
				return HSSFColor.HSSFColorPredefined.BROWN.getIndex();
			case "coral":
				return HSSFColor.HSSFColorPredefined.CORAL.getIndex();
			case "cornflower blue":
				return HSSFColor.HSSFColorPredefined.CORNFLOWER_BLUE.getIndex();
			case "dark blue":
				return HSSFColor.HSSFColorPredefined.DARK_BLUE.getIndex();
			case "dark green":
				return HSSFColor.HSSFColorPredefined.DARK_GREEN.getIndex();
			case "dark red":
				return HSSFColor.HSSFColorPredefined.DARK_RED.getIndex();
			case "dark teal":
				return HSSFColor.HSSFColorPredefined.DARK_TEAL.getIndex();
			case "dark yellow":
				return HSSFColor.HSSFColorPredefined.DARK_YELLOW.getIndex();
			case "gold":
				return HSSFColor.HSSFColorPredefined.GOLD.getIndex();
			case "green":
				return HSSFColor.HSSFColorPredefined.GREEN.getIndex();
			case "grey":
			case "grey_25":
				return HSSFColor.HSSFColorPredefined.GREY_25_PERCENT.getIndex();
			case "grey_40":
				return HSSFColor.HSSFColorPredefined.GREY_40_PERCENT.getIndex();
			case "grey_50":
				return HSSFColor.HSSFColorPredefined.GREY_50_PERCENT.getIndex();
			case "grey_80":
				return HSSFColor.HSSFColorPredefined.GREY_80_PERCENT.getIndex();
			case "indigo":
				return HSSFColor.HSSFColorPredefined.INDIGO.getIndex();
			case "lavender":
				return HSSFColor.HSSFColorPredefined.LAVENDER.getIndex();
			case "lemon chiffon":
				return HSSFColor.HSSFColorPredefined.LEMON_CHIFFON.getIndex();
			case "light blue":
				return HSSFColor.HSSFColorPredefined.LIGHT_BLUE.getIndex();
			case "light cornflower":
				return HSSFColor.HSSFColorPredefined.LIGHT_CORNFLOWER_BLUE.getIndex();
			case "light green":
				return HSSFColor.HSSFColorPredefined.LIGHT_GREEN.getIndex();
			case "light orange":
				return HSSFColor.HSSFColorPredefined.LIGHT_ORANGE.getIndex();
			case "light turquoise":
				return HSSFColor.HSSFColorPredefined.LIGHT_TURQUOISE.getIndex();
			case "light yellow":
				return HSSFColor.HSSFColorPredefined.LIGHT_YELLOW.getIndex();
			case "lime":
				return HSSFColor.HSSFColorPredefined.LIME.getIndex();
			case "maroon":
				return HSSFColor.HSSFColorPredefined.MAROON.getIndex();
			case "olive green":
				return HSSFColor.HSSFColorPredefined.OLIVE_GREEN.getIndex();
			case "orange":
				return HSSFColor.HSSFColorPredefined.ORANGE.getIndex();
			case "orchid":
				return HSSFColor.HSSFColorPredefined.ORCHID.getIndex();
			case "pale blue":
				return HSSFColor.HSSFColorPredefined.PALE_BLUE.getIndex();
			case "pink":
				return HSSFColor.HSSFColorPredefined.PINK.getIndex();
			case "plum":
				return HSSFColor.HSSFColorPredefined.PLUM.getIndex();
			case "red":
				return HSSFColor.HSSFColorPredefined.RED.getIndex();
			case "rose":
				return HSSFColor.HSSFColorPredefined.ROSE.getIndex();
			case "royal blue":
				return HSSFColor.HSSFColorPredefined.ROYAL_BLUE.getIndex();
			case "sea green":
				return HSSFColor.HSSFColorPredefined.SEA_GREEN.getIndex();
			case "sky blue":
				return HSSFColor.HSSFColorPredefined.SKY_BLUE.getIndex();
			case "tan":
				return HSSFColor.HSSFColorPredefined.TAN.getIndex();
			case "teal":
				return HSSFColor.HSSFColorPredefined.TEAL.getIndex();
			case "turquoise":
				return HSSFColor.HSSFColorPredefined.TURQUOISE.getIndex();
			case "violet":
				return HSSFColor.HSSFColorPredefined.VIOLET.getIndex();
			case "white":
				return HSSFColor.HSSFColorPredefined.WHITE.getIndex();
			case "yellow":
				return HSSFColor.HSSFColorPredefined.YELLOW.getIndex();
		}
		return -1;
	}

	public static HyperlinkType getHyperlinkType(String hyperlinkType)
	{
		switch (hyperlinkType.toLowerCase())
		{
			case "document":
				return HyperlinkType.DOCUMENT;
			case "email":
				return HyperlinkType.EMAIL;
			case "file":
				return HyperlinkType.FILE;
			case "url":
				return HyperlinkType.URL;
		}
		return null;
	}

	public static XLS updateSummarySheetXLS(String filePathName, String sheetName, String sheetLinkName)
			throws IOException, QAException
	{
		final XLS excel = new XLS(filePathName, SHEET_SUMMARY);
		final String hyperlinkSheetSummary = "'" + SHEET_SUMMARY + "'!A1";
		final String hyperlinkSheetData = "'" + sheetName + "'!A1";
		if (!excel.sheetExists(SHEET_SUMMARY))
		{
			excel.createSheet(SHEET_SUMMARY, "");
		}
		final int column = 0;
		int row = (excel.getRowCount(SHEET_SUMMARY));
		if (excel.getSheetCount() == 1)
		{
			row = (excel.getRowCount(SHEET_SUMMARY));
			excel.writeCell(SHEET_SUMMARY, column, row, SHEET_SUMMARY);
			excel.setFormatHeading(SHEET_SUMMARY, column, row);
		}
		row = excel.getSheetCount();
		// excel.writeCell(SHEET_SUMMARY, 0, row, sheetLinkName);
		excel.addLink(SHEET_SUMMARY, 0, row, "DOCUMENT", sheetLinkName, hyperlinkSheetData);
		excel.autoSizeColumns(SHEET_SUMMARY);
		excel.createSheet(sheetName);
		excel.addLink(sheetName, 0, 0, "DOCUMENT", SHEET_SUMMARY, hyperlinkSheetSummary);
		return excel;
	}

	public static XLSX updateSummarySheetXLSX(String filePathName, String sheetName, String sheetLinkName)
			throws IOException, QAException
	{
		final XLSX excel = new XLSX(filePathName, SHEET_SUMMARY);
		final String hyperlinkSheetSummary = "'" + SHEET_SUMMARY + "'!A1";
		final String hyperlinkSheetData = "'" + sheetName + "'!A1";
		if (!excel.sheetExists(SHEET_SUMMARY))
		{
			excel.createSheet(SHEET_SUMMARY, "");
		}
		final int column = 0;
		int row = (excel.getRowCount(SHEET_SUMMARY));
		if (excel.getSheetCount() == 1)
		{
			row = (excel.getRowCount(SHEET_SUMMARY));
			excel.writeCell(SHEET_SUMMARY, column, row, SHEET_SUMMARY);
			excel.setFormatHeading(SHEET_SUMMARY, column, row);
		}
		row = excel.getSheetCount();
		// excel.writeCell(SHEET_SUMMARY, 0, row, sheetLinkName);
		excel.addLink(SHEET_SUMMARY, 0, row, "DOCUMENT", sheetLinkName, hyperlinkSheetData);
		excel.autoSizeColumns(SHEET_SUMMARY);
		excel.createSheet(sheetName);
		excel.addLink(sheetName, 0, 0, "DOCUMENT", SHEET_SUMMARY, hyperlinkSheetSummary);
		return excel;
	}
}