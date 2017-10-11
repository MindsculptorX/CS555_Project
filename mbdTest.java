
public class mbdTest {
public static void main(String[] args) {
	Family fam = new Family();
	fam.setMarried("6 AUG 1999");
	String divorced = "6 SEP 1999";
	if(marriageBeforeDivorce(fam, divorced)){
		fam.setDivorced(divorced);
	}
	System.out.println(fam.getMarried()+"||"+fam.getDivorced());
}
public static boolean marriageBeforeDivorce(Family fam,String date){
	date = fam.transDate(date);
	String[] temp = date.split("-");
	int year = Integer.parseInt(temp[0]);
	int month = Integer.parseInt(temp[1]);
	int day = Integer.parseInt(temp[2]);
	if(year<fam.getmYear()){
		return false;
	}else if(year>fam.getmYear()){
		return true;
	}else{
		if(month<fam.getmMonth()){
			return false;
		}else if(month>fam.getdMonth()){
			return true;
		}else{
			if(day<=fam.getmDay()){
				return false; 
			}else{
				return true;
			}
		}
	}
}
}
