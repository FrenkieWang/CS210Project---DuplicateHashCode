package hashDictionary;

import java.io.*;

// change word. text into noun verb adj.....
// new Dictionary object....!

public class Dictionary{
     
    private String input[]; 

    public Dictionary(){
         // Change the directory of the input document!
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
        // File类程序中操作文件和目录
        StringBuffer contents = new StringBuffer();
        //字符串变量，它的对象是可以扩充和修改的
        BufferedReader input = null;
        // 其他字符输入流添加一些缓冲功能
        try {
            input = new BufferedReader( new FileReader(aFile) );
            // 能从磁盘上读入文件
            String line = null; 
            int i = 0;
            while (( line = input.readLine()) != null){
            	// 表示每次读取一行数据
                contents.append(line);
                i++;
                contents.append(System.getProperty("line.separator"));
                //返回的是行分隔符
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
