package com.blackmanatee.nacsac;

import java.util.ArrayList;
import static com.blackmanatee.rawtest.RawTest.echo;

public class Sac extends TagNac{
	private ArrayList<String> bag;
	private static Stack dump;

	public Sac() {
		super();
		setType("sac");
		bag = new ArrayList<>();
	}
	
	public Sac(String[] s,Stack t) {
		super();
		setType("sac");
		bag = new ArrayList<>();
		dump = t;
		for(String i:s){
			add(i);
		}
	}
	
	public Sac(String n,String[] s,Stack t){
		super();
		setName(n);
		setType("sac");
		bag = new ArrayList<>();
		dump = t;
		for(String i:s){
			add(i);
		}
	}
	
	public Sac(String nl,Stack t){
		super();
		setType("sac");
		bag = new ArrayList<>();
		dump = t;
		for(String s:nl.split(";")){
			add(s);
		}
	}
	
	public Sac(String n,String nl,Stack t){
		super();
		setName(n);
		setType("sac");
		bag = new ArrayList<>();
		dump = t;
		for(String s:nl.split(";")){
			add(s);
		}
	}
	
	private void updateData() {
		String d = "";
		for(String n:bag){
			d += ";"+n;
		}
		super.setData(d.substring(1));
	}
	
	public void setStack(Stack s){
		dump = s;
	}
	
	public void add(String n) {
		if((!bag.contains(n))
			&& dump != null
			&& dump.readNac(n) != null) {
			bag.add(n);
			updateData();
		}
	}
	
	public void add(Nac n){
		if(!bag.contains(n.getName())) {
			dump.createNac(n);
			bag.add(n.getName());
			updateData();
		}
	}
	
	public Nac get(String n) {
		if(bag.contains(n))
			return dump.readNac(n);
		return null;
	}
	
	public void remove(String n) {
		bag.remove(n);
		updateData();
	}
	
	public void update(Nac n) {
		dump.updateNac(n);
		updateData();
	}
	
	public int count() {
		return bag.size();
	}
	
	@Override
	public boolean equals(Object o){
		if(!(o instanceof Sac)){
			echo(o+":Not Sac",1);
			return false;
		}
		Sac b = (Sac)o;
//		if(bag.size() != b.count()){
//			//echo("Count mismatch");
//			return false;
//		}
//		for(String n:bag){
//			Nac i = b.get(n);
//			if(i == null || !n.equals(i.getName())){
//				/*if(i == null)
//					echo("Nac not found");
//				else
//					echo("Nac mismatch");*/
//				return false;
//			}
//		}
		return super.equals(o);
	}

	@Override
	public void setData(String data)
	{
		for(String s:data.split(";")){
			add(s);
		}
	}
}
