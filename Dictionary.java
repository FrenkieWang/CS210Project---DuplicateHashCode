package hashDictionary;

import java.io.*;

// change word. text into noun verb adj.....
// new Dictionary object....!

public class Dictionary{
     
    private String input[]; 

    public Dictionary(){
        input = load("C://words.txt");  
    }
    //64 characters.... highest possible == 20-25%   25 best........
    //make code very fast... avoid loops.... create lots of sentences at least 10,000,000 - 100,000,000.
    
    public int getSize(){
        return input.length;
    }
    
    public String getWord(int n){
        return input[n];
    }
    
    private String[] load(String file) {
        File aFile = new File(file);
        // File������в����ļ���Ŀ¼
        StringBuffer contents = new StringBuffer();
        //�ַ������������Ķ����ǿ���������޸ĵ�
        BufferedReader input = null;
        // �����ַ����������һЩ���幦��
        try {
            input = new BufferedReader( new FileReader(aFile) );
            // �ܴӴ����϶����ļ�
            String line = null; 
            int i = 0;
            while (( line = input.readLine()) != null){
            	// ��ʾÿ�ζ�ȡһ������
                contents.append(line);
                i++;
                contents.append(System.getProperty("line.separator"));
                //���ص����зָ���
            }
        }catch (FileNotFoundException ex){
            System.out.println("Can't find the file - are you sure the file is in this location: "+file);
            ex.printStackTrace();
        }catch (IOException ex){
            System.out.println("Input output exception while processing file");
            ex.printStackTrace();
        }finally{
            try {
                if (input!= null) {
                    input.close();
                }
            }catch (IOException ex){
                System.out.println("Input output exception while processing file");
                ex.printStackTrace();
            }
        }
        String[] array = contents.toString().split("\n");
        for(String s: array){
            s.trim();
        }
        return array;
    }
}