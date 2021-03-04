
import java.io.Serializable;
import java.util.*;
import java.sql.Date;
/**
 * @author shivam singh
 *
 */
public class Movie implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int movieID;
	private String movieName;
	private Category movieCat;
	private Language movieLang;
	private Date releaseDate;
	ArrayList<String> cast;
	private double movieRating;
	private double totalBusiness;
	
	Movie()
	{
		movieID=0;
		movieName="";
		movieCat=new Category();
		movieLang=new Language();
		cast=new ArrayList<String>();
		movieRating=totalBusiness=0.0;
	}

	public int getMovieID() {
		return movieID;
	}

	public void setMovieID(int movieID) {
		this.movieID = movieID;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public Category getMovieCat() {
		return movieCat;
	}

	public void setMovieCat(Category movieCat) {
		this.movieCat = movieCat;
	}

	public Language getMovieLang() {
		return movieLang;
	}

	public void setMovieLang(Language movieLang) {
		this.movieLang = movieLang;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public ArrayList<String> getCast() {
		return cast;
	}

	public void setCast(ArrayList<String> cast) {
		this.cast = cast;
	}

	public double getMovieRating() {
		return movieRating;
	}

	public void setMovieRating(double movieRating) {
		this.movieRating = movieRating;
	}

	public double getTotalBusiness() {
		return totalBusiness;
	}

	public void setTotalBusiness(double totalBusiness) {
		this.totalBusiness = totalBusiness;
	}
		
}
