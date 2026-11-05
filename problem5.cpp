//In this program, I have used an unordered map to find the first character that appears only once in the string. First, I go through the entire string and count how many times each character appears. After that, I go through the string again from the beginning and check the frequency of each character. When I find a character whose count is one, I return it. If none of the characters satisfy this condition, it means there is no non-repeating character in the string.
#include <iostream>
#include <unordered_map>
using namespace std;
char first(string s) {
    unordered_map<char, int> mp;
    for (int i = 0; i < s.length(); i++) {
        mp[s[i]]++;
    }
    for (int i = 0; i < s.length(); i++) {
        if (mp[s[i]] == 1)
            return s[i];
    }
    return '\0';
}
int main() {
    string a = "swiss";
    string b = "aabbcc";
    string c = "leetcode";
    char x = first(a);
    char y = first(b);
    char z = first(c);
    cout << (x == '\0' ? "No character" : string(1, x)) << endl;
    cout << (y == '\0' ? "No character" : string(1, y)) << endl;
    cout << (z == '\0' ? "No character" : string(1, z)) << endl;
    return 0;
}
