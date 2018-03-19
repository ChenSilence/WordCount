import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class wordCount {
	
			private char[] order = {'c','w','l','o'}; 
			private int[]  flag=new int[10];
			private char[] div = {' ',','};
			private String sourceFilePath,outFilePath;
			
			public static void main(String[] args) throws IOException {
				args="-c -l src/test2.c -o result3.txt".split(" ");
				new wordCount().entrance(args);
			}
			
			private void entrance(String[] args) throws IOException {//入口程序
				doPar(args);
				doOrder();
			}
			
			private void doPar(String[] args) {//处理参数
					boolean outFlag=false;
					for(int i = 0;i < args.length;i++) {
					String nowPar = args[i];
					
					if (nowPar.length()==2) {//如果是命令
						switch(nowPar.charAt(1)) {
							case 'c':flag[0]=1;break;
							case 'w':flag[1]=1;break;
							case 'l':flag[2]=1;break;
							case 'o':
								flag[3]=1;
								outFlag=true;
								break;
							default:break;
							}
					}
					
					else {//如果参数是文件路径
						if(outFlag) {
							outFilePath =nowPar;
							outFlag = false;
						}
						else {
							sourceFilePath = nowPar;
						}
					}
				}
			 }
			
			private void doOrder() throws IOException {
					StringBuilder result = new StringBuilder();
					if(flag[0]==1)
					{
						String text = readFile(sourceFilePath);
						 result.append(sourceFilePath).append(" 字符数: ").append(text.length()).append("\r\n");
					}
					if(flag[1]==1)
					{
						int wordNum = cWordNumber(sourceFilePath);
						result.append(sourceFilePath).append(" 单词数: ").append(wordNum).append("\r\n");
					}
					if(flag[2]==1)
					{
						int lineNum = cLineNumber(sourceFilePath);
						result.append(sourceFilePath).append(" 行数: ").append(lineNum).append("\r\n");
					}
					
					if(flag[3]==1)
					{
						FileWriter fout = new FileWriter(outFilePath);
			            try {
							fout.write(result.toString());
						} catch (IOException e) {
							e.printStackTrace();
						}
			            fout.close();
			        }
					else {
						FileWriter fout = new FileWriter("result2.txt");
			            try {
							fout.write(result.toString());
						} catch (IOException e) {
							e.printStackTrace();
						}
			            fout.close();
					}
			}
				
			
			private String readFile(String path) { // 读文件
		        try {
		            byte[] byteText = Files.readAllBytes(Paths.get(path));
		            return new String(byteText, "UTF-8");
		
		        } catch (IOException ex) {
		            throw new RuntimeException(ex);
		        }
		    }
			
			public int cWordNumber(String path) {
				String text = readFile(sourceFilePath);
				 String s[] = text.split(" |,|\\n");//split()里面是正则表达式
				return s.length;
			}
			
			public int cLineNumber(String path) {
				String text = readFile(sourceFilePath);
				 String s[] = text.split("\\n");//
				return s.length;
			}
}

