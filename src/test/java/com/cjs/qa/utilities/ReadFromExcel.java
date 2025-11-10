package com.cjs.qa.utilities;

import java.util.Locale;

import com.cjs.qa.core.Environment;

public class ReadFromExcel {

  public void whichTestType(String type) {

    switch (type.toLowerCase(Locale.ENGLISH)) {
      case "policyverification":
        // go to policyVerification class to do all the things
        break;
      case "policyverificationbuild":
        // go to policyVerificationBuild class to do all the things
        break;
      case "policyentry":
        // go to policyEntry class to do all the things
        break;
      case "policyentrybuild":
        // go to policyEntryBuild class to do all the things
        break;
      default:
        Environment.sysOut("Unknown test type: " + type + ". No action taken.");
        break;
    }
  }
}
