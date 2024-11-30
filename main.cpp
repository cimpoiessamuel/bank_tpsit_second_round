#include "BankAccount.h"
#include <iostream>
#include <string>

using namespace std;

int main() {
    string name;
    double money;
    int choice1, choice2;
    bool check{ true };
    cout << "Insert your full name and then the money in your wallet" << endl;
    getline(cin, name);
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
        cin >> choice1;
        cout << "-----------------------------------" << endl;
        switch (choice1) {
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
            cout << "How much time will it take: 1) 3 months 2) 6 months 3) 11 months"
                << endl;
            cin >> choice1;
            cout << "How much risk will you take: 1) low(10%) 2) medium(40%) 3) "
                "high(80%)"
                << endl;
            cin >> choice2;
            cout << "How much money are you investing" << endl;
            cin >> money;
            myAccount.investment(choice1, choice2, money);
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