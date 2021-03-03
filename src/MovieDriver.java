import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;







public class MovieDriver {
	ArrayList<Movie> al;
	BufferedReader br;
	Connection con;
	
	MovieDriver() throws SQLException
	{
		al=new ArrayList<Movie>();
		br=new BufferedReader(new InputStreamReader(System.in));
		con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","scott","tiger");
	}

	public static void main(String[] args) throws NumberFormatException, IOException, SQLException {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
		
		MovieDriver driver = new MovieDriver();
		driver.userMenu();

	}

	private void userMenu() throws NumberFormatException, IOException {
		for(; ;)
		{
			System.out.println("1. Insert Into DataBase\n2. Add a New Movie\n3. Serialize the Movies\n4. Deserialize the "
					+ "Movie\n5. Find Movies By Year\n6. Find Movies By Actor\n7. Update Movie Rating\n8."
					+ " Update Business Done by movie\n9. Map Languages and Movies\n10. Exit");
			int opt=Integer.parseInt(br.readLine());
			if(opt==10)
				break;
			switch(opt)
			{
				case 1: this.insertMoviesInDB();break;
				
				case 2: this.inputAddNewMovie();break;
				
				case 3: this.addMovieToFile();break;
				
				case 4: this.readMovieFromFile();break;
				
				case 5: System.out.println("Enter Year");
						ArrayList<Movie> al= this.findMoviesByYear(br.readLine());this.displayMovieList(al);
						break;
						
				case 6: System.out.println("Enter Actor");
				 	    ArrayList<Movie> al1= this.findMoviesByYear(br.readLine());this.displayMovieList(al1);
				 	    break;
				 	    
				case 7:this.inputUpdateMovieRating();break;
				
				case 8:this.inputUpdateBusiness();break;
				
				case 9: System.out.println("Enter Amount");
						Map<Language, Set<Movie>> mp= this.getMoviesByBusiness(Double.parseDouble(br.readLine()));
						break;
						
				default : this.inputAddNewMovie();break;
			}
		}
		
	}

	private void inputUpdateBusiness() {
		// TODO Auto-generated method stub
		
	}

	private Map<Language, Set<Movie>> getMoviesByBusiness(double parseDouble) {
		// TODO Auto-generated method stub
		return null;
	}

	private void inputUpdateMovieRating() {
		// TODO Auto-generated method stub
		
	}

	private void displayMovieList(ArrayList<Movie> al1) {
		// TODO Auto-generated method stub
		
	}

	private ArrayList<Movie> findMoviesByYear(String readLine) {
		// TODO Auto-generated method stub
		return null;
	}

	private void readMovieFromFile() {
		// TODO Auto-generated method stub
		
	}

	private void addMovieToFile() {
		// TODO Auto-generated method stub
		
	}

	private void inputAddNewMovie() {
		// TODO Auto-generated method stub
		
	}

	private void insertMoviesInDB() {
		// TODO Auto-generated method stub
		
	}

}
