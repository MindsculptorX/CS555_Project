import static org.junit.Assert.*;

import org.junit.Test;

public class ProjectTests {
	@Test
	public void testPassForIsValidTag() {
		assertEquals(true, isValid.isTagValid(1, "SEX"));
	}

	@Test
	public void testFailForIsValidTag() {
		assertEquals(false, isValid.isTagValid(2, "WIFE"));
	}

	@Test
	public void testPassForIsValidDate() {
		assertEquals(true, DateComparison.beforeToday("30 JUL 1994"));
	}

	@Test
	public void testFailForIsValidDate() {
		assertEquals(false, DateComparison.beforeToday("31 DEC 2017"));
	}

	@Test
	public void testPassForDateComparison() {
		assertEquals(true, DateComparison.beforeDate("25 MAY 2001", "2016-2-14"));
	}

	@Test
	public void testFailForDateComparison() {
		assertEquals(false, DateComparison.beforeDate("26 MAR 2001", "1997-1-1"));
	}

}