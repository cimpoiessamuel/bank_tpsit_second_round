include <iostream>
#include <string>
#include "BankAccount.h"

using namespace std;

int main() {
    string name;
    double money;
    int n, n1;
    bool check{ true };
    cout << "Insert your full name and then the money in your wallet" << endl;
    cin >> name;
    cin >> money;
    BankAccount myAccount{ name, money };
    while (check) {
        cout << "-----------------------------------" << endl;
        cout << "Chose tour next action by typing the number indicated: " << endl;
        cout << "1) Deposit money " << endl;
        cout << "2) Take money " << endl;
        cout << "3) Show wallet money " << endl;
        cout << "4) Show money in the bank " << endl;
        cout << "5) Next month " << endl;
        cout << "6) Invest money " << endl;
        cout << "7) Exit the account " << endl;
        cin >> n;
        cout << "-----------------------------------" << endl;
        switch (n) {
        case 1:
            cout << "How much money are you depositing? " << endl;
            cin >> money;
            myAccount.addMoney(money);
            break;
        case 2:
            cout << "How much money are you tacking? " << endl;
            cin >> money;
            myAccount.getMoney(money);
            break;
        case 3:
            myAccount.printWallet();
            break;
        case 4:
            myAccount.printBankAccount();
            break;
        case 5:
            myAccount.skipMonth();
            cout << "A month has passsed" << endl;
            break;
        case 6:
            cout << "How much time will it take and then how much are you riscking, and finally how much money" << endl;
            cin >> n;
            cin >> n1;
            cin >> money;
            myAccount.investment(n, n1, money);
            break;
        case 7:
            check = false;
            break;
        default:
            break;
        }
    }

    return 0;
}