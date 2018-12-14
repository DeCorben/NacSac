package com.blackmanatee.nacsac;

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
		if(!(o instanceof Nac))
			return false;
		Nac test = (Nac)o;
		if(!name.equals(test.getName()))
			return false;
		if(!type.equals(test.getType()))
			return false;
		if(!data.equals(test.getData()))
			return false;
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