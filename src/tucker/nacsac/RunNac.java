package tucker.nacsac;

public class RunNac extends RawTest
{
	private static Nac fact;
	private static Stack stack;
	
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
	
	private void phaseFour() {
		//Phase Four: links
	}
	
	private void phaseThree() {
		//Phase Three: Sac
	}

	private void phaseTwo() {
		//Phase Two: Stack
		//Stack.equals
		stack =  new Stack(new Nac[] {new Nac("one","string","lorem"),
				new Nac("two","number","3.1419"),
				new Nac("ipsum","string","dolor")});
		check(new Stack(new Nac[] {new Nac("one","string","lorem"),
				new Nac("two","number","3.1419"),
				new Nac("ipsum","string","dolor")}).equals(stack));
		check(!new Stack().equals(stack));
		test();
		
		//Stack.readNac
		stack = new Stack(new Nac[] {new Nac("lorem","string","ipsum"),new Nac("dolor","string","sit"),new Nac("amet","string","consectetuer")});
		check(new Nac("dolor","string","sit").equals(stack.readNac("dolor")));
		check(new Nac("amet","string","consectetuer").equals(stack.readNac("amet")));
		check(new Nac("lorem","string","ipsum").equals(stack.readNac("lorem")));
		check(stack.readNac("decoy") == null);
		test();
		
		//Stack.createNac
		stack = new Stack();
		stack.createNac(new Nac("lorem","string","ipsum"));
		check(new Stack(new Nac[] {new Nac("lorem","string","ipsum")}).equals(stack));
		stack.createNac(new Nac("lorem","number","3.1419"));
		check(new Stack(new Nac[] {new Nac("lorem","string","ipsum")}).equals(stack));
		test();
		
		//Stack.removeNac
		stack = new Stack(new Nac[] {new Nac("lorem","string","ipsum"),new Nac("dolor","string","sit"),new Nac("amet","string","consectetuer")});
		stack.deleteNac("dolor");
		check(new Stack(new Nac[] {new Nac("lorem","string","ipsum"),new Nac("amet","string","consectetuer")}).equals(stack));
		stack.deleteNac("amet");
		check(new Stack(new Nac[] {new Nac("lorem","string","ipsum")}).equals(stack));
		stack.deleteNac("lorem");
		check(new Stack().equals(stack));
		test();
		
		//Stack.updateNac
		stack = new Stack(new Nac[] {new Nac("lorem","string","ipsum")});
		stack.updateNac(new Nac("lorem","number","3.1419"));
		check(new Stack(new Nac[] {new Nac("lorem","number","3.1419")}).equals(stack));
		test();
	}
	
	private void phaseOne() {
		//Phase One: Nac
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
		stack = null;
	}
}
