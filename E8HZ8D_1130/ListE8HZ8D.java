package jsone8hz8d;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class ListE8HZ8D {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		
		JSONArray user = new JSONArray();
		user.add("BL");
		user.add(new Double(1000000));
		user.add(new Integer(21));
		
		for(Object obj : user) {
			System.out.print(obj+" ");
		}
	}
}
