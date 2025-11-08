package com.cjs.qa.utilities;

public class ReadFromExcel {

	public void whichTestType(String type) {

		switch (type.toLowerCase()) {
			case "policyverification" :
				// go to policyVerification class to do all the things
				break;
			case "policyverificationbuild" :
				// go to policyVerificationBuild class to do all the things
				break;
			case "policyentry" :
				// go to policyEntry class to do all the things
				break;
			case "policyentrybuild" :
				// go to policyEntryBuild class to do all the things
				break;
		}

	}
}
