package com.blackmanatee.nacsac;

import java.util.HashMap;

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
	
	public int nacCount() {
		return pile.size();
	}
	
	public void createNac(Nac n) {
		if(!pile.containsKey(n.getName())) {
			pile.put(n.getName(),n);
		}
	}
	
	public Nac readNac(String n) {
		return pile.get(n);
	}
	
	public void updateNac(Nac n) {
		pile.put(n.getName(), n);
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
