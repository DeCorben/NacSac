package com.blackmanatee.nacsac;

import java.util.ArrayList;
import static com.blackmanatee.rawtest.RawTest.echo;

public class Sac {
	private ArrayList<String> bag;
	private static Stack dump;

	public Sac() {
		bag = new ArrayList<>();
	}
	
	public Sac(String[] s,Stack t) {
		bag = new ArrayList<>();
		dump = t;
		for(String i:s){
			bag.add(i);
		}
	}
	
	public void setStack(Stack s){
		dump = s;
	}
	
	public void add(String n) {
		if((!bag.contains(n))
			&& dump != null
			&& dump.readNac(n) != null)
			bag.add(n);
	}
	
	public void add(Nac n){
		if(!bag.contains(n.getName()))
			dump.createNac(n);
			bag.add(n.getName());
	}
	
	public Nac get(String n) {
		if(bag.contains(n))
			return dump.readNac(n);
		return null;
	}
	
	public void remove(String n) {
		bag.remove(n);
	}
	
	public void update(Nac n) {
		dump.updateNac(n);
	}
	
	public int count() {
		return bag.size();
	}
	
	@Override
	public boolean equals(Object o){
		if(!(o instanceof Sac)){
			//echo(o+":Not Sac");
			return false;
		}
		Sac b = (Sac)o;
		if(bag.size() != b.count()){
			//echo("Count mismatch");
			return false;
		}
		for(String n:bag){
			Nac i = b.get(n);
			if(i == null || !n.equals(i.getName())){
				/*if(i == null)
					echo("Nac not found");
				else
					echo("Nac mismatch");*/
				return false;
			}
		}
		return true;
	}
}
