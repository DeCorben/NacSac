package com.blackmanatee.nacsac;

import java.util.ArrayList;
import static com.blackmanatee.rawtest.RawTest.echo;
import org.json.*;

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
		if(nl.startsWith("{")){
			try{
				JSONObject json = new JSONObject(nl);
				setName(json.getString("name"));
				setData(json.getString("data"));
			}
			catch(JSONException ex){
				//ex.printStackTrace();
			}
		}
		else{
			for(String s:nl.split(";")){
				add(s);
			}
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
		//stacked sacs don't get a stack from this
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
			String inName = n;
		if(n.startsWith("{"))
			inName = new TagNac(n).getName();
		if(!bag.contains(inName)){
			if(dump != null){
				echo("Dump not null",11);
				if(dump.readNac(inName) != null)
					bag.add(inName);
				else
					echo("Stack read failed",11);
			}
			else{
				echo("Raw add for stackless",11);
				bag.add(n);
			}
			updateData();
		}
	}
	
	public void add(Nac n){
		if(!bag.contains(n.getName())) {
			if(dump != null){
				echo("Dump populated",7);
				dump.createNac(n);
				bag.add(n.getName());
			}
			else{
				echo("Stackless add",7);
				bag.add(n.toString());
			}
			updateData();
		}
	}
	
	public Nac get(String n) {
		//will fail if n is JSON string and this is a stacked Sac
		if(bag.contains(n)){
			if(dump == null)
				return new TagNac(bag.get(bag.indexOf(n)));
			else
				return dump.readNac(n);
		}
		for(String i:bag){
			if(i.startsWith("{\"name\":\""+n+"\""))
				return new TagNac(i);
		}
		return null;
	}
	
	public void remove(String n) {
		if(bag.contains(n))
			bag.remove(n);
		else{
			for(String i:bag){
				if(i.startsWith("{\"name\":\""+n+"\""))
					bag.remove(i);
			}
		}
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
		if(dump != null){
			dump.updateNac(j);
			updateData();
		}
		else
			update(new TagNac(j));
	}
	
	public int count() {
		return bag.size();
	}
	
	@Override
	public boolean equals(Object o){
		if(!(o instanceof Sac)){
			echo(o+":Not Sac",10);
			return false;
		}
		Sac b = (Sac)o;
		if(!getName().equals(b.getName())){
			echo("Sac name unmatched",10);
			return false;
		}
		if(!getType().equals(b.getType())){
			echo("Sac type unmatched",10);
			return false;
		}
		if(bag.size() != b.count()){
			echo("Sac bag count differs",10);
			return false;
		}
		for(String i:bag){
			Nac n = get(i);
			if(!n.equals(b.get(n.getName()))){
				echo("Sac nacs not equal:"+n.getName(),10);
				return false;
			}
		}
		if(tagCount() != b.tagCount()){
			echo("Sac tac count differs",10);
			return false;
		}
		echo("Base tag list:"+getTags(),10);
		for(String t:getTags().split(";")){
			if(t.length()>0 && !b.hasTag(t)){
				echo("Sac has no tag:"+t,10);
				return false;
			}
		}
		return true;
	}

	@Override
	public String getData()
	{
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
	
	public void merge(Sac m){
		for(String n:m.nacList()){
			add(m.get(n));
		}
		updateData();
	}
	
	public ArrayList<String> nacList(){
		ArrayList<String> l = new ArrayList<>();
		for(String n:bag){
			if(n.startsWith("{"))
				l.add(new Nac(n).getName());
			else
				l.add(n);
		}
		return l;
	}
}
