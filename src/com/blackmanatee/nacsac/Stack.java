package com.blackmanatee.nacsac;

import java.util.HashMap;
import static com.blackmanatee.rawtest.RawTest.echo;
import java.util.*;

public class Stack {
	private HashMap<String,Nac> pile;
	
	public Stack() {
		pile = new HashMap<>();
	}
	
	public Stack(Nac[] n) {
		pile = new HashMap<>();
		for(Nac o:n) {
			createNac(o);
		}
	}
	
	public ArrayList<String> nacList(){
		ArrayList<String> l = new ArrayList<>();
		for(String n:pile.keySet()){
			l.add(n);
		}
		return l;
	}
	
	public void merge(Stack m){
		for(String n:m.nacList()){
			createNac(m.readNac(n));
		}
	}
	
	public int nacCount() {
		return pile.size();
	}
	
	public void createNac(Nac n) {
		if(!pile.containsKey(n.getName())) {
			pile.put(n.getName(),n);
		}
	}
	
	public void createNac(String j){
		createNac(new TagNac(j));
	}
	
	public Nac readNac(String n) {
		return pile.get(n);
	}
	
	public void updateNac(Nac n) {
		pile.put(n.getName(), n);
	}
	
	public void updateNac(String j){
		echo(j,9);
		updateNac(new TagNac(j));
	}
	
	public void deleteNac(String n) {
		pile.remove(n);
	}
	
	@Override
	public boolean equals(Object o) {
		if(!(o instanceof Stack))
			return false;
		Stack s = (Stack)o;
		if(s.nacCount() != pile.size())
			return false;
		for(String n:pile.keySet()) {
			if(!pile.get(n).equals(s.readNac(n)))
				return false;
		}
		return true;
	}

	@Override
	public String toString()
	{
		String o = "";
		for(String n:pile.keySet()){
			o += ","+pile.get(n).toString();
		}
		return "{\"pile\":["+o.substring(1)+"]}";
	}
	
}
