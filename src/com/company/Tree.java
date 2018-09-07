package com.company;
/*

自底向上输出二叉树的层次遍历；

 */

import java.util.*;

public class Tree {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        Queue<TreeNode> queue = new ArrayDeque<>();
        if (root == null)
            return res;
        queue.offer(root);
        while (!queue.isEmpty()) {
            List<Integer> list = new ArrayList<>();
            int size = queue.size();
            while (size-- > 0) {
                TreeNode temp = queue.poll();
                list.add(temp.val);
                if (temp.left != null) {
                    queue.add(temp.left);
                }
                if (temp.right != null) {
                    queue.add(temp.right);
                }
            }
            res.add(0, list);
        }
        return res;
    }

    private boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null)
            return true;
        if (p == null || q == null)
            return false;
        if (p.val == q.val) {
            return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        } else {
            return false;
        }
    }
/*
给定一个所有节点为非负值的二叉搜索树，求树中任意两节点的差的绝对值的最小值。
 */
    private int getMinimumDifference(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        int res = -1;
        int last = -1;
        TreeNode p = root;
        while (p != null || !stack.isEmpty()) {
            if (p != null) {
                stack.push(p);
                p = p.left;
            } else {
                TreeNode temp = stack.pop();
                if (last >= 0) {
                    if (res != -1) {
                        res = Math.min(res, Math.abs(temp.val - last));
                    } else {
                        res = Math.abs(temp.val - last);
                    }
                }
                last = temp.val;
                p = temp.right;
            }
        }
        return res;
    }

    private List<String> binaryTreePaths(TreeNode root) {
        List<String> resList = new ArrayList<>();
        if (root == null)
            return resList;
        String s = "";
        binaryTreePathsRecursive(root, s, resList);
        return resList;
    }

    private void binaryTreePathsRecursive(TreeNode root, String s, List<String> list) {
        if (root == null)
            return;
        s += root.val;
        if (root.left != null) {
            binaryTreePathsRecursive(root.left, s + "->", list);
        }
        if (root.right != null) {
            binaryTreePathsRecursive(root.right, s + "->", list);
        }
        if (root.left == null && root.right == null) {
            list.add(s);
        }
    }


    /*
    给定一个二叉树，计算整个树的坡度。
    一个树的节点的坡度定义即为，该节点左子树的结点之和和右子树结点之和的差的绝对值。空结点的的坡度是0。
    整个树的坡度就是其所有节点的坡度之和。

    输入:
             1
           /   \
          2     3
    输出: 1
    解释:
    结点的坡度 2 : 0
    结点的坡度 3 : 0
    结点的坡度 1 : |2-3| = 1
    树的坡度 : 0 + 0 + 1 = 1
     */
    private int findTilt(TreeNode root) {
        if (root == null)
            return 0;
        int sum = Math.abs(treeSum(root.left) - treeSum(root.right));
        sum += findTilt(root.left);
        sum += findTilt(root.right);
        return sum;
    }

    private int treeSum(TreeNode root) {
        if (root == null)
            return 0;
        return root.val + treeSum(root.left) + treeSum(root.right);
    }

/*
给定一个二叉搜索树，同时给定最小边界L 和最大边界 R。
通过修剪二叉搜索树，使得所有节点的值在[L, R]中 (R>=L) 。
你可能需要改变树的根节点，所以结果应当返回修剪好的二叉搜索树的新的根节点。
 */

    private TreeNode trimBST(TreeNode root, int L, int R) {
        if (root == null)
            return root;
        if (root.val < L) {
            return trimBST(root.right, L, R);
        } else if (root.val > R) {
            return trimBST(root.left, L, R);
        } else {
            root.left = trimBST(root.left, L, R);
            root.right = trimBST(root.right, L, R);
            return root;
        }
    }


    private int sumOfLeftLeaves(TreeNode root) {
        if (root == null)
            return 0;
        TreeNode left = root.left;
        if (left == null) {
            return sumOfLeftLeaves(root.right);
        } else if (left.left == null && left.right == null) {
            return left.val + sumOfLeftLeaves(root.right);
        } else {
            return sumOfLeftLeaves(root.left) + sumOfLeftLeaves(root.right);
        }
    }


/*
给定一棵二叉搜索树, 找到该树中两个指定节点的最近公共祖先。
 */
    private TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == p || root == q)
            return root;
        if (p.val < root.val && q.val < root.val)
            return lowestCommonAncestor(root.left, p, q);
        if (p.val > root.val && q.val > root.val) {
            return lowestCommonAncestor(root.right, p, q);
        } else {
            return root;
        }
    }

/*
给定一个二叉搜索树的根结点 root, 返回树中任意两节点的差的最小值。
 */
    private int minDiffInBST(TreeNode root) {
        if (root.left == null && root.right == null)
            return Integer.MAX_VALUE;
        int res = Integer.MAX_VALUE;
        if (root.left == null) {
            return Math.min(minValue(root.right) - root.val, minDiffInBST(root.right));
        }
        if (root.right == null) {
            return Math.min(root.val - maxValue(root.left), minDiffInBST(root.left));
        } else {
            res = Math.min(root.val - maxValue(root.left), minValue(root.right) - root.val);
            int leftVal = minDiffInBST(root.left);
            int rightVal = minDiffInBST(root.right);
            res = Math.min(res, Math.min(leftVal, rightVal));
        }
        return res;
    }

    private int maxValue(TreeNode root) {
        TreeNode temp = root;
        while (temp.right != null) {
            temp = temp.right;
        }
        return temp.val;
    }
    private int minValue(TreeNode root) {
        TreeNode temp = root;
        while (temp.left != null) {
            temp = temp.left;
        }
        return temp.val;
    }

/*
给定一个非空特殊的二叉树，每个节点都是正数，并且每个节点的子节点数量只能为 2 或 0。
如果一个节点有两个子节点的话，那么这个节点的值不大于它的子节点的值。
给出这样的一个二叉树，你需要输出所有节点中的第二小的值。如果第二小的值不存在的话，输出 -1 。
 */
    private int findSecondMinimumValue(TreeNode root) {
        if (root.left == null && root.right == null)
            return -1;
        if (root.val == root.left.val && root.val == root.right.val) {
            int l = findSecondMinimumValue(root.left);
            int r = findSecondMinimumValue(root.right);
            if (l == -1) {
                return r;
            }
            if (r == -1) {
                return l;
            }
            return Math.min(l, r);
        }
        if (root.val == root.left.val) {
            if (findSecondMinimumValue(root.left) == -1)
                return root.right.val;
            return Math.min(findSecondMinimumValue(root.left), root.right.val);
        }
        if (root.val == root.right.val) {
            if (findSecondMinimumValue(root.right) == -1)
                return root.left.val;
            return Math.min(findSecondMinimumValue(root.right), root.left.val);
        }
        return Math.min(root.left.val, root.right.val);
    }
/*
求出二叉树每层节点的最大值
 */
    private List<Integer> maxEachLevel(TreeNode root) {
        if (root == null)
            return null;
        List<Integer> list = new ArrayList<>();
        int curLen = 0;
        int nextLen = 0;
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        curLen = 1;
        TreeNode node;
        int maxValue = Integer.MIN_VALUE;
        while (!queue.isEmpty()) {
            node = queue.poll();
            if (node.left != null) {
                queue.offer(node.left);
                nextLen++;
            }
            if (node.right != null) {
                queue.offer(node.right);
                nextLen++;
            }
            if (node.val > maxValue) {
                maxValue = node.val;
            }
            if (--curLen == 0) {
                curLen = nextLen;
                nextLen = 0;
                list.add(maxValue);
                maxValue = Integer.MIN_VALUE;
            }
        }
        return list;
    }


/*
给定一个二叉树，判断它是否是高度平衡的二叉树。
一棵高度平衡二叉树定义为：
一个二叉树每个节点的左右两个子树的高度差的绝对值不超过1。
 */
    private boolean isBalanced(TreeNode root) {
        if (root == null)
            return true;
        if (root.left == null && root.right == null)
            return true;
        if (Math.abs(treeHeight(root.left) - treeHeight(root.right)) <= 1) {
            return isBalanced(root.left) && isBalanced(root.right);
        } else {
            return false;
        }
    }
    private int treeHeight(TreeNode root) {
        if (root == null)
            return 0;
        return 1 + Math.max(treeHeight(root.left), treeHeight(root.right));
    }

    /**
     * 给定一个二叉树，检查它是否是镜像对称的。
     * @param root 二叉树根节点
     * @return 是否是镜像对称的
     */
    private boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isSymmetric(root.left, root.right);
    }
    private boolean isSymmetric(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if (left == null || right == null) {
            return false;
        }
        if (left.val != right.val) {
            return false;
        }
        return isSymmetric(left.left, right.right) &&
                isSymmetric(left.right, right.left);
    }


    private int getTreeWidth(MTree root) {
        if (root == null)
            return 0;
        Queue<MTree> nodeQueue = new ArrayDeque<>();
        int curWidth = 1;
        int nextWidth = 0;
        int res = 0;
        MTree node;
        nodeQueue.offer(root);
        while (!nodeQueue.isEmpty()) {
            node = nodeQueue.poll();
            curWidth--;
            for (MTree t : node.children) {
                nodeQueue.offer(t);
                nextWidth++;
            }
            if (curWidth == 0) {
                curWidth = nextWidth;
                nextWidth = 0;
                if (curWidth > res)
                    res = curWidth;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode l1 = new TreeNode(2);
        TreeNode l2 = new TreeNode(5);
        TreeNode r1 = new TreeNode(3);
        root.left = l1;
        root.right = r1;
        l1.right = l2;
        Tree t = new Tree();
        System.out.println(t.maxEachLevel(root));
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
        left = null;
        right = null;
    }
}

class MTree {
    int val;
    List<MTree> children;
}