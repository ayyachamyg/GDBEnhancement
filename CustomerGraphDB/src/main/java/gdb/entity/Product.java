package gdb.entity;

import gdb.Vertex;

public class Product extends Vertex{


	public Product(int id, String name, String type) {
		super(id, name, type);
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
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
