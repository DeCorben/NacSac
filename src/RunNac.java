import com.blackmanatee.nacsac.*;
import com.blackmanatee.rawtest.*;
import java.io.*;

public class RunNac extends RawTest
{
	private static Nac fact;
	private static Stack stack;
	private static Sac sac;
	
	public static void main(String[] args)
	{
		new RunNac();
	}
	
	public RunNac() {
		threshold = 2;
		setTest(1);
		phaseOne();
		phaseTwo();
		phaseThree();
		phaseFour();
		phaseFive();
		phaseSix();
		phaseSeven();
		phaseEight();
		phaseNine();
		phaseTen();
	}
	
	//JSON Object input/output
	
	//merge Sac and merge Stack
	
	private void phaseTen(){
		//refine stackless Sac functioning
		startPhase("Stackless Sacs:");
		//Sac.equals
		stack = new Stack(new Nac[] {new Nac("lorem","string","ipsum"),
			new Nac("dolor","string","sit"),
			new Nac("amet","string","consectetuer")});
		sac = new Sac(new Nac[] {new Nac("lorem","string","ipsum"),
			new Nac("dolor","string","sit"),
			new Nac("amet","string","consectetuer")});
		Sac comp = new Sac(new Nac[] {new Nac("lorem","string","ipsum"),
			new Nac("dolor","string","sit"),
			new Nac("amet","string","consectetuer")});
		boolean b = sac.equals(comp);
		check(b);
		check(!sac.equals("not sac"));
		check(!sac.equals(new Sac(new Nac[] {new Nac("lorem","string","ipsum"),
									  new Nac("dolor","string","sit")})));
		check(!sac.equals(new Sac(new Nac[] {new Nac("lorem","string","ipsum"),
									  new Nac("dolor","string","sit"),
									  new Nac("adipiscing","string","maecenas")})));
		//should stackless Sac equal stacked w/ identical Nacs? -> yes
		check(sac.equals(new Sac(new String[] {"lorem","dolor","amet"},stack)));
		check(!new Sac("foo",new Nac[]{new Nac("lorem","string","ipsum"),
						  new Nac("dolor","string","sit"),
						  new Nac("amet","string","consectetuer")}).equals(sac));
		sac.setName("foo");
		check(new Sac("foo",new Nac[]{new Nac("lorem","string","ipsum"),
						  new Nac("dolor","string","sit"),
						  new Nac("amet","string","consectetuer")}).equals(sac));
		test();

		//Sac.add
		sac =  new Sac();
		sac.add(new Nac("lorem","string","ipsum"));
		check(new Sac(new Nac[]{new Nac("lorem","string","ipsum")}).equals(sac));
		test();

		//Sac.get
		sac = new Sac(new Nac[] {new Nac("amet","string","consectetuer")});
		check(new Nac("amet","string","consectetuer").equals(sac.get("amet")));
		test();

		//Sac.remove
		sac = new Sac(new Nac[] {new Nac("lorem","string","ipsum"),
			new Nac("dolor","string","sit")});
		sac.remove("dolor");
		check(new Sac(new Nac[] {new Nac("lorem","string","ipsum")}).equals(sac));
		test();

		//Sac.update
		sac = new Sac(new Nac[] {new Nac("lorem","string","ipsum"),
			new Nac("dolor","string","sit"),
			new Nac("amet","string","consectetuer")});
		sac.update(new Nac("dolor","number","3.1419"));
		check(new Nac("dolor","number","3.1419").equals(sac.get("dolor")));
		test();

		//Sac.count
		sac = new Sac();
		check(sac.count() == 0);
		sac.add(new Nac("lorem","string","ipsum"));
		sac.add(new Nac("dolor","string","sit"));
		sac.add(new Nac("amet","string","consectetuer"));
		check(sac.count() == 3);
		sac.remove("amet");
		check(sac.count() == 2);
		test();
	}
	
	private void phaseNine(){
		//JSON input
		startPhase("JSON String Input");
		//Nac constructor
		fact = new Nac("lorem","string","ipsum");
		check(new Nac(fact.toString()).equals(fact));
		test();
		//TagNac Constructor
		fact = new TagNac("lorem","string","ipsum","dolor;sit");
		check(new TagNac(fact.toString()).equals(fact));
		test();
		//Sac Constructor
		stack = new Stack(new Nac[]{new Nac("lorem","string","ipsum"),
							  new Nac("dolor","string","sit")});
		sac = new Sac("amet","lorem;dolor",stack);
		check(new Sac(sac.toString()).equals(sac));
		test();
		//Stack.add
		stack = new Stack();
		stack.createNac(new Nac("lorem","string","ipsum").toString());
		check(new Stack(new Nac[]{new Nac("lorem","string","ipsum")}).equals(stack));
		test();
		//Stack.update
		stack = new Stack(new Nac[]{new Nac("lorem","string","ipsum")});
		stack.updateNac(new Nac("lorem","string","sit"));
		check(new Stack(new Nac[]{new Nac("lorem","string","sit")}).equals(stack));
		test();
		//Sac.add
		stack = new Stack(new Nac[]{new Nac("lorem","string","ipsum")});
		sac = new Sac();
		sac.setStack(stack);
		sac.add(stack.readNac("lorem").toString());
		check(new Sac("lorem",stack).equals(sac));
		test();
		//Sac.update
		stack = new Stack(new Nac[]{new Nac("lorem","string","ipsum")});
		sac = new Sac("lorem",stack);
		sac.update(new Nac("lorem","string","amet").toString());
		check(new Stack(new Nac[]{new Nac("lorem","string","amet")}).equals(stack));
	}
	
	private void phaseEight(){
		//JSON out
		startPhase("JSON string output");
		//Nac
		check("{\"name\":\"lorem\",\"type\":\"string\",\"data\":\"ipsum\"}".equals(new Nac("lorem","string","ipsum").toString()));
		test();
		//Stack
		String buf = new Stack(new Nac[]{new Nac("lorem","string","ipsum"),new Nac("dolor","string","sit"),new Nac("amet","string","consectetuer")}).toString();
		echo(buf,1);
		check("{\"pile\":[{\"name\":\"amet\",\"type\":\"string\",\"data\":\"consectetuer\"},{\"name\":\"dolor\",\"type\":\"string\",\"data\":\"sit\"},{\"name\":\"lorem\",\"type\":\"string\",\"data\":\"ipsum\"}]}".equals(buf));
		test();
		//Sac
		stack = new Stack(new Nac[]{new Nac("lorem","string","ipsum"),new Nac("dolor","string","sit"),new Nac("amet","string","consectetuer")});
		check("{\"name\":\"scrot\",\"type\":\"sac\",\"data\":\"lorem;dolor;amet\"}".equals(new Sac("scrot","lorem;dolor;amet",stack).toString()));
		test();
		//stackless Sac
		buf = new Sac("scrot",new Nac[]{new Nac("lorem","string","ipsum"),new Nac("dolor","string","sit"),new Nac("amet","string","consectetuer")}).toString();
		echo(buf,1);
		check("{\"name\":\"scrot\",\"type\":\"sac\",\"data\":\"{\"name\":\"lorem\",\"type\":\"string\",\"data\":\"ipsum\"};{\"name\":\"dolor\",\"type\":\"string\",\"data\":\"sit\"};{\"name\":\"amet\",\"type\":\"string\",\"data\":\"consectetuer\"}\"}".equals(buf));
		test();
		//TagNac
		check("{\"name\":\"lorem\",\"type\":\"string\",\"data\":\"ipsum\",\"tags\":\"dolor;sit;amet\"}".equals(new TagNac("lorem","string","ipsum","dolor;sit;amet").toString()));
		test();
	}
	
	private void phaseSeven(){
		//Phase: Sac to inherit TagNac
		startPhase("Nac based Sac:");
		//new equals
		stack = new Stack();
		stack.createNac(new TagNac("lorem","string","ipsum",new String[]{"test"}));
		stack.createNac(new TagNac("dolor","string","sit",new String[]{"test"}));
		stack.createNac(new Sac("amet",new String[]{"lorem","dolor"},stack));
		check(new Sac("amet",new String[]{"lorem","dolor"},stack).equals(stack.readNac("amet")));
		stack.createNac(new TagNac("consectetuer","string","maecenas",new String[]{}));
		check(!new Sac(new String[]{"lorem","dolor"},stack).equals(stack.readNac("amet")));
		check(!new Sac("amet",new String[]{"lorem","consecteuer"},stack).equals(stack.readNac("amet")));
		test();
	}
	
	private void phaseSix(){
		//phase: splitable Strings as alternative to array in constructors
		startPhase("splitable String constructors");
		//TagNac
		check(new TagNac("lorem","string","ipsum",new String[]{"dolor","sit"}).equals(new TagNac("lorem","string","ipsum","dolor;sit")));
		check(new TagNac(new String[]{"lorem","ipsum"}).equals(new TagNac("lorem;ipsum")));
		test();
		//Sac
		stack = new Stack(new Nac[]{
			new Nac("lorem","string","ipsum"),
			new Nac("dolor","string","sit")
		});
		check(new Sac(new String[]{"lorem","dolor"},stack).equals(new Sac("lorem;dolor",stack)));
		check(new Sac("amet",new String[]{"lorem","dolor"},stack).equals(new Sac("amet","lorem;dolor",stack)));
		test();
	}
	
	private void phaseFive(){
		//Phase Five: tags for Nac
		startPhase("TagNac:");
		//equals TagNac
		fact= new TagNac("lorem","string","ipsum",new String[]{"dolor","sit"});
		check(new TagNac("lorem","string","ipsum",new String[]{"dolor","sit"}).equals(fact));
		check(!new TagNac().equals(fact));
		check(!fact.equals(new TagNac("lorem","string","amet",new String[]{"dolor","sit"})));
		check(!fact.equals(new TagNac("lorem","string","ipsum",new String[]{"dolor","sit","amet"})));
		check(!fact.equals(new TagNac("lorem","string","ipsum",new String[]{"dolor","amet"})));
		check(!fact.equals("Not Tagnac"));
		test();
		
		//addTag
		fact = new TagNac();
		((TagNac)fact).addTag("lorem");
		check(new TagNac(new String[]{"lorem"}).equals(fact));
		((TagNac)fact).addTag("lorem");
		check(new TagNac(new String[]{"lorem"}).equals(fact));
		test();
		
		//removeTag
		fact = new TagNac(new String[]{"lorem","ipsum"});
		((TagNac)fact).removeTag("lorem");
		check(new TagNac(new String[]{"ipsum"}).equals(fact));
		((TagNac)fact).removeTag("ipsum");
		check(new TagNac().equals(fact));
		test();
	}
	
	private void phaseFour() {
		//Phase Four: throughput adds for Sac
		startPhase("Sac to Stack add:");
		//add through Sac
		stack = new Stack();
		sac = new Sac();
		sac.setStack(stack);
		sac.add(new Nac("lorem","string","ipsum"));
		check(new Stack(new Nac[]{new Nac("lorem","string","ipsum")}).equals(stack));
		test();
	}
	
	private void phaseThree() {
		startPhase("Sac");
		//Sac.equals
		stack = new Stack(new Nac[] {new Nac("lorem","string","ipsum"),
							  new Nac("dolor","string","sit"),
							  new Nac("amet","string","consectetuer"),
							  new Nac("adipiscing","string","maecenas")});
		sac = new Sac(new String[] {"lorem","dolor","amet"},stack);
		Sac comp = new Sac(new String[] {"lorem","dolor","amet"},stack);
		boolean b = sac.equals(comp);
		check(b);
		check(!sac.equals("not sac"));
		check(!sac.equals(new Sac(new String[] {"lorem","dolor"},stack)));
		check(!sac.equals(new Sac(new String[] {"lorem","dolor","adipiscing"},stack)));
		test();
		
		//Sac.add
		stack = new Stack(new Nac[] {new Nac("lorem","string","ipsum"),
							  new Nac("dolor","string","sit"),
							  new Nac("amet","string","consectetuer")});
		sac =  new Sac();
		sac.add("lorem");
		check(new Sac(new String[]{"lorem"},stack).equals(sac));
		test();
		
		//Sac.get
		stack = new Stack(new Nac[] {new Nac("lorem","string","ipsum"),
				new Nac("dolor","string","sit"),
				new Nac("amet","string","consectetuer")});
		sac = new Sac(new String[] {"amet"},stack);
		check(new Nac("amet","string","consectetuer").equals(sac.get("amet")));
		test();
		
		//Sac.remove
		stack = new Stack(new Nac[] {new Nac("lorem","string","ipsum"),
							  new Nac("dolor","string","sit"),
							  new Nac("amet","string","consectetuer")});
		sac = new Sac(new String[] {"lorem","dolor"},stack);
		sac.remove("dolor");
		check(new Sac(new String[] {"lorem"},stack).equals(sac));
		test();
		
		//Sac.update
		stack = new Stack(new Nac[] {new Nac("lorem","string","ipsum"),
				new Nac("dolor","string","sit"),
				new Nac("amet","string","consectetuer")});
		sac = new Sac(new String[] {"lorem","dolor","amet"},stack);
		sac.update(new Nac("dolor","number","3.1419"));
		check(new Stack(new Nac[] {new Nac("lorem","string","ipsum"),
				new Nac("dolor","number","3.1419"),
				new Nac("amet","string","consectetuer")}).equals(stack));
		test();
		
		//Sac.count
		stack = new Stack(new Nac[] {new Nac("lorem","string","ipsum"),
				new Nac("dolor","string","sit"),
				new Nac("amet","string","consectetuer")});
		sac = new Sac();
		check(sac.count() == 0);
		sac.add("lorem");
		sac.add("dolor");
		sac.add("amet");
		check(sac.count() == 3);
		sac.remove("amet");
		check(sac.count() == 2);
		test();
	}

	private void phaseTwo() {
		startPhase("Stack");
		//Stack.equals
		stack =  new Stack(new Nac[] {new Nac("one","string","lorem"),
				new Nac("two","number","3.1419"),
				new Nac("ipsum","string","dolor")});
		check(new Stack(new Nac[] {new Nac("one","string","lorem"),
				new Nac("two","number","3.1419"),
				new Nac("ipsum","string","dolor")}).equals(stack));
		check(!new Stack().equals(stack));
		check(!stack.equals("not a stack"));
		check(!stack.equals(new Stack(new Nac[] {new Nac("one","string","lorem"),new Nac("two","number","22/7")})));
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
		startPhase("Nac");
		//Nac equals
		fact = new Nac();
		fact.setName("lorem");
		fact.setType("string");
		fact.setData("ipsum");
		check(new Nac("lorem","string","ipsum").equals(fact));
		check(!fact.equals("not nac"));
		check(!fact.equals(new Nac("dolor","string","ipsum")));
		check(!fact.equals(new Nac("lorem","link","ipsum")));
		check(!fact.equals(new Nac("lorem","string","dolor")));
	
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
		sac = null;
	}
}
