package engine;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This class represents a chromosome
 * @author Peter
 *
 */
public class Chromosome {
	private List<Integer> start_list = new ArrayList<Integer>(); //chromosome is list of start positions
	private int fitnes;
	
	public Chromosome(){
		
	}
	
	/**
	 * this method creates one chromosome with random generated genes
	 * @param max
	 */
	public Chromosome(int max){
		
		Random rand = new Random();
		for(int i = 0; i < max ; i++){
			int value = rand.nextInt(48);
			if(value == 13 || value==47){
				value--;
			}
			if (value == 0 || value== 34){
				value++;
			}
			/* this makes a chromosome with unique genes*/
			while(start_list.contains(value)){
				value = rand.nextInt(48);
				if(value == 13 || value==47){
					value--;
				}
				if (value == 0 || value== 34){
					value++;
				}
			}
			start_list.add(value);
			
		}
	}	
	
	public List<Integer> getChromosomeList(){
		return start_list;
	}
	public void setFitnes(int fitnes){
		this.fitnes = fitnes;
	}
	
	public int getFitnes(){
		return fitnes;
	}
}
