package jsone8hz8d;

import org.json.simple.JSONObject;

public class ObjectE8HZ8D {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		JSONObject obj = new JSONObject();
		
		obj.put("Név", "BLászló");
		obj.put("Fizetes", new Double(1000000));
		obj.put("Kor", new Integer(21));

		System.out.println(obj.toJSONString());
	}
}
