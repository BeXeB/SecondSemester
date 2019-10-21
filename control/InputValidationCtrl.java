package control;

import java.util.regex.*;


public class InputValidationCtrl {

	public boolean numberValidateInput(String s) {
		for (int i = 0; i < s.length(); i++)
			if (Character.isDigit(s.charAt(i)) == false)
				return false; //if it's not a number

		return true;
		}
	
	public boolean numberDoubleValidateInput(String s) {
		for (int i = 0; i < s.length(); i++)
			if (Character.isDigit(s.charAt(i)) == false && s.charAt(i) != '.')
				return false; //if it's not a number

		return true;
		}
	
	public boolean dateValidation(String date) {
		//dd/mm/yyyy not d/m/yyyy
		//String regex = "^[0-3][0-9]/[0-3][0-9]/(?:[0-9][0-9])?[0-9][0-9]$";
		//yyyy-MM-dd hh:mm:ss
		String regex = "^\\d\\d\\d\\d-([0]{0,1}[1-9]|1[012])-([1-9]|([012][0-9])|(3[01])) (20|21|22|23|[0-1]?\\d):[0-5]?\\d:[0-5]?\\d$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(date);
		return matcher.matches();
		//https://howtodoinjava.com/regex/java-regex-date-format-validation/
		//free to use and edit
		}

}
