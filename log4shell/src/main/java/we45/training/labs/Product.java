package we45.training.labs;

public class Product{
	String name;
	String description;
	Integer quantity;
	
	public void setName(String name) {
		this.name = name;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	public String getName() {
		return name;
	}
	public String getDescription() {
		return description;
	}
	public Integer getQuantity() {
		return quantity;
	}
}