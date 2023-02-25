package com.techreturn;

import java.util.Map;
import java.util.HashMap;


public class Cinema {
    public final int MaxRow = 3;
    public final int MaxRowSeat = 5;

    protected MovieSeat[][] listOfSeats = new MovieSeat[MaxRow][MaxRowSeat];
    // a map of the array index to label
    Map<Integer, String> labelMap = new HashMap<Integer, String>();

    //init all seat as available
    public Cinema(){
        String label;
        //construct all seats for cinema
        labelMap.put(0, "A");
        labelMap.put(1, "B");
        labelMap.put(2, "C");
        // set each
        for (int i=0; i< MaxRow; i++){
            label = labelMap.get(i);
            for (int j=0; j<MaxRowSeat; j++){
                this.listOfSeats[i][j] = new MovieSeat(label,j+1);
            }
        }
    }

    public int mapRowIndex( String rowLabel){
        //lookup hashmap to find the index for row A,B, & C
        int rowIdx = -1;
        for (int i : labelMap.keySet()){
            if (labelMap.get(i).equals(rowLabel)){
                rowIdx = i;
            }
        }
        return rowIdx;
    }

}
