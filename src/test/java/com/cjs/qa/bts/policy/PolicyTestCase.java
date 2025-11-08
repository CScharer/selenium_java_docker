package com.cjs.qa.bts.policy;

import org.junit.Test;

import com.cjs.qa.core.Environment;

public class PolicyTestCase
{
	private void PolicyTestCase()
	{ // Empty
	}

	@Test
	public static void mainTest()// main(String[] args)
	{
		final Policy policy = new Policy("123456789-10");
		Environment.sysOut(policy.policy);
		Environment.sysOut(policy.policyNumber);
		Environment.sysOut(policy.sequenceNumber);
	}
}