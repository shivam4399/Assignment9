

import java.util.Comparator;



/**
 * @author shivam singh
 *
 */
public class CompareAmount implements Comparator<Movie>{

	@Override
	public int compare(Movie o1, Movie o2) {
		return Double.compare(o1.getTotalBusiness(), o2.getTotalBusiness());
	}

}