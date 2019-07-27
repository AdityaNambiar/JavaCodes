public class StaticVsInstance { 
	int data = 10;
	static int staticData = 20;
	
	public static <T> void op(Object x) { // Java Generic method
		System.out.println(x);
	}
 	/* 
 	 * A static method can only have static variables or static methods ONLY.
 	 * "this" keyword and "super" keyword CANNOT be used in a static method.
 	 * "this" keyword refers to an object and "super" refers to parent class object. Objects do not come in the context of static methods. 
 	 *  - So, you'd only use "this" as a keyword to refer to an Object in it's context (not a static i.e. Class context)
 	 * */
	public static int staticMethod(int a, int b) {
		// Member variables: a, b
		//System.out.println(String.valueOf(a)+" "+String.valueOf(b));
		return a+b;
	}
	public void instanceMethod() {
		// An instance method can call a static method.
		// A static method CANNOT have ANYTHING "instance i.e. non-static" (variable/method) inside a static method.
		op(staticMethod(2,3));
	}
	
	/* 
	 * Static methods can call (basically) access other static methods within them.
	 * */
	public static void main(String args[]) {
		StaticVsInstance X = new StaticVsInstance();
		// Static variables/methods belongs to Class so it's like:
		/*
		 * Main issi class ka hu, meri pechan is class mein sabko pata hai
		 * Toh mein bina object (reference name) ke execute ho sakta hu
		 * Mujhe koi refer karwane ki zarurat nhi hai.
		 * 
		 * */
		staticMethod(2,3);
		StaticVsInstance Y = X;
		op(staticData);
		staticData = 15;
		op(staticData); // This remains changed for all other objects that
							// we will create.
	}
}
