import java.io.IOException;

public class testWC {
	String [] testcase= {"-c test4.c",//�����������ͳ���ַ�����
						"-w  test4.c",//���Ի�����ͳ�Ƶ��ʹ���
						"-l  test4.c",//��������ͳ��
						"-c -w -l test4.c",//ͬʱ������������
						"-l -w test5.c -o result5.txt",//����ָ�����·��
						"-l -a test6.c -o result6.txt",//����ͳ�Ƹ��ӵ������ݣ���ͳ��������
						"�Ca test4.c",//ͳ�Ƹ��ӵ�������
						"�Cw test5.c �Ce stop.txt",//������ͣ�ôʱ�
						"�Cw test6.c �Ce stop.txt -o result6.txt",//ͬʱ����ͣ�ôʱ���ָ�����·��
						"-w -l -s *.c",//���Եݹ�
						"-l -w -s D:\\java-oxygen\\eclipse-workspace\\WordCount\\*.c",//����ָ��·���ĵݹ�
						"-l -w -c -a -e stop.txt -s D:\\java-oxygen\\eclipse-workspace\\WordCount\\*.c -o result7.txt"
			};			//����ӵ�·�����в���

	
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
