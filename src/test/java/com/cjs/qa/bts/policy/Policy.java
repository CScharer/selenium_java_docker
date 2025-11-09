package com.cjs.qa.bts.policy;

import com.cjs.qa.core.Environment;
import com.cjs.qa.utilities.DateHelpers;

public class Policy {
  public String userName = null;
  public String computerName = null;
  public String policy = null;
  public String policyNumber = null;
  public String sequenceNumber = null;
  public String dateTimeStamp = DateHelpers.getCurrentDateAndTime();

  public static void main(String[] args) { }

  public Policy(String policy) {
    this.computerName = Environment.getComputerName();
    this.userName = Environment.getCurrentUser();
    this.dateTimeStamp = DateHelpers.getCurrentDateAndTime();
    this.policy = policy;
    this.policyNumber = policy.substring(0, policy.indexOf("-"));
    this.sequenceNumber = policy.substring((policy.indexOf("-") + 1), policy.length());
  }
}
