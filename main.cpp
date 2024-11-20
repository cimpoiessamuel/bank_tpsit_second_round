#include <iostream>
#include "BankAccount.hpp"

using namespace std;

int main() {
    BankAccount myAccount{"Simone Riolfo", 3000.0};
    myAccount.printBankAccount();
    myAccount.printWallet();
    myAccount.addMoney(2000.0);
    myAccount.printBankAccount();
    myAccount.printWallet();
    myAccount.getMoney(1000.0);
    myAccount.printBankAccount();
    myAccount.printWallet();
    return 0;
}