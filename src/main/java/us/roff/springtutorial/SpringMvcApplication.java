package us.roff.springtutorial;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringMvcApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(SpringMvcApplication.class, args);
		
//		System.out.println("Bean Count = " + ctx.getBeanDefinitionCount());
//		System.out.println("Bean Names = ");
//		for (String beanName : ctx.getBeanDefinitionNames()) {
//			System.out.println(beanName);
//		}
	}
}
