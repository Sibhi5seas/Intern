//In this program, I have used an unordered map to count the number of times each character appears in the given string. I go through the string one character at a time and increase its count in the map. If the character appears again, its count gets increased. After checking the entire string, I display each character along with how many times it has appeared.
#include <iostream>
#include <vector>
using namespace std;
int second(vector<int> a) {
    int max1 = INT_MIN, max2 = INT_MIN;
    for (int i = 0; i < a.size(); i++) {
        if (a[i] > max1) {
            max2 = max1;
            max1 = a[i];
        }
        else if (a[i] < max1 && a[i] > max2) {
            max2 = a[i];
        }
    }
    if (max2 == INT_MIN)
        return -1;
    return max2;
}
int main() {
    vector<int> a = {10, 5, 20, 8, 15};
    vector<int> b = {10, 20, 20, 5};
    cout << second(a) << endl;
    cout << second(b) << endl;
    return 0;
}
