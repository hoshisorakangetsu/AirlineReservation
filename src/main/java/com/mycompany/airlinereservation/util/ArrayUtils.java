package com.mycompany.airlinereservation.util;

import java.util.Arrays;

public class ArrayUtils {
    
    // finds the first index of the `obj` in the `objArr`
    // returns -1 if not found
    public static int indexOf(Object[] objArr, Object obj) {
        for (int i = 0; i < objArr.length; i++) {
            // check for null, null checks need to use ==
            // and technically even if is not null, as long as reference same then it shud be the same obj right
            if (objArr[i] == obj)
                return i;

            // last resort, use the object's equals, which can have diff implements for objects of diff reference but should equal to each other
            if (objArr[i].equals(obj))
                return i;
        }

        return -1;
    }

    // assumes all elements in arr1 are not null, and all elements in arr2 are not null
    public static <T> T[] appendArray(T[] arr1, T[] arr2) {
        // create a new array where the size is arr1+arr2
        T[] newArr = Arrays.copyOf(arr1, arr1.length + arr2.length);
        // copy all the arr2 content to the new array
        System.arraycopy(arr2, 0, newArr, arr1.length, arr2.length);

        return newArr;
    }

    // appends the object T into array, and returns it as Object[], so need to self cast it back
    public static <T> T[] appendIntoArray(T[] arr, T obj) {
        int firstNull = ArrayUtils.indexOf(arr, null);
        // if the array alrd has empty space
        if (firstNull != -1) {
            arr[firstNull] = obj;
            return arr;
        } else {
            // copy the array into a new array with the length is 1 bigger (performance isnt concerned)
            T[] newArr = Arrays.copyOf(arr, arr.length + 1);
            newArr[newArr.length - 1] = obj;
            return newArr;
        }
    }
}
