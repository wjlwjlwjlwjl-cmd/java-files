package com.wjl.ioc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class IocApplication {

	public static void main(String[] args) {
		//五大注解
		//@Controller 控制层
		//@Service 业务逻辑层
		//@Repository 数据层
		//@Configuration 配置层
		//@Component 组件层
		//使用这些注解，通过IOC创建的对象都是同一个；
		//@Bean需要配合五大注解使用，spring才会扫描相应方法
		//@Bean创建的方式与五大注解不同，是自己创建，然后交给Spring管理，写在方法上，适合第三方类；@Component适合自己创建的类
		//@Bean的BeanName默认是类名的小驼峰表示，但是也可以像@Controller自己指定

		//控制层一般必须使用@Controller修饰，否则可能出现Spring无法识别的情况

		ApplicationContext context = SpringApplication.run(IocApplication.class, args);

		//直接使用类的名称获取，类的名称的即小驼峰版本的类名
		HelloController bean1 = (HelloController)context.getBean("helloController");
		bean1.print();

		HelloController bean2 = context.getBean(HelloController.class);
		bean2.print();

		HelloController bean3 = context.getBean("helloController", HelloController.class);
		bean3.print();

		//获取到的对象都是Spring管理的同一个
		System.out.println(bean1);
		System.out.println(bean2);
		System.out.println(bean3);
	}

}
