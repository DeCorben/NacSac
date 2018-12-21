package com.blackmanatee.nacsac;
import java.util.*;
import static com.blackmanatee.rawtest.RawTest.echo;

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
	
	public TagNac(String tl){
		super();
		tags = new ArrayList<>();
		for(String t:tl.split(";")){
			addTag(t);
		}
	}
	
	public TagNac(String n,String t,String d,String tl){
		super(n,t,d);
		tags = new ArrayList<>();
		for(String s:tl.split(";")){
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
		if(!(o instanceof TagNac)){
			echo("Not a TagNac",1);
			return false;
		}
		TagNac c = (TagNac)o;
		if(c.tagCount() != tags.size()){
			echo("Tag array size unmatched",1);
			return false;
		}
		for(String t:tags){
			if(!c.hasTag(t)){
				echo("Unmatched tag",1);
				return false;
			}
		}
		return super.equals(o);
	}
}
