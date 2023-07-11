# Blackjack with Monte Carlo Simulation

This is a fully functional blackjack program with all of the nuances of the game like splitting, doubling down, insurance, etc. \The game support `n` number of players with `n` number of hands per player (Capped per game rules).
There are a few different AI players that can be added/removed from the game `Human Player, Dealer, Monte Carlo Player, Book Player, Random Player`. A game must support a human player and the dealer. There is a bank supported for the human player along with stats for all players.

The menu looks as such: \
![Main menu](https://www.josephmilici.com/terminal.png)

One of the most unique parts of this program is the Monte Carlo Player/Table. The game of blackjack is solved, and the solved state looks like this: \
![Solved State](https://www.josephmilici.com/blackjackSolved.png)

The program predicts the probability of a variety of outcomes at every given game state to generate a highly accurate table. Increasing the number of tests increases the accuracy.
Examples of these generated tables are below.

Hard Totals: \
![Hard Totals](https://josephmilici.com/hardTable.png)

Soft Totals: \
![Soft Totals](https://josephmilici.com/softTable.png)


There are also unit tests, along with dev docs that may be generated. 
Have fun playing :)

Credits: Joseph Milici

