package com.CK;

public class Main {

    public static void main(String[] args) {
        int[] a = new int[]{1, 3, 5, 7};
        int[] b = new int[]{10, 11, 16, 20};
        int[] c = new int[]{23, 30, 34, 50};

        int[][] matrix = new int[3][4];
        matrix[0] = a;
        matrix[1] = b;
        matrix[2] = c;
        Solution solution = new Solution();
        System.out.println(solution.searchMatrix(matrix, 30));

    }
}

class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0 || matrix[0].length == 0) return false;
        int top = 0, bottom = matrix.length - 1, left = 0, right = matrix[0].length - 1;
        int tpMid, lrMid;
        while (top + 1 < bottom) {
            tpMid = (top + bottom) / 2;
            int tpMidVal = matrix[tpMid][0];
            if (tpMidVal == target) return true;
            else if (tpMidVal > target) bottom = tpMid;
            else top = tpMid;
        }
        if (matrix[bottom][0] == target) return true;
        if (matrix[top][0] == target) return true;
        if (matrix[bottom][0] < target) return findTargetValueInDeterminedLevel(matrix, target, bottom);
        else return findTargetValueInDeterminedLevel(matrix, target, top);
    }

    private boolean findTargetValueInDeterminedLevel(int[][] matrix, int target, int level) {
        int left = 0, right = matrix[0].length - 1, lrMid;
        while (left + 1 < right) {
            lrMid = (left + right) / 2;
            int lrMidVal = matrix[level][lrMid];
            if (lrMidVal == target) return true;
            else if (lrMidVal > target) right = lrMid;
            else left = lrMid;
        }
        if (matrix[level][left] == target) return true;
        else if (matrix[level][right] == target) return true;
        else return false;
    }
}

// Standard Binary Search
class Solution2 {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        if (m == 0) return false;
        int n = matrix[0].length;

        // binary search
        int left = 0, right = m * n - 1;
        int pivotIdx, pivotElement;
        while (left <= right) {
            pivotIdx = (left + right) / 2;
            pivotElement = matrix[pivotIdx / n][pivotIdx % n];
            if (target == pivotElement) return true;
            else {
                if (target < pivotElement) right = pivotIdx - 1;
                else left = pivotIdx + 1;
            }
        }
        return false;
    }
}