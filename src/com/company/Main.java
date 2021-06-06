package com.company;

import java.util.*;

public class Main {


    private int countBinarySubstrings(String s) {
        if (s == null || s.length() == 1)
            return 0;
        char[] str = s.toCharArray();
        int res = 0;
        int i = 0;
        int currLen = 0;
        int start;
        while (i < str.length - 1) {
            currLen = 1;
            start = i;
            while (i < str.length - 1 && str[i] == str[i + 1]) {
                i++;
                currLen++;
            }
            i++;
            currLen--;
            while (i < str.length - 1 && currLen > 0 && str[i] == str[i + 1]) {
                i++;
                currLen--;
            }
            if (currLen == 0) {
                res++;
            }
            i = start + 1;
        }
        return res;
    }

    private boolean isPerfect(int sum, int[] seats) {
        int curSum = 0;
        for (int i = seats.length - 1; i >= 0; i--) {
            curSum = seats[i];
            for (int j = i - 1; j >= 0; j--) {
                if (curSum == sum) {
                    return true;
                } else {
                    curSum += seats[j];
                }
            }
            if (curSum == sum) {
                return true;
            }
        }
        return false;
    }


    private boolean isToeplitzMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return false;
        }
        for (int i = matrix[0].length - 1; i >= 0; i--) {
            for (int j = 0; i + j + 1 < matrix[0].length && j + 1 < matrix.length; j++) {
                if (matrix[j][i + j] != matrix[j + 1][i + j + 1]) {
                    return false;
                }
            }
        }
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 0; i + j + 1 < matrix.length && j + 1 < matrix[0].length; j++) {
                if (matrix[i + j][j] != matrix[i + j + 1][j + 1]) {
                    return false;
                }
            }
        }
        return true;
    }

    private int[] beliefValue(int[][] nums, int n) {
        int[] temp = new int[101];
        for (int i = 0; i < temp.length; i++) {
            temp[i] = 0;
        }
        for (int[] num : nums) {
            for (int j = num[0]; j <= num[1]; j++) {
                temp[j + 50 - 1]++;
            }
        }
        int minIndex = -1;
        int maxIndex = -1;
        for (int i = 0; i < temp.length; i++) {
            if (temp[i] >= n) {
                maxIndex = i;
            }
        }
        for (int i = temp.length - 1; i >= 0; i--) {
            if (temp[i] >= n) {
                minIndex = i;
            }
        }
        if (maxIndex == -1 || minIndex == -1) {
            return null;
        } else {
            return new int[]{minIndex, maxIndex};
        }
    }

    private double angle(String time) {
        String[] ht = time.split(":");
        double[] hnt = new double[2];
        hnt[0] = Double.parseDouble(ht[0]);
        hnt[1] = Double.parseDouble(ht[1]);
        ht = null;
        double res;
        double minuteAngle = hnt[1] / 60.0 * 360.0;
        double hourAngle = hnt[0] / 12.0 * 360.0 + hnt[1] / 60.0 * 30.0;
        if (minuteAngle > hourAngle) {
            res = (minuteAngle - hourAngle) > 180 ? 360.0 - (minuteAngle - hourAngle) : (minuteAngle - hourAngle);
        } else {
            res = (hourAngle - minuteAngle) > 180 ? 360.0 - (hourAngle - minuteAngle) : (hourAngle - minuteAngle);
        }
        return res;
    }

    /*
    生成窗口最大值数组；
     */
    private int[] slidingWindow(int[] nums, int w) {
        if (nums == null || nums.length < w || w < 1)
            return null;
        int[] res = new int[nums.length - w + 1];
        Deque<Integer> qmax = new ArrayDeque<>();
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (qmax.isEmpty()) {
                qmax.offerLast(i);
            } else {
                while (!qmax.isEmpty() && nums[qmax.peekLast()] <= nums[i]) {
                    qmax.pollLast();
                }
                qmax.offerLast(i);
            }
            if (qmax.peekFirst() == i - w) {
                qmax.pollFirst();
            }
            if (i >= w - 1) {
                res[index++] = nums[qmax.peekFirst()];
            }
        }
        return res;
    }


    private boolean isMatch(char[] str, int i, char[] pat, int j) {
        if (i == str.length && j == pat.length)
            return true;
        if (i < str.length && j == pat.length)
            return false;
        if (j < pat.length - 1 && pat[j + 1] == '*') {
            if (i < str.length && (str[i] == pat[j] || pat[j] == '?')) {
                return isMatch(str, i + 1, pat, j + 2)
                        || isMatch(str, i + 1, pat, j)
                        || isMatch(str, i, pat, j + 2);
            } else {
                return isMatch(str, i, pat, j + 2);
            }
        }
        if (i < str.length && (str[i] == pat[j] || pat[j] == '?')) {
            return isMatch(str, i + 1, pat, j + 1);
        }
        return false;
    }

    public int calPoints(String[] ops) {
        Stack<Integer> point = new Stack<>();
        int sum = 0;
        for (String s : ops) {
            if (s.charAt(0) == '-') {
                int i = -Integer.parseInt(s.substring(1));
                sum += i;
                point.push(i);
            } else if (s.charAt(0) - '0' <= 9 && s.charAt(0) - '0' >= 0) {
                int i = Integer.parseInt(s);
                sum += i;
                point.push(i);
            } else if (s.equals("C")) {
                sum -= point.pop();
            } else if (s.equals("D")) {
                int i = point.peek();
                sum += 2 * i;
                point.push(2 * i);
            } else if (s.equals("+")) {
                int i = point.pop();
                int j = point.peek();
                int k = i + j;
                point.push(i);
                point.push(k);
                sum += k;
            }
        }
        return sum;
    }

    private boolean hasAlternatingBits(int n) {
        boolean isOdd = (n % 2 == 1);
        String bin = Integer.toBinaryString(n);
        if (isOdd) {
            char k = '1';
            for (int i = bin.length() - 1; i >= 0; i--) {
                if (bin.charAt(i) != k) {
                    return false;
                }
                String s = String.valueOf(1 - Character.getNumericValue(k));
                k = s.charAt(0);
            }
        } else {
            char k = '0';
            for (int i = bin.length() - 1; i >= 0; i--) {
                if (bin.charAt(i) != k) {
                    return false;
                }
                String s = String.valueOf(1 - Character.getNumericValue(k));
                k = s.charAt(0);
            }
        }
        return true;
    }

    private int happinessNum(String[] times) {
        String[] t = new String[times.length];
        String[] time;
        for (int i = 0; i < times.length; i++) {
            time = times[i].split(":");
            t[i] = time[0] + time[1];
        }
        char[] temp;
        int count = 0;
        for (int i = 0; i < t.length; i++) {
            temp = t[0].toCharArray();
            if ((temp[0] == temp[1] && temp[2] == temp[3])
                    || (temp[0] == temp[3] && temp[1] == temp[2])
                    || (temp[0] == temp[2] && temp[1] == temp[3])) {
                count++;
            }
        }
        return count;
    }

    private int[] twoNums(int num) {
        if ((num & 1) == 1)
            return null;
        int one = num;
        int two = 1;
        while ((one & 1) != 1) {
            one = one / 2;
            two = two * 2;
        }
        return new int[]{one, two};
    }


    private int specialNum(int n) {
        int[] nums = new int[100];
        for (int k = 0; k < 100; k++) {
            nums[k] = 0;
        }
        nums[0] = 1;
        nums[1] = 2;
        nums[2] = 3;
        nums[3] = 5;
        int i = 1;
        int j = 1;
        int temp;
        int rear = 3;
        while (i < n - 1 || j < n - 1) {
            temp = nums[i] * nums[j];
            int k;
            for (k = rear; k > j; k--) {
                if (nums[k] < temp)
                    break;
                nums[k + 1] = nums[k];
            }
            nums[k + 1] = temp;
            rear++;
            if (j < n - 1) {
                j++;
            } else if (i < n - 1) {
                i++;
                j = i;
            } else {
                break;
            }
        }
        return nums[n - 1];
    }

    private int bitCount(int[] nums) {
        return 1 + nums[1] - nums[2];
    }

    private int domain(int num) {
        HashSet<Integer> domainSet = new HashSet<>();
        int temp;
        for (int i = 2; i <= num; i++) {
            if (isNum(i)) {
                temp = i;
                while (temp <= num) {
                    domainSet.add(temp);
                    temp *= i;
                }
            }
        }
        return domainSet.size();
    }

    private boolean isNum(int n) {
        int i = 2;
        while (i <= n / 2) {
            if (n % i == 0) {
                return false;
            }
            i++;
        }
        return true;
    }

    private int method(long num) {
        int res = 0;
        for (long i = 0; i <= num; i++) {
            if (isValid(i)) {
                res++;
            }

        }
        return res;
    }

    private boolean isValid(long n) {
        char[] s = Long.toBinaryString(n).toCharArray();
        int i = 0;
        int j = s.length - 1;
        while (i <= j) {
            if (s[i] != s[j]) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    private int countNum(long num) {
        int[] nums = new int[Long.toBinaryString(num).toCharArray().length + 1];
        if (num == 0)
            return 1;
        if (num == 1)
            return 2;
        if (num == 2)
            return 3;
        nums[0] = 0;
        nums[1] = 1;
        nums[2] = 1;
        for (int i = 3; i < nums.length; i++) {
            nums[i] = 2 * nums[i - 2];
        }
        nums[nums.length - 1] = 0;
        char[] s = Long.toBinaryString(num).toCharArray();
        for (long k = (long) Math.pow(2, s.length - 1); k <= num; k++) {
            if (isValid(k)) {
                nums[nums.length - 1]++;
            }
        }
        int res = 0;
        for (int n : nums) {
            res += n;
        }
        return res + 1;
    }

    private long secondGod(long n) {
        if (n == 2)
            return 1;
        long res = n;
        n = n - 1;
        while (n >= 3) {
            res = res * n;
            n = n - 1;
        }
        return res;
    }


    private int maxHeight(int[][] matrix, int x, int y) {
        if (x < 0 || y < 0 || x >= matrix.length || y >= matrix[0].length) {
            return 0;
        }
        int a = 0;
        int b = 0;
        int c = 0;
        int d = 0;

        if (x - 1 >= 0)
            a = maxHeight(matrix, x - 1, y);
        if (x + 1 <= matrix.length - 1)
            b = maxHeight(matrix, x + 1, y);
        if (y - 1 >= 0)
            c = maxHeight(matrix, x, y - 1);
        if (y + 1 <= matrix[0].length - 1)
            d = maxHeight(matrix, x, y + 1);
        int max = Math.max(Math.max(a, b), Math.max(c, d));
        if (matrix[x][y] >= max) {
            return matrix[x][y];
        } else {
            return max;
        }
    }

    private int maxHeight2(int[][] matrix, int x, int y) {
        boolean[][] flag = new boolean[matrix.length][matrix[0].length];
        for (int i = 0; i < flag.length; i++) {
            for (int j = 0; j < flag[0].length; j++) {
                flag[i][j] = false;
            }
        }
        int max = 0;
        Deque<int[]> queue = new ArrayDeque<>();
        queue.offerLast(new int[]{x, y});
        int[] nums;
        while (!queue.isEmpty()) {
            nums = queue.pollFirst();
            flag[nums[0]][nums[1]] = true;
            if (matrix[nums[0]][nums[1]] > max)
                max = matrix[nums[0]][nums[1]];
            if (nums[0] - 1 >= 0 && !flag[nums[0] - 1][nums[1]])
                queue.offerLast(new int[]{nums[0] - 1, nums[1]});
            if (nums[1] - 1 >= 0 && !flag[nums[0]][nums[1] - 1])
                queue.offerLast(new int[]{nums[0], nums[1] - 1});
            if (nums[0] + 1 <= matrix.length - 1 && !flag[nums[0] + 1][nums[1]])
                queue.offerLast(new int[]{nums[0] + 1, nums[1]});
            if (nums[1] + 1 <= matrix[0].length - 1 && !flag[nums[0]][nums[1] + 1])
                queue.offerLast(new int[]{nums[0], nums[1] + 1});
        }
        return max;
    }


    private boolean checkRecord(String s) {
        char[] record = s.toCharArray();
        int countA = 0;
        for (char r : record) {
            if (r == 'A')
                countA++;
        }
        if (countA > 1) {
            return false;
        } else {
            for (int i = 1; i < record.length - 1; i++) {
                if (record[i - 1] == 'L' && record[i] == 'L' && record[i + 1] == 'L') {
                    return false;
                }
            }
            return true;
        }
    }

    private int changeColor(String color) {
        char[] s = color.toCharArray();
        int res = 0;
        for (int i = 0; i < s.length - 1; ) {
            if (s[i] == s[i + 1]) {
                res++;
                i += 2;
            } else {
                i++;
            }

        }
        return res;
    }

    private int missingNumber(int[] nums) {
        Arrays.sort(nums);
        int mid = -1;
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            mid = (left + right) / 2;
            if (nums[mid] == mid) {
                left = mid + 1;
            } else if (mid == 0 || nums[mid - 1] == mid - 1) {
                return mid;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    private int rotatedDigits(int N) {
        Map<Character, Character> map = new HashMap<>();
        map.put('0', '0');
        map.put('1', '1');
        map.put('2', '5');
        map.put('5', '2');
        map.put('6', '9');
        map.put('8', '8');
        map.put('9', '6');
        int res = 0;
        char[] s;
        boolean flag;
        for (int i = 1; i <= N; i++) {
            s = Integer.toString(i).toCharArray();
            flag = false;
            for (char value : s) {
                if (!map.containsKey(value)) {
                    flag = false;
                    break;
                } else {
                    if (value != map.get(value)) {
                        flag = true;
                    }
                }
            }
            if (flag) {
                res++;
            }
        }
        return res;

    }

    private int maxRectangleArea(int[] nums) {
        Stack<Integer> stack = new Stack<>();
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            while (!stack.isEmpty() && nums[i] <= nums[stack.peek()]) {
                int j = stack.pop();
                int k = stack.isEmpty() ? -1 : stack.peek();
                int curArea = (i - k - 1) * nums[j];
                res = Math.max(res, curArea);
            }
            stack.push(i);
        }

        while (!stack.isEmpty()) {
            int j = stack.pop();
            int k = stack.isEmpty() ? -1 : stack.peek();
            int curArea = (nums.length - k - 1) * nums[j];
            res = Math.max(res, curArea);
        }

        return res;
    }

    private String eraseBlank(String s) {
        if (s == null)
            return null;
        StringBuilder res = new StringBuilder();
        char[] ch = s.toCharArray();
        for (int i = 0; i < ch.length; i++) {
            if (ch[i] != ' ' || (i != 0 && ch[i - 1] != ' ')) {
                res.append(ch[i]);
            }
        }
        return String.valueOf(res);
    }

    private String[] linuxCmd(String[] cmd) {
        List<String> list = new ArrayList<>();
        Deque<String> fileQueue = new ArrayDeque<>();
        String fileName;
        String[] files;
        for (int i = 0; i < cmd.length; i++) {
            if (cmd[i].contains(" ")) {
                fileName = cmd[i].substring(cmd[i].indexOf(" ") + 1);
                if (fileName.charAt(0) == '/') {
                    files = fileName.split("/");
                    fileQueue.clear();
                    for (int j = 1; j < files.length; j++) {
                        if (files[j].equals("..")) {
                            fileQueue.pollLast();
                        } else {
                            fileQueue.offerLast(files[j]);
                        }
                    }
                } else {
                    files = fileName.split("/");
                    for (int j = 0; j < files.length; j++) {
                        if (files[j].equals("..")) {
                            fileQueue.pollLast();
                        } else {
                            fileQueue.offerLast(files[j]);
                        }
                    }
                }
            } else {
                StringBuilder sb = new StringBuilder();
                for (String s : fileQueue) {
                    sb.append("/");
                    sb.append(s);
                }
                sb.append("/");
                list.add(String.valueOf(sb));
            }
        }
        String[] res = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }

    private boolean isMatch2(char[] str, int i, char[] pat, int j) {
        if (i == str.length && j == pat.length)
            return true;
        if (i < str.length && j == pat.length)
            return false;
        if (j < pat.length && pat[j] == '*') {
            return i == str.length ||
                    isMatch2(str, i, pat, j + 1) ||
                    isMatch2(str, i + 1, pat, j) ||
                    isMatch2(str, i + 1, pat, j + 1);
        } else if (i < str.length && (str[i] == pat[j] || pat[j] == '?')) {
            return isMatch2(str, i + 1, pat, j + 1);
        }

        return false;

    }

    private boolean isMatch(String s, String p) {
        if (s == null || p == null)
            return false;
        char[] str = s.toCharArray();
        char[] pat = p.toCharArray();
        return isMatch2(str, 0, pat, 0);
    }

    private boolean isPalindrome(int n) {
        int temp = 0;
        int origin = n;
        while (n > 0) {
            temp = temp * 10 + n % 10;
            n = n / 10;
        }
        return temp == origin;
    }


    private void mergeSort(int[] nums, int start, int end) {
        if (nums == null || nums.length == 0 || start >= end)
            return;
        int mid = (start + end) / 2;
        mergeSort(nums, start, mid);
        mergeSort(nums, mid + 1, end);
        merge(nums, start, mid, end);
    }

    private void merge(int[] nums, int left, int mid, int right) {
        if (left >= right)
            return;
        int i = left;
        int j = mid + 1;
        int[] temp = new int[right - left + 1];
        int index = 0;
        while (i <= mid && j <= right) {
            if (nums[i] < nums[j]) {
                temp[index++] = nums[i++];
            } else {
                temp[index++] = nums[j++];
            }
        }
        while (i <= mid) {
            temp[index++] = nums[i++];
        }
        while (j <= right) {
            temp[index++] = nums[j++];
        }
        System.arraycopy(temp, 0, nums, left, temp.length);
    }

    private Set<String> permutation(char[] str) {
        if (str == null || str.length == 0)
            return null;
        Set<String> set = new TreeSet<>();
        per(str, 0, set);
        return set;
    }

    private void per(char[] str, int start, Set<String> set) {
        if (start == str.length) {
            set.add(String.valueOf(str));
        } else {
            char temp;
            for (int i = start; i < str.length; i++) {
                temp = str[i];
                str[i] = str[start];
                str[start] = temp;
                per(str, start + 1, set);
                temp = str[i];
                str[i] = str[start];
                str[start] = temp;
            }
        }
    }

    private int getCandy(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1)
            return 1;
        int min = 0;
        int res = 0;
        int temp = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            int j;
            if (i < nums.length - 1 && nums[i] > nums[i + 1]) {
                temp = 1;
                for (j = i; j < nums.length - 1 && nums[j] > nums[j + 1]; j++) {
                    temp++;
                }
                for (int k = 1; k <= temp; k++) {
                    res += k;
                }
                min = 1;
                i = j - 1;
            } else if (i < nums.length - 1 && nums[i] == nums[i + 1]) {
                res += min;
            } else {
                min++;
                res += min;
            }
        }
        return res;
    }

    private int maxAreaOfIsland(int[][] grid) {
        int max = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] != 0) {
                    max = Math.max(max, areaOfIsland(grid, i, j));
                }
            }
        }
        return max;
    }

    private int areaOfIsland(int[][] matrix, int i, int j) {
        if (i >= 0 && i < matrix.length && j >= 0 && j < matrix[0].length) {
            matrix[i][j] = 0;
            return 1 + areaOfIsland(matrix, i - 1, j) + areaOfIsland(matrix, i - 1, j)
                    + areaOfIsland(matrix, i, j + 1) + areaOfIsland(matrix, i, j - 1);
        }
        return 0;
    }

    private int[] shortestToChar(String S, char C) {
        if (!S.contains(String.valueOf(C))) {
            return null;
        }
        char[] str = S.toCharArray();
        int fpos = -1;
        int lpos = 0;
        for (int i = 0; i < str.length; i++) {
            if (str[i] == C) {
                lpos = i;
                break;
            }
        }
        int[] res = new int[str.length];
        for (int i = 0; i < res.length; i++) {
            if (str[i] == C) {
                res[i] = 0;
                fpos = i;
            } else {
                if (lpos < i) {
                    for (int j = i; j < str.length; j++) {
                        if (str[j] == C) {
                            lpos = j;
                            break;
                        }
                    }
                }
                if (fpos < 0) {
                    res[i] = lpos - i;
                } else if (lpos < i) {
                    res[i] = i - fpos;
                } else {
                    res[i] = Math.min(lpos - i, i - fpos);
                }
            }
        }
        return res;
    }

    private List<List<Integer>> generate(int numRows) {
        List<List<Integer>> lists = new ArrayList<>();
        if (numRows == 0) {
            return lists;
        }
        List<Integer> list = new ArrayList<>();
        List<Integer> lastList;
        list.add(1);
        lists.add(list);
        for (int i = 1; i < numRows; i++) {
            lastList = lists.get(i - 1);
            list = new ArrayList<>(lastList.size() + 1);
            list.add(1);
            for (int j = 1; j < lastList.size(); j++) {
                list.add(lastList.get(j - 1) + lastList.get(j));
            }
            list.add(1);
            lists.add(list);
        }
        return lists;
    }


    /*
    求两个字符串的乘积；
     */
    private String multiply(String num1, String num2) {
        if (num1 == null || num2 == null)
            return null;
        char[] s1 = num1.toCharArray();
        char[] s2 = num2.toCharArray();
        StringBuilder s = new StringBuilder();
        String res = "0";
        int zero = 0;
        int cur = 0;
        int carry = 0;
        for (int i = s1.length - 1; i >= 0; i--) {
            for (int j = s2.length - 1; j >= 0; j--) {
                cur = (s1[i] - '0') * (s2[j] - '0') + carry;
                int mod = cur % 10;
                s.insert(0, mod);
                carry = cur / 10;
            }
            if (carry > 0) {
                s.insert(0, carry);
            }
            for (int k = 1; k <= zero; k++) {
                s.append(0);
            }
            res = strSum(res, new String(s));
            s.delete(0, s.length());
            carry = 0;
            zero++;
        }
        return res;
    }

    private String strSum(String str1, String str2) {
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();
        int i = s1.length - 1;
        int j = s2.length - 1;
        int carry = 0;
        int cur;
        StringBuilder ret = new StringBuilder();

        while (i >= 0 || j >= 0) {
            if (i >= 0 && j >= 0) {
                cur = (s1[i] - '0') + (s2[j] - '0') + carry;
            } else if (i >= 0) {
                cur = (s1[i] - '0') + carry;
            } else {
                cur = (s2[j] - '0') + carry;
            }
            int mod = cur % 10;
            ret.insert(0, mod);
            carry = cur / 10;
            i--;
            j--;
        }
        if (carry > 0) {
            ret.insert(0, carry);
        }
        while (ret.charAt(0) == '0' && ret.length() > 1) {
            ret.deleteCharAt(0);
        }
        return new String(ret);
    }


    public char findTheDifference(String s, String t) {
        Map<Character, Integer> map = new HashMap<>();
        int i;
        for (i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                map.put(s.charAt(i), map.get(s.charAt(i)) + 1);
            } else {
                map.put(s.charAt(i), 1);
            }
        }
        for (i = 0; i < t.length(); i++) {
            if (!map.containsKey(t.charAt(i)) || map.get(t.charAt(i)) == 0) {
                break;
            } else {
                map.put(t.charAt(i), map.get(t.charAt(i)) - 1);
            }
        }
        return t.charAt(i);
    }

    private String countAndSay(int n) {
        String res = "1";
        StringBuilder temp = new StringBuilder();
        int count = 0;
        char c;
        for (int i = 2; i <= n; i++) {
            count = 0;
            c = res.charAt(0);
            for (int j = 0; j < res.length(); j++) {
                if (j + 1 == res.length() || res.charAt(j) != res.charAt(j + 1)) {
                    temp.append(count + 1).append(c);
                    if (j < res.length() - 1) {
                        count = 0;
                        c = res.charAt(j + 1);
                    }
                } else {
                    count++;
                }
            }
            res = new String(temp);
            temp = temp.delete(0, temp.length());
        }
        return res;
    }

    private int majorityElement(int[] nums) {
        int count = 0;
        int res = nums[0];
        for (int num : nums) {
            if (count == 0) {
                res = num;
                count++;
            } else if (res == num) {
                count++;
            } else {
                count--;
            }
        }
        return res;
    }

    /*
    给定两个数组，写一个函数来计算它们的交集。
     */
    private int[] intersection(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums1) {
            map.put(num, 1);
        }
        int count = 0;
        for (int num : nums2) {
            if (map.containsKey(num)) {
                if (map.get(num) == 1)
                    count++;
                map.put(num, map.get(num) + 1);
            }
        }
        int[] res = new int[count];
        int index = 0;
        for (int key : map.keySet()) {
            if (map.get(key) > 1) {
                res[index++] = key;
            }
        }
        return res;
    }

    private int countPrimeSetBits(int L, int R) {
        String s;
        int count;
        int res = 0;
        for (int i = L; i <= R; i++) {
            s = Integer.toBinaryString(i);
            count = 0;
            for (int j = 0; j < s.length(); j++) {
                if (s.charAt(j) == '1') {
                    count++;
                }
            }
            if (isPrimeNumber(count)) {
                res++;
            }
        }
        return res;
    }

    private boolean isPrimeNumber(int num) {
        if (num <= 1)
            return false;
        for (int i = 2; i <= num / 2; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }


    private boolean isHappy(int n) {
        if (n == 1)
            return true;
        Set<Integer> set = new HashSet<>();
        int temp;
        while (true) {
            temp = bitSum(n);
            if (temp == 1) {
                return true;
            } else if (set.contains(temp)) {
                return false;
            } else {
                set.add(temp);
                n = temp;
            }
        }
    }

    private int bitSum(int n) {
        int res = 0;
        while (n > 0) {
            res += (n % 10) * (n % 10);
            n /= 10;
        }
        return res;
    }


    private List<String> letterCasePermutation(String S) {
        List<String> res = new ArrayList<>();
        res.add(S);
        permutationCore(S, 0, res);
        return res;
    }

    private void permutationCore(String S, int start, List<String> list) {
        if (start >= S.length())
            return;
        permutationCore(S, start + 1, list);
        if (S.charAt(start) >= 'a' && S.charAt(start) <= 'z') {
            StringBuilder str = new StringBuilder(S);
            str.setCharAt(start, Character.toUpperCase(S.charAt(start)));
            String ss = new String(str);
            list.add(ss);
            permutationCore(ss, start + 1, list);
        } else if (S.charAt(start) >= 'A' && S.charAt(start) <= 'Z') {
            StringBuilder str = new StringBuilder(S);
            str.setCharAt(start, Character.toLowerCase(S.charAt(start)));
            String ss = new String(str);
            list.add(ss);
            permutationCore(ss, start + 1, list);
        }
    }

    private int[][] imageSmoother(int[][] M) {
        int[][] res = new int[M.length][M[0].length];
        for (int i = 0; i < res.length; i++) {
            for (int j = 0; j < res[0].length; j++) {
                res[i][j] = smoother(M, i, j);
            }
        }
        return res;
    }

    private int smoother(int[][] M, int i, int j) {
        int sum = 0;
        int count = 0;
        for (int k = i - 1; k <= i + 1; k++) {
            for (int m = j - 1; m <= j + 1; m++) {
                if (k >= 0 && k < M.length && m >= 0 && m < M[0].length) {
                    sum += M[k][m];
                    count++;
                }
            }
        }
        return sum / count;
    }

    private int maxProfit2(int[] prices) {
        if (prices == null || prices.length == 0 || prices.length == 1)
            return 0;
        int min = prices[0];
        int sum = 0;
        for (int i = 1; i < prices.length; i++) {
            if ((i + 1 == prices.length || prices[i + 1] < prices[i]) && prices[i] > min) {
                sum += prices[i] - min;
                if (i + 1 < prices.length) {
                    min = prices[i + 1];
                }
            } else if (prices[i] < min) {
                min = prices[i];
            }
        }
        return sum;
    }


    private boolean isHaveB(String A, String B) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < A.length(); i++) {
            char c = A.charAt(i);
            if (!map.containsKey(c)) {
                map.put(c, 1);
            } else {
                map.put(c, map.get(c) + 1);
            }
        }
        for (int i = 0; i < B.length(); i++) {
            char c = B.charAt(i);
            if (!map.containsKey(c) || map.get(c) == 0) {
                return false;
            } else {
                map.put(c, map.get(c) - 1);
            }
        }
        return true;
    }

    private Character[] colorSort(Character[] str) {
        Arrays.sort(str, (o1, o2) -> o2 - o1);
        return str;
    }

    private int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        Queue<Integer> queue = new ArrayDeque<>();
        boolean[][] flag = new boolean[image.length][image[0].length];
        for (int i = 0; i < flag.length; i++) {
            for (int j = 0; j < flag[0].length; j++) {
                flag[i][j] = false;
            }
        }
        int orgin = image[sr][sc];
        queue.offer(sr);
        queue.offer(sc);
        int x;
        int y;
        while (!queue.isEmpty()) {
            x = queue.poll();
            y = queue.poll();
            image[x][y] = newColor;
            flag[x][y] = true;
            if (x - 1 >= 0 && image[x - 1][y] == orgin && !flag[x - 1][y]) {
                queue.offer(x - 1);
                queue.offer(y);
            }
            if (x + 1 < image.length && image[x + 1][y] == orgin && !flag[x + 1][y]) {
                queue.offer(x + 1);
                queue.offer(y);
            }
            if (y - 1 >= 0 && image[x][y - 1] == orgin && !flag[x][y - 1]) {
                queue.offer(x);
                queue.offer(y - 1);
            }
            if (y + 1 < image[0].length && image[x][y + 1] == orgin && !flag[x][y + 1]) {
                queue.offer(x);
                queue.offer(y + 1);
            }
        }
        return image;
    }


    private int findShortestSubArray(int[] nums) {
        Map<Integer, Integer> counts = new HashMap<>();
        Map<Integer, Integer> starts = new HashMap<>();
        Map<Integer, Integer> ends = new HashMap<>();
        int maxCount = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (!starts.containsKey(num)) {
                starts.put(num, i);
                counts.put(num, 0);
            }
            counts.put(num, counts.get(num) + 1);
            ends.put(num, i);
            maxCount = Math.max(maxCount, counts.get(num));
        }
        int result = Integer.MAX_VALUE;
        for (Map.Entry<Integer, Integer> entry : counts.entrySet()) {
            if (entry.getValue() == maxCount) {
                result = Math.min(result, ends.get(entry.getKey()) - starts.get(entry.getKey()) + 1);
            }
        }
        return result;
    }


    private boolean isAnagram(String s, String t) {
        if (s.length() != t.length())
            return false;
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!map.containsKey(c)) {
                map.put(c, 0);
            }
            map.put(c, map.get(c) + 1);
        }
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            if (!map.containsKey(c)) {
                return false;
            }
            if (map.get(c) == 0) {
                return false;
            }
            map.put(c, map.get(c) - 1);
        }
        return true;
    }


    private boolean isPowerOfFour(int num) {
        return num > 0 && (num & (num - 1)) == 0
                && (num & 0xAAAAAAAA) == 0;

    }

    private boolean canConstruct(String ransomNote, String magazine) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < magazine.length(); i++) {
            char c = magazine.charAt(i);
            if (!map.containsKey(c)) {
                map.put(c, 0);
            }
            map.put(c, map.get(c) + 1);
        }
        for (int i = 0; i < ransomNote.length(); i++) {
            char c = ransomNote.charAt(i);
            if (!map.containsKey(c) || map.get(c) == 0) {
                return false;
            }
            map.put(c, map.get(c) - 1);
        }
        return true;
    }

    private int climbStairs(int n) {
        if (n <= 0)
            return 0;
        if (n == 1)
            return 1;
        if (n == 2)
            return 2;
        int a = 1;
        int b = 2;
        int i = 3;
        int res = 0;
        while (i <= n) {
            res = a + b;
            a = b;
            b = res;
            i++;
        }
        return res;
    }

    private String reverseString(String s) {
        char[] str = s.toCharArray();
        int i = 0;
        int j = str.length - 1;
        while (i < j) {
            char temp = str[i];
            str[i] = str[j];
            str[j] = temp;
            i++;
            j--;
        }
        return String.valueOf(str);
    }

    private int firstUniqChar(String s) {
        int[] nums = new int[26];
        for (int i = 0; i < s.length(); i++) {
            nums[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < s.length(); i++) {
            if (nums[s.charAt(i) - 'a'] == 1)
                return i;
        }
        return -1;
    }

    public boolean isPalindrome(String s) {
        if (s == null)
            return true;
        s = s.toLowerCase();
        int i = 0;
        int j = s.length() - 1;
        while (i < j) {
            char a = s.charAt(i);
            char b = s.charAt(j);
            if (!((a >= 'a' && a <= 'z') || (a >= '0' && a <= '9'))) {
                i++;
                continue;
            }
            if (!((b >= 'a' && b <= 'z') || (b >= '0' && b <= '9'))) {
                j--;
                continue;
            }
            if (a != b) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    private int buyOrange(int num) {
        if (num < 6)
            return -1;
        if (num == 6 || num == 8) {
            return 1;
        }
        if (buyOrange(num - 6) == -1 && buyOrange(num - 8) == -1) {
            return -1;
        }
        if (buyOrange(num - 6) == -1) {
            return 1 + buyOrange(num - 8);
        }
        if (buyOrange(num - 8) == -1) {
            return 1 + buyOrange(num - 6);
        } else {
            return 1 + Math.min(buyOrange(num - 6), buyOrange(num - 8));
        }
    }

    private int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        int index = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1]) {
                nums[index] = nums[i];
                index++;
            }
        }
        return index;
    }

    private void rotate(int[] nums, int k) {
        if (nums == null || nums.length == 0)
            return;
        if (k > nums.length) {
            k = k % nums.length;
        }
        reverse(nums, 0, nums.length - k - 1);
        reverse(nums, nums.length - k, nums.length - 1);
        reverse(nums, 0, nums.length - 1);
    }

    private void reverse(int[] nums, int start, int end) {
        if (start >= end)
            return;
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }

    private boolean containsDuplicate(int[] nums) {
        if (nums == null || nums.length == 0)
            return false;
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (set.contains(num)) {
                return true;
            } else {
                set.add(num);
            }
        }
        return false;
    }


    private int[] intersect(int[] nums1, int[] nums2) {
        List<Integer> list = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums1) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        for (int num : nums2) {
            if (map.containsKey(num) && map.get(num) > 0) {
                map.put(num, map.get(num) - 1);
                list.add(num);
            }
        }
        int[] res = new int[list.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = list.get(i);
        }
        return res;
    }

    private int[] plusOne(int[] digits) {
        List<Integer> list = new ArrayList<>();
        int carry = (digits[digits.length - 1] + 1) / 10;
        int ele = (digits[digits.length - 1] + 1) % 10;
        list.add(0, ele);
        for (int i = digits.length - 2; i >= 0; i--) {
            ele = (digits[i] + carry) % 10;
            carry = (digits[i] + carry) / 10;
            list.add(0, ele);
        }
        if (carry > 0)
            list.add(0, carry);
        int[] res = new int[list.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = list.get(i);
        }
        return res;
    }

    private boolean isHealth(int day, int a, int b, int c) {
        if (a < 0 || b < 0 || c < 0) {
            return false;
        }
        if (day == 0) {
            return true;
        }
        if (c >= 2) {
            return isHealth(day - 1, a, b, c - 2);
        } else {
            return isHealth(day - 1, a - 1, b - 1, c - 1) ||
                    isHealth(day - 1, a - 3, b, c - 1) ||
                    isHealth(day - 1, a, b - 3, c) ||
                    isHealth(day - 1, a - 2, b - 2, c) ||
                    isHealth(day - 1, a - 4, b - 1, c) ||
                    isHealth(day - 1, a - 6, b, c);
        }
    }

    private char nextGreatestLetter(char[] letters, char target) {
        for (char c : letters) {
            if (target < c) {
                return c;
            }
        }
        return letters[0];
    }

    private int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int min = Integer.MAX_VALUE;
        int res = 0;
        for (int price : prices) {
            if (price < min) {
                min = price;
            }
            if (price - min > res) {
                res = price - min;
            }
        }
        return res;
    }

    private int hammingWeight(int n) {
        int count = 0;
        while (n != 0) {
            count++;
            n = n & (n - 1);
        }
        return count;
    }


    private boolean rotateString(String A, String B) {
        if (A.length() != B.length()) {
            return false;
        }
        for (int i = 0, j = A.length() - 1; i < A.length() && j >= 0; i++, j--) {
            if (A.charAt(i) != B.charAt(j)) {
                return false;
            }
        }
        return true;
    }

    private ListNode reverseList(ListNode list) {
        if (list == null)
            return null;
        ListNode head = list;
        ListNode node = head.next;
        ListNode temp;
        while (node != null) {
            temp = node.next;
            node.next = head;
            head = node;
            node = temp;
        }
        return head;
    }


    private boolean isValid(char[] s) {
        if (s == null || s.length == 0)
            return false;
        Stack<Character> charStack = new Stack<>();
        int i = 0;
        while (i < s.length) {
            if (s[i] == '(') {
                charStack.push(s[i]);
            }
            if (s[i] == ')') {
                if (charStack.isEmpty()) {
                    return false;
                } else {
                    charStack.pop();
                }
            }
            i++;
        }
        return charStack.isEmpty();
    }

    private List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> list = new ArrayList<>();

        comSumCore(res, list, candidates, 0, target, 0);
        return res;
    }

    private void comSumCore(List<List<Integer>> res, List<Integer> list, int[] candidates, int index, int target, int sum) {
        if (sum > target)
            return;
        if (sum == target) {
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = index; i < candidates.length; i++) {
            list.add(candidates[i]);
            comSumCore(res, list, candidates, i, target, sum + candidates[i]);
            list.remove(list.size() - 1);
        }
    }

    private int score(int n, int[] m) {
        int[] height = new int[n];
        int res = 0;
        for (int i = 0; i < height.length; i++) {
            height[i] = 0;
        }
        for (int c : m) {
            height[c - 1]++;
            if (!isHaveZero(height)) {
                for (int j = 0; j < height.length; j++) {
                    height[j]--;
                }
                res++;
            }
        }
        return res;
    }

    private boolean isHaveZero(int[] height) {

        for (int h : height) {
            if (h == 0)
                return true;
        }
        return false;
    }

    private int maxInterest(int k, int[] interest, int[] awake) {
        int max = -1;
        int sum = 0;
        for (int i = 0; i < interest.length; i++) {
            sum += awake[i] == 1 ? interest[i] : 0;
        }
        for (int i = 0; i < interest.length; i++) {
            if (awake[i] == 1) {
                max = Math.max(max, sum);
            } else {
                int newSum = 0;
                int count = 0;
                for (int j = i; j < interest.length && j < i + k; j++) {
                    newSum += interest[j];
                    count += awake[j] == 1 ? interest[j] : 0;
                }
                newSum = sum + newSum - count;
                max = Math.max(max, newSum);
            }
        }
        return max;
    }


    private int relationTeam(List[] lists) {
        int res = 0;
        boolean[] flag = new boolean[lists.length];
        boolean[] flag2 = new boolean[lists.length];
        for (int i = 0; i < flag.length; i++) {
            flag[i] = false;
            flag2[i] = false;
        }
        Queue<Integer> queue = new LinkedList<>();

        boolean isAddOne = true;
        for (int i = 0; i < lists.length; i++) {
            isAddOne = true;
            if (!flag[i]) {
                queue.offer(i);
                while (!queue.isEmpty()) {
                    int index = queue.poll();
                    flag[index] = true;
                    flag2[index] = true;
                    for (Object j : lists[index]) {
                        if (flag[(int) j - 1] && !flag2[(int) j - 1]) {
                            isAddOne = false;
                        } else {
                            if (!queue.contains((int) j - 1)) {
                                queue.offer((int) j - 1);
                            }
                        }
                    }
                }
                for (int k = 0; k < flag.length; k++) {
                    flag2[k] = false;
                }
                if (isAddOne) {
                    res++;
                }
            }
        }
        return res;
    }

    private int searchPos(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        int mid;
        while (start < end) {
            mid = (start + end) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (target < nums[mid]) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return target < nums[0] ? start : start + 1;
    }

    private int maxIncSection(int[] nums) {
        Stack<Integer> stack = new Stack<>();
        int res = 1;
        stack.push(nums[0]);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > stack.peek()) {
                stack.push(nums[i]);
                res = Math.max(res, stack.size());
            } else {
                stack.clear();
                stack.push(nums[i]);
            }
        }
        return res;
    }

    private int minSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int min = 0;
        int sum = 0;
        for (int num : nums) {
            if (num <= 0) {
                sum += num;
            } else {
                if (sum + num <= 0) {
                    sum += num;
                } else {
                    sum = 0;
                }
            }
            min = Math.min(min, sum);
        }
        return min;
    }

    private int oneBitCount(long n) {
        int res = 0;
        while (n != 0) {
            n = n & (n - 1);
            res++;
        }
        return res;
    }

    private List getRecords(int[][] records, int time) {
        List<Integer> list = new ArrayList<>();
        for (int[] record : records) {
            if (time >= record[1] && time <= record[2]) {
                list.add(record[0]);
            }
        }
        list.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        return list;
    }


    private int maxLength(String s) {
        if (s.length() == 0)
            return 0;
        int max = 0;
        Set<Character> set = new HashSet<>();
        int i = 0;
        int j = 0;
        while (i < s.length() && j < s.length()) {
            if (!set.contains(s.charAt(j))) {
                set.add(s.charAt(j));
                j++;
                max = Math.max(max, j - i);
            } else {
                set.remove(s.charAt(i));
                i++;
            }
        }
        return max;
    }


    private int countTeam(int[][] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        boolean[][] flag = new boolean[nums.length][nums[0].length];
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums[0].length; j++) {
                flag[i][j] = false;
            }
        }
        int res = 0;
        Queue<Point> queue = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums[0].length; j++) {
                if (nums[i][j] == 1 && !flag[i][j]) {
                    queue.offer(new Point(i, j));
                    Point p;
                    while (!queue.isEmpty()) {
                        p = queue.poll();
                        flag[p.x][p.y] = true;
                        if (p.x - 1 >= 0 && nums[p.x - 1][p.y] == 1 && !flag[p.x - 1][p.y]) {
                            queue.offer(new Point(p.x - 1, p.y));
                        }
                        if (p.x + 1 < nums.length && nums[p.x + 1][p.y] == 1 && !flag[p.x + 1][p.y]) {
                            queue.offer(new Point(p.x + 1, p.y));
                        }
                        if (p.y - 1 >= 0 && nums[p.x][p.y - 1] == 1 && !flag[p.x][p.y - 1]) {
                            queue.offer(new Point(p.x, p.y - 1));
                        }
                        if (p.y + 1 < nums[0].length && nums[p.x][p.y + 1] == 1 && !flag[p.x][p.y + 1]) {
                            queue.offer(new Point(p.x, p.y + 1));
                        }
                    }
                    res++;
                }
            }
        }
        return res;
    }

    private int legalUTF8(int[] nums) {
        int n = nums.length;
        int res = 0;
        if (n == 1) {
            if (nums[0] >= 128) {
                return 0;
            } else {
                return 1;
            }
        } else {
            switch (n) {
                case 2: {
                    if (nums[0] >= 192 && nums[0] < 224 && nums[1] >= 128 && nums[1] <= 255) {
                        res = 1;
                    } else {
                        res = 0;
                    }
                }
                break;
                case 3: {
                    if (nums[0] >= 224 && nums[0] <= 240 && nums[1] >= 128 && nums[1] <= 255
                            && nums[2] >= 128 && nums[2] <= 255) {
                        res = 1;
                    } else {
                        res = 0;
                    }

                }
                break;
                case 4: {
                    if (nums[0] >= 240 && nums[0] < 248 && nums[1] >= 128 && nums[1] <= 255
                            && nums[2] >= 128 && nums[2] <= 255 && nums[3] >= 128 && nums[3] <= 255) {
                        res = 1;
                    } else {
                        res = 0;
                    }

                }
                break;
            }
        }
        return res;
    }

    private int countUnaccept(int[][] nums) {
        int res = 0;

        boolean isCount = false;
        for (int i = 0; i < nums.length; i++) {
            isCount = false;
            for (int j = 0; j < nums.length; j++) {
                if (nums[i][0] < nums[j][0] && nums[i][1] < nums[j][1] && nums[i][2] < nums[j][2]) {
                    isCount = true;
                    break;
                }
            }
            if (isCount) {
                res++;
            }
        }
        return res;
    }


    private int[] findDisappearedNum(int[] nums) {
        int i = 0;
        while (i < nums.length) {
            while (nums[i] != i + 1 && nums[i] != nums[nums[i] - 1]) {
                swap(nums, i, nums[i] - 1);
            }
            i++;
        }

        List<Integer> list = new ArrayList<>();
        for (int k = 0; k < nums.length; k++) {
            if (nums[k] != k + 1) {
                list.add(k + 1);
            }
        }
        int[] res = new int[list.size()];
        for (int j = 0; j < list.size(); j++) {
            res[j] = list.get(j);
        }
        return res;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }


    private int isLuckyId(String s) {
        int[] nums1 = new int[3];
        int[] nums2 = new int[3];

        int sum1 = 0;
        int sum2 = 0;
        for (int i = 0; i < nums1.length; i++) {
            nums1[i] = s.charAt(i) - '0';
            sum1 += nums1[i];
        }
        for (int i = 3; i < 6; i++) {
            nums2[i - 3] = s.charAt(i) - '0';
            sum2 += nums2[i - 3];
        }

        if (sum1 == sum2)
            return 0;

        int dis = Math.abs(sum1 - sum2);
        int flag = sum1 < sum2 ? 1 : 2;

        if (dis / 10 == 0) {
            if (flag == 1) {
                for (int num : nums1) {
                    if (num < (10 - dis)) {
                        return 1;
                    }
                }
                return 2;
            } else {
                for (int num : nums2) {
                    if (num < (10 - dis)) {
                        return 1;
                    }
                }
                return 2;
            }
        } else if (dis / 10 == 1) {
            if (flag == 1) {
                if (nums1[0] + nums1[1] <= 18 - dis || nums1[0] + nums1[2] <= 18 - dis ||
                        nums1[2] + nums1[1] <= 18 - dis) {
                    return 2;
                } else {
                    return 3;
                }
            } else {
                if (nums2[0] + nums2[1] <= 18 - dis || nums2[0] + nums2[2] <= 18 - dis ||
                        nums2[2] + nums2[1] <= 18 - dis) {
                    return 2;
                } else {
                    return 3;
                }
            }
        } else {
            return 3;
        }
    }

    private ListNode reverseListEveryK(ListNode head, int k) {
        ListNode p = new ListNode(0);
        ListNode pre = p;
        ListNode cur = pre;
        p.next = head;
        int num = 0;
        cur = cur.next;
        while (cur != null) {
            num++;
            cur = cur.next;
        }
        ListNode node;
        while (num >= k) {
            cur = pre.next;
            for (int i = 1; i < k; i++) {
                node = cur.next;
                cur.next = node.next;
                node.next = pre.next;
                pre.next = node;
            }
            pre = cur;
            num = num - k;
        }

        return p.next;
    }


    private int countZero(int n) {
        int res = 0;
        int count;
        for (int i = 5; i <= n; i++) {
            count = 0;
            int temp = i;
            while (temp % 5 == 0) {
                count++;
                temp = temp / 5;
            }
            res += count * (n - i + 1);
        }
        return res;
    }

    private int isSatisfied(int[][] nums, int n) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(nums[0][0]);
        boolean[] flag = new boolean[n];
        for (boolean f : flag) {
            f = false;
        }
        while (!queue.isEmpty()) {
            int k = queue.poll();
            flag[k - 1] = true;
            for (int[] num : nums) {
                if (num[0] == k) {
                    if (queue.contains(num[1])) {
                        return 0;
                    }
                    if (!flag[num[1] - 1]) {
                        queue.offer(num[1]);
                    }
                }
            }

        }
        return 1;
    }

    private String GetResult(int N) {
        int start = 1;
        int end = 0;
        int i = 1;
        while (end < N) {
            start = i + start;
            end = start + i;
            i++;
        }

        int count = N - start;
        int k = i - count;
        int m = 1 + count;

        if ((i & 1) == 0) {
            return m + "/" + k;
        } else {
            return k + "/" + m;
        }
    }

    public int[][] setZeroes(int[][] matrix) {
        boolean rowZero = false;
        boolean columnZero = false;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    if (i == 0 || j == 0) {
                        if (i == 0)
                            rowZero = true;
                        if (j == 0)
                            columnZero = true;
                    } else {
                        matrix[i][0] = 0;
                        matrix[0][j] = 0;
                    }
                }
            }
        }
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][0] == 0) {
                for (int j = 1; j < matrix[0].length; j++) {
                    matrix[i][j] = 0;
                }
            }
        }
        for (int j = 0; j < matrix[0].length; j++) {
            if (matrix[0][j] == 0) {
                for (int i = 1; i < matrix.length; i++) {
                    matrix[i][j] = 0;
                }
            }
        }
        if (rowZero) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[0][j] = 0;
            }
        }
        if (columnZero) {
            for (int i = 0; i < matrix.length; i++) {
                matrix[i][0] = 0;
            }
        }
        return matrix;
    }
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null)
            return null;
        ListNode p1 = head;
        int i = 1;
        while (i < n) {
            p1 = p1.next;
            i++;
        }
        ListNode p2 = head;
        ListNode pre = null;
        while (p1.next != null) {
            p1 = p1.next;
            p2 = p2.next;
            if (pre == null) {
                pre = head;
            } else {
                pre = pre.next;
            }
        }
        if (pre == null) {
            return head.next;
        } else {
            pre.next = p2.next;
            p2.next = null;
            return head;
        }
    }

    private double distance(int n, int m) {
        double res = n;
        double dis = (double) n / 2;
        int count = 1;
        while (count < m) {
            res += dis * 2;
            dis /= 2;
            count++;
        }
        return res;
    }

    private void bubbleSort(int[] nums) {
        if (nums == null || nums.length == 0)
            return;
        for (int i = nums.length - 1; i >= 0; i--) {
            for (int j = 0; j < i; j++) {
                if (nums[j] > nums[j + 1]) {
                    int temp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = temp;
                }
            }
        }
    }


    private String[] matchArray(String[] str, String pat) {
        String[] res = new String[str.length];
        int index = res.length - 1;
        int count = 0;
        for (int i = 0; i < res.length; i++) {
            res[i] = null;
        }
        for (String s : str) {
            if (stringMatch(s, pat)) {
                res[index--] = s;
                count++;
            }
        }
        String[] ss = new String[count];
        index = res.length - 1;
        for (int i = ss.length - 1; i >= 0; i--) {
            ss[i] = res[index--];
        }
        return ss;
    }
    private boolean stringMatch(String source, String pattern) {
        int i = 0;
        int j = 0;
        while (i < source.length() && j < pattern.length()) {
            if (source.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
            } else {
                i = i - (j - 1);
                j = 0;
            }
        }
        return j == pattern.length();
    }

    private int func(int[][] nums) {
        Arrays.sort(nums, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] > o2[0]) {
                    return 1;
                } else if (o1[0] < o2[0]){
                    return -1;
                } else {
                    return 0;
                }
            }
        });

        int[] dp = new int[nums.length];
        for (int i = 0; i < dp.length; i++) {
            dp[i] = 1;
        }
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i][0] >= nums[j][0] && nums[i][1] >= nums[j][1]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    private int count = 0;
    private int palinCount(String s) {
        for (int i = 0; i < s.length(); i++) {
            explore(s, i, i);
            explore(s, i, i + 1);
        }
        return count;
    }

    private void explore(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            count++;
            left--;
            right++;
        }
    }

    private int minBox(int x) {
        int[] dp = new int[x + 1];
        for (int i = 0; i < dp.length; i++) {
            dp[i] = Integer.MAX_VALUE;
        }
        for (int i = 1; i < dp.length; i++) {
            if (i == 3 || i == 5 || i == 7) {
                dp[i] = 1;
            } else {
                int m = Integer.MAX_VALUE;
                int n = Integer.MAX_VALUE;
                int p = Integer.MAX_VALUE;
                if (i - 3 > 0) {
                    m = dp[i - 3];
                }
                if (i - 5 > 0) {
                    n = dp[i - 5];
                }
                if (i - 7 > 0) {
                    p = dp[i - 7];
                }
                int min = Math.min(m, Math.min(n, p));
                if (min != Integer.MAX_VALUE) {
                    dp[i] = min + 1;
                }
            }
        }
        return dp[x] == Integer.MAX_VALUE ? -1 : dp[x];
    }

    private int moveSteps(int x, int y) {
        int s = x + y;
        int top = 1;
        for (int i = s; i >= y + 1; i--) {
            top *= i;
        }
        int bot = 1;
        for (int i = x; i >= 1; i--) {
            bot *= i;
        }
        return top / bot;
    }

    /**
     * 给你一个二进制字符串数组 strs 和两个整数 m 和 n 。
     *
     * 请你找出并返回 strs 的最大子集的大小，该子集中 最多 有 m 个 0 和 n 个 1 。
     *
     * 如果 x 的所有元素也是 y 的元素，集合 x 是集合 y 的 子集 。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/ones-and-zeroes
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param strs
     * @param m
     * @param n
     * @return
     */
    public int findMaxForm(String[] strs, int m, int n) {
        int[][] dp = new int[m + 1][n + 1];
        int length = strs.length;
        for (int i = 0; i < length; i++) {
            int[] zerosOnes = getZerosOnes(strs[i]);
            int zeros = zerosOnes[0], ones = zerosOnes[1];
            for (int j = m; j >= zeros; j--) {
                for (int k = n; k >= ones; k--) {
                    dp[j][k] = Math.max(dp[j][k], dp[j - zeros][k - ones] + 1);
                }
            }
        }
        return dp[m][n];
    }

    public int[] getZerosOnes(String str) {
        int[] zerosOnes = new int[2];
        int length = str.length();
        for (int i = 0; i < length; i++) {
            zerosOnes[str.charAt(i) - '0']++;
        }
        return zerosOnes;
    }

    public static void main(String[] args) {

        Main main = new Main();
        Scanner s = new Scanner(System.in);

        int x = s.nextInt();
        int y = s.nextInt();
        System.out.println(main.moveSteps(x, y));

    }

}

class Point {
    int x;
    int y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
















