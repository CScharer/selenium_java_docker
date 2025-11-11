package com.cjs.qa.rest;

import com.cjs.qa.core.QAException;
import com.cjs.qa.utilities.JavaHelpers;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class REST {
  private REST() {
    // Utility class - prevent instantiation
  }


  public static Map<String, String> getAPIJSONResponse(
      String credentials, String requestMethod, String apiRequest, String url) throws QAException {
    final Map<String, String> map = new HashMap<>();
    System.out.println(
        "getAPIJSONResponse Parameters:\n\trequestMethod:["
            + requestMethod
            + "]\n\turl:["
            + url
            + "]\n\tapiRequest["
            + apiRequest
            + "]");
    String responseCode = "-1";
    map.put("responseCode", responseCode);
    HttpURLConnection httpURLConnection = null;
    final StringBuilder stringBuilderJSON = new StringBuilder();
    String line = "";
    try {
      final URL oURL = new URL(url);
      httpURLConnection = (HttpURLConnection) oURL.openConnection();
      httpURLConnection.setDoInput(true);
      httpURLConnection.setDoOutput(true);
      httpURLConnection.setInstanceFollowRedirects(false);
      if (!requestMethod.isEmpty()) {
        httpURLConnection.setRequestMethod(requestMethod);
      }
      if (JavaHelpers.hasValue(credentials)) {
        final String encoding =
            Base64.getEncoder().encodeToString((credentials).getBytes(Charset.forName("UTF-8")));
        httpURLConnection.setRequestProperty("Authorization", "Basic " + encoding);
      }
      // httpURLConnection.setRequestProperty("X-Atlassian-Token:",
      // "no-check")
      httpURLConnection.setRequestProperty("Content-Type", "application/json");
      httpURLConnection.setRequestProperty("Accept", "application/json");
      httpURLConnection.setRequestProperty("charset", StandardCharsets.UTF_8.toString());
      // httpURLConnection.setRequestProperty("charset",
      // StandardCharsets.US_ASCII.toString());
      if (JavaHelpers.hasValue(apiRequest)) {
        httpURLConnection.setRequestProperty(
            "Content-Length", "" + Integer.toString(apiRequest.getBytes().length));
      } else {
        httpURLConnection.setRequestProperty("X-Requested-With", "Curl");
        httpURLConnection.setRequestProperty("Content-Length", "0");
      }
      httpURLConnection.setUseCaches(false);
      //
      // final PrintStream printStream = new
      // PrintStream(httpURLConnection.getOutputStream());
      // printStream.print(apiRequest);
      // printStream.close();
      //
      final DataOutputStream dataOutputStream =
          new DataOutputStream(httpURLConnection.getOutputStream());
      if (JavaHelpers.hasValue(apiRequest)) {
        dataOutputStream.writeBytes(apiRequest);
      }
      dataOutputStream.flush();
      responseCode = String.valueOf(httpURLConnection.getResponseCode());
      map.put("responseCode", responseCode);
      final String responseMessage = String.valueOf(httpURLConnection.getResponseMessage());
      map.put("responseMessage", responseMessage);
      // if (responseCode == HttpURLConnection.HTTP_OK)
      // {
      // final BufferedReader bufferedReader = new BufferedReader(new
      // InputStreamReader(httpURLConnection.getInputStream(),
      // StandardCharsets.UTF_8));
      final BufferedReader bufferedReader =
          new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
      while ((line = bufferedReader.readLine()) != null) {
        stringBuilderJSON.append(line);
      }
      bufferedReader.close();
      // }
      dataOutputStream.close();
      httpURLConnection.disconnect();
      // if (responseCode != HttpURLConnection.HTTP_OK)
      // {
      System.out.println("responseCode:[" + responseCode + "]");
      System.out.println("responseMessage:[" + responseMessage + "]");
      // }
      dataOutputStream.close();
      httpURLConnection.disconnect();
    } catch (final Exception e) {
      throw new QAException(e);
    }
    map.put("json", stringBuilderJSON.toString());
    return map;
  }
}
