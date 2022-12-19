package files;

import io.restassured.path.json.JsonPath;

public class Sec06ComplexJSOnPath {
	public static void main(String[] args) {
		
		JsonPath js=new JsonPath(Payloads.CoursePrice());
//		1. Print No of courses returned by API
		int c=js.getInt("courses.size()");
		System.out.println(c);
		
//		2.Print Purchase Amount
		int CoursePrice = js.getInt("dashboard.purchaseAmount");
		System.out.println(CoursePrice);
		
		
//		3. Print Title of the first course
		String fcourse = js.get("courses[0].title");
		System.out.println(fcourse);

		//		4. Print All course titles and their respective Prices
		for(int i=0;i<c;i++) {
			String ctitle = js.getString("courses["+i+"].title");
			String cprice = js.getString("courses["+i+"].price");
					
			System.out.println(ctitle+" "+cprice);
		}

		//		5. Print no of copies sold by RPA Course
		for(int i=0;i<c;i++) {
			String ctitle = js.getString("courses["+i+"].title");
			if(ctitle.equalsIgnoreCase("RPA")) {
				System.out.println(js.getString("courses["+i+"].copies"));
			break;
			}
			
		}
		
//		6. Verify if Sum of all Course prices matches with Purchase mount
		int sum=0;
		for(int i=0;i<c;i++) {
			int cprice = js.getInt("courses["+i+"].price");
			int ccopies = js.getInt("courses["+i+"].copies");
			int a=cprice*ccopies;
			sum =sum+a;
			System.out.println(a);
		}
		System.out.println(sum);

		
		
	}

}
