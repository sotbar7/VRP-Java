package Q2;

import java.util.ArrayList;
import java.util.Random;

/**
 * 
 */
/**
 * @author sotirisbaratsas
 *
 */

public class Q2 {

	public static void main(String[] args) {
		// TODO code application logic here
		
		//Creating the depot
		Customer depot = new Customer();
		depot.x = 50;
		depot.y = 50;
		depot.demand = 0;
		

		int myBirthNumber = 8021994;
		
		Random ran = new Random (myBirthNumber); 
		
		int numberOfCustomers = 30;
		
		//Creating the list with the customers		
		ArrayList <Customer> customers = new ArrayList<>(); // with this code we initialize the new ArrayList, which is called "customers"
		for (int i = 1 ; i <= numberOfCustomers; i++)
		{
			Customer cust = new Customer();
			cust.x = ran.nextInt(100);
			cust.y = ran.nextInt(100); 
			cust.demand = 4 + ran.nextInt(7); 
			customers.add(cust);
		}
	
		//Build the allCustomers array and the corresponding distance matrix
		ArrayList <Customer> allCustomers = new ArrayList<Customer>();


		allCustomers.add(depot);
		for (int i = 0 ; i < customers.size(); i++)
		{
			Customer cust = customers.get(i);
			allCustomers.add(cust);
		}

		for (int i = 0 ; i < allCustomers.size(); i++)
		{
			Customer nd = allCustomers.get(i);
			nd.ID = i;
		}

		
		// This is a 2-D array which will hold the distances between node pairs
		// The [i][j] element of this array is the distance required for moving 
		// from the i-th node of allNodes (node with id : i)
		// to the j-th node of allNodes list (node with id : j)
		double [][] distanceMatrix = new double [allCustomers.size()][allCustomers.size()];
		for (int i = 0 ; i < allCustomers.size(); i++)
		{
			Customer from = allCustomers.get(i);

			for (int j = 0 ; j < allCustomers.size(); j++)
			{
				Customer to = allCustomers.get(j);

				double Delta_x = (from.x - to.x);
				double Delta_y = (from.y - to.y);
				double distance = Math.sqrt((Delta_x * Delta_x) + (Delta_y * Delta_y));

				distance = Math.round(distance);

				distanceMatrix[i][j] = distance;

			}
		}
		
		

		// This is the solution object - It will store the solution as it is iteratively generated
		// The constructor of Solution class will be executed
		Solution s = new Solution();

		int numberOfVehicles = 10;
		
		//Let rtList be the ArrayList of Vehicles assigned to the solution "s".		
		ArrayList <Route> rtList = s.routes; 
		for (int i = 1 ; i <= numberOfVehicles; i++)
		{
			Route routeTemp = new Route();
			routeTemp.load = 0;
			routeTemp.capacity = 50;
			routeTemp.cost = 0;
			rtList.add(routeTemp);
		}
				
		// indicate that all customers are non-routed
		for (int i = 0 ; i < customers.size(); i++)
		{
			customers.get(i).isRouted = false;
		}
		
		
		
		
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
