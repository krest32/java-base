package Arithmetic.code;

public class Merge {

    public static void sort(int[] arr) {
        separator(arr, 0, arr.length - 1);
    }

    private static void separator(int[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) >> 1;
            separator(arr, left, mid);
            separator(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    private static void merge(int[] a, int left, int mid, int right) {
        // 辅助数组
        int[] tmp = new int[a.length];
        // p1、p2是检测指针，k是存放指针
        int p1 = left, p2 = mid + 1, k = left;

        while (p1 <= mid && p2 <= right) {
            if (a[p1] <= a[p2]) {
                tmp[k++] = a[p1++];
            } else {
                tmp[k++] = a[p2++];
            }
        }

        while (p1 <= mid) {
            // 如果第一个序列未检测完，直接将后面所有元素加到合并的序列中
            tmp[k++] = a[p1++];
        }
        while (p2 <= right) {
            tmp[k++] = a[p2++];
        }

        // 复制回原素组
        if (right + 1 - left >= 0) {
            System.arraycopy(tmp, left, a, left, right + 1 - left);
        }
    }
}
