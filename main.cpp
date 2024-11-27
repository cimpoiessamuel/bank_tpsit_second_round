#include <iostream>
#include <string>
#include "BankAccount.hpp"

using namespace std;

int main() {
    BankAccount myAccount{"Simone Riolfo", 3000.0};
    myAccount.printBankAccount();
    myAccount.printWallet();
    cout << "-----------------" << endl;
    myAccount.addMoney(2000.0);
    myAccount.printBankAccount();
    myAccount.printWallet();
    cout << "-----------------" << endl;
    myAccount.getMoney(1000.0);
    myAccount.printBankAccount();
    myAccount.printWallet();
    cout << "-----------------" << endl;
    myAccount.skipMonth();
    myAccount.skipMonth();
    myAccount.printWallet();
    myAccount.printBankAccount();
    myAccount.investment(1, 1, 100.0);
    myAccount.skipMonth();
    myAccount.skipMonth();
    myAccount.skipMonth();
    cout << "-----------------" << endl;
    myAccount.printWallet();
    myAccount.printBankAccount();
    return 0;
}