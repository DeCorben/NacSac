package com.blackmanatee.nacsac;

import java.util.ArrayList;
import static com.blackmanatee.rawtest.RawTest.echo;

public class Sac extends TagNac{
	private ArrayList<String> bag;
	private Stack dump;

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
	
	public Sac(Nac[] n){
		super();
		setType("sac");
		bag = new ArrayList<>();
		for(Nac z:n){
			add(z);
		}
	}
	
	public Sac(String d,Nac[] n){
		super();
		setName(d);
		setType("sac");
		bag = new ArrayList<>();
		for(Nac z:n){
			add(z);
		}
	}
	
	public Sac(String j){
		//stacked sacs don't geta stack from this
		super(j);
	}
	
	private void updateData() {
		if(dump == null){
			String buf = "";
			for(String n:bag){
				buf += ","+n;
			}
			super.setData("["+buf.substring(1)+"]");
		}
		else{
			String d = "";
			for(String n:bag){
				d += ";"+n;
			}
			super.setData(d.substring(1));
		}
	}
	
	public void setStack(Stack s){
		dump = s;
	}
	
	public void add(String n) {
		if(bag == null)
			bag = new ArrayList<>();
		if(!bag.contains(n)){
			if(dump != null){
				if(dump.readNac(n) != null)
					bag.add(n);
			}
			else{
				//iffy choice here
				bag.add(n);
			}
			updateData();
		}
	}
	
	public void add(Nac n){
		if(!bag.contains(n.getName())) {
			if(dump != null){
				echo("Dump populated",1);
				dump.createNac(n);
				bag.add(n.getName());
			}
			else{
				echo("Stackless add",1);
				bag.add(n.toString());
			}
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
		if(dump != null)
			dump.updateNac(n);
		else{
			for(String s:bag){
				Nac e = new Nac(s);
				if(e.getName().equals(n.getName())){
					bag.set(bag.indexOf(s),n.toString());
					break;
				}
			}
		}
		updateData();
	}
	
	public void update(String j){
		
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
		return super.equals(o);
	}

	@Override
	public String getData()
	{
		// TODO: Implement this method
		if(dump != null)
			return super.getData();
		String buf = "";
		for(String n:bag){
			buf += ","+n;
		}
		return "["+buf.substring(1)+"]";
	}

	@Override
	public void setData(String data)
	{
		if(data.startsWith("{")){
			String buf = data;
			while(buf.length() > 0){
				if(buf.startsWith(";"))
					buf = buf.substring(1);
				String obj = buf.substring(0,buf.indexOf('{'));
				buf = buf.substring(obj.length());
				add(obj);
			}
		}
		else{
			for(String s:data.split(";")){
				//semicolon splits won't work for nesting
				//may need to shake up inheritance
				add(s);
			}
		}
		updateData();
	}

	@Override
	public String toString()
	{
		String d = dump==null?getData():"\""+getData()+"\"";
		return "{\"name\":\""+getName()+"\",\"type\":\""+getType()+"\",\"data\":"+d+"}";
	}
}
