package study.thread.exception;

public class TcpServer implements Runnable {
	
	public TcpServer() {
		//����һ���߳�
		Thread thread = new Thread(this);
		//Ϊ�߳�ָ���쳣������
		thread.setUncaughtExceptionHandler(new TcpServerExceptionHandler());
		//�����߳�
		thread.start();
	}

	@Override
	public void run() {

		for (int i = 0; i < 3; i++) {
			System.out.println("ϵͳ��������"+i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		//ʹ�������е��߳��׳��쳣
		throw new RuntimeException();
	}

	
	
	/**
	 * ʵ���̲߳�׽���Ľӿ� 
	 * @author Administrator
	 *
	 */
	private static class TcpServerExceptionHandler implements Thread.UncaughtExceptionHandler{
		@Override
		public void uncaughtException(Thread t, Throwable e) {
			System.out.println("�߳�"+t.getName()+"�����쳣�����������������ԭ��");
			e.printStackTrace();
			new TcpServer();
		}
	}
	
	public static void main(String[] args) {
		//�����������߳�
		new TcpServer();
	}
}
