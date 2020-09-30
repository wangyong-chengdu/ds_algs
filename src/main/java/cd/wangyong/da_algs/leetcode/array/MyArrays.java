package cd.wangyong.da_algs.leetcode.array;

import java.util.Objects;

/**
 * @author wangyong
 */
public class MyArrays {

    private MyArrays() {
        throw new RuntimeException("ArrayUtils has non-instantiability.");
    }

    public static <E extends Comparable<? super E>> int binarySearch(E[] array, E key) {
        return binarySearch(array, 0, array.length, key);
    }

    /**
     * ���ֲ���
     */
    public static <E extends Comparable<? super E>> int binarySearch(E[] array, int fromIndex, int toIndex, E key) {
        Objects.requireNonNull(array, "array is null.");
//        rangeCheck(array.length, fromIndex, toIndex);

        return binarySearch1(array, fromIndex, toIndex, key, 0);
    }

//    private static <E> void rangeCheck(int length, int fromIndex, int toIndex) {
//        Assert.throwIfTrue(fromIndex > toIndex, "fromIndex(" + fromIndex + ") > toIndex(" + toIndex + ")");
//        ArrayAssert.throwIfTrue(fromIndex < 0, "fromIndex(" + fromIndex + ")");
//        ArrayAssert.throwIfTrue(toIndex > length, "toIndex(" + toIndex + ") > length(" + length + ")");
//    }

    private enum FirstLastEnum {
        FIRST(1), LAST(2);
        private int id;

        FirstLastEnum(int id) {
            this.id = id;
        }
        public int getId() {
            return id;
        }
    }

    public static <E extends Comparable<? super E>> int binarySearch(E[] array, E key, int firstLastFlag) {
        return binarySearch(array, 0, array.length, key, firstLastFlag);
    }

    public static <E extends Comparable<? super E>> int binarySearch(E[] array, int fromIndex, int toIndex, E key, int firstLastFlag) {

        Objects.requireNonNull(array, "array is null.");
//        rangeCheck(array.length, fromIndex, toIndex);

//        FirstLastEnum firstLastEnum = EnumUtils.ofId(FirstLastEnum.class, firstLastFlag);
        return binarySearch1(array, fromIndex, toIndex, key, firstLastFlag);
    }


    private static <E extends Comparable<? super E>> int binarySearch1(E[] array, int fromIndex, int toIndex, E key, int flag) {
        int low = fromIndex;
        int high = toIndex;
        int mid = low + ((high - low) >>> 1);

        while (low <= high) {
            int result = array[mid].compareTo(key);

            if (result < 0) {
                low = mid + 1;
                mid = low + ((high - low) >>> 1);
            }
            else if (result > 0) {
                high = mid - 1;
                mid = low + ((high - low) >>> 1);
            }
            else {
                if (flag == 0) {
                    return mid;
                }
                else if (flag == FirstLastEnum.FIRST.getId()) {
                    high = mid - 1;
                    if (high < 0 || array[high].compareTo(key) < 0) {
                        return mid;
                    }
                    else {
                        mid = low + ((high - low) >>> 1);
                    }
                }
                else {
                    low = mid + 1;
                    if (low >= array.length || array[low].compareTo(key) > 0) {
                        return mid;
                    }
                    else {
                        mid = low + ((high - low) >>> 1);
                    }
                }
            }
        }
        return -(low + 1);
    }

    public static <E extends Comparable<? super E>> int binarySearchByRecursive(E[] array, E key) {
        Objects.requireNonNull(array, "array is null.");
        return binarySearchByRecursive(array, 0, array.length, key);
    }

    public static <E extends Comparable<? super E>> int binarySearchByRecursive(E[] array, int fromIndex, int toIndex, E key) {
        Objects.requireNonNull(array, "array is null.");
//        rangeCheck(array.length, fromIndex, toIndex);

        return binarySearch2(array, fromIndex, toIndex, key);
    }

    private static <E extends Comparable<? super E>> int binarySearch2(E[] array, int fromIndex, int toIndex, E key) {
        if (fromIndex > toIndex) {
            return -(fromIndex + 1);
        }

        int mid = fromIndex + ((toIndex - fromIndex) >>> 1);
        int result = array[mid].compareTo(key);

        if (result == 0) {
            return mid;
        }
        else if (result < 0) {
            return binarySearch2(array, mid + 1, toIndex, key);
        }
        else {
            return binarySearch2(array, fromIndex, mid - 1, key);
        }
    }


    static class Node {
        private Integer id;
        private Object value;
        private Node left;
        private Node right;
    }

    /**
     * ��������
     */
    public void maxHeapify(Node[] a, int i) {
        int largest = -1;
        int l = a[i].left.id;
        int r = a[i].right.id;

        if (a[l].id <= a.length && a[l].id > a[i].id) {
            largest = l;
        }
        else {
            largest = i;
        }
        if (r <= a.length && a[r].id > a[largest].id) {
            largest = r;
        }

        if (largest != i) {
            Node temp = a[i];
            a[i] = a[largest];
            a[largest] = temp;

            maxHeapify(a, largest);
        }
    }
}
