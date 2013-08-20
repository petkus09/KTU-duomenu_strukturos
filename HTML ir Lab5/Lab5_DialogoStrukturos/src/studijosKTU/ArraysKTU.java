package studijosKTU;
import java.lang.reflect.Array;
import java.util.Comparator;

/**
 *
 * @author Eimutis KTU
 */
public class ArraysKTU {
    /**
     * Sorts the specified array of objects into ascending order, according to
     * the <i>natural ordering</i> of its elements.  All elements in the array
     * must implement the <tt>Comparable</tt> interface.  Furthermore, all
     * elements in the array must be <i>mutually comparable</i> (that is,
     * <tt>e1.compareTo(e2)</tt> must not throw a <tt>ClassCastException</tt>
     * for any elements <tt>e1</tt> and <tt>e2</tt> in the array).<p>
     *
     * This sort is guaranteed to be <i>stable</i>:  equal elements will
     * not be reordered as a result of the sort.<p>
     *
     * The sorting algorithm is a modified mergesort (in which the merge is
     * omitted if the highest element in the low sublist is less than the
     * lowest element in the high sublist).  This algorithm offers guaranteed
     * n*log(n) performance.
     * 
     * @param a the array to be sorted.
     * @throws  ClassCastException if the array contains elements that are not
     *		<i>mutually comparable</i> (for example, strings and integers).
     * @see Comparable
     */
    public static void sort(Object[] a) {
        Object[] aux = (Object[])a.clone();
        mergeSort(aux, a, 0, a.length, 0);
    }

    /**
     * Sorts the specified range of the specified array of objects into
     * ascending order, according to the <i>natural ordering</i> of its
     * elements.  The range to be sorted extends from index
     * <tt>fromIndex</tt>, inclusive, to index <tt>toIndex</tt>, exclusive.
     * (If <tt>fromIndex==toIndex</tt>, the range to be sorted is empty.)  All
     * elements in this range must implement the <tt>Comparable</tt>
     * interface.  Furthermore, all elements in this range must be <i>mutually
     * comparable</i> (that is, <tt>e1.compareTo(e2)</tt> must not throw a
     * <tt>ClassCastException</tt> for any elements <tt>e1</tt> and
     * <tt>e2</tt> in the array).<p>
     *
     * This sort is guaranteed to be <i>stable</i>:  equal elements will
     * not be reordered as a result of the sort.<p>
     *
     * The sorting algorithm is a modified mergesort (in which the merge is
     * omitted if the highest element in the low sublist is less than the
     * lowest element in the high sublist).  This algorithm offers guaranteed
     * n*log(n) performance.
     * 
     * @param a the array to be sorted.
     * @param fromIndex the index of the first element (inclusive) to be
     *        sorted.
     * @param toIndex the index of the last element (exclusive) to be sorted.
     * @throws IllegalArgumentException if <tt>fromIndex &gt; toIndex</tt>
     * @throws ArrayIndexOutOfBoundsException if <tt>fromIndex &lt; 0</tt> or
     *	       <tt>toIndex &gt; a.length</tt>
     * @throws    ClassCastException if the array contains elements that are
     *		  not <i>mutually comparable</i> (for example, strings and
     *		  integers).
     * @see Comparable
     */
    public static void sort(Object[] a, int fromIndex, int toIndex) {
        rangeCheck(a.length, fromIndex, toIndex);
	Object[] aux = cloneSubarray(a, fromIndex, toIndex);
        mergeSort(aux, a, fromIndex, toIndex, -fromIndex);
    }

    /**
     * Tuning parameter: list size at or below which insertion sort will be
     * used in preference to mergesort or quicksort.
     */
    private static final int INSERTIONSORT_THRESHOLD = 7;

    /**
     * Clones an array within the specified bounds.
     * This method assumes that a is an array.
     */
    private static <T> T[] cloneSubarray(T[] a, int from, int to) {
        int n = to - from;
	T[] result = (T[])Array.newInstance(a.getClass().getComponentType(), n);
        System.arraycopy(a, from, result, 0, n);
        return result;
    }

    /**
     * Src is the source array that starts at index 0
     * Dest is the (possibly larger) array destination with a possible offset
     * low is the index in dest to start sorting
     * high is the end index in dest to end sorting
     * off is the offset to generate corresponding low, high in src
     */
    private static void mergeSort(Object[] src,
				  Object[] dest,
				  int low,
				  int high,
				  int off) {
	int length = high - low;

	// Insertion sort on smallest arrays
        if (length < INSERTIONSORT_THRESHOLD) {
            for (int i=low; i<high; i++)
                for (int j=i; j>low &&
			 ((Comparable) dest[j-1]).compareTo(dest[j])>0; j--)
                    swap(dest, j, j-1);
            return;
        }

        // Recursively sort halves of dest into src
        int destLow  = low;
        int destHigh = high;
        low  += off;
        high += off;
        int mid = (low + high) >> 1;
        mergeSort(dest, src, low, mid, -off);
        mergeSort(dest, src, mid, high, -off);

        // If list is already sorted, just copy from src to dest.  This is an
        // optimization that results in faster sorts for nearly ordered lists.
        if (((Comparable)src[mid-1]).compareTo(src[mid]) <= 0) {
            System.arraycopy(src, low, dest, destLow, length);
            return;
        }

        // Merge sorted halves (now in src) into dest
        for(int i = destLow, p = low, q = mid; i < destHigh; i++) {
            if (q >= high || p < mid && ((Comparable)src[p]).compareTo(src[q])<=0)
                dest[i] = src[p++];
            else
                dest[i] = src[q++];
        }
    }

    /**
     * Swaps x[a] with x[b].
     */
    private static void swap(Object[] x, int a, int b) {
	Object t = x[a];
	x[a] = x[b];
	x[b] = t;
    }

    /**
     * Sorts the specified array of objects according to the order induced by
     * the specified comparator.  All elements in the array must be
     * <i>mutually comparable</i> by the specified comparator (that is,
     * <tt>c.compare(e1, e2)</tt> must not throw a <tt>ClassCastException</tt>
     * for any elements <tt>e1</tt> and <tt>e2</tt> in the array).<p>
     *
     * This sort is guaranteed to be <i>stable</i>:  equal elements will
     * not be reordered as a result of the sort.<p>
     *
     * The sorting algorithm is a modified mergesort (in which the merge is
     * omitted if the highest element in the low sublist is less than the
     * lowest element in the high sublist).  This algorithm offers guaranteed
     * n*log(n) performance. 
     *
     * @param a the array to be sorted.
     * @param c the comparator to determine the order of the array.  A
     *        <tt>null</tt> value indicates that the elements' <i>natural
     *        ordering</i> should be used.
     * @throws  ClassCastException if the array contains elements that are
     *		not <i>mutually comparable</i> using the specified comparator.
     * @see Comparator
     */
    public static <T> void sort(T[] a, Comparator<? super T> c) {
	T[] aux = (T[])a.clone();
        if (c==null)
            mergeSort(aux, a, 0, a.length, 0);
        else
            mergeSort(aux, a, 0, a.length, 0, c);
    }

    /**
     * Sorts the specified range of the specified array of objects according
     * to the order induced by the specified comparator.  The range to be
     * sorted extends from index <tt>fromIndex</tt>, inclusive, to index
     * <tt>toIndex</tt>, exclusive.  (If <tt>fromIndex==toIndex</tt>, the
     * range to be sorted is empty.)  All elements in the range must be
     * <i>mutually comparable</i> by the specified comparator (that is,
     * <tt>c.compare(e1, e2)</tt> must not throw a <tt>ClassCastException</tt>
     * for any elements <tt>e1</tt> and <tt>e2</tt> in the range).<p>
     *
     * This sort is guaranteed to be <i>stable</i>:  equal elements will
     * not be reordered as a result of the sort.<p>
     *
     * The sorting algorithm is a modified mergesort (in which the merge is
     * omitted if the highest element in the low sublist is less than the
     * lowest element in the high sublist).  This algorithm offers guaranteed
     * n*log(n) performance. 
     *
     * @param a the array to be sorted.
     * @param fromIndex the index of the first element (inclusive) to be
     *        sorted.
     * @param toIndex the index of the last element (exclusive) to be sorted.
     * @param c the comparator to determine the order of the array.  A
     *        <tt>null</tt> value indicates that the elements' <i>natural
     *        ordering</i> should be used.
     * @throws ClassCastException if the array contains elements that are not
     *	       <i>mutually comparable</i> using the specified comparator.
     * @throws IllegalArgumentException if <tt>fromIndex &gt; toIndex</tt>
     * @throws ArrayIndexOutOfBoundsException if <tt>fromIndex &lt; 0</tt> or
     *	       <tt>toIndex &gt; a.length</tt>
     * @see Comparator
     */
    public static <T> void sort(T[] a, int fromIndex, int toIndex,
				Comparator<? super T> c) {
        rangeCheck(a.length, fromIndex, toIndex);
	T[] aux = (T[])cloneSubarray(a, fromIndex, toIndex);
        if (c==null)
            mergeSort(aux, a, fromIndex, toIndex, -fromIndex);
        else
            mergeSort(aux, a, fromIndex, toIndex, -fromIndex, c);
    }

    /**
     * Src is the source array that starts at index 0
     * Dest is the (possibly larger) array destination with a possible offset
     * low is the index in dest to start sorting
     * high is the end index in dest to end sorting
     * off is the offset into src corresponding to low in dest
     */
    private static void mergeSort(Object[] src,
				  Object[] dest,
				  int low, int high, int off,
				  Comparator c) {
	int length = high - low;

	// Insertion sort on smallest arrays
	if (length < INSERTIONSORT_THRESHOLD) {
            Object iOb = dest[low];     // iOb atitinka dest[i] objekta
	    for (int i=low+1; i<high; i++){
                Object j1Ob = iOb;      // j1Ob atitinka dest[j-1] objekta
                iOb = dest[i];
                Object jOb = iOb;
//		for (int j=i; j>low && c.compare(dest[j-1], dest[j])>0; j--)
		for (int j=i; ; ){
//		    swap(dest, j, j-1);
                    if(c.compare(j1Ob, jOb)<=0) break;
                    Object t=jOb; jOb=j1Ob; j1Ob=t;  // cia vyksta swap
                    if(--j==low) break;
                    jOb=j1Ob; j1Ob=dest[j];  // pereiname prie naujos poros
                }    
            }
	    return;
	}

        // Recursively sort halves of dest into src
        int destLow  = low;
        int destHigh = high;
        low  += off;
        high += off;
        int mid = (low + high) >> 1;
        mergeSort(dest, src, low, mid, -off, c);
        mergeSort(dest, src, mid, high, -off, c);

        // If list is already sorted, just copy from src to dest.  This is an
        // optimization that results in faster sorts for nearly ordered lists.
        if (c.compare(src[mid-1], src[mid]) <= 0) {
           System.arraycopy(src, low, dest, destLow, length);
           return;
        }

        // Merge sorted halves (now in src) into dest
            Object qOb=src[mid];
            Object pOb=src[low];
        for(int i = destLow, p = low, q = mid; i < destHigh; i++) {
//            if (q >= high || p < mid && c.compare(src[p], src[q]) <= 0)
            if (q >= high || p < mid && c.compare(pOb, qOb) <= 0)
//                dest[i] = src[p++];
                {dest[i] = pOb; pOb = src[p++];}
            else
//                dest[i] = src[q++];
                {dest[i] = qOb; pOb = src[q++];}
        }
    }
    /**
     * Check that fromIndex and toIndex are in range, and throw an
     * appropriate exception if they aren't.
     */
    private static void rangeCheck(int arrayLen, int fromIndex, int toIndex) {
        if (fromIndex > toIndex)
            throw new IllegalArgumentException("fromIndex(" + fromIndex +
                       ") > toIndex(" + toIndex+")");
        if (fromIndex < 0)
            throw new ArrayIndexOutOfBoundsException(fromIndex);
        if (toIndex > arrayLen)
            throw new ArrayIndexOutOfBoundsException(toIndex);
    }
}
