package test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;


import org.junit.jupiter.api.Test;

import production.Bank;


public class BankTest {

    @Test
    public void addChipsTest(){
        Bank bank = new Bank();
        int chips = bank.getChips();
        bank.addChips(1000);
        assertEquals(chips + 1000, bank.getChips());
    }

    @Test
    public void subtractChipsTest(){
        Bank bank = new Bank();
        int chips = bank.getChips();
        bank.subtractChips(1000);
        assertEquals(chips - 1000, bank.getChips());
    }

    @Test
    public void getTableMinimumTest(){
        Bank bank = new Bank();
        assertTrue(bank.getTableMinimum() == 500 || bank.getTableMinimum() == 1000);
    }

    @Test
    public void isOutOfChipsTest(){
        Bank bank = new Bank();
        assertEquals(false, bank.isOutOfChips());
        bank.subtractChips(5000);
        assertEquals(true, bank.isOutOfChips());
    }

    @Test
    public void hasTableMiniumTest(){
        Bank bank = new Bank();
        assertEquals(true, bank.hasTableMinium(bank.getChips()));

        assertEquals(false, bank.hasTableMinium(0));
    }

    
}
