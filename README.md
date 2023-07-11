# Blackjack with GUI
For my capstone project, I will recreate the game of blackjack and implement a graphical user interface (GUI) in java using the JavaFX library for the graphics.

The game will support multiple decks and all obvious actions like hitting, standing, splitting, and doubling down. A dealer will be implemented that will play to the book (hitting until 17 or higher is reached). A bank will be available with all of the abnormal rulings like insurance, being dealt blackjack increasing the payout, and possible side bets. Multiple decks will be supported along with the "cut card" which signifies when the deck needs to be reshuffled.

Additionally, AI players can be added to the table, to see how you compare against a few different playing algorithms. The game of blackjack was mathematically solved, for one AI player I can use the solved actions for a given hand (stored in a matrix as shown below). Another I would run a Monte Carlo simulation and use those results (storing them locally to be retrieved for quick access later). And a third currently undetermined, possibly just a weak player to give the user some confidence.

![Blackjack solved](https://josephmilici.com/blackjackSolved.png)

All of this will be represented visually, hopefully, in a clear and concise playing experience (I do not have much experience with developing GUI applications, the only reason for the use of hopefully).
