package com.leetcode.graph;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.List;

//https://leetcode.com/problems/keys-and-rooms/description/
public class KeysAndRooms841 {

    public static void main(String[] args) {
        System.out.println(new KeysAndRooms841().canVisitAllRooms(Arrays.asList(List.of(1), List.of(2), List.of(3), List.of())));
        System.out.println(new KeysAndRooms841().canVisitAllRooms(Arrays.asList(List.of(1, 3), List.of(3, 0, 1), List.of(2), List.of(0))));
        System.out.println(new KeysAndRooms841().canVisitAllRooms(Arrays.asList(List.of(1), List.of(1))));

        //[[1,3],[3,0,1],[2],[0]]
    }


    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        var keys = new ArrayDeque<>(rooms.get(0));
        rooms.set(0, null);
        int count = 0;
        while(!keys.isEmpty()) {
           var key = keys.pop();
         //   System.out.println("visit " + key);
            if(rooms.get(key) != null) {
                count++;
                keys.addAll(rooms.get(key));
               rooms.set(key, null);
            }
        }
        return  count + 1 == rooms.size();
    }

}
