package com.example.demo;

import java.text.DecimalFormat;
import java.util.Arrays;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Utils.NumbersToWords;

@RestController
@RequestMapping("/tania")
public class Controller {

	/**
	 * @param value
	 * @return
	 * 
	 * The method takes a String as a parameter and returns the string in alphabetical order without repeating the letters
	 */
	@GetMapping(value = "/chaine/{value}")
	public String chaineCaracteres(@PathVariable String value) {
		String resultString = "";
		char[] chars = value.toCharArray();

		Arrays.sort(chars);
		String sort = new String(chars);

		for (int i = 0; i < sort.length(); i++) {
			if (!resultString.contains(String.valueOf(sort.charAt(i)))) {
				resultString += String.valueOf(sort.charAt(i));
			}
		}

		return resultString;
	}
	
	
	
	/**
	 * @param value
	 * @return
	 * 
	 * The method takes an integer between 0 and 999 999 as a parameter and converts the number to words
	 */
	@GetMapping(value = "/algorithme/{value}")
	public String algorithme(@PathVariable String value) {
		return convert(value);
	}

	private static String convertUnderOneThousand(int number) {
		String soFar;

		if (number % 100 < 20) {
			soFar = NumbersToWords.getUnitsarray()[number % 100];
			number /= 100;
		} else {
			soFar = NumbersToWords.getUnitsarray()[number % 10];
			number /= 10;

			soFar = NumbersToWords.getTensarray()[number % 10] + soFar;
			number /= 10;
		}
		if (number == 0)
			return soFar;
		return NumbersToWords.getUnitsarray()[number] + " hundred" + soFar;
	}

	public static String convert(String numberStr) {
		
		Long number;
		
		String regex = "[0-9]+";
		
		if(!numberStr.matches(regex)) {
			return "Please enter a number";
		}
		
		number=Long.valueOf(numberStr).longValue();
		
		// 0 to 999 999
		if (number == 0) {
			return "zero";
		}
		if(number>999999) {
			return "Number only between 0 and 999 999";
		}

		String snumber = Long.toString(number);
		
		String mask = "000000";
		DecimalFormat df = new DecimalFormat(mask);
		snumber = df.format(number);

		int hundredThousands = Integer.parseInt(snumber.substring(0, 3));

		int thousands = Integer.parseInt(snumber.substring(3, 6));

		String tradHundredThousands;
		switch (hundredThousands) {
		case 0:
			tradHundredThousands = "";
			break;
		case 1:
			tradHundredThousands = "one thousand ";
			break;
		default:
			tradHundredThousands = convertUnderOneThousand(hundredThousands) + " thousand ";
		}
		String result = tradHundredThousands;

		String tradThousand;
		tradThousand = convertUnderOneThousand(thousands);
		result = result + tradThousand;

		// remove extra spaces!
		return result.replaceAll("^\\s+", "").replaceAll("\\b\\s{2,}\\b", " ");
	}

}
