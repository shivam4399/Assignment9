import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
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

	public static void main(String[] args) throws Exception {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
		
		
		
		MovieDriver driver = new MovieDriver();
		driver.userMenu();

	}
	public List<Movie> populateMovies(File file) throws FileNotFoundException, ParseException
	{
		List<Movie> list=new ArrayList<Movie>();	
		  Scanner sc=new Scanner(file);
		 // DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			String text[] = null;						
			while(sc.hasNextLine())
			{
				Movie m=new Movie();
				Language language= new Language();
				Category c = new Category();
				text=sc.nextLine().split(",");
				m.setMovieID(Integer.valueOf(text[0]));
				m.setMovieName(text[1]);
				language.setLangName(text[2]);
				m.setMovieLang(language);
				c.setCatName(text[3]);
				m.setMovieCat(c);
				ArrayList cast = new ArrayList();
				cast.add(text[4]);
				cast.add(text[5]);
				m.setCast(cast);
				m.setMovieRating(Double.valueOf(text[6]));
				m.setTotalBusiness(Double.valueOf(text[7]));
				String d=text[8];
				m.setReleaseDate(Date.valueOf(d));
				list.add(m);
				al.add(m);
				
			}
			
			return list;
	}

	private void userMenu() throws Exception {
		for(; ;)
		{
			System.out.println("1. Insert Into DtaBase\n2. Add a New Movie\n3. Serialize the Movies\n4. Deserialize the "
					+ "Movie\n5. Find Movies By Year\n6. Find Movies By Actor\n7. Update Movie Rating\n8."
					+ " Update Business Done by movie\n9. Map Languages and Movies\n10. populate from file");
			int opt=Integer.parseInt(br.readLine());
			if(opt==11)
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
				case 10:
						System.out.println("Population from file");
						File f=new File("C:\\Users\\shivam singh\\eclipse-workspace\\Ass9\\Movies_details.txt");
						this.populateMovies(f);
						System.out.println("Movie data populated from file");
						continue;
						
				
						
				default : this.inputAddNewMovie();break;
			}
		}
		
	}

	public boolean isMovieIDValid(int id)
	{
		Iterator<Movie> it= al.iterator();
		
		while(it.hasNext())
		{
			Movie ref=(Movie)it.next();
			if(ref.getMovieID()==id)
			{
				System.out.println("Movie with this ID Exists");
				return false;
			}
			
		}
		return true;
	
	}
	
	public void inputAddNewMovie()throws Exception
	{
		
		Movie ref=new Movie();
		
		Category cat=ref.getMovieCat();
		Language lang=ref.getMovieLang();
		
		System.out.println("Enter Movie ID");
		int id=Integer.parseInt(br.readLine());
		if(isMovieIDValid(id))
			ref.setMovieID(id);
		else
		{
			System.out.println("Movie with this ID Exists in List");
			return;
		}

		System.out.println("Enter Movie Name");
		ref.setMovieName(br.readLine());
		
		System.out.println("Enter Movie Category Name");
		cat.setCatName(br.readLine());
				
		System.out.println("Enter Movie Language");
		lang.setLangName(br.readLine());
		
		System.out.println("Enter Release Date in the format yyyy-mm-dd");
		ref.setReleaseDate(Date.valueOf(br.readLine()));
		
		System.out.println("Enter Movie rating");
		ref.setMovieRating(Double.parseDouble(br.readLine()));
		
		System.out.println("Enter Movie Business");
		ref.setTotalBusiness(Double.parseDouble(br.readLine()));
		
		ArrayList<String> cast=inputCastInformation();
		ref.setCast(cast);

		addNewMovie(ref);
		
	}
	
	public ArrayList<String> inputCastInformation()throws Exception
	{
		ArrayList<String> cast=new ArrayList<String>(); 
		for(int i=0;i<2;i++)
		{
			System.out.println("Enter Cast Name");
			String ip=br.readLine();
			cast.add(ip);
		}
		return cast;
	}
	
	public void addNewMovie(Movie m)
	{
		al.add(m);
		System.out.println("Movie added Successfully");
	}
	
	public void displayMovieList(ArrayList<Movie> al)
	{
		Iterator<Movie> it=al.iterator();
		
		while(it.hasNext())
		{
			Movie ref=(Movie)it.next();
			System.out.println(ref.getMovieID()+"     "+ref.getMovieName());
		}
	}
	
	public void addMovieToFile()throws Exception
	{
		
		FileOutputStream fos=new FileOutputStream("Movies.ser");
		ObjectOutputStream oos=new ObjectOutputStream(fos);
		
		oos.writeObject(al);
		
		System.out.println("Object Inserted Successfully");
		
		oos.close();
		fos.close();
		
	}
	
	public ArrayList<Movie> readMovieFromFile()
	{
		FileInputStream fis=null;
		ObjectInputStream ois=null;
			try
			{
				
				
				fis=new FileInputStream("Movies.ser");
				ois=new ObjectInputStream(fis);
		
				Object object=ois.readObject();
			    List<Object> objects3 = Collections.singletonList(object);
			    
			    System.out.println(objects3.size());

				for(int i=0;i<objects3.size();i++)
				{
					Movie m=(Movie)objects3.get(i);
					al.add(m);
				}
				
				
				ois.close();
				fis.close();
				
			}catch(Exception e){
				System.out.println();
				e.printStackTrace();}
			return al;
	}
	
	public void inputFindMoviesByYear()throws Exception
	{
		System.out.println("Enter Year");
		String year=br.readLine();
		
		findMoviesByYear(year);
	}
	
	public ArrayList<Movie> findMoviesByYear(String year)
	{
		Iterator<Movie> it=al.iterator();
		
		ArrayList<Movie> mal=new ArrayList<Movie>();
		
		while(it.hasNext())
		{
			Movie m=it.next();
			String [] s=m.getReleaseDate().toString().split("-");
			if(s[0].equalsIgnoreCase(year))
				mal.add(m);
		}
		return mal;
		
				
	}
	
	public void inputFindMoviesByActor()throws Exception
	{
		System.out.println("Enter Actor Name");
		String actor=br.readLine();
		
		findMoviesByActor(actor);
	}
	
	public ArrayList<Movie> findMoviesByActor(String actor)
	{
		Iterator<Movie> it=al.iterator();
		
		ArrayList<Movie> mal=new ArrayList<Movie>();
		
		while(it.hasNext())
		{
			Movie m=it.next();
			ArrayList<String> cast=m.getCast();
			if(cast.contains(actor))
				mal.add(m);
		}
		
		return mal;
	}
	
	public void inputUpdateMovieRating()throws Exception
	{
		System.out.println("Enter Movie ID");
		int mid=Integer.parseInt(br.readLine());
		
		Movie m=containsMovie(mid);
		if(m==null)
		{
			System.out.println("Movie Not Found");
			return;
		}
		
		System.out.println("Enter Movie Rating");
		double rating=Double.parseDouble(br.readLine());
		
		updateMovieRating(m, rating);
	}
	
	public Movie containsMovie(int id)
	{
		Iterator<Movie> it=al.iterator();
		while(it.hasNext())
		{
			Movie m=it.next();
			if(m.getMovieID()==id)
				return m;
		}
		return null;
	}
	public void updateMovieRating(Movie m, double rating)
	{
		m.setMovieRating(rating);
	}
	
	public void inputUpdateBusiness()throws Exception
	{
		System.out.println("Enter Movie ID");
		int mid=Integer.parseInt(br.readLine());
		
		Movie m=containsMovie(mid);
		if(m==null)
		{
			System.out.println("Movie Not Found");
			return;
		}
		
		System.out.println("Enter Movie Business");
		double biz=Double.parseDouble(br.readLine());
		
		updateBusiness(m,biz);
	}
	
	public void updateBusiness(Movie m, double biz)
	{
		m.setTotalBusiness(biz);	
	}
		
	public Map<Language, Set<Movie>> getMoviesByBusiness(double amount)
	{
		Iterator<Movie> it=al.iterator();
		
		Map<Language, Set<Movie>> mp=new HashMap<Language,Set<Movie>>();
			
		Language lang1=new Language(1,"english");
		Language lang2=new Language(2,"hindi");
		Language lang3=new Language(3,"marathi");
		
		Set<Movie> eng=new HashSet<Movie>();
		Set<Movie> hin=new HashSet<Movie>();
		Set<Movie> mar=new HashSet<Movie>();
		
		while(it.hasNext())
		{
			Movie m=it.next();
			if(m.getMovieLang().getLangName().equalsIgnoreCase("english") && m.getTotalBusiness()>amount)
				eng.add(m);
			if(m.getMovieLang().getLangName().equalsIgnoreCase("hindi") && m.getTotalBusiness()>amount)
				hin.add(m);
			if(m.getMovieLang().getLangName().equalsIgnoreCase("tamil") && m.getTotalBusiness()>amount)
				mar.add(m);
		}
		
		mp.put(lang1, eng);
		mp.put(lang2, hin);
		mp.put(lang3, mar);
		
		return mp;
		
	}
	
	public void insertMoviesInDB()throws Exception
	{
		PreparedStatement ps=con.prepareStatement("Insert into movie_table values(?,?,?,?,?,?,?,?,?)");
		Iterator <Movie> it=al.iterator();
		
		while(it.hasNext())
		{
			Movie m=(Movie)it.next();
			System.out.println("OK");
			ps.setInt(1, m.getMovieID());
			ps.setString(2, m.getMovieName());
			ps.setInt(3,m.getMovieCat().getCatID());
			ps.setInt(4, m.getMovieLang().getLangID());
			ps.setDate(5,m.getReleaseDate());
			ps.setString(6, m.getCast().get(0));
			ps.setString(7, m.getCast().get(1));
			ps.setDouble(8, m.getMovieRating());
			ps.setDouble(9, m.getTotalBusiness());
			
			System.out.println(ps.executeUpdate());
			
			System.out.println("Data is entered");
		}
	}
	
	
	
}
