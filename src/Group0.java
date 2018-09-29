import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Group0 {

	public static void main(String[] args) throws InterruptedException, FileNotFoundException {
		// testing the comparator:
		Data.testM_LRMUS();
		
		if (args.length < 2) {
			System.out.println("Please run with two command line arguments: input and output file names");
			System.exit(0);
		}

		String inputFileName = args[0];
		String outFileName = args[1];
		
		// read as strings
		String [] data = readData(inputFileName);
		
		String [] toSort = data.clone();
		
		Data [] sorted = sort(toSort);
		
		//printArray(sorted, 100);
		
		toSort = data.clone();
		
		Thread.sleep(10); //to let other things finish before timing; adds stability of runs

		long start = System.currentTimeMillis();
		
		sorted = sort(toSort);
		
		long end = System.currentTimeMillis();
		
		System.out.println(end - start);
		
		writeOutResult(sorted, outFileName);

	}
	
	// YOUR SORTING METHOD GOES HERE. 
	// You may call other methods and use other classes. 
	// Note: you may change the return type of the method. 
	// You would need to provide your own function that prints your sorted array to 
	// a file in the exact same format that my program outputs
	private static Data[] sort(String[] toSort) {
		Data[] toSortData = new Data[toSort.length];
		for (int i = 0; i < toSort.length; ++i) {
			toSortData[i] = new Data(toSort[i]);
		}
		Arrays.sort(toSortData, new M_LRMUSComparator());
		return toSortData;
	}
	
	private static String[] readData(String inFile) throws FileNotFoundException {
		ArrayList<String> input = new ArrayList<>();
		Scanner in = new Scanner(new File(inFile));
		
		while(in.hasNext()) {
			input.add(in.next());
		}
				
		in.close();
		
		// the string array is passed just so that the correct type can be created
		return input.toArray(new String[0]);
	}
	
	private static void writeOutResult(Data[] sorted, String outputFilename) throws FileNotFoundException {

		PrintWriter out = new PrintWriter(outputFilename);
		for (Data s : sorted) {
			out.println(s.number);
		}
		out.close();

	}
	
	private static class M_LRMUSComparator implements Comparator<Data> {

		@Override
		public int compare(Data s1, Data s2) {
			/* Length test */
			if(s1.M_LRMUSLength() < s2.M_LRMUSLength()){return -1;}
			if(s1.M_LRMUSLength() > s2.M_LRMUSLength()){return 1;}

			/* Position test*/
			if(s1.M_LRMUSPosition() < s2.M_LRMUSPosition()){return -1;}
			if(s1.M_LRMUSPosition() > s2.M_LRMUSPosition()){return 1;}

			/* Alphabetical test */
			int tmp = s1.M_LRMUSStr().compareTo(s2.M_RMUSSstr());
			if(tmp(!=0)){return(tmp);}

			/* Fallback */
			return(tmp = s1.value().compareTo(s2.value()))
		}
	}	
	private static class Data {
		private class LRMUS{
			int position=-1;
			int length=0;
			String referenceStr;

			public LRMUS(String str){
				referenceStr=str; // REFERENCE to original string				
			}
			
			public void updateAt(int start){ //0-based position.  Will update LRMUS if there is an improvement at start
				while(int end=referenceStr.length;end>start;end--){
					int matchCnt=0;
					String toMatch=referenceStr.substr(start,end) //indices are 0-based so str.length is 1 more than the final index
					int m=toMatch.length;
					if (m<length){
						break; // If the string to match is shorter than our current best length there's no reason to continue
					}
					int testEnd=referenceStr.length-m;
					while(int testStart=0;testStart<=testEnd;testStart++){
						String test=referenceStr.substr(testStart,testEnd);
						if(toMatch.compareTo(test)==0){matchCnt++;}
					}
					if(matchCnt==0){System.out.println("DANGER!  No Match in updateAt!  This should never happen");}
					if(matchCnt==1){ // In this case the substring is unique.  If we are here than the match is at least as good as the previous best
						if(m==this.length && start < this.position){// If it's a tie then we check position
							this.position=start;
						}	
						this.length=m; 
						break; // We can't do any better since $m$ is decreasing... our m<length sentinel would capture this... but why waste time 	
					}
					// If we are here then we haven't found a unique match yet
				}
			}
			public void findBest(LRMUS target){
	                     for(int i =0; i< referenceStr.length;i++){
                                target$updateAt(i) //
        	                }
			}

		}
		public String string;
		private LRMUS best;

		public int M_LRMUSLength(){return best.length;}
		public int M_LRMUSPosition(){return best.position;}
		public int M_LRMUSStr() {return referenceStr.substring(position,position+length)}
		public String value(){return refrenceStr;}

		
		public Data(String str) {
			string = new String(str);
			
			best = new LRMUS(str);
			findBest(best); // Updates best so it contains the best LRMUS
		}
		
		public static void testM_LRMUS() {
			
			testItem=new LRMUS("Morrocco");
			testItem$findBest();
			System.out.println(testItem.M_LRMUSLength());
			System.out.println(testItem.M_LRMUSPosition());
			System.out.println(testItem.M_LRMUSStr());
			System.out.println(testItem.value());

/*			if (productOfPrimeFactors(1) != 1) { // definition for 1
				System.out.println("fails on 1");
				System.out.println(productOfPrimeFactors(1));
			}
*/
		}
		
	}

}
