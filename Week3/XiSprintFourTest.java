import static org.junit.Assert.*;

import org.junit.Test;

public class XiSprintFourTest {

	@Test
	public void testlistMultipleBirths() {
		ParseGEDCOMFile.setMap();
		ReportingTool4.listMultipleBirths();
	}

}
