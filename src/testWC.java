import java.io.IOException;

public class testWC {
	String [] testcase= {"-c test4.c",
						"-w  test4.c",
						"-l  test4.c",
						"-c -w -l test4.c",
						"-l -w test5.c -o result5.txt",
						"-l -a test6.c -o result6.txt",
						"每a test4.c",
						"每w test5.c 每e stop.txt",
						"每w test6.c 每e stop.txt -o result6.txt",
						"-w -l -s *.c",
						"-l -w -s D:\\java-oxygen\\eclipse-workspace\\WordCount\\*.c",
						"-l -w -s D:\\java-oxygen\\eclipse-workspace\\WordCount\\*.c -o result7.txt"
			};

	
	public static void main(String[] args) throws IOException {
		new testWC().test();
	}

	public void test() throws IOException {
		
		
		wordCount aWc =new wordCount();
		for(int i=0;i<testcase.length;i++) {
			String[] arg = testcase[i].split(" ");
			aWc.entrance(arg);
		}
	}
}
