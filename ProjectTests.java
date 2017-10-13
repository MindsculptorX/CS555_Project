import static org.junit.Assert.*;

import org.junit.Test;

public class ProjectTests {
	@Test
	public void testPassForIsValidDate() {
		assertEquals(true, isValid.dateBeforeToday("30 JUL 1994"));
	}

	@Test
	public void testFailForIsValidDate() {
		assertEquals(false, isValid.dateBeforeToday("31 DEC 2017"));
	}

	@Test
	public void testPassForIsValidTag() {
		assertEquals(true, isValid.isTagValid(1, "SEX"));
	}

	@Test
	public void testFailForIsValidTag() {
		assertEquals(false, isValid.isTagValid(2, "WIFE"));
	}

}
