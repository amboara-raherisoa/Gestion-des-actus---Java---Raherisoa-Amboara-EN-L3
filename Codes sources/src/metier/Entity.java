package metier;

public abstract class Entity implements Comparable<Entity> {
	
	protected int id;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	@Override
	public boolean equals(Object obj) {
		return this.toString().equals(obj.toString());
	}
	
	@Override
	public int compareTo(Entity o) {
		return this.toString().compareTo(o.toString());
	}
	
}
