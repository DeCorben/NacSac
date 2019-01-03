package com.blackmanatee.nacsac;
import java.util.*;
import static com.blackmanatee.rawtest.RawTest.echo;
import org.json.*;

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
		String list = "";
		//determine if tl is a taglist or Json string:
		if(tl.startsWith("{")){
			try{
				JSONObject json = new JSONObject(tl);
				setName(json.getString("name"));
				setType(json.getString("type"));
				setData(json.getString("data"));
				list = json.getString("tags");
			}
			catch(JSONException ex){
				ex.printStackTrace();
				echo("list:"+list,10);
			}
		}
		else
			list = tl;
		//stacked sacs cause this code to think that the data semicolons are for tags
		if(list != null && list.length() > 0)
			for(String t:list.split(";")){
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
	public String toString(){
		if(tags.size() == 0)
			return super.toString();
		else{
			String sup = super.toString();
			String ta = "";
			for(String t:tags){
				ta += ";"+t;
			}
			return sup.substring(0,sup.length()-1)+",\"tags\":\""+ta.substring(1)+"\"}";
		}
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
			echo("Tag array size unmatched:"+tags.size()+"]["+c.tagCount(),1);
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
