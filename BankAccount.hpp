#ifndef BANKACCOUNT_HPP
#define BANKACCOUNT_HPP

#include <string>

using namespace std;

class BankAccount {
    private:
        double wallet;
        double virtualWallet;
        string fullName;
        unsigned int month;
    public:
        BankAccount(string name, double money);
        void printBankAccount();
        void printWallet();
        void addMoney(double money);
        void getMoney(double money);
};

#endif // BANKACCOUNT_HPP