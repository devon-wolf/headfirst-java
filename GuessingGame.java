class GameLauncher {
	public static void main (String[] args) {
		GuessingGame game = new GuessingGame();

		game.startGame();
	}
}

class GuessingGame {
	public void startGame() {
		Player p1 = new Player();
		Player p2 = new Player();
		Player p3 = new Player();
		int targetNumber = (int) (Math.random() * 10);

		System.out.println("I'm thinking of a number between 0 and 9...");

		while (true) {
			System.out.println("...the number to guess is " + targetNumber);
			p1.guess();
			p2.guess();
			p3.guess();

			System.out.println("Player 1 guessed " + p1.number);
			System.out.println("Player 2 guessed " + p2.number);
			System.out.println("Player 3 guessed " + p3.number);

			if (
				p1.number == targetNumber ||
				p2.number == targetNumber ||
				p3.number == targetNumber
			) {
				System.out.println("We have a winner!");
				System.out.println("Player 1? " + (p1.number == targetNumber));
				System.out.println("Player 2? " + (p2.number == targetNumber));
				System.out.println("Player 3? " + (p3.number == targetNumber));
				System.out.println("Game over.");
				break;
			} else {
				System.out.println("Players will have to try again.");
			}
		}
	}
}

class Player {
	int number;

	public void guess() {
		number = (int) (Math.random() * 10);
	}
}
