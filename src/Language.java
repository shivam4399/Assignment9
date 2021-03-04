

import java.io.*;
/**
 * @author shivam singh
 *
 */
public class Language implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	private int languageID;
	private String languageName;
	
	Language()
	{
		languageID=0;
		languageName="";
	}

	Language(int id, String name)
	{
		this.languageID=id;
		this.languageName=name;
	}
	
	//setter methods
	public void setLangID(int langID) {
		this.languageID = langID;
	}

	
	public void setLangName(String langName) {
		this.languageName = langName;
		if(langName.equalsIgnoreCase("english"))
			this.setLangID(1);
		if(langName.equalsIgnoreCase("hindi"))
			this.setLangID(2);
		if(langName.equalsIgnoreCase("tamil"))
			this.setLangID(3);
	}
	
	//getter methods
	public int getLangID() {
		return languageID;
	}
	
	public String getLangName() {
		return languageName;
	}
	
	

}
