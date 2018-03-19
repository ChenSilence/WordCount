import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class wordCount {
	
			private char[] order = {'c','w','l','o','s','a','e'}; 
			private int[]  flag=new int[10];
			private String sourceFilePath,outFilePath,stopFilePath;
			private boolean stopFlag=false;
			private boolean outFlag=false;
			
			public static void main(String[] args) throws IOException {
				args="-c -w -l test4.c -o result4.txt -e stop.txt".split(" ");
				new wordCount().entrance(args);
			}
			
			private void entrance(String[] args) throws IOException {//入口程序
				doPar(args);
				doOrder();
			}
			
			private void doPar(String[] args) {//处理参数
					
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
								outFilePath =args[++i];
								break;
							case 'e':
								flag[6]=1;
								stopFlag=true;
								stopFilePath =args[++i];
								break;
							default:break;
							}
					}
					
					else sourceFilePath = nowPar;//当参数为路径而前面又无命令符说明时，判定为输入文件路径
//					else {
//						if(outFlag) {
//							outFilePath =nowPar;
//						}
//						else if(stopFlag) {
//							stopFilePath = nowPar;
//						}
//						else {
//							sourceFilePath = nowPar;
//						}
//					}
				}
			 }
			
			private void doOrder() throws IOException {
					StringBuilder result = new StringBuilder();
					if(flag[0]==1)
					{
						int charNum = cCharNumber();
						 result.append(sourceFilePath).append(" 字符数: ").append(charNum).append("\r\n");
					}
					if(flag[1]==1)
					{
						int wordNum = cWordNumber();
						result.append(sourceFilePath).append(" 单词数: ").append(wordNum).append("\r\n");
					}
					if(flag[2]==1)
					{
						int lineNum = cLineNumber();
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
						FileWriter fout;
						
						if(!outFlag) {//不指定输出文件
						 fout = new FileWriter("result.txt");
						}
						else //指定输出文件
							fout = new FileWriter(outFilePath);
						
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
			
			public int cCharNumber() {
				String text = readFile(sourceFilePath);
				int num_r=0;
				for(int k=0;k<text.length();k++) {
					if (text.charAt(k)=='\r') {
						num_r++;
					}
				}
				return text.length()-num_r;
			}
			
			
			public int cWordNumber() {
				String text = readFile(sourceFilePath);
				 String s[] = text.split("\\s+|,|\n");//split()里面是正则表达式
				 int wn = s.length;
//				if(stopFlag) {
//					return wn-doStop(s);
//				}
//				else
					return s.length;
			}
			
			public int cLineNumber() {
				String text = readFile(sourceFilePath);
				 String s[] = text.split("\n");//
				return s.length;
			}
			
			public int doStop(String[] s) {//与停用词表比对，统计出不该计算的单词数
				String stoplist = readFile(stopFilePath);
				 String slist[] = stoplist.split(" +");
				int same=0;
				for(int i=0;i<s.length;i++) {
					for(int j=0;j<slist.length;j++) {
						if (s[i].equals(slist[j])){
							same++;
						}
					}
				}
				return same;
			}
}

