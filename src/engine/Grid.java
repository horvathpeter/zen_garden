package engine;

/**
 * This class represents a garden
 * @author Peter
 *
 */
public class Grid {

	public int puzzle[][] = new int[12][14]; //garden is 2D array of integers
	int counter = 0;
	int fitnes = 0;
	int not_started = 0;
	int path = 1;
	
	/**
	 * This function prepare the 2D array for testing. Places numbers to their right positions.
	 */
	public void preparePuzzle() {
		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 14; j++) {
				if (i == 0 || i == 11 || j == 0 || j == 13) {
					puzzle[i][j] = counter;
					counter++;
				} else {
					puzzle[i][j] = 0;
				}
			}
		}
		puzzle[3][2] = 99;	//rock
		puzzle[5][3] = 99;
		puzzle[2][6] = 99;
		puzzle[4][5] = 99;
		puzzle[7][9] = 99;
		puzzle[7][10] = 99;
		puzzle[1][0] = 14;	//border
		puzzle[2][0] = 15;
		puzzle[3][0] = 16;
		puzzle[4][0] = 17;
		puzzle[5][0] = 18;
		puzzle[6][0] = 19;
		puzzle[7][0] = 20;
		puzzle[8][0] = 21;
		puzzle[9][0] = 22;
		puzzle[10][0] = 23;
		puzzle[1][13] = 24;
		puzzle[2][13] = 25;
		puzzle[3][13] = 26;
		puzzle[4][13] = 27;
		puzzle[5][13] = 28;
		puzzle[6][13] = 29;
		puzzle[7][13] = 30;
		puzzle[8][13] = 31;
		puzzle[9][13] = 32;
		puzzle[10][13] = 33;

		// for (int i = 0; i < 12; i++) { for (int j = 0; j < 14; j++) {
		// System.out.printf(String.format("%3d", puzzle[i][j])); }
		// System.out.println(); } System.out.println();
		//
	}
	
	/**
	 * returns fitnes of chromosome given as parameter
	 * @param chrom
	 * @return
	 */
	public int tryThis(Chromosome chrom) {
		fitnes = 0;
		boolean cont;
		for (int i = 1; i < chrom.getChromosomeList().size(); i++) {
			int value = chrom.getChromosomeList().get(i - 1);
			
			if (value <= 12) {
				cont = makeDownMove(1, path, value, "starting");
				if (cont == false) {	//if stucked, it's over so break this out
					break;
				}
			}
			if (value >= 14 && value <= 23) {
				cont = makeRightMove(1, path, value - 13, "starting");
				if (cont == false) {
					break;
				}
			}
			if (value >= 24 && value <= 33) {
				cont = makeLeftMove(12, path, value - 23, "starting");
				if (cont == false) {
					break;
				}
			}
			if (value >= 35) {
				cont = makeUpMove(10, path, value - 34, "starting");
				if (cont == false) {
					break;
				}
			}
			if(not_started == 0){
				path++;
			}
			else{
				not_started = 0;
			}

		}
		
		// for (int i = 0; i < 12; i++) {
		// for (int j = 0; j < 14; j++) {
		// System.out.printf(String.format("%3d", puzzle[i][j]));
		// }
		// System.out.println();
		// }
		// System.out.println(fitnes + " poli bolo pohrabanych");

		return fitnes;
	}

	
	/* this function simulates moving down in array */
	public boolean makeDownMove(int start, int path, int column, String state) {
		int cont = 0;
		boolean solution = false;
		for (int i = start; i < 11; i++) {
			if (puzzle[i][column] == 0) {
				puzzle[i][column] = path;
				fitnes++;
				cont++;
				if (i == 10)
					solution = true;

			} else if (puzzle[i][column] != 0) {
				if (cont == 0 && ("starting").equals(state)) {
					not_started++;
					solution = true;
				}

				if (puzzle[i - 1][column + 1] == 0) {
					solution = makeRightMove(column + 1, path, i - 1, "nope");
				}

				else if (puzzle[i - 1][column - 1] == 0) {
					solution = makeLeftMove(column - 1, path, i - 1, "nope");

				}

				else if ((column + 1) == 13 || (column) - 1 == 0) {
					solution = true;
				}

				break;
			}
		}
		return solution;

	}
	
	/* this function simulates moving up in array */
	public boolean makeUpMove(int start, int path, int column, String state) {
		int cont = 0;
		boolean solution = false;
		for (int i = start; i > 0; i--) {
			if (puzzle[i][column] == 0) {
				puzzle[i][column] = path;
				fitnes++;
				cont++;
				if (i == 1)
					solution = true;

			} else if (puzzle[i][column] != 0) {
				if (cont == 0 && ("starting").equals(state)) {
					not_started++;
					solution = true;
				}
				if (puzzle[i + 1][column + 1] == 0) {
					solution = makeRightMove(column + 1, path, i + 1, "nope");
				}

				else if (puzzle[i + 1][column - 1] == 0) {
					solution = makeLeftMove(column - 1, path, i + 1, "nope");
				}

				else if (column + 1 == 13 || column - 1 == 0) {
					solution = true;
				}

				break;
			}
		}
		return solution;
	}

	/* this function simulates moving right in array */
	public boolean makeRightMove(int start, int path, int row, String state) {
		int cont = 0;
		boolean solution = false;
		for (int i = start; i < 13; i++) {
			if (puzzle[row][i] == 0) {
				puzzle[row][i] = path;
				fitnes++;
				cont++;
				if (i == 12)
					solution = true;
			} else if (puzzle[row][i] != 0) {
				if (cont == 0 && ("starting").equals(state)) {
					not_started++;
					solution = true;
				}
				if (puzzle[row + 1][i - 1] == 0) {
					solution = makeDownMove(row + 1, path, i - 1, "nope");
				}

				else if (puzzle[row - 1][i - 1] == 0) {
					solution = makeUpMove(row - 1, path, i - 1, "nope");

				} else if (row + 1 == 11 || row - 1 == 0) {
					solution = true;
				}
				break;
			}
		}
		return solution;
	}

	
	/* this function simulates moving left in array */
	public boolean makeLeftMove(int start, int path, int row, String state) {
		int cont = 0;
		boolean solution = false;
		for (int i = start; i > 0; i--) {
			if (puzzle[row][i] == 0) {
				puzzle[row][i] = path;
				fitnes++;
				cont++;
				if (i == 1)
					solution = true;
			} else if (puzzle[row][i] != 0) {
				if (cont == 0 && ("starting").equals(state)) {
					not_started++;
					solution = true;
				}
				if (puzzle[row + 1][i + 1] == 0) {
					solution = makeDownMove(row + 1, path, i + 1, "nope");

				} else if (puzzle[row - 1][i + 1] == 0) {
					solution = makeUpMove(row - 1, path, i + 1, "nope");

				} else if (row + 1 == 11 || row - 1 == 0) {
					solution = true;
				}
				break;
			}

		}
		return solution;
	}

	public void printPuzzle() {
		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 14; j++) {
				System.out.printf(String.format("%3d", puzzle[i][j]));
			}
			System.out.println();
		}
	}

}
