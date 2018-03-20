import java.io.IOException;

public class testWC {
	String [] testcase= {"-c test4.c",//测试最基本的统计字符功能
						"-w  test4.c",//测试基本的统计单词功能
						"-l  test4.c",//测试行数统计
						"-c -w -l test4.c",//同时满足三个条件
						"-l -w test5.c -o result5.txt",//测试指定输出路径
						"-l -a test6.c -o result6.txt",//测试统计复杂的行数据，且统计所有行
						"–a test4.c",//统计复杂的行数据
						"–w test5.c –e stop.txt",//　测试停用词表
						"–w test6.c –e stop.txt -o result6.txt",//同时满足停用词表且指定输出路径
						"-w -l -s *.c",//测试递归
						"-l -w -s D:\\java-oxygen\\eclipse-workspace\\WordCount\\*.c",//测试指定路径的递归
						"-l -w -c -a -e stop.txt -s D:\\java-oxygen\\eclipse-workspace\\WordCount\\*.c -o result7.txt"
			};			//对最复杂的路径进行测试

	
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
