import java.io.Serializable;

public class Category implements Serializable  {
	
	
	private static final long serialVersionUID = 1L;
	
	private int categoryID;
	private String categoryName;
	
	Category()
	{
		categoryID=0;
		categoryName="";
	}

	
	//setter methods
	public void setCatID(int catID) {
		this.categoryID = catID;
	}
	
	public void setCatName(String catName) {
		this.categoryName = catName;
		if(catName.equalsIgnoreCase("horror"))
			this.setCatID(2);
		if(catName.equalsIgnoreCase("thriller"))
			this.setCatID(1);
		if(catName.equalsIgnoreCase("drama"))
			this.setCatID(3);
	}
	
	//getter methods
	public int getCatID() {
		return categoryID;
	}

	public String getCatName() {
		return categoryName;
	}
	

	
	
}
