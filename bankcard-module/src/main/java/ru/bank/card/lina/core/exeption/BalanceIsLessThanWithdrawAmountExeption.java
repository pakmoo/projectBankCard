package ru.bank.card.lina.core.exeption;

public class BalanceIsLessThanWithdrawAmountExeption extends RuntimeException {
    public BalanceIsLessThanWithdrawAmountExeption() {
        System.out.println("Текущий баланс карты меньше суммы вывода.");
    }
}
