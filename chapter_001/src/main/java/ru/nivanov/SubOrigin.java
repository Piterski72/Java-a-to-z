package ru.nivanov;

public class SubOrigin {
	
	boolean contains(String origin, String sub) {
		
		char[]chOrig = origin.toCharArray();
		char[]chSub = sub.toCharArray();
		
		int index = 0;
		int count = 0;
		boolean result = false;
				
		for (int j = 0; j < chSub.length; j++) {
			
			for (int i = index; i < chOrig.length; i++) {
				if (chSub[j]==chOrig[i]) {
					index = i + 1;
					count ++;
					break;
				} 
				else {
					count = 0;
				}
										
			}
					
		}
		
		if (count == chSub.length) {
			result = true;
		} else 
			result = false;
		
		return result;
		
	}

}