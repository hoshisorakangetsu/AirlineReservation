package com.mycompany.airlinereservation.util;

import java.util.ArrayList;
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
    @SuppressWarnings("unchecked")
    public static <T> T[] appendArray(T[] arr1, T[] arr2) {
        // since cannot create array of generic types, will create array list and then convert it back to array and return the array
        ArrayList<T> newArrList = new ArrayList<T>();

        // make the array contents into List, which is a Collection, to use the addAll method to put all the content into the ArrayList
        newArrList.addAll(Arrays.asList(arr1));
        newArrList.addAll(Arrays.asList(arr2));

        // this is the reason why we need to surpress warnings of unchecked, as casting Object to generic types can lead to `ClassCastException`
        return (T[]) newArrList.toArray();
    }

    // appends the object T into array T[]
    @SuppressWarnings("unchecked")
    public static <T> T[] appendIntoArray(T[] arr, T obj) {
        int firstNull = ArrayUtils.indexOf(arr, null);
        // if the array alrd has empty space
        if (firstNull != -1) {
            arr[firstNull] = obj;
            return arr;
        } else {
            // performance isn't concerned, make arr into ArrayList and add obj into the arraylist, convert and return the arraylist as T[]
            ArrayList<T> arrList = new ArrayList<T>(Arrays.asList(arr));
            arrList.add(obj);

            return (T[]) arrList.toArray();
        }
    }
}
