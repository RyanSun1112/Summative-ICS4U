/* Upas Narayan
   Chemical Equation Balancer Version 1.7
   Version 1.0 released in January 2011
   Balancer.java
*/

/*
   Android application which balances chemical equations.
   The algorithm used in this application is linear system equation
   solving, and is a deterministic method to balance a chemical equation.
   This application requires no special permissions, and operates completely
   offline when used on an Android device.
*/


import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class Balancer {
	
	/* Constants for settings and help menus. */

	
	/* Primary method that balances an input chemical equation. */
	private static String equationBalance(String equation) {
	    // initialize reactant and product ArrayLists
		ArrayList<String> rterms = new ArrayList<String>();
		ArrayList<String> pterms = new ArrayList<String>();
		
		// used for redox equations
		ArrayList<Double> coeffsr = new ArrayList<Double>();
		ArrayList<Double> coeffsp = new ArrayList<Double>();
		
		// enclose all code in a large try/catch to catch all possible errors in equation
		try {
		    
		    // split equation into reactants and products, and convert to arrays of compounds
			String reactants = equation.substring(0, equation.indexOf(">"));
			String products = equation.substring(equation.indexOf(">") + 1, equation.length());
			reactants = reactants.trim();
			products = products.trim();
			converttoArrayList(reactants, rterms);
			converttoArrayList(products, pterms);
			String rterms1[] = new String[rterms.size()];
			String pterms1[] = new String[pterms.size()];
			for (int i = 0; i < rterms.size(); i++){
				if (Character.isDigit(rterms.get(i).charAt(0))){
					throw new Exception();
				}
				rterms.set(i, rterms.get(i).trim());
				rterms1[i] = rterms.get(i);
			}
			for (int i = 0; i < pterms.size(); i++){
				if (Character.isDigit(pterms.get(i).charAt(0))){
					throw new Exception();
				}
				pterms.set(i, pterms.get(i).trim());
				pterms1[i] = pterms.get(i);
			}
			
			// deal with redox equations
			boolean isredox = configureRedox(rterms, coeffsr);
			isredox = configureRedox(pterms, coeffsp);
			
			// add "1" subscripts and deal with polyatomic ions
			addNums(rterms);
			configureParenthesis(rterms);
			addNums(pterms);
			configureParenthesis(pterms);
			addNums(rterms);
			addNums(pterms);
			
			// equation is now all configured, do pattern matching to extract elements
			ArrayList <String> elements = new ArrayList<String>();
			patternMatch(rterms, elements);
			patternMatch(pterms, elements);

            // initialize matrix m in order to solve system of equations
            int size = rterms.size() + pterms.size();
            String h[] = new String[1000];
			double m[][] = null;
			int rows = elements.size();
			if (size > rows){
				m = new double[size][size];
			}
			else if (rows > size){
				m = new double[rows][size];
			}
			else if (rows == size){
				m = new double[size][rows];
			}
			if (isredox){
				m = new double[m.length + 1][m[0].length];
				int c = 0;
				for (int i = 0; i < coeffsr.size(); i++){
					m[m.length - 1][c] = coeffsr.get(i);
					c++;
				}
				for (int i = 0; i < coeffsp.size(); i++){
					if (i == coeffsp.size() - 1){
						m[m.length - 1][c] = coeffsp.get(i);
					}
					else
					{
						m[m.length - 1][c] = coeffsp.get(i) * -1;
						c++;
					}
				}
			}
			
			// add system of equations to matrix m
			addToMatrix(rterms, m, h);
			addToMatrix(pterms, m, h);
			
			// perform RREF to solve system of linear equations
			toRREF(m);
			
			// re-extract balanced equation from solved matrix m
			ArrayList<Double> coefficients = new ArrayList<Double>();
			for (int i = 0; i < m[0].length; i++){
				if (m[i][m[0].length - 1] == 0.0){
					m[i][m[0].length - 1] = 1.0;
				}
				coefficients.add(m[i][m[0].length - 1]);
			}
			Double elem[] = new Double[coefficients.size()];
			int factor = 0;
			int denoms[] = new int[elem.length];
			for (int i = 0; i < elem.length; i++){
				elem[i] = m[i][elem.length - 1];
				denoms[i] = toFraction(elem[i]);
			}
			factor = lcm(denoms);
			int fin[] = new int[elem.length];
			for (int i = 0; i < elem.length; i++){
				elem[i] *= factor;
				fin[i] = (int)Math.round(elem[i].doubleValue());
			}
			
			// generate final equation
			String newequ1 = generateFinalEquation(rterms1, pterms1, fin);
			String newequ2 = removeSpaces(newequ1);
			if (newequ2.equals(equation)){
				return null;
			}
			return newequ2;
		} catch(Exception e) {
		    return null;
		}
	}
	
	/* Generate final balanced equation from solved linear system of equations. */
	private static String generateFinalEquation(String[] rterms1, String[] pterms1, int[] fin) {
	    int cou = 0;
	    String newequ = "";
	    String newequ1 = "";
		for (int i = 0; i < rterms1.length; i++){
			if (fin[cou] == 1){
				if (i == rterms1.length - 1){
					newequ += rterms1[i] + " \u2192 ";
					newequ1 += rterms1[i] + " = ";
				}
				else {
					newequ += rterms1[i] + " + ";
					newequ1 += rterms1[i] + " + ";
				}
				cou++;
			}
			else
			{
				if (i == rterms1.length - 1){
					newequ += fin[cou] + rterms1[i] + " \u2192 ";
					newequ1 += fin[cou] + rterms1[i] + " = ";
				}
				else {
					newequ += fin[cou] + rterms1[i] + " + ";
					newequ1 += fin[cou] + rterms1[i] + " + ";
				}
				cou++;
			}
		}
		for (int i = 0; i < pterms1.length; i++){
			if (fin[cou] == 1){
				if (i == pterms1.length - 1) {
					newequ += pterms1[i];
					newequ1 += pterms1[i];
				}
				else {
					newequ += pterms1[i] + " + ";
					newequ1 += pterms1[i] + " + ";
				}
				cou++;
			}
			else
			{
				if (i == pterms1.length - 1) {
					newequ += fin[cou] + pterms1[i];
					newequ1 += fin[cou] + pterms1[i];
				}
				else {
					newequ += fin[cou] + pterms1[i] + " + ";
					newequ1 += fin[cou] + pterms1[i] + " + ";
				}
				cou++;
			}
		}
		return newequ1;
	}
	
	
	/* Perform pattern matching to extract elements into output ArrayList. */
	private static void patternMatch(ArrayList<String> terms, ArrayList<String> elements) {
	    for (int i = 0; i < terms.size(); i++){
	        // match single uppercase letter, or uppercase with 1 or 2 lowercase
			Pattern p1 = Pattern.compile("([A-Z])(\\d+)");
			String comp = terms.get(i);
			Matcher m1 = p1.matcher(comp);
			while (m1.find()) {
				if (!elements.contains(m1.group(1)))
				{
					elements.add(m1.group(1));
				}
			}
			Pattern p2 = Pattern.compile("([A-Z])([a-z])(\\d+)");
			Matcher m2 = p2.matcher(comp);
			while (m2.find()) {
				if (!elements.contains(m2.group(1) + "" + m2.group(2)))
				{
					elements.add(m2.group(1) + "" + m2.group(2));
				}
			}
			Pattern p3 = Pattern.compile("([A-Z])([a-z])([a-z])(\\d+)");
			Matcher m3 = p3.matcher(comp);
			while (m3.find()) {
				if (!elements.contains(m3.group(1) + "" + m3.group(2) + "" + m3.group(3)))
				{
					elements.add(m3.group(1) + "" + m3.group(2) + "" + m3.group(3));
				}
			}
			if (terms.get(i).length() == 1){
				String e = terms.get(i);
				terms.set(i, e + "1");
			}
		}

	}

	/* Add the terms (either products or reactants) to a matrix m in order to solve the RREF of the
	   system of linear equations involved in the chemical equation.
	*/
	private static void addToMatrix(ArrayList<String> terms, double[][] m, String[] h) {
	    int count = 0;
	    int track = 0;
	    for (int i = 0; i < terms.size(); i++){
			ArrayList <String> x = new ArrayList<String>();
			ArrayList <Integer> y = new ArrayList<Integer>();
			Pattern p1 = Pattern.compile("([A-Z])(\\d+)");
			String comp = terms.get(i);
			Matcher m1 = p1.matcher(comp);
			while (m1.find()) {
				if (x.contains(m1.group(1))){
					int ind = x.indexOf(m1.group(1));
					Integer add = y.get(ind);
					y.set(ind, add + Integer.parseInt(m1.group(2)));
				}
				else
				{
					x.add(m1.group(1));
					y.add(Integer.parseInt(m1.group(2)));
				}
			}
			Pattern p2 = Pattern.compile("([A-Z])([a-z])(\\d+)");
			Matcher m2 = p2.matcher(comp);
			while (m2.find()) {
				if (x.contains(m2.group(1) + "" + m2.group(2))){
					int ind = x.indexOf(m2.group(1) + "" + m2.group(2));
					Integer add = y.get(ind);
					y.set(ind, add + Integer.parseInt(m2.group(3)));
				}
				else {
					x.add(m2.group(1) + "" + m2.group(2));
					y.add(Integer.parseInt(m2.group(3)));
				}
			}
			Pattern p3 = Pattern.compile("([A-Z])([a-z])([a-z])(\\d+)");
			Matcher m3 = p3.matcher(comp);
			while (m3.find()) {
				if (x.contains(m3.group(1) + "" + m3.group(2) + "" + m3.group(3))){
					int ind = x.indexOf(m3.group(1) + "" + m3.group(2)+ "" + m3.group(3));
					Integer add = y.get(ind);
					y.set(ind, add + Integer.parseInt(m3.group(4)));
				}
				else {
					x.add(m3.group(1) + "" + m3.group(2) + "" + m3.group(3));
					y.add(Integer.parseInt(m3.group(4)));
				}
			}
			if (i == 0){
				for (int j = 0; j < y.size(); j++){
					m[track][i] = y.get(j);
					track++;
					h[count] = x.get(j);
					count++;
				}
			}
			else
			{
				boolean b = false;
				for (int j = 0; j < x.size(); j++){
					b = false;
					for (int k = 0; k < h.length; k++){
						if (x.get(j).equals(h[k])){
							m[k][i] = y.get(j);
							b = true;
						}
					}
					if (!b){
						m[track][i] = y.get(j);
						track++;
						h[count] = x.get(j);
						count++;
					}
				}
			}
		}
	}

	/* Determine if an equation is a redox equation and add
	   oxidation/reduction coefficients to coeffs array.
	*/
	private static boolean configureRedox(ArrayList<String> terms, ArrayList<Double> coeffs) {
	    boolean isredox = false;
		for (int i = 0; i < terms.size(); i++){
			String x = terms.get(i);
			for (int j = 0; j < x.length(); j++){
				if (j == x.length() - 1 && x.charAt(j) != ']'){
					coeffs.add(0.0);
				}
				if (x.charAt(j) == '['){
					isredox = true;
					int ind = x.indexOf(']');
					String num = x.substring(j + 1, ind);
		    		coeffs.add(Double.parseDouble(num));
					x = x.substring(0, j);
					break;
				}
			}
			terms.set(i, x);
		}
		return isredox;
	}

	/* Removes all spaces from input string s. */
	private static String removeSpaces(String s) {
		StringTokenizer st = new StringTokenizer(s, " ", false);
		String t = "";
		while (st.hasMoreElements()) t += st.nextElement();
		return t;
	}

	/* Computes the greatest common denominator between a and b. */
	private static int gcd(int a, int b) {
		while (b > 0) {
			int temp = b;
			b = a % b;
			a = temp;
		}
		return a;
	}

    /* Computes the least common multiple between integers a and b. */
	public static int lcm(int a, int b) {
		return a * (b / gcd(a, b));
	}

    /* computes the least common multiple of an array of integers. */
	public static int lcm(int[] input) {
		int result = input[0];
		for(int i = 1; i < input.length; i++) result = lcm(result, input[i]);
		return result;
	}

	/* Returns the denominator of the fraction represented by the input decimal value. */
	public static int toFraction(double decimal) {
		int LIMIT = 12;
		int denominators[] = new int[LIMIT + 1];
		int numerator, denominator = 0, temp;
		int MAX_GOODNESS = 100;
		int i = 0;
		while (i < LIMIT + 1) {
			denominators[i] = (int) decimal;
			decimal = 1.0 / (decimal - denominators[i]);
			i = i + 1;
		}
		int last = 0;
		while (last < LIMIT) {
			numerator = 1;
			denominator = 1;
			temp = 0;
			int current = last;
			while (current >= 0) {
				denominator = numerator;
				numerator = (numerator * denominators[current]) + temp;
				temp = denominator;
				current = current - 1;
			}
			last = last + 1;
			int goodness = denominators[last];
			if (Math.abs(goodness) > MAX_GOODNESS) break;
		}
		return denominator;
	}

    /* Converts each side of a chemical equation to an ArrayList of strings. */
	public static void converttoArrayList(String a, ArrayList<String> b){
		int pos = 0;
		for (int i = 0; i < a.length(); i++){
			if (i == a.length() - 1){
				String x = a.substring(pos, a.length());
				b.add(x);
			}
			if (Character.toString(a.charAt(i)).equals("+")){
				String x = a.substring(pos, i);
				pos = i + 1;
				b.add(x);
			}
		}
	}

	/* Configure compounds like NaCl which consist of one ion, by adding subscripts of 1.
	   e.g. NaCl -> Na1Cl1
	*/
	public static void addNums(ArrayList<String> b){
		for (int i = 0; i < b.size(); i++){
			String x = b.get(i);
			for (int j = 0; j < x.length() - 1; j++){
				if (!Character.isDigit(x.charAt(j)) && x.charAt(j + 1) == ')'){
					x = x.substring(0, j + 1) + "1" + x.substring(j + 1, x.length());
					break;
				}
				if ((Character.isUpperCase(x.charAt(j)) && !Character.isDigit(j + 1) && Character.isUpperCase(x.charAt(j + 1)))){
					x = x.substring(0, j + 1) + "1" + x.substring(j + 1, x.length());
				}
				else if (j == x.length() - 2 && Character.isUpperCase(x.charAt(j + 1))){
					x = x + "1";
				}
				if (Character.isUpperCase(x.charAt(j)) && Character.isLowerCase(x.charAt(j + 1))){
					if (j != x.length() - 2){
						if (Character.isUpperCase(x.charAt(j + 2)) || x.charAt(j + 2) == '('){
							x = x.substring(0, j + 2) + "1" + x.substring(j + 2, x.length());
						}
					}
					else if (j == x.length() - 2){
						x = x + "1";
					}
				}
			}
			b.set(i, x);
		}
	}

	/* Deals with compounds which have polyatomic ions (parentheses). */
	public static void configureParenthesis(ArrayList<String> b){
		int oldlength = 0;
		for (int i = 0; i < b.size(); i++){
			String x = b.get(i);
			oldlength = x.length();
			for (int j = 0; j < x.length() - 1; j++){
				if (x.charAt(j) == '('){
					int end = x.indexOf(')');
					Integer factor = Integer.parseInt(Character.toString(x.charAt(end + 1)));
					for (int k = j + 1; k < end; k++){
						if (Character.isDigit(x.charAt(k))){
							Integer num = Integer.parseInt(Character.toString(x.charAt(k)));
							Integer newnum = num * factor;
							String num1 = num.toString();
							int ind = x.indexOf(num1, k);
							x = x.substring(0, ind) + newnum.toString() + x.substring(ind + 1, x.length());
							if (x.length() > oldlength){
								k += x.length() - oldlength;
								end += x.length() - oldlength;
							}
							oldlength = x.length();
						}
					}
					if (j == 0){
						end = x.indexOf(')');
						x = x.substring(1, end) + x.substring(end + 2, x.length());
						b.set(i, x);
						break;
					}
					else
					{
						end = x.indexOf(')');
						x = x.substring(0, j) + x.substring(j + 1, end);
						b.set(i, x);
						break;
					}

				}
			}
		}
	}

	/* Computes the RREF (reduced row echelon form) of the matrix M through Gaussian row reduction. */
	public static void toRREF(double[][] M) {
		int rowCount = M.length;
		if (rowCount == 0)
			return;

		int columnCount = M[0].length;

		int lead = 0;
		for (int r = 0; r < rowCount; r++) {
			if (lead >= columnCount)
				break;
			{
				int i = r;
				while (M[i][lead] == 0) {
					i++;
					if (i == rowCount) {
						i = r;
						lead++;
						if (lead == columnCount)
							return;
					}
				}
				double[] temp = M[r];
				M[r] = M[i];
				M[i] = temp;
			}

			{
				double lv = M[r][lead];
				for (int j = 0; j < columnCount; j++)
					M[r][j] /= lv;
			}

			for (int i = 0; i < rowCount; i++) {
				if (i != r) {
					double lv = M[i][lead];
					for (int j = 0; j < columnCount; j++)
						M[i][j] -= lv * M[r][j];
				}
			}
			lead++;
		}
	}
	public static void main(String[] args){
		System.out.print(equationBalance("CH4 + O2 > H2O + CO2"));

	}

	/* Displays an "About" dialog with the current version of the application. */


	/* Displays the help menu with detailed information on the functionality of the application. */


	/* Initialize the menu for the application. */


	/* Controls what happens when a menu item is selected. */

}

// END OF CODE

