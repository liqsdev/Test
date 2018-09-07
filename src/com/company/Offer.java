package com.company;

import java.util.*;

public class Offer {
    /*
4数之和；
 */
    private List<List<Integer>> fourSum(int[] nums, int target) {
        Stack<Integer> stack = new Stack<>();
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        subSet(nums, target, stack, res, 0);
        return res;
    }

    private void subSet(int[] nums, int target, Stack<Integer> stack, List<List<Integer>> res, int start) {

        if (target == 0 && stack.size() == 4) {
            List<Integer> list = new ArrayList<>(stack);
            res.add(list);
            return;
        }
        for (int i = start; i < nums.length; i++) {
            stack.push(nums[i]);
            subSet(nums, target - nums[i], stack, res, i + 1);
            stack.pop();
        }
    }

    /**
     * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字，
     * 例如，如果输入如下4 X 4矩阵： 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16
     * 则依次打印出数字1,2,3,4,8,12,16,15,14,13,9,5,6,7,11,10.
     *
     * @param nums
     */
    private void printMatrix(int[][] nums) {
        if (nums == null) {
            return;
        }
        int start = 0;
        while (nums.length > start * 2 && nums[0].length > start * 2) {
            printMatrixInCircle(nums, start);
            start++;
        }
    }

    private void printMatrixInCircle(int[][] nums, int start) {
        //endX指终止列号，即横向终点；
        //endY指终止行号，即纵向终点；
        int endX = nums[0].length - 1 - start;
        int endY = nums.length - 1 - start;
        for (int i = start; i <= endX; i++) {
            System.out.print(nums[start][i] + " ");
        }
        if (start < endY) {
            for (int i = start + 1; i <= endY; i++) {
                System.out.print(nums[i][endX] + " ");
            }
        }
        if (start < endX && start < endY) {
            for (int i = endX - 1; i >= start; i--) {
                System.out.print(nums[endY][i] + " ");
            }
        }
        if (start < endX && start < endY - 1) {
            for (int i = endY - 1; i >= start + 1; i--) {
                System.out.print(nums[i][start] + " ");
            }
        }
    }

    /*
    根据前序遍历和中序遍历得出后序遍历；
     */
    private int[] getPosArray(int[] pre, int[] in) {
        if (pre == null || in == null) {
            return null;
        }
        int[] res = new int[pre.length];
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < pre.length; i++) {
            map.put(in[i], i);
        }
        setPos(pre, 0, pre.length - 1, in, 0, in.length - 1, res, 0, map);
        return res;
    }

    private int setPos(int[] p, int pi, int pj, int[] n, int ni, int nj,
                       int[] s, int si, HashMap<Integer, Integer> map) {
        if (pi > pj) {
            return si;
        }
        s[si--] = p[pi];
        int i = map.get(p[pi]);
        si = setPos(p, pj - nj + i + 1, pj, n, i + 1, nj, s, si, map);
        return setPos(p, pi + 1, pi + i - ni, n, ni, i - 1, s, si, map);
    }

    /*
    一个长度为n的数组里，所有数字都在0~n-1范围内，数组中某些数字是重复的；
    找出任意一个重复数字；
     */
    private int duplicate(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 0 || nums[i] > nums.length - 1) {
                return -1;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] != i) {
                if (nums[i] == nums[nums[i]]) {
                    return nums[i];
                }
                int temp = nums[i];
                nums[i] = nums[temp];
                nums[temp] = temp;
            }
        }
        return -1;
    }


    /*
    二维数组查找；
    */
    private boolean find(int[][] matrix, int target) {
        if (matrix == null)
            return false;
        int row = 0;
        int col = matrix[0].length - 1;
        while (row < matrix.length && col >= 0) {
            if (matrix[row][col] == target)
                return true;
            if (matrix[row][col] > target) {
                col--;
            } else {
                row++;
            }
        }
        return false;
    }

    /*
    打印出从1到最大的n位数；
    */
    private void print1ToMaxOfNDigits(int n) {
        if (n <= 0)
            return;
        char[] nums = new char[n];
        for (int i = 0; i < 10; i++) {
            nums[0] = String.valueOf(i).charAt(0);
            print1ToMaxOfNDigitsRecursively(nums, 1);
        }
    }

    private void print1ToMaxOfNDigitsRecursively(char[] nums, int index) {
        if (index == nums.length) {
            printNumber(nums);
            return;
        }
        for (int i = 0; i < 10; i++) {
            nums[index] = String.valueOf(i).charAt(0);
            print1ToMaxOfNDigitsRecursively(nums, index + 1);
        }
    }

    private void printNumber(char[] nums) {
        boolean isFirstNonZero = false;
        for (int i = 0; i < nums.length; i++) {
            if (!isFirstNonZero && nums[i] != '0') {
                isFirstNonZero = true;
            }
            if (isFirstNonZero) {
                System.out.print(nums[i]);
            }
        }
        System.out.println();
    }

    /*
   表示数值的字符串；
   */
    private boolean isNumeric(String str) {
        if (str == null)
            return false;
        char[] nums = str.toCharArray();
        int start = 0;
        if (nums[start] == '+' || nums[start] == '-') {
            start++;
        }
        int index = scanUnsignedInteger(nums, start);
        boolean numeric = index > start;
        if (index == nums.length) {
            return true;
        }
        if (nums[index] == '.') {
            if (index + 1 == nums.length) {
                return numeric;
            }
            int endpoint = scanUnsignedInteger(nums, index + 1);
            numeric = numeric || (endpoint > index + 1);
            index = endpoint;
        }
        if (nums[index] == 'e' || nums[index] == 'E') {
            index++;
            if (nums[index] == '+' || nums[index] == '-') {
                index++;
            }
            int endpoint = scanUnsignedInteger(nums, index);
            numeric = numeric && (endpoint > index);
            index = endpoint;
        }
        return numeric && (index == nums.length);
    }

    private int scanUnsignedInteger(char[] nums, int start) {
        int i = start;
        while (i < nums.length && nums[i] <= '9' && nums[i] >= '0') {
            i++;
        }
        return i;
    }


    /*
    调整数组顺序使奇数位于偶数前面；
     */
    private int[] reorderOddEven(int[] nums) {
        if (nums == null || nums.length == 0)
            return null;
        int start = 0;
        int end = nums.length - 1;
        while (start < end) {
            while (start < end && (nums[start] & 1) == 1) {
                start++;
            }
            while (start < end && (nums[end] & 1) == 0) {
                end--;
            }
            if (start < end) {
                int temp = nums[start];
                nums[start] = nums[end];
                nums[end] = temp;
            }
        }
        return nums;
    }


    /*
    计算a^b%n;
    思想是将b换成二进制，每次右移一位，根据奇偶进行判断；
     */
    private long power(int a, int b, int n) {
        long ans = 1;
        long temp = a;
        while (b > 0) {
            if ((b & 1) == 1) {
                ans = (ans * temp) % n;
            }
            temp = (temp * temp) % n;
            b = b / 2;
        }
        return ans % n;
    }


    /*
    求字符串的全排列；
     */
    private void permutation(char[] str) {
        if (str == null || str.length == 0)
            return;
        per(str, 0);
    }

    private void per(char[] str, int start) {
        if (start == str.length) {
            System.out.println(String.valueOf(str));
        } else {
            char temp;
            for (int i = start; i < str.length; i++) {
                temp = str[i];
                str[i] = str[start];
                str[start] = temp;
                per(str, start + 1);
                temp = str[i];
                str[i] = str[start];
                str[start] = temp;
            }
        }
    }

    /**
     * 求字符串的最长回文子串
     *
     * @param s 给定的字符串
     * @return 最长回文子串
     */
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        int[][] dp = new int[s.length()][s.length()];
        for (int i = 0; i < dp.length; i++) {
            dp[i][i] = 1;
        }
        int max = -1;
        int start = -1;
        int end = -1;
        for (int i = 0; i < dp.length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (s.charAt(i) != s.charAt(j)) {
                    dp[j][i] = 0;
                } else {
                    if (i - j == 1) {
                        dp[j][i] = 2;
                    } else {
                        if (dp[j + 1][i - 1] != 0) {
                            dp[j][i] = dp[j + 1][i - 1] + 2;
                        } else {
                            dp[j][i] = 0;
                        }
                    }
                }
                if (dp[j][i] > max) {
                    max = dp[j][i];
                    start = j;
                    end = i;
                }
            }
        }
        return s.substring(start, end + 1);
    }

    /**
     * 生成窗口最大值数组
     *
     * @param arr 数组
     * @param w   窗口大小
     * @return 窗口最大值数组
     */
    private int[] getMaxWindow(int arr[], int w) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        LinkedList<Integer> qmax = new LinkedList<>();
        int[] res = new int[arr.length - w + 1];
        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            while (!qmax.isEmpty() && arr[qmax.peekLast()] <= arr[i]) {
                qmax.pollLast();
            }
            qmax.offerLast(i);
            if (qmax.peekFirst() == i - w) {
                qmax.pollFirst();
            }
            if (i >= w - 1) {
                res[index++] = arr[qmax.peekFirst()];
            }
        }
        return res;
    }

    /**
     * 在一个二维数组中（每个一维数组的长度相同），每一行都按照从左到右递增的顺序排序，
     * 每一列都按照从上到下递增的顺序排序。请完成一个函数，
     * 输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
     *
     * @param target 目标值
     * @param array  二维数组
     * @return 是否含有该整数
     */
    public boolean Find(int target, int[][] array) {
        if (array == null) {
            return false;
        }
        int i = 0;
        int j = array[0].length - 1;
        while (i < array.length && j >= 0) {
            if (array[i][j] == target) {
                return true;
            } else if (array[i][j] > target) {
                j--;
            } else {
                i++;
            }
        }
        return false;
    }

    /**
     * 输入一个链表，按链表值从尾到头的顺序返回一个ArrayList。
     *
     * @param listNode 链表
     * @return 链表从尾到头的列表
     */
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {

        Stack<Integer> stack = new Stack<>();
        while (listNode != null) {
            stack.push(listNode.val);
            listNode = listNode.next;
        }
        ArrayList<Integer> res = new ArrayList<>();
        while (!stack.isEmpty()) {
            res.add(stack.pop());
        }
        return res;
    }

    /**
     * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
     * 输入一个非减排序的数组的一个旋转，输出旋转数组的最小元素。
     * 例如数组{3,4,5,1,2}为{1,2,3,4,5}的一个旋转，该数组的最小值为1。
     * NOTE：给出的所有元素都大于0，若数组大小为0，请返回0。
     *
     * @param array 旋转数组
     * @return 数组的最小元素
     */
    public int minNumberInRotateArray(int[] array) {
        if (array == null || array.length == 0) {
            return 0;
        }
        int res = array[0];
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] > array[i + 1]) {
                return array[i + 1];
            }
        }
        return res;
    }

    /**
     * 现在要求输入一个整数n，请你输出斐波那契数列的第n项（从0开始，第0项为0）。
     * n<=39
     *
     * @param n
     * @return
     */
    public int Fibonacci(int n) {
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else {
            int a = 0;
            int b = 1;
            int res = 1;
            for (int i = 2; i <= n; i++) {
                res = a + b;
                a = b;
                b = res;
            }
            return res;
        }
    }

    /**
     * 输入一个整数，输出该数二进制表示中1的个数。其中负数用补码表示。
     *
     * @param n
     * @return
     */
    public int NumberOf1(int n) {
        int res = 0;
        while (n != 0) {
            n = n & (n - 1);
            res++;
        }
        return res;
    }

    /**
     * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有的奇数位于数组的前半部分，
     * 所有的偶数位于数组的后半部分，并保证奇数和奇数，偶数和偶数之间的相对位置不变。
     *
     * @param array 输入的整数数组
     */
    public void reOrderArray(int[] array) {
        if (array == null || array.length == 0) {
            return;
        }
        int[] res = new int[array.length];
        int start = 0;
        int end = res.length - 1;
        int i = 0;
        int j = array.length - 1;
        while (i < array.length && j >= 0) {
            if ((array[i] & 1) == 1) {
                res[start++] = array[i++];
            } else {
                i++;
            }
            if ((array[j] & 1) == 0) {
                res[end--] = array[j--];
            } else {
                j--;
            }
        }
        System.arraycopy(res, 0, array, 0, array.length);
    }

    /**
     * 输入一个链表，输出该链表中倒数第k个结点。
     *
     * @param head 链表的头节点
     * @param k
     * @return
     */
    public ListNode FindKthToTail(ListNode head, int k) {
        if (head == null) {
            return null;
        }
        ListNode p1 = head;
        ListNode p2 = head;
        try {
            while (k > 0) {
                p2 = p2.next;
                k--;
            }
        } catch (NullPointerException e) {
            return null;
        }
        while (p2 != null) {
            p1 = p1.next;
            p2 = p2.next;
        }
        return p1;
    }

    /**
     * 输入两棵二叉树A，B，判断B是不是A的子结构。（ps：我们约定空树不是任意一个树的子结构）
     *
     * @param root1 二叉树A
     * @param root2 二叉树B
     * @return
     */
    public boolean HasSubtree(TreeNode root1, TreeNode root2) {
        boolean result = false;
        if (root1 != null && root2 != null) {
            if (root1.val == root2.val) {
                result = DoesTree1haveTree2(root1, root2);
            }
            if (!result)
                result = HasSubtree(root1.left, root2) || HasSubtree(root1.right, root2);
        }
        return result;
    }

    public boolean DoesTree1haveTree2(TreeNode root1, TreeNode root2) {
        if (root2 == null) return true;
        if (root1 == null) return false;
        if (root1.val == root2.val)
            return DoesTree1haveTree2(root1.left, root2.left) && DoesTree1haveTree2(root1.right, root2.right);
        else
            return false;
    }

    /**
     * 操作给定的二叉树，将其变换为源二叉树的镜像。
     *
     * @param root
     */
    public void Mirror(TreeNode root) {
        if (root != null) {
            TreeNode node = root.left;
            root.left = root.right;
            root.right = node;
            Mirror(root.left);
            Mirror(root.right);
        }
    }

    /**
     * 从上往下打印出二叉树的每个节点，同层节点从左至右打印。
     *
     * @param root
     * @return
     */
    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        ArrayList<Integer> res = new ArrayList<>();
        if (root != null) {
            queue.offer(root);
        }
        TreeNode node;
        while (!queue.isEmpty()) {
            node = queue.poll();
            res.add(node.val);
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        return res;
    }

    /**
     * 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果。
     * 如果是则输出Yes,否则输出No。假设输入的数组的任意两个数字都互不相同。
     *
     * @param sequence
     * @return
     */
    public boolean VerifySquenceOfBST(int[] sequence) {
        if (sequence == null || sequence.length == 0) {
            return false;
        }
        return getRes(sequence, 0, sequence.length - 1);
    }

    public boolean getRes(int[] s, int start, int end) {
        int j = 0;
        int i = 0;
        if (end - start <= 1) {
            return true;
        }
        for (i = start; i < end; i++) {
            if (s[i] > s[end]) {
                break;
            }
        }

        for (j = i; j < end; j++) {
            if (s[j] < s[end]) {
                return false;
            }
        }
        boolean left = true;
        boolean right = true;
        if (i > 0) {
            left = getRes(s, start, i - 1);
        }
        if (i < s.length - 1) {
            right = getRes(s, i, end - 1);
        }
        return left && right;
    }

}
















