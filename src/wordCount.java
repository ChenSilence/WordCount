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
				args="-l -w src/test2.c -e stop.txt -o result3.txt".split(" ");
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
								break;
							case 'e':
								flag[6]=1;
								stopFlag=true;
								break;
							default:break;
							}
					}
					
					else {//����������ļ�·��
						if(outFlag) {
							outFilePath =nowPar;
						}
						else if(stopFlag) {
							stopFilePath = nowPar;
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
						 result.append(sourceFilePath).append(" �ַ���: ").append(text.length()).append("\r\n");
					}
					if(flag[1]==1)
					{
						int wordNum = cWordNumber(sourceFilePath);
						result.append(sourceFilePath).append(" ������: ").append(wordNum).append("\r\n");
					}
					if(flag[2]==1)
					{
						int lineNum = cLineNumber(sourceFilePath);
						result.append(sourceFilePath).append(" ����: ").append(lineNum).append("\r\n");
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
			}
				
			
			private String readFile(String path) { // ���ļ�
		        try {
		            byte[] byteText = Files.readAllBytes(Paths.get(path));
		            return new String(byteText, "UTF-8");
		
		        } catch (IOException ex) {
		            throw new RuntimeException(ex);
		        }
		    }
			
			public int cWordNumber(String path) {
				String text = readFile(sourceFilePath);
				 String s[] = text.split(" |,|\\n");//split()������������ʽ
				 int wn = s.length;
				if(stopFlag) {
					return wn-doStop(s);
				}
				else
					return s.length;
			}
			
			public int cLineNumber(String path) {
				String text = readFile(sourceFilePath);
				 String s[] = text.split("\\n");//
				return s.length;
			}
			
			public int doStop(String[] s) {//��ͣ�ôʱ�ȶԣ�ͳ�Ƴ����ü���ĵ�����
				String stoplist = readFile(stopFilePath);
				 String slist[] = stoplist.split(" ");
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

