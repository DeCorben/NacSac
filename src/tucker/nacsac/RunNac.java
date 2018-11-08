package tucker.nacsac;

public class RunNac extends RawTest
{
	private static Nac fact;
	
	public static void main(String[] args)
	{
		new RunNac();
	}
	
	public RunNac() {
		setTest(1);
		resetTest();
		phaseOne();
		phaseTwo();
	}

	private void phaseTwo() {
		//
	}
	
	private void phaseOne() {
		//Nac equals
		fact = new Nac();
		fact.setName("lorem");
		fact.setType("string");
		fact.setData("ipsum");
		check(new Nac("lorem","string","ipsum").equals(fact));
		test();
		
		//Nac.getXXX
		fact = new Nac("pi","number","3.1419");
		check("pi".equals(fact.getName()));
		check("number".equals(fact.getType()));
		check("3.1419".equals(fact.getData()));
		test();
	}
	
	@Override
	protected void resetTest() {
		super.resetTest();
		fact = null;
	}
}
