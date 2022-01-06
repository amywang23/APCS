//Updated on 12.14.2020 v2

//Name:   Date:
import java.util.*;
import java.io.*;
public class McRonald
{
	public static final int TIME = 1080;     //18 hrs * 60 min
	public static double CHANCE_OF_CUSTOMER = .2;
	public static int customers = 0;
	public static int totalMinutes = 0;
	public static int longestWaitTime = 0;
	public static int longestQueue = 0;
	public static int serviceWindow = 0;      // to serve the front of the queue
	public static int thisCustomersTime;
	public static PrintWriter outfile = null; // file to display the queue information

	public static int timeToOrderAndBeServed()
	{
		return (int)(Math.random() * 6 + 2);
	}

	public static void displayTimeAndQueue(Queue<Customer> q, int min)
	{ 
		//Billington's
		outfile.println(min + ": " + q);	
		//Jurj's
		//outfile.println("Customer#" + intServiceAreas[i] + 
		//                            " leaves and his total wait time is " + (intMinute - intServiceAreas[i]));                     	

	}

	public static int getCustomers()
	{
		return customers;
	}
	public static double calculateAverage()
	{
		return (int)(1.0 * totalMinutes/customers * 10)/10.0;
	}
	public static int getLongestWaitTime()
	{
		return longestWaitTime;
	}
	public static int getLongestQueue()
	{
		return longestQueue;
	}

	public static void main(String[] args)
	{     
		//set up file      
		try
		{
			outfile = new PrintWriter(new FileWriter("McRonald 1 Queue 1 ServiceArea.txt"));
		}
		catch(IOException e)
		{
			System.out.println("File not created");
			System.exit(0);
		}

		mcRonald(TIME, outfile);   //run the simulation

		outfile.close();	
	}

	public static void mcRonald(int TIME, PrintWriter of)
	{
		/***************************************
           Write your code for the simulation   
		 **********************************/
		Queue<Customer> q = new LinkedList<Customer>();
		Customer c = null;
		thisCustomersTime = 0;
		for(int i = 1; i <= TIME; i++) {
			if(Math.random() <= CHANCE_OF_CUSTOMER) {
				customers++;
				c = new Customer(i, timeToOrderAndBeServed(), customers);
				//				System.out.println(c.getArrivedAt() + " " + c.getCustomerNum() + " " + c.getOrderAndBeServed());
				q.add(c);
			}

			if(!q.isEmpty()) { //if there are customer(s) in queue
				if(serviceWindow == 0) { //service window open
					thisCustomersTime = q.peek().getOrderAndBeServed();
					serviceWindow = q.peek().getCustomerNum();
				} else { //service window busy
					if(thisCustomersTime == 1) {
						//update stats
						int waitingTime = i - q.peek().getArrivedAt();
						totalMinutes += waitingTime;
						if(longestWaitTime < waitingTime) {
							longestWaitTime = waitingTime;
						}
						if(longestQueue < q.size()) {
							longestQueue = q.size();
						}
						q.remove();
						if(!q.isEmpty()) {
							thisCustomersTime = q.peek().getOrderAndBeServed();
							serviceWindow = q.peek().getCustomerNum();
						}
					} else {
						thisCustomersTime --;
					}
				}
			}

			if(q.isEmpty()) {
				thisCustomersTime = 0;
				serviceWindow = 0;
			}
			displayTimeAndQueue(q, i);
			if(!q.isEmpty()) {
				of.println("\t\t" + q.peek().getCustomerNum() + " is now being served for " + thisCustomersTime + " minutes." );
			}

		} //end for-loop
		
		thisCustomersTime --;
		int time = TIME + 1;
		while(!q.isEmpty()) {
			int temp = thisCustomersTime;
			if(thisCustomersTime > 0 && serviceWindow > 0) {
				for(int i = 0; i < temp; i++) {
					displayTimeAndQueue(q, time+i);
					of.println("\t\t" + q.peek().getCustomerNum() + " is now being served for " + thisCustomersTime + " minutes." );
					thisCustomersTime--;
				}
				//update stats
				time += temp;
				int waitingTime = time - q.peek().getArrivedAt();
				totalMinutes += waitingTime;
				if(longestWaitTime < waitingTime) {
					longestWaitTime = waitingTime;
				}
				if(longestQueue < q.size()) {
					longestQueue = q.size();
				}
				q.remove();
				if(!q.isEmpty()) {
					serviceWindow = q.peek().getCustomerNum();
					thisCustomersTime = q.peek().getOrderAndBeServed();
				}

			} 
		}


		/*   report the data to the screen    */  
		System.out.println("1 queue, 1 service window, probability of arrival = "+ CHANCE_OF_CUSTOMER);
		System.out.println("Total customers served = " + getCustomers());
		System.out.println("Average wait time = " + calculateAverage());
		System.out.println("Longest wait time = " + longestWaitTime);
		System.out.println("Longest queue = " + longestQueue);
	}

	static class Customer      
	{
		private int arrivedAt;
		private int orderAndBeServed;
		private int customerNum;

		/**********************************
      Complete the Customer class with  
      constructor, accessor methods, toString.
		 ***********************************/

		public Customer(int a, int b, int c) {
			arrivedAt = a;
			orderAndBeServed = b;
			customerNum = c;
		}

		public int getArrivedAt() {
			return arrivedAt;
		}

		public void setArrivedAt(int arrivedAt) {
			this.arrivedAt = arrivedAt;
		}

		public int getOrderAndBeServed() {
			return orderAndBeServed;
		}

		public void setOrderAndBeServed(int orderAndBeServed) {
			this.orderAndBeServed = orderAndBeServed;
		}

		public int getCustomerNum() {
			return customerNum;
		}

		public void setCustomerNum(int customerNum) {
			this.customerNum = customerNum;
		}

		public String toString(){
			return "" + customerNum;
		}
	}
}