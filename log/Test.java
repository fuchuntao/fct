package log;

import java.lang.reflect.Proxy;
import java.util.Date;
import java.util.List;

import log.dao.Dao;
import log.dao.DaoImpl;
import log.entity.Employee;
import log.proxy.MyInvocationHandler;

public class Test {
	public static void main(String[] args) throws Exception {
		//创建Dao接口的对象
		Dao dao = new DaoImpl();
		//创建myinvocationHand的对象
		MyInvocationHandler<Dao> h = new MyInvocationHandler<Dao>(dao);
		//生成一个代理对象(InvocationHandler 代理处理类)
		Dao proxy = (Dao)Proxy.newProxyInstance(Dao.class.getClassLoader(),
				new Class[] {Dao.class}, h);
		
		//查找id
		//System.out.println(proxy.find(2));
		//添加一行
//		Employee employee = new Employee(57,"姬进洞",20, new Date(), "男", "河北衡水锤子屯", 15698338563L, false, "葛二蛋", "java", "web", 20000);
//		proxy.insert(employee);
		
		//修改一行
//		Employee employee1 = new Employee(5,"姬进洞",20, new Date(), "男", "河北衡水锤子屯", 15698338563L, false, "葛二蛋", "java", "web", 20000);
//		proxy.update(employee1);
		
		//移除一行
		proxy.remove(15);
//		List<Employee> list = proxy.list();
//		//遍历list集合
//		for (Employee e : list) {
//			System.out.println(e);
//		}	
		
		
	}
}
