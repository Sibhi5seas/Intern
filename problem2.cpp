//In this program, I have used an unordered map to count the number of times each character appears in the given string. I go through the string one character at a time and increase its count in the map. If the character appears again, its count gets increased. After checking the entire string, I display each character along with how many times it has appeared.
#include <iostream>
#include <unordered_map>
using namespace std;
void count(string s) {
    unordered_map<char, int> mp;
    for (int i = 0; i < s.length(); i++) {
        mp[s[i]]++;
    }
    for (auto x : mp) {
        cout << x.first << " : " << x.second << endl;
    }
}
int main() {
    string s1 = "banana";
    string s2 = "hello";
    count(s1);
    cout << "------" << endl;
    count(s2);
    return 0;
}
