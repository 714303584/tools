package study.thread.exception;

public class TcpServer implements Runnable {
	
	public TcpServer() {
		//创建一个线程
		Thread thread = new Thread(this);
		//为线程指定异常处理器
		thread.setUncaughtExceptionHandler(new TcpServerExceptionHandler());
		//启动线程
		thread.start();
	}

	@Override
	public void run() {

		for (int i = 0; i < 3; i++) {
			System.out.println("系统运行正常"+i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		//使正在运行的线程抛出异常
		throw new RuntimeException();
	}

	
	
	/**
	 * 实现线程捕捉器的接口 
	 * @author Administrator
	 *
	 */
	private static class TcpServerExceptionHandler implements Thread.UncaughtExceptionHandler{
		@Override
		public void uncaughtException(Thread t, Throwable e) {
			System.out.println("线程"+t.getName()+"出现异常，自行重启，请分析原因");
			e.printStackTrace();
			new TcpServer();
		}
	}
	
	public static void main(String[] args) {
		//创建并运行线程
		new TcpServer();
	}
}
