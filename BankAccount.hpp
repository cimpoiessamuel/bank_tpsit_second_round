#pragma once

#include <string>

using namespace std;

class BankAccount {
    private:
        double wallet;
        double virtualWallet;
        string fullName;
        unsigned short int monthRemeining;
        double earned;
    public:
        BankAccount(string name, double money);
        void printBankAccount();
        void printWallet();
        void addMoney(double money);
        void getMoney(double money);
        void skipMonth();
        void investment(int time, int risk, double money);
};