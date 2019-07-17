package Q1;

import java.util.ArrayList;

/**
 * 
 */
/**
 * @author sotirisbaratsas
 *
 */

public class Q1 {

	public static void main(String[] args) {
		// TODO code application logic here

		//Creating the depot
		Customer depot = new Customer();
		depot.x = 50;
		depot.y = 50;
		depot.ID = 0;
		depot.demand = 0;
	}

}

class Customer 
{
	int x;
	int y;
	int ID;
	int demand; // product demand of each customer
	boolean isRouted; // true/false flag indicating if a customer has been inserted in the solution

	Customer() 
	{
	}
}

class Route 
{
	ArrayList <Customer> customers;
	double cost;
	int load; // load of the route (initially = 0)
	int capacity; // capacity variable indicating the capacity of the vehicles
	
	Route() 
	{
		cost = 0;
		load = 0;
		capacity = 50;
		// A new arraylist of nodes is created
		customers = new ArrayList<Customer>();
	}
}

class Solution 
{
	double cost; 
	ArrayList <Route> routes;

	Solution ()
	{
		routes = new ArrayList<Route>();
		cost = 0;
	}
}
