package com.blackmanatee.nacsac;
import static com.blackmanatee.rawtest.RawTest.echo;

public class Nac{
	private String name,type,data;
	
	public Nac() {
		name = "";
		type = "";
		data = "";
	}
	
	public Nac(String n,String t,String d) {
		name = n;
		type = t;
		data = d;
	}
	
	@Override
	public boolean equals(Object o) {
		if(!(o instanceof Nac)){
			echo("Not a Nac");
			return false;
		}
		Nac test = (Nac)o;
		if(!name.equals(test.getName())){
			echo("Unmatched name");
			return false;
		}
		if(!type.equals(test.getType())){
			echo("Unmatched type");
			return false;
		}
		if(!data.equals(test.getData())){
			echo("Unmatched data:"+data+"]["+test.getData());
			return false;
		}
		return true;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
