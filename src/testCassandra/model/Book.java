package testCassandra.model;

import java.util.UUID;

public class Book {
	UUID id;
	String title;
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Book(UUID id, String title) {
		super();
		this.id = id;
		this.title = title;
	}
	
	
	
	

}
