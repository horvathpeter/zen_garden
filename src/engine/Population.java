package engine;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This class represents a population
 * @author Peter
 *
 */
public class Population {
	private List<Chromosome> generation = new ArrayList<Chromosome>(); //population is list of chromosomes

	public Population() {

	}

	/**
	 * Creates population with chromosomes filled with random value
	 * @param amount
	 */
	public Population(int amount) {
		for (int i = 0; i < amount; i++) {
			Chromosome chrom = new Chromosome(28);
			generation.add(chrom);
		}
	}

	public List<Chromosome> getGenerationList() {
		return generation;
	}
	
	/**
	 * Creates new population using tournament selection, tournament size = 2
	 * @param pop
	 * @return
	 */
	public Population makeNewGenerationTournament(Population pop) {
		Random rand = new Random();
		Population new_population = new Population();
		Population finalpop = new Population();
		for (int i = 0; i < pop.getGenerationList().size(); i++) {
			/* choose 2 players randomly*/
			int tour_player1 = rand.nextInt(28);
			int tour_player2 = rand.nextInt(28);

			// System.out.print("Porovnavam "+pop.getGenerationList().get(tour_player1).getFitnes()+
			// " s"+ pop.getGenerationList().get(tour_player2).getFitnes());
			if (pop.getGenerationList().get(tour_player1).getFitnes() >= pop
					.getGenerationList().get(tour_player2).getFitnes()) {
				new_population.getGenerationList().add(
						pop.getGenerationList().get(tour_player1));
				// System.out.println(" a pridavam prvy" +
				// new_population.getGenerationList().size());
			} else {
				new_population.getGenerationList().add(
						pop.getGenerationList().get(tour_player2));
				// System.out.println(" a pridavam druhy " +
				// new_population.getGenerationList().size());
			}

		}
		
		/* time for creating descendants*/
		for (int i = 0; i < new_population.getGenerationList().size(); i++) {
			/* choose parents randomly */
			int parent1 = rand.nextInt(28);
			int parent2 = rand.nextInt(28);
			Chromosome chrom = new Chromosome();
			for (int j = 0; j < (new_population.getGenerationList()
					.get(parent1).getChromosomeList().size() / 2); j++) {
				chrom.getChromosomeList().add(
						new_population.getGenerationList().get(parent1)
								.getChromosomeList().get(j));
			}
			for (int k = (new_population.getGenerationList().get(parent2)
					.getChromosomeList().size() / 2); k < (new_population
					.getGenerationList().get(parent2).getChromosomeList()
					.size()); k++) {
				chrom.getChromosomeList().add(
						new_population.getGenerationList().get(parent2)
								.getChromosomeList().get(k));
			}
			/* iterate through genes and change its value with 10% possibility*/
			for (int l = 0; l < chrom.getChromosomeList().size(); l++) {
				int prem = rand.nextInt(10);
				if (prem < 1) {
					int value = rand.nextInt(48);
					if (value == 13 || value == 47) {
						value--;
					}
					if (value == 0 || value == 34) {
						value++;
					}
					chrom.getChromosomeList().set(l, value);
				}
			}
			/*add chromosome to new generation*/
			finalpop.getGenerationList().add(chrom);
		}

		return finalpop;
	}
	
	/**
	 * Creates new population using roulette selection, tournament size = 2
	 * @param pop
	 * @return
	 */
	public Population makeNewGenerationRoulette(Population pop) {
		Population newpop = new Population();
		Population finalpop = new Population();
		Random rand = new Random();
		int all_fitnes = 0;
		int poc = 0;
		/* get the total fitness of generation */
		for (int i = 0; i < pop.getGenerationList().size(); i++) {
			all_fitnes += pop.getGenerationList().get(i).getFitnes();
		}
		
		for (int i = 0; i < pop.getGenerationList().size(); i++) {
			int value = rand.nextInt(all_fitnes);
			for (int j = 0; j < pop.getGenerationList().size(); j++) {
				poc += pop.getGenerationList().get(j).getFitnes();
				if(poc >= value){
					newpop.getGenerationList().add(pop.getGenerationList().get(j));
					break;
				}
			}
		}
		
		/* time for creating descendants*/
		for (int i = 0; i < newpop.getGenerationList().size(); i++) {
			/* choose parents randomly */
			int parent1 = rand.nextInt(28);
			int parent2 = rand.nextInt(28);
			Chromosome chrom = new Chromosome();
			for (int j = 0; j < (newpop.getGenerationList()
					.get(parent1).getChromosomeList().size() / 2); j++) {
				chrom.getChromosomeList().add(
						newpop.getGenerationList().get(parent1)
								.getChromosomeList().get(j));
			}
			for (int k = (newpop.getGenerationList().get(parent2)
					.getChromosomeList().size() / 2); k < (newpop
					.getGenerationList().get(parent2).getChromosomeList()
					.size()); k++) {
				chrom.getChromosomeList().add(
						newpop.getGenerationList().get(parent2)
								.getChromosomeList().get(k));
			}
			/* iterate through genes and change its value with 10% possibility*/
			for (int l = 0; l < chrom.getChromosomeList().size(); l++) {
				int prem = rand.nextInt(10);
				if (prem < 1) {
					int value = rand.nextInt(48);
					if (value == 13 || value == 47) {
						value--;
					}
					if (value == 0 || value == 34) {
						value++;
					}
					chrom.getChromosomeList().set(l, value);
				}
			}
			/*add chromosome to new generation*/
			finalpop.getGenerationList().add(chrom);
		}
		
		
		return finalpop;
	}
}
