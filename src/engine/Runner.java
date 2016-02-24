package engine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Runner class contains main method and triggers the simulation
 * @author Peter
 *
 */
public class Runner {

	private static List<Integer> vysledky = new ArrayList<Integer>();
	private static List<Integer> solution = new ArrayList<Integer>();
	static int populacie = 0;

	public static void main(String[] args) {

		for (int i = 0; i < 28; i++) {
			solution.add(1);
		}
		Population pop = new Population(500);
		for (int i = 0; i < pop.getGenerationList().size(); i++) {
			Chromosome chrom = pop.getGenerationList().get(i);

			// for (int j = 0; j < chrom.getChromosomeList().size(); j++) {
			// System.out.print(chrom.getChromosomeList().get(j) + " ");
			// }

			Grid grid = new Grid();
			grid.preparePuzzle();
			chrom.setFitnes(grid.tryThis(chrom));
			vysledky.add(chrom.getFitnes());
		}

		Collections.sort(vysledky);
		System.out.println("Prva populacia: ");
		for (int i = 0; i < vysledky.size(); i++) {
			System.out.print(vysledky.get(i) + " ");
		}
		System.out.println();
		Population newpop = new Population();
		newpop = newpop.makeNewGenerationRoulette(pop);

		while (!vysledky.contains(114)) {
			vysledky.clear();

			populacie++;
			for (int i = 0; i < newpop.getGenerationList().size(); i++) {
				Chromosome chrom = newpop.getGenerationList().get(i);

				// for (int j = 0; j < chrom.getChromosomeList().size(); j++) {
				// System.out.print(chrom.getChromosomeList().get(j) + " ");
				// }
				Grid grid = new Grid();
				grid.preparePuzzle();
				chrom.setFitnes(grid.tryThis(chrom));
				vysledky.add(chrom.getFitnes());
				if (chrom.getFitnes() == 114) {
					solution.clear();
					for (int j = 0; j < chrom.getChromosomeList().size(); j++) {
						solution.add(chrom.getChromosomeList().get(j));
					}
					System.out.println();
					grid.printPuzzle();
					break;
				}

				newpop = newpop.makeNewGenerationRoulette(newpop); // crate
																		// new
																		// generation
			}
		}
		System.out.println();
		System.out.println("Riesenie: ");
		for (int i = 0; i < solution.size(); i++) {
			System.out.print(solution.get(i) + " ");
		}
		System.out.println();
		System.out.println("Riesenie najdene v populacii " + populacie);

	}
}
