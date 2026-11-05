//In this program, I have used an unordered set along with a vector. The set helps me check whether an element has already been added or not. I go through the array one by one, and if an element is not already present in the set, I add it to both the set and the result vector. If it is already present, I skip it. This way, I can remove the duplicate elements without changing their original order.
#include <iostream>
#include <vector>
#include <unordered_set>
using namespace std;
vector<int> remove(vector<int> a) {
    unordered_set<int> s;
    vector<int> ans;
    for (int i = 0; i < a.size(); i++) {
        if (s.find(a[i]) == s.end()) {
            s.insert(a[i]);
            ans.push_back(a[i]);
        }
    }
    return ans;
}
int main() {
    vector<int> a = {4, 2, 4, 3, 2, 5, 3};
    vector<int> b = {1, 1, 1, 1};
    vector<int> x = remove(a);
    vector<int> y = remove(b);
    for (int i : x)
        cout << i << " ";
    cout << endl;
    for (int i : y)
        cout << i << " ";
    return 0;
}