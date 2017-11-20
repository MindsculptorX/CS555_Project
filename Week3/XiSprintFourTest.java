import static org.junit.Assert.*;

import org.junit.Test;

public class XiSprintFourTest {

	@Test
	public void testlistMultipleBirths() {
		ParseGEDCOMFile.setMap();
		System.out.println(ReportingTool4.listMultipleBirths());
	}
	
	@Test
	public void testlistLargeAgeDiff() {
		ParseGEDCOMFile.setMap();
		System.out.println(ReportingTool4.listLargeAgeDiff());
	}

}
