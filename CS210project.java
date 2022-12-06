package hashDictionary;

import java.security.MessageDigest;
import java.io.*;

public class CS210project {
    public static void main (String[] args) {
    	//Part 1: Definition
    	long startTime = System.currentTimeMillis(); //calculate the start time of the system
    	int current = -1; // current same hash value
    	int maxhash = -1; //maximum same hash value 
    	int runtimes = 500000; //The amounts of sentence to be created
    	String sentence[] = new String [runtimes];
    	String hash[] = new String [runtimes];
    	String []output = new String[2];
    	String []outputhash = new String[2]; 	
    	CS210project txt = new CS210project();
    	Dictionary dictionary1 = new Dictionary();
    	
    	System.out.println("Number of words in the dictionary: "
    			+ dictionary1.getSize() + "\n");
    	System.out.println("Calculating, please wait: "
    			+ "\n");
    	
    	//Part 2 First nested Loop:
    	for (int a = 0; a < runtimes; a++) {
			String temp = "";
        	String word[] = new String [10];
        	int sentencelength = (int)(Math.random()*6)+2;
        	// Randomly select 2 to 7 words in the dictionary
        	
    		for (int i=0; i<sentencelength; i++) {
    			word[i] = dictionary1.getWord((int)(Math.random()*(dictionary1.getSize()))).replace('\r' ,',');
    			/*Get a word randomly from dictionary. 
    			 * There is a '\r' at the end of each word, 
    			 * so use ','(comma) to replace it */
    			if (i==0) {
    		    	word[i] = upperCaseFirst(word[i]);
    			} // The first word should capitalize the first letter.
    			if (i==sentencelength-1) {
    				word[i] = word[i].replace(',' ,'\s');
    			}// The last word should use '\s' to replace ','.
    			temp = temp + word[i];
    		}
    		temp = temp + "are wonderful words!";
    		sentence[a] = temp;
    		hash[a] = sha256(sentence[a]);
    		// Concatenate these words into a sentence, then hash them.
    	}
    	
    	//Part 3 Second nested Loop:
    	for (int x = 0; x < runtimes-1; x++) {
    		for (int y = x+1; y < runtimes; y++) {
    			// Use O(n*n) to compare the hash value.
    			current = compare(hash[x],hash[y]);
            	if (current >= 22) {
            		// If current value is bigger than 22, write them into the txt.file. 
            		txt.writeUsingFileWriter("Same hash value " + current); 
            		txt.writeUsingFileWriter(sentence[x]);
            		txt.writeUsingFileWriter(sentence[y]);
            		txt.writeUsingFileWriter("");
                	System.out.println("The two sentences are: "
                			+ "\n" + sentence[x] + "\n" + sentence[y] + "\n");
                	System.out.println("Their hash values are: "
                			+ "\n" + hash[x] + "\n" + hash[y] + "\n");
                	System.out.println("Hash value bigger than 21 is: "
                			+ current + "\n");
            	}
    	    	if (current > maxhash) {
    	    		/* If current value is bigger than the max common hash value,
    	    		 * then refresh the max common hash value
    	    		 * and record the sentence and their hashed code.
    	    		 */
    				maxhash = current;
    				output[0] = sentence[x];
    				output[1] = sentence[y]; 
    				outputhash[0] = hash[x]; 
    				outputhash[1] = hash[y]; 
    	    	}
    		}
    	}
    	
    	//Part 4 Output:
    	// Print out the maximum common hash value and its situation.
    	System.out.println("The two sentences with the closest hash values are: "
    			+ "\n" + output[0] + "\n" + output[1] + "\n");
		System.out.println("The hashed sentences are: "
				+ "\n" + outputhash[0] + "\n" + outputhash[1] + "\n");
		System.out.println("The maximum same hash value is: "		
				+ maxhash + "\n");
		// Print out the running time of this program.
		long endTime = System.currentTimeMillis();
		long runminute = (endTime - startTime)/60000;
		long runhour = 0;
		while (runminute > 60)
		{
			runminute = runminute - 60;
			runhour ++;
		}
		System.out.println("Running Time£º" + runhour + " hour " + runminute + " min");	   	
    }
    
    // Method 1: Capitalize the first letter.
    public static String upperCaseFirst(String val) {
        char[] arr = val.toCharArray();
        arr[0] = Character.toUpperCase(arr[0]);
        return new String(arr);
     }
    
    // Method 2: Compare the common hash value
    public static int compare (String input1, String input2) {
    	int count = 0;
    	for (int i = 0; i < 64; i++) {
    		if(input1.charAt(i) == input2.charAt(i)) {
    			count++;
    		}
        	if (count==64) { //avoid the same sentence
        		count = 0;
        	}
    	}
    	return count;
    }
    
    // Method 3: Hash the sentence into a 64-bit String. 
    public static String sha256(String input) { 
        try {
            MessageDigest mDigest = MessageDigest.getInstance("SHA-256");
            byte[] salt = "CS210+".getBytes("UTF-8");
            mDigest.update(salt);
            byte[] data = mDigest.digest(input.getBytes("UTF-8"));
            StringBuffer sb = new StringBuffer();
            for (int i=0;i<data.length;i++){
                sb.append(Integer.toString((data[i]&0xff)+0x100,16).substring(1));
            }
            return sb.toString();
        } catch(Exception e){
            return(e.toString());
        }
    }
    /** Method 4:
     * Use FileWriter when number of write operations are less
     * @param data
     */
    public void writeUsingFileWriter(String data) {
        File file = new File("D:\\Answer.txt");
        FileWriter fr = null;
        try {
            fr = new FileWriter(file,true); //Add sentence behind the txt.
            fr.write(data+'\n');
        } catch (IOException e) { 
            e.printStackTrace(); //Print out the error message
        }finally{
            //close resources
            try {
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}