package gdb.entity;

import gdb.Vertex;

public class Customer extends Vertex{

	public int region;
	public int ageGroup;
	
	
	public Customer(int id, String name, String type, int location, int age) {
		super(id, name, type);
		this.region = location;
		this.ageGroup = age;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		if(!(this.id==other.id && this.type.equalsIgnoreCase(other.type)))
				return false;
		return true;
		
    }
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (id ==null? 0 : id.hashCode());
	    result = prime * result + (type ==null? 0 : type.hashCode());
		
		return result;
	}
	

}
