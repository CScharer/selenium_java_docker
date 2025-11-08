package com.cjs.qa.utilities;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import org.junit.Test;

import com.cjs.qa.core.Environment;

public class CommandLine
{
	public static final String	TASKKILL	= "taskkill /f /im ";
	public static final String	TASKLIST	= "tasklist";

	@Test
	public void testCommandLine() throws IOException
	{
		Environment.sysOut("Chrome is running:[" + isProcessRunning("chrome.exe") + "]");
	}

	@Test
	public void testGetJpsProcesses() throws Throwable
	{
		final String SUREFIRE_BOOTER = "surefirebooter";
		List<String> jpsProcessList = new ArrayList<>();
		Map<String, String> jpsProcessMap = new HashMap<>();
		jpsProcessList = getJpsProcessesList(SUREFIRE_BOOTER);
		Environment.sysOut("jpsProcessList:" + jpsProcessList.toString());
		jpsProcessList = getJpsProcessesList(null);
		Environment.sysOut("jpsProcessList:" + jpsProcessList.toString());
		jpsProcessMap = getJpsProcessesMap(null);
		Environment.sysOut("jpsProcessMap:" + jpsProcessMap.toString());
		jpsProcessMap = getJpsProcessesMap(SUREFIRE_BOOTER);
		Environment.sysOut("jpsProcessMap:" + jpsProcessMap.toString());
		for (Entry entry : jpsProcessMap.entrySet())
		{
			String pid = (String) entry.getKey();
			String processName = (String) entry.getValue();
			String imageName = null;
			String command = TASKLIST + " /fo:csv /nh /fi \"pid eq " + pid + "\"";
			Processes processes = new Processes(command);
			if (!processes.getProcessList().isEmpty())
			{
				imageName = processes.getProcessList().get(0).getImageName();
			}
			Environment.sysOut("pid:[" + pid + "], processName:[" + processName + "], imageName:[" + imageName + "]");
		}
	}

	public Map<String, String> getJpsProcessesMap(String jpsProcessName) throws Throwable
	{
		Map<String, String> jpsProcessMap = new HashMap<>();
		List<String> jpsProcessList = getJpsProcessesList(jpsProcessName);
		for (String jpsProcess : jpsProcessList)
		{
			String[] jpsProcessArray = jpsProcess.split(" ");
			String pid = jpsProcessArray[0];
			String processName = null;
			if (jpsProcessArray.length > 1)
			{
				processName = jpsProcessArray[1];
			}
			jpsProcessMap.put(pid, processName);
		}
		return jpsProcessMap;
	}

	public List<String> getJpsProcessesList(String jpsProcessName) throws Throwable
	{
		List<String> jpsProcessListNew = new ArrayList<>();
		String command = "cmd /C jps";
		final Map<String, String> mapProcess = runProcess(command, true);
		String status = mapProcess.get("status");
		if (status.equals("0"))
		{
			String processes = mapProcess.get("lines").toString();
			List<String> jpsProcessList = Arrays.asList(processes.split(Constants.NEWLINE));
			for (String jpsProcess : jpsProcessList)
			{
				if (JavaHelpers.hasValue(jpsProcessName))
				{
					if (jpsProcess.indexOf(jpsProcessName) != -1)
					{
						jpsProcessListNew.add(jpsProcess);
					}
				} else
				{
					jpsProcessListNew.add(jpsProcess);
				}
			}
		}
		return jpsProcessListNew;
	}

	@Test
	public void testProcesses() throws Throwable
	{
		Processes processes = null;
		String command = "";
		command = TASKLIST + " /fo:csv /nh /fi \"imagename eq chrome.exe\"";
		Environment.sysOut("command:[" + command + "]");
		processes = new Processes(command);
		Environment.sysOut(processes.toString());
		command = TASKLIST + " /fo:csv /nh /fi \"windowtitle eq Selenium-Grid2-Hub_CSCHARER-LAPTOP*\"";
		Environment.sysOut("command:[" + command + "]");
		processes = new Processes(command);
		Environment.sysOut(processes.toString());
		command = TASKLIST + " /fo:csv /nh /fi \"windowtitle eq Selenium-Grid2-Node_CSCHARER-LAPTOP*\"";
		Environment.sysOut("command:[" + command + "]");
		processes = new Processes(command);
		Environment.sysOut(processes.toString());
		command = TASKLIST + " /fo:csv /nh /fi \"windowtitle eq Selenium-Grid2-*\"";
		Environment.sysOut("command:[" + command + "]");
		processes = new Processes(command);
		Environment.sysOut(processes.toString());
		command = TASKLIST + " /fo:csv /nh /fi \"windowtitle eq Selenium-Grid3-*\"";
		Environment.sysOut("command:[" + command + "]");
		processes = new Processes(command);
		Environment.sysOut(processes.toString());
	}

	public static String executeCommand(String command) throws Throwable
	{
		final StringBuilder stringBuilder = new StringBuilder();
		try
		{
			Process process = Runtime.getRuntime().exec(command);
			final InputStream inputStream = process.getInputStream();
			stringBuilder.append(printLines(inputStream));
		} catch (final IOException e)
		{
			e.printStackTrace();
		}
		return stringBuilder.toString();
	}

	private static String getInputStream(InputStream inputStream)
	{
		final Scanner scanner = new Scanner(inputStream, "UTF-8").useDelimiter(Constants.BACKSLASH + "A");
		final String string = scanner.hasNext() ? scanner.next() : "";
		scanner.close();
		return string;
	}

	public static boolean isProcessRunning(String processName) throws IOException
	{
		final ProcessBuilder processBuilder = new ProcessBuilder(TASKLIST + ".exe");
		final Process process = processBuilder.start();
		final String tasksList = getInputStream(process.getInputStream()).toLowerCase();
		return tasksList.contains(processName.toLowerCase());
	}

	public static boolean IsProcessRunning(String processRunning)
	{
		Process process;
		try
		{
			process = Runtime.getRuntime().exec(TASKLIST);
			final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line;
			while ((line = bufferedReader.readLine()) != null)
			{
				line = line.toLowerCase();
				if (line.contains(processRunning.toLowerCase()))
				{
					if (Environment.isLogAll())
					{
						Environment.sysOut(line);
					}
					return true;
				}
			}
		} catch (final Exception e)
		{
			e.printStackTrace();
			Environment.sysOut(e);
		}
		return false;
	}

	public static void killProcess(String processRunning) throws Exception
	{
		final String command = TASKKILL + processRunning;
		if (Environment.isLogAll())
		{
			Environment.sysOut("command:[" + command + "]");
		}
		Runtime.getRuntime().exec(command);
	}

	private static String printLines(InputStream inputStream) throws Exception
	{
		final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
		final StringBuilder stringBuilder = new StringBuilder();
		String line;
		while ((line = bufferedReader.readLine()) != null)
		{
			if (!stringBuilder.toString().equals(""))
			{
				stringBuilder.append(Constants.NEWLINE);
			}
			stringBuilder.append(line);
		}
		return stringBuilder.toString();
	}

	public static void runApplication(String applicationFilePath) throws IOException
	{
		final File application = new File(applicationFilePath);
		final String applicationName = application.getName();
		if (!isProcessRunning(applicationName))
		{
			Desktop.getDesktop().open(application);
		}
	}

	public static int runProcess(String command) throws Exception
	{
		final Process process = Runtime.getRuntime().exec(command);
		printLines(process.getInputStream());
		process.waitFor();
		return process.exitValue();
	}

	public static Map<String, String> runProcess(String command, boolean wait) throws Exception
	{
		Process process = Runtime.getRuntime().exec(command);
		final Map<String, String> mapProcess = new HashMap<>();
		final String lines = printLines(process.getInputStream());
		process.waitFor();
		final int status = process.exitValue();
		mapProcess.put("lines", lines);
		mapProcess.put("status", String.valueOf(status));
		return mapProcess;
	}

	public static void runProcessNoWait(String command) throws Exception
	{
		Environment.sysOut("command:[" + command + "]");
		Runtime.getRuntime().exec(command);
	}
}