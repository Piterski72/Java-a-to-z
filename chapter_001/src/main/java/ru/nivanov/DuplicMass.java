package ru.nivanov;

public class DuplicMass {
	
	String [] symbols;
	
	public DuplicMass (String [] symbols) {
		
		this.symbols = symbols;	
	}
	
	public String [] copyRemover(){
		
		int max = symbols.length;
		int count = 0;
		
		System.out.println("Before");
				
		// original massive
		for (int i = 0; i < max; i++) {
			System.out.printf(" %s",symbols[i]);
		}
			System.out.println();
				
		// finding copies and converting to nulls
		for (int i = 0; i < max - 1; i++) {
			if (symbols[i].equals("")){
				count ++;
			}
			for (int j  = i + 1; j < max; j++) {
				
				if (symbols[i].equals(symbols[j])) {
					
					symbols [j] = "";
				}
			}
		}
		
		//moving nulls to end of massive 
		String tmp = "";
		for (int i = max-1; i > 0; i--) {
			for (int j = 0; j < i; j++){
				if ( symbols[max-j-1] != "" && symbols[max-j-2] == "") {
					tmp = symbols[max-j-2];
					symbols[max-j-2] = symbols [max-j-1];
					symbols[max-j-1] = tmp;
				}
			}
		} 
			
		// creating new massive, cutting copies 
		System.out.println("After");	
			String [] result = new String [symbols. length - count];
			for (int i = 0; i < result.length; i++) {
				result[i] = symbols [i];
				System.out.printf(" %s",result[i]);
				
			}
		
		System.out.println();	
		System.out.printf("Number of duplicates = %d ",count);
		System.out.println();		
		System.out.printf("Result massive length = %d ",result.length);
		System.out.println();
		return result;
		
	}
	
}