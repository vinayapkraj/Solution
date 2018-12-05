package com.pricer.solution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class Solution {
	public static void main(String[] args) {
		
//		int[] inputArray = {1,2,5,1,3,6,7,1,2,8,4};
		int[] inputArray = {1, 3, 2, 1, 2, 1, 5, 3, 3, 4, 2};
		List<Integer> inputArrayTolist = Arrays.stream(inputArray).boxed().collect(Collectors.toList());
		
		int horizontalStrokes = 0;
		System.out.print("Input Array is :  ");
		inputArrayTolist.forEach(System.out::print);
		System.out.println("");
		horizontalStrokes+=calculateHorizontalStrokes(inputArrayTolist,horizontalStrokes);
		if(horizontalStrokes > 1000000000){
			horizontalStrokes=-1;
		}
		System.out.println("Final Horizontal Strokes Value is " + horizontalStrokes);		
	}
	public static int calculateHorizontalStrokes(List<Integer> inputList, int horiStrokes){
		List<List<Integer>> listOfList = new ArrayList<List<Integer>>();
		
		if(inputList.size()<=2){
			horiStrokes+=inputList.stream().mapToInt(v->v).max().orElse(0);
			return horiStrokes;
		}
		else{
			int minVal=calculateMinValue(inputList);
			listOfList=splitList( calculateMinValList(inputList, minVal ));
			horiStrokes+=+minVal;
			for (int j=0;j<listOfList.size();j++){
				if(listOfList.get(j).size()!=0){
					horiStrokes= calculateHorizontalStrokes(listOfList.get(j),horiStrokes);
				}
			}
			return horiStrokes;
		}	
	}

	private static List<List<Integer>> splitList(List<Integer> minValReducedList) {
		int startIndex=0;
		int endIndex= minValReducedList.indexOf(0);
		List<Integer> tempList ;
		List<Integer> subList;
		List<List<Integer>> tempListOfList = new ArrayList<List<Integer>>();
		
		do{
			tempList= minValReducedList.subList(startIndex, endIndex);
			if(tempList!=null)
				tempListOfList.add(tempList);
			subList = minValReducedList.subList(endIndex+1, minValReducedList.size());
			startIndex = endIndex+1;
			endIndex = endIndex+subList.indexOf(0)+1;
		}
		while (endIndex!=-1 && startIndex < endIndex);
		tempListOfList.add(subList);
		return tempListOfList;
	}

	private static int calculateMinValue(List<Integer> inputArrayTolist) {
		return inputArrayTolist.stream().mapToInt(v->v).min().orElse(0);
	}

	private static List<Integer> calculateMinValList(List<Integer> inputArrayTolist, int minVal) {
		return inputArrayTolist.stream().map(n -> n - minVal).collect(Collectors.toList());
		
	}
	
	

}
