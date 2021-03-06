/* package whatever; // don't place package name! */

import java.util.*;
import java.lang.*;
import java.io.*;

/* Name of the class has to be "Main" only if the class is public. */
class PractiseProblem
{
	static int res = 0;
	static Set<String> resSet = null;
	static <T> void op(T ...x){// Java Generic function
		
		System.out.println(x);
	}
	static <T> void getUnknownWords(String text, String[] vocabulary){
		Set<String> unknownWords = new HashSet<String>();
		Set<String> text1 = new HashSet<String>();
		String[] temp = text.split("\\s");
		for(String e: temp) {
			text1.add(e);
		}
		Set<String> vocab = new HashSet<String>();
		for(String e: vocabulary) {
			vocab.add(e);
		}
		// Actual logic below:
		if (text1.equals(vocab)) {
			res = -1;
		}
		else {
			int flag = 0;
			/* Iterator<String> i = text1.iterator();
			Iterator<String> i2 = vocab.iterator();
			while(i.hasNext()){
				while (i2.hasNext()) {
					
				}
				i.next();
			} */
			
			for(String i: text1) {
				for(String j: vocab) {
					if (i != j) {
						op(i,j);
						flag = 1;
					} else { flag = 0; }
				}
				if (flag == 1) {
					unknownWords.add(i);
				}
			}
			resSet = unknownWords;
		}
	}
	public static void main (String[] args) throws java.lang.Exception
	{
		String[] s = {"hot"};
		String s0 = "coffee is hot";
		getUnknownWords(s0,s);
		if (res != 0) {
			op(res);
		}
		if (resSet != null) {
			op(resSet);
		}
			
	}
}