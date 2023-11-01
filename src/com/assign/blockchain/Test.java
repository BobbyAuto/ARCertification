package com.assign.blockchain;

import java.util.ArrayList;
import java.util.Calendar;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<String> sourceList = new ArrayList<>();
        

        // Add elements to the source list
        sourceList.add("Item 1");
        sourceList.add("Item 2");
        sourceList.add("Item 3");
        sourceList.add("Item 5");
        
        ArrayList<String> destinationList = (ArrayList<String>) sourceList.clone();
        sourceList.set(0, "11111");
        
        System.out.println(destinationList.get(0));


	}

}
