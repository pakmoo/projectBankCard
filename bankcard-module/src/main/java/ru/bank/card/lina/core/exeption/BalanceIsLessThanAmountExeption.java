package ru.bank.card.lina.core.exeption;

public class BalanceIsLessThanAmountExeption extends RuntimeException{
    public BalanceIsLessThanAmountExeption(){
        System.out.println("Сумма перевода меньше текущего баланса карты");
    }

}
