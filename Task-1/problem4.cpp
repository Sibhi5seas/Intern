//In this program, I have used a frequency array to check whether the two given strings are anagrams. First, I check whether both strings have the same length. Then I increase the count of each character in the first string and decrease the count using the second string. At the end, I check whether all the values in the frequency array are zero. If they are, it means both strings have the same characters with the same number of occurrences, so they are anagrams.
#include <iostream>
using namespace std;
bool anagram(string a, string b) {
    if (a.length() != b.length())
        return false;
    int f[26] = {0};
    for (int i = 0; i < a.length(); i++) {
        f[a[i] - 'a']++;
    }
    for (int i = 0; i < b.length(); i++) {
        f[b[i] - 'a']--;
    }
    for (int i = 0; i < 26; i++) {
        if (f[i] != 0)
            return false;
    }
    return true;
}
int main() {
    cout << anagram("listen", "silent") << endl;
    cout << anagram("hello", "world") << endl;
    return 0;
}
