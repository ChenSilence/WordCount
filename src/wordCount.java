import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.regex.Pattern;

public class wordCount {
	
			private char[] order = {'c','w','l','o','s','a','e'}; 
			private int[]  flag=new int[10];
			private String sourceFilePath,outFilePath,stopFilePath;
			private boolean stopFlag=false;
			private boolean outFlag=false;
			private boolean aFlag=false;
			
			public static void main(String[] args) throws IOException {
				args="-c -w -a test5.c -o result5.txt".split(" ");
				new wordCount().entrance(args);
			}
			
			private void entrance(String[] args) throws IOException {//��ڳ���
				doPar(args);
				doOrder();
			}
			
			private void doPar(String[] args) {//�������
					
					for(int i = 0;i < args.length;i++) {
					String nowPar = args[i];
					
					if (nowPar.length()==2) {//���������
						switch(nowPar.charAt(1)) {
							case 'c':flag[0]=1;break;
							case 'w':flag[1]=1;break;
							case 'l':flag[2]=1;break;
							case 'o':
								flag[3]=1;
								outFlag=true;
								outFilePath =args[++i];
								break;
							case 'a':
								flag[5]=1;
								aFlag=true;
								break;
							case 'e':
								flag[6]=1;
								stopFlag=true;
								stopFilePath =args[++i];
								break;
							default:break;
							}
					}
					
					else sourceFilePath = nowPar;//������Ϊ·����ǰ�����������˵��ʱ���ж�Ϊ�����ļ�·��
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
						 result.append(sourceFilePath).append(" �ַ���: ").append(charNum).append("\r\n");
					}
					if(flag[1]==1)
					{
						int wordNum = cWordNumber();
						result.append(sourceFilePath).append(" ������: ").append(wordNum).append("\r\n");
					}
					if(flag[2]==1)
					{
						int lineNum = cLineNumber();
						result.append(sourceFilePath).append(" ����: ").append(lineNum).append("\r\n");
					}
					if(aFlag)
					{
						String lineInfo = aCount();
						result.append(sourceFilePath).append(lineInfo).append("\r\n");
					}
					
				
						FileWriter fout;
						
						if(!outFlag) {//��ָ������ļ�
						 fout = new FileWriter("result.txt");
						}
						else //ָ������ļ�
							fout = new FileWriter(outFilePath);
						
			            try {
							fout.write(result.toString());
						} catch (IOException e) {
							e.printStackTrace();
						}
			            fout.close();
					
			}
				
			
			private String readFile(String path) { // ���ļ�
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
				 String s[] = text.split("\\s+|,|\n");//split()������������ʽ
				 int wn = s.length;
				if(stopFlag) {
					return wn-doStop(s);
				}
				else
					return s.length;
			}
			
			public int cLineNumber() {//ͳ��������
				String text = readFile(sourceFilePath);
				 String s[] = text.split("\n");//
				return s.length;
			}
			
			public String aCount() {//ͳ�Ƹ�����
				String all = null;
				String text = readFile(sourceFilePath);
				String s[] = text.split("\\r\\n");
				int noteline,spaceline,codeline;
				noteline=spaceline=codeline=0;
				 for(String tmp:s) {
					 if(Pattern.matches("\\s*.?\\s*//.*|//.*", tmp))
					 noteline++;
					 else if(Pattern.matches("\\s*.?\\s*", tmp) || s==null)//���п�����һ���ַ������߱���Ϊ�մ�
					spaceline++;
					 else 
					codeline++;
				 }
				all=" ������/����/ע����:"+codeline+"/"+spaceline+"/"+noteline;
				return all;
			}
			
			public int doStop(String[] s) {//��ͣ�ôʱ�ȶԣ�ͳ�Ƴ����ü���ĵ�����
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

