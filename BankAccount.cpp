#include "BankAccount.h"
#include <ctime>
#include <iostream>
#include <stdlib.h>


using namespace std;

BankAccount::BankAccount(string name, double money)
    : wallet{ money }, virtualWallet{ 0.0 }, fullName{ name }, monthRemeining{ 0 }, earned{ 0 } {
};

void BankAccount::printBankAccount() {
    cout << fullName << " has " << virtualWallet << " euro in his bank account" << endl;
}

void BankAccount::printWallet() {
    cout << fullName << " has " << wallet << " euro in his wallet" << endl;
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

void BankAccount::skipMonth() {
    wallet += 100.0;
    if (monthRemeining == 0 && earned != 0) {
        cout << "Your investment made: " << earned << " euro." << endl;
        virtualWallet += earned;
        earned = 0;
    }
    else if (monthRemeining == 0 && earned == 0) {
        return;
    }
    else {
        monthRemeining--;
    }
}

void BankAccount::investment(int duration, int risk, double money) {
    earned = money;
    if (virtualWallet <= 0) {
        cout << "You can't invest" << endl;
        return;
    }
    virtualWallet -= money;
    switch (duration) {
    case 1:
        monthRemeining = 2;
        break;
    case 2:
        monthRemeining = 5;
        break;
    case 3:
        monthRemeining = 11;
        break;
    default:
        cout << "Time error" << endl;
        return;
        break;
    }
    int range{ 0 };
    switch (risk) {
    case 1:
        range = 10;
        break;
    case 2:
        range = 40;
        break;
    case 3:
        range = 80;
        break;
    default:
        cout << "Risk error" << endl;
        return;
    }
    for (int i{ 0 }; i <= monthRemeining; i++) {
        srand(time(NULL) + i * 4);
        int multi = rand() % range + 1;
        if (multi % 2 == 0) {
            earned += money * (static_cast<double>(multi) / 100.0);
        }
        else {
            earned -= money * (static_cast<double>(multi) / 100.0);
        }
    }
}