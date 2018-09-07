package com.company;

public class HeapSort {
    /*
    数据升序排序，调整为大根堆；
     */
    private void heapAdjust(int[] nums, int start, int end) {
        int temp = nums[start];
        for (int i = 2 * start + 1; i <= end; i = 2 * i + 1) {
            if (i < end && nums[i] < nums[i + 1]) {
                i++;
            }
            if (temp >= nums[i])
                return;
            swap(nums, start, i);
            start = i;
        }
    }

    private void heapSort(int[] nums) {
        if (nums == null || nums.length == 0)
            return;
        for (int i = nums.length / 2 - 1; i >= 0; i--) {
            heapAdjust(nums, i, nums.length - 1);
        }
        for (int i = nums.length - 1; i >= 0; i--) {
            swap(nums, 0, i);
            heapAdjust(nums, 0, i - 1);
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}

