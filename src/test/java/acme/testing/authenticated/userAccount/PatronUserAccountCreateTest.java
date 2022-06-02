package acme.testing.authenticated.userAccount;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class PatronUserAccountCreateTest extends TestHarness {

	
	// Lifecycle management ---------------------------------------------------

			// Test cases -------------------------------------------------------------

			
			  @ParameterizedTest
			  
			  @CsvFileSource(resources = "/authenticated/userAccount/patron/create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
			  
			  @Order(10)
			  public void positiveTest(final int recordIndex, final String company, final String statement, final String moreInfo) {
			  super.signIn("inventor1", "inventor1");
			  
			  super.clickOnMenu("Account", "Become a patron");
			  
			  
			  super.fillInputBoxIn("company", company);
			  super.fillInputBoxIn("statement", statement);
			  super.fillInputBoxIn("moreInfo", moreInfo);
			  super.clickOnSubmit("Register");
			  
			  
			  super.signOut();
			  }
			 
			
			  @ParameterizedTest
			  
			  @CsvFileSource(resources = "/authenticated/userAccount/patron/create-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
			  
			  @Order(20)
			  public void negativeTest(final int recordIndex, final String company, final String statement, final String moreInfo) {
			  
			  super.signIn("inventor2", "inventor2");
			  
			  super.clickOnMenu("Account", "Become a patron");
		
			  
			  
			  super.fillInputBoxIn("company", company);
			  super.fillInputBoxIn("statement", statement);
			  super.fillInputBoxIn("moreInfo", moreInfo);
			  super.clickOnSubmit("Register");
			  
			  super.checkErrorsExist();
			  
			  super.signOut();
			  }
			 
		
			@Test
			@Order(30)
			public void hackingTest() {
			// HINT: the framework doesn't provide enough support to implement this test case,
			// HINT+ so it must be performed manually:
			// HINT+ a) create an patron account when you are already a patron
			// HINT+ b) anonymous user wants to create a patron account 			
			  }

			// Ancillary methods ------------------------------------------------------

}
