package Service;

import java.util.List;

public class TesterClass {

	public static void main(String[] args) {
		ServiceImages service = new ServiceImages();
		List<String> res= service.retriveImages();
		for (String string : res) {
			System.out.println(string);
		}
	}
}
