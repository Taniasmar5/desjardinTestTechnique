package Utils;

public class NumbersToWords {

	private static final String[] tensArray = { "", " ten", " twenty", " thirty", " forty", " fifty", " sixty",
			" seventy", " eighty", " ninety" };

	private static final String[] unitsArray = { "", " one", " two", " three", " four", " five", " six", " seven",
			" eight", " nine", " ten", " eleven", " twelve", " thirteen", " fourteen", " fifteen", " sixteen",
			" seventeen", " eighteen", " nineteen" };

	
	public static String[] getTensarray() {
		return tensArray;
	}

	public static String[] getUnitsarray() {
		return unitsArray;
	}
	
}
