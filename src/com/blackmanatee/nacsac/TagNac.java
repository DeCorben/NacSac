package com.blackmanatee.nacsac;
import java.util.*;

public class TagNac extends Nac{
	private ArrayList<String> tags;
	
	public TagNac(){
		super();
		tags = new ArrayList<>();
	}
	
	public TagNac(String[] t){
		super();
		tags = new ArrayList<>();
		for(String s:t){
			addTag(s);
		}
	}
	
	public TagNac(String n,String t,String v,String[] a){
		super(n,t,v);
		tags = new ArrayList<>();
		for(String s:a){
			addTag(s);
		}
	}
	
	public void addTag(String t){
		if(!tags.contains(t))
			tags.add(t);
	}
	
	public void removeTag(String t){
		tags.remove(t);
	}
	
	public boolean hasTag(String t){
		return tags.contains(t);
	}
	
	public int tagCount(){
		return tags.size();
	}

	@Override
	public boolean equals(Object o)
	{
		if(!(o instanceof TagNac))
			return false;
		TagNac c = (TagNac)o;
		if(c.tagCount() != tags.size())
			return false;
		for(String t:tags){
			if(!c.hasTag(t))
				return false;
		}
		return super.equals(o);
	}
}
