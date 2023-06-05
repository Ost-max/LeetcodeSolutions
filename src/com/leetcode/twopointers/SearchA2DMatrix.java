package com.leetcode.twopointers;

import com.leetcode.BinarySearch;

//https://leetcode.com/explore/interview/card/top-interview-questions-medium/110/sorting-and-searching/806/
public class SearchA2DMatrix {

    public static void main(String[] args) {
        System.out.println(new SearchA2DMatrix().searchMatrix(new int[][]{  {1,4,7,11,15},
                                                                            {2,5,8,12,19},
                                                                            {3,6,9,16,22},
                                                                            {10,13,14,17,24},
                                                                            {18,21,23,26,30}
                },
                5));

        System.out.println(new SearchA2DMatrix().searchMatrix(new int[][]{{ -5}}, -5));
        System.out.println(new SearchA2DMatrix().searchMatrix(new int[][]{{ 1, 1}}, 1));

        System.out.println(new SearchA2DMatrix().searchMatrix(new int[][]{{5},
                                                                          {6}}, 5));


    }

    public boolean searchMatrix(int[][] matrix, int target) {
       BinarySearch search =  new BinarySearch();
        for(int i = 0; i<matrix.length;  i++) {
            var p1 = search(matrix, target);
            if( p1 != -1) {
                return true;
            }
        }
        return false;
    }


    public int search(int[][] arr, int searchEl) {
        return search(arr, 0, arr[0].length, 0, arr.length, searchEl);

    }

    public int search(int[][] arr, int leftRow, int rightRow, int leftCol, int rightCol, int searchEl) {
        int rowIndex = (rightRow + leftRow)/ 2;
        int colIndex = (rightCol + leftCol)/ 2;
        if (rightRow == leftRow || arr[colIndex].length <= rowIndex) {
            return -1;
        }
        int middleEl = arr[colIndex][rowIndex];
        if(middleEl == searchEl) {
            return rowIndex;
        } else if (middleEl < searchEl) {
            return search(arr, rowIndex + 1, rightRow, rowIndex + 1, rightCol, searchEl);
        } else {
            return search(arr, leftRow, Math.min(rowIndex - 1, 0), leftCol, Math.min(colIndex - 1, 0),  searchEl);
        }
    }


}
