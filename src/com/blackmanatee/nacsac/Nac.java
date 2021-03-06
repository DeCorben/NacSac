package com.blackmanatee.nacsac;
import static com.blackmanatee.rawtest.RawTest.echo;
import org.json.*;

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
	
	public Nac(String j){
		//JSON string parser
		try
		{
			JSONObject json = new JSONObject(j);
			name = json.getString("name");
			type = json.getString("type");
			data = json.getString("data");
		}
		catch (JSONException e)
		{
			e.printStackTrace();
			name= "";
			type = "";
			data = "";
		}
	}
	
	@Override
	public boolean equals(Object o) {
		if(!(o instanceof Nac)){
			echo("Not a Nac",10);
			return false;
		}
		Nac test = (Nac)o;
		if(!name.equals(test.getName())){
			echo("Unmatched name",10);
			return false;
		}
		if(!type.equals(test.getType())){
			echo("Unmatched type",10);
			return false;
		}
		if(!data.equals(test.getData())){
			echo("Unmatched data:"+data+"]["+test.getData(),10);
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

	@Override
	public String toString(){
		return "{\"name\":\""+name+"\",\"type\":\""+type+"\",\"data\":\""+data+"\"}";
	}
}
