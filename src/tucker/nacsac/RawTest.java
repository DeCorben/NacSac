package tucker.nacsac;

public abstract class RawTest {
	private static boolean pass;
	private static int testNum;
	
	public static void echo(String e) {
		System.out.println(e);
	}
	
	public void check(boolean b) {
		pass = pass && b;
	}
	
	public void test() {
		if(pass)
			echo("Test number "+testNum+": passed");
		else
			echo("Test number "+testNum+": failed");
		testNum++;
		pass = true;
		resetTest();
	}
	
	public void setTest(int i) {
		testNum = i;
	}
	
	abstract protected void resetTest();
}
