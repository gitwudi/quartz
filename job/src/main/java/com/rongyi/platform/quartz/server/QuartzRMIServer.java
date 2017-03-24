package com.rongyi.platform.quartz.server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Date;

import org.quartz.Scheduler;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QuartzRMIServer {

	public void run() throws Exception {
		Logger log = LoggerFactory.getLogger(this.getClass());

		// Use this properties file instead of quartz.properties
		System.setProperty("org.quartz.properties", "config/server.properties");

		System.out.println(System.getProperty("org.quartz.properties"));
		// RMI with Quartz requires a special security manager
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new java.rmi.RMISecurityManager());
		}
		// Get a reference to the Scheduler
		Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

		/*
		 * 在代码 9.2 中，安装了 RMISecurityManager 之后，通过工厂方法获得调度器实例，并调用它的 start() 方法。服务端是设计 成在控制台运行的，因此一旦调度器启动之后，直至用户在控制台上键入
		 * exit 。接着调度器被关闭也不再为远程的客户端提供服务 了。 除了要使用 RMISecurityManager，我们注意到用不着在代码中做任何特别的事情，就能让 Quartz 调度器作为一个远程调度器
		 * 来用。那些全是托 server.properties 文件的福所致。当调度器被创建后，假如属性文件告诉它这么做，调度器就会把自己导出并 注册到 RMI 注册服务器上，并使之可被远程调用。 5. 使用 RMI 注册服务
		 * 需要运行一个 RMI 注册服务让客户端能访问到服务对象。你可以选择在命令行下使用 Java 的 rmiregistry 命令来运行注册服 务，或者你可以允许 Quartz
		 * 自动启动注册服务。完全由你自己选择，但是，假如你没什么偏爱的话，让 Quartz 自已在需要的时 候启动注册服务大概要简单些。
		 * 假如你要通过命令行启动注册服务，要确保你启动时所用的端口号要与属性文件所指定的一致。要从命令行启动，你应先进入到 <JAVA_HOME>/bin 目录下，然后键入如下命令： rmiregistry <port>
		 * 假如你不指定端口号，会使用默认的 1099。这个默认值与 Quartz 所用的默ex认端口是一样的。 假如你不想从命令行中运行注册服务，在你为属性
		 * org.quartz.scheduler.rmi.createRegistry 设置了正确值的情况下，Quartz 会自动启动注册服务。看表 9.1，这个属性可取以下几个值： ·false (never) Due to
		 * the server.properties file, our Scheduler will be exported to RMI Registry automatically.
		 */
		scheduler.start();

		log.info("Quartz RMI Server started at " + new Date());
		log.info("RMI Clients may now access it. ");

		System.out.println("\n");
		System.out.println("The scheduler will run until you type \"exit\"");

		BufferedReader rdr = new BufferedReader(new InputStreamReader(System.in));

		while (true) {
			System.out.print("Type 'exit' to shutdown server: ");
			if ("exit".equals(rdr.readLine())) {
				break;
			}
		}

		log.info("Scheduler is shutting down...");
		scheduler.shutdown(true);
		log.info("Scheduler has been stopped.");
	}

	public static void main(String[] args) throws Exception {
		QuartzRMIServer example = new QuartzRMIServer();
		example.run();
	}
}
