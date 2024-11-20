#include "BankAccount.hpp"
#include <iostream>

using namespace std;

BankAccount::BankAccount(string name, double money) 
    : wallet{ money }, virtualWallet{ 0.0 }, fullName{ name }, month{1} {
};

void BankAccount::printBankAccount() {
    cout << fullName << " has " << virtualWallet << " euro in his bank account." << endl;
}

void BankAccount::printWallet() {
    cout << fullName << " has " << wallet << " euro in his wallet." << endl;
}

void BankAccount::addMoney(double money) {
    if (money > wallet) {
        cout << "You don't have enough money in the wallet!" << endl;
        return;
    }
    wallet -= money;
    virtualWallet += money;
}

void BankAccount::getMoney(double money) {
    if (money > virtualWallet || virtualWallet <= 0) {
        cout << "You don't have enough money in the bank!" << endl;
        return;
    }
    virtualWallet -= money;
    wallet += money;
}