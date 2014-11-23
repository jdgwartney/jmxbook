package jmxbook.ch13;

import javax.management.ObjectName;

import jmxbook.ch3.RMIClientFactory;

import com.sun.jdmk.comm.RmiConnectorClient;

public class JMSSetup {
	private RmiConnectorClient client = null;

	public JMSSetup() {
		System.out.println("\n\tCONNECT to the MBeanServer.");
		client = RMIClientFactory.getClient();
		System.out.println("\n\tGot RMI Client.");
	}

	public void createMBeans() {
		try {
			System.out.println("\n>>> REGISTERING JMS MBean");
			// register the JMS Controller MBean
			System.out.println("\n>>> REGISTERING JMS Controller MBean");
			ObjectName JMSBeanName = new ObjectName(
					"JMXBookAgent:name=JMS_Controller_Bean");
			client.createMBean("jmxbook.ch13.JMSController", JMSBeanName);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error Registering MBeans");
		}
	}

	public void close() {
		client.disconnect();
	}

	public static void main(String[] args) {
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("\n>>> START of JMS MBean example");
		JMSSetup agent = new JMSSetup();
		agent.createMBeans();
		agent.close();
		System.exit(0);
	}
}
