import java.io.IOException;
import java.util.ArrayList;

public class HashManager {

	public static void main(String[] args) throws IOException {

		boolean WRITEMODE = false;
		String[] FILENAMES = {"kittens", "me_at_the_zoo", "trending_today", "videos_worth_spreading"};
		long sessionPoints = 0;

		for (int i = 0; i<FILENAMES.length; i++) {

			String filename = FILENAMES[i];
			String infile = "data/"+filename+".in";
			String outfile = "data/"+filename+".out";

			// Create ArrayLists.
			ArrayList<HashCache> caches;
			ArrayList<HashEndpoint> endpoints;
			ArrayList<HashRequest> requests;
			ArrayList<HashVideo> videos;

			System.out.println("Reading from file '"+filename+"'...");

			// Read in from input file.
			HashInputter inputter = new HashInputter(infile);
			caches = inputter.caches;
			endpoints = inputter.endpoints;
			requests = inputter.requests;
			videos = inputter.videos;
			System.out.println("File read in.");

			// Optimise caches.
			HashOptimiser optimiser = new HashOptimiser(caches, requests, videos, endpoints);
			caches = optimiser.run();
			System.out.println("Optimisation complete.");

			// Calculate Score.
			HashScorer scorer = new HashScorer(caches, requests, videos, endpoints);
			sessionPoints += scorer.getPoints();

			// Generate output.
			if(WRITEMODE) { // If write mode is activate, writes output to files.
				HashOutputter outputter = new HashOutputter(caches, outfile);
				System.out.println("Written to file "+outfile+".");
			}

			System.out.println();
		}

		System.out.println("\nTotal points : "+sessionPoints);

    }
}
