package net.japoness.emailcount;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EmailCountTest {
	
	private EmailCount objUnderTest;

	@BeforeEach
	public void setup() {
		objUnderTest = new EmailCount();
	}

	@Test
	void testDotRemoval() {
		assertThat(objUnderTest.massageAddress("a.b.c@d.org"), equalTo("abc@d.org"));
	}
	
	@Test
	void testAtSign() {
		assertThat(objUnderTest.massageAddress("a@b@c@d"), equalTo("a@b@c@d"));
	}

	@Test
	void testPlusRemoval() {
		assertThat(objUnderTest.massageAddress("a+b.c@d.org"), equalTo("a@d.org"));
	}
	
	@Test
	void testEmptyList() {
		assertThat(objUnderTest.countDistinctMassagedAddresses(new String[0]), equalTo(0));
	}
	
	@Test
	void testVariationsOfOneAddress() {
		String[] list = {"a.b@c", "ab+d@c", "a..b@c"};
		assertThat(objUnderTest.countDistinctMassagedAddresses(list), equalTo(1));
	}

	@Test
	void testVariationsOfTwoAddress() {
		String[] list = {"a.b@c", "ab+d@d", "a..b@c", "ab@d"};
		assertThat(objUnderTest.countDistinctMassagedAddresses(list), equalTo(2));
	}
}
