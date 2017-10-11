public class Individual {
		private String id;
		private String name;
		private int age;
		private String gender;
		private String birthday;
		private boolean Alive;
		private String death = "N/A";
		private String famcId = "N/A";
		private String famsId = "N/A";

		public Individual(){
			setAlive(true);
		}

		public Individual(String id,String name,String gender,String birthday){
			setId(id);
			setName(name);
			setGender(gender);
			setBirthday(transDate(birthday));
			setAlive(true);
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
		}

		public String getGender() {
			return gender;
		}

		public void setGender(String gender) {
			this.gender = gender;
		}

		public String getBirthday() {
			return birthday;
		}

		public void setBirthday(String birthday) {
			this.birthday = transDate(birthday);
		}

		public boolean isAlive() {
			return Alive;
		}

		public void setAlive(boolean alive) {
			Alive = alive;
		}

		public String getDeath() {
			return death;
		}

		public void setDeath(String death) {
			this.death = transDate(death);
		}

		public String getFamcId() {
			return famcId;
		}

		public void setFamcId(String famcId) {
			this.famcId = famcId;
		}

		public String getFamsId() {
			return famsId;
		}

		public void setFamsId(String famsId) {
			this.famsId = famsId;
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

		@Override
		public String toString() {
			return "Individual [id=" + id + ", name=" + name + ", age="
					+ age + ", gender=" + gender + ", birthday=" + birthday
					+ ", Alive=" + Alive + ", death=" + death + ", famcId="
					+ famcId + ", famsId=" + famsId + "]";
		}
}
