import java.util.ArrayList;

public class Family{
	private String id;
	private String married;
	private String divorced = "N/A";
	private String husId;
	private String wifId;
	private String husName;
	private String wifName;
	private ArrayList<String> children;
	private int mDay;
	private int mMonth;
	private int mYear;
	private int dDay;
	private int dMonth;
	private int dYear;

	public Family(){
		setChildren(new ArrayList<String>());
	}

	public Family(String id,String married,String husId,String wifId){
		setId(id);
		setMarried(married);
		setHusId(husId);
		setWifId(wifId);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMarried() {
		return married;
	}

	public void setMarried(String married) {
		this.married = transDate(married);
		String[] temp = this.married.trim().split("-");
		setmYear(Integer.parseInt(temp[0]));
		setmMonth(Integer.parseInt(temp[1]));
		setmDay(Integer.parseInt(temp[2]));
	}

	public String getDivorced() {
		return divorced;
	}

	public void setDivorced(String divorced) {
		this.divorced = transDate(divorced);
		String[] temp = this.divorced.trim().split("-");
		setdYear(Integer.parseInt(temp[0]));
		setdMonth(Integer.parseInt(temp[1]));
		setdDay(Integer.parseInt(temp[2]));
	}

	public String getHusId() {
		return husId;
	}

	public void setHusId(String husId) {
		this.husId = husId;
	}

	public String getWifId() {
		return wifId;
	}

	public void setWifId(String wifId) {
		this.wifId = wifId;
	}

	public String getHusName() {
		return husName;
	}

	public void setHusName(String husName) {
		this.husName = husName;
	}

	public String getWifName() {
		return wifName;
	}

	public void setWifName(String wifName) {
		this.wifName = wifName;
	}

	public ArrayList<String> getChildren() {
		return children;
	}

	public void setChildren(ArrayList<String> children) {
		this.children = children;
	}

	public int getmDay() {
		return mDay;
	}

	public void setmDay(int mDay) {
		this.mDay = mDay;
	}

	public int getmMonth() {
		return mMonth;
	}

	public void setmMonth(int mMonth) {
		this.mMonth = mMonth;
	}

	public int getmYear() {
		return mYear;
	}

	public void setmYear(int mYear) {
		this.mYear = mYear;
	}

	public int getdDay() {
		return dDay;
	}

	public void setdDay(int dDay) {
		this.dDay = dDay;
	}

	public int getdMonth() {
		return dMonth;
	}

	public void setdMonth(int dMonth) {
		this.dMonth = dMonth;
	}

	public int getdYear() {
		return dYear;
	}

	public void setdYear(int dYear) {
		this.dYear = dYear;
	}

	public String transDate(String date){
		String split[] = date.split(" ");
		String day = split[0];
		int temp = Integer.parseInt(day);
		if(temp<10){
			day="0"+day;
		}
		String month=null;
		if(split[1].equals("JAN")){
			month="01";
		}else if(split[1].equals("FEB")){
			month="02";
		}else if(split[1].equals("MAR")){
			month="03";
		}else if(split[1].equals("APR")){
			month="04";
		}else if(split[1].equals("MAY")){
			month="05";
		}else if(split[1].equals("JUN")){
			month="06";
		}else if(split[1].equals("JUL")){
			month="07";
		}else if(split[1].equals("AUG")){
			month="08";
		}else if(split[1].equals("SEP")){
			month="09";
		}else if(split[1].equals("OCT")){
			month="10";
		}else if(split[1].equals("NOV")){
			month="11";
		}else if(split[1].equals("DEC")){
			month="12";
		}
		String year = split[2];
		String d = year+"-"+month+"-"+day;
		return d;
	}

	public  boolean marriageBeforeDivorce(){
	if(DateComparison.beforeDate(getMarried(),getDivorced())){
		return true;
	}else{
		return false;
	}
	}
	@Override
	public String toString() {
		return "Family [id=" + id + ", married=" + married
				+ ", divorced=" + divorced + ", husId=" + husId
				+ ", wifId=" + wifId + ", husName=" + husName
				+ ", wifName=" + wifName + ", children=" + children
				+ "]";
	}
}
