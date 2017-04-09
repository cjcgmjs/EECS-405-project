package algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Fast Indexes and Algorithms for Set Similarity Selection Queries by Hadjieleftheriou et al.
 */
public class ShortestFirstAlgorithm<T> {
	private List<String> s;
	private List<List<T>> database;
	
	public ShortestFirstAlgorithm(List<List<T>> database) {
		this.database = database;
	}
	
	public List<List<T>> shortestFirst(List<List<T>> lists, T threshold) {
		return null;
	}
	
	private double idfSimilarity(List<T> s, List<T> q) {
		double similarity = 0;
		for (T token : intersect(s, q)) {
			similarity += Math.pow(inverseDocumentFrequency(token), 2) / 
					(normalizedLength(s) * normalizedLength(q));
		}
		return similarity;
	}
	
	private double normalizedLength(List<T> set) {
		double totalSquaredIDF = 0;
		for (T token : set) {
			totalSquaredIDF += Math.pow(inverseDocumentFrequency(token), 2);
		}
		return Math.sqrt(totalSquaredIDF);
	}
	
	private double inverseDocumentFrequency(T token) {
		return Math.log(1 + database.size() / totalSetsContainingToken(token)) / Math.log(2);
	}
	
	private int totalSetsContainingToken(T token) {
		int total = 0;
		for (List<T> list : database) {
			if (list.contains(token)) {
				total++;
			}
		}
		return total;
	}
	
	/** Returns the intersect of two lists. */
	private List<T> intersect(List<T> a, List<T> b) {
		List<T> copy = new ArrayList<T>(a); // copy so we don't modify A
		copy.retainAll(b);
		return copy;
	}
	
	/** Main method for testing */
	public static void main(String[] args) {
		List<String> a = Arrays.asList("a", "b", "c");
		List<String> b = Arrays.asList("a", "b", "c");
		
		ShortestFirstAlgorithm<String> sfa = new ShortestFirstAlgorithm<String>(Arrays.asList(a, b));
		System.out.println(sfa.idfSimilarity(a, b));
	}
}
