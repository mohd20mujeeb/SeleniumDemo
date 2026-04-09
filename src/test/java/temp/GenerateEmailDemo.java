package temp;

import java.util.Date;

;

public class GenerateEmailDemo {
public static void main(String[] args) {
	Date date = new Date();
	
	String dateString=date.toString();
	String noSpaceDateString=dateString.replaceAll(" ", "");
	String noSpaceAndNoColon=noSpaceDateString.replaceAll("\\:","");
	String emailWithTimeStamp=noSpaceAndNoColon+"@gmail.com";
    System.out.println(emailWithTimeStamp);	
}
}
