
public class SI034 {
	 private String id;
	   private int age;

	   public SI034(String id, int age) {
	       setId(id);
	       setAge(age);
	   }
	   
	   void setId(String id) {
		   this.id = id;
	   }
	   
	   void setAge(int age) {
		   this.age = age;
	   }
	   
	   public String getId() {
		   return id;
	   }
	   
	   public int getAge() {
		   return age;
	   }
}
