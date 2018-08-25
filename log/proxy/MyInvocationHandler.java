package log.proxy;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyInvocationHandler<T> implements InvocationHandler{
	private T target;
	public  MyInvocationHandler(T target) {
		this.target = target;
		
		
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("开始代理...");
		//记录文件操作日志
		//1时间
		String startTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS").format(new Date());
		//2.记录做了什么
		String methodName = method.getName();//获取方法名
		//3.记录传入参数
		String parameter = "传入参数为。。。";
		if(args == null) {
			parameter = "没有参数。。。";
			
		}else {
			for(int i = 0; i < args.length; i++) {
				parameter += args + ",";
			}
			parameter = parameter.substring(0, parameter.length() - 1);
		}	
		//创建日志文件
		File file = new File("C:\\Users\\Administrator\\Desktop\\a\\b\\log.txt"); 
		if(!file.exists()) {
			file.createNewFile();
		}
		//创建字符流
		FileWriter fw = null;
		BufferedWriter bw = null;
		
		fw = new FileWriter(file, true);
		bw = new BufferedWriter(fw);
		//写入的内容
		String content = "操作时间：" + startTime + "," + "方法：" + methodName + "," + parameter;
		bw.write(content);
		bw.newLine();
		bw.close();
		Object result = method.invoke(target, args);
		System.out.println("代理结束。。。");
		return result;	
	}
	
}
