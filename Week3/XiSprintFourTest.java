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

	@Test
	public void testlistUpcomingAnniversaries() {
		ParseGEDCOMFile.setMap();
		System.out.println(ReportingTool4.listUpcomingAnniversaries());
	}
	@Test
	public void testlistUpcomingBirthdays(){
		ParseGEDCOMFile.setMap();
		System.out.println(ReportingTool4.listUpcomingBirthdays());
	}
}
