package Q3;

import java.util.ArrayList;
import java.util.Random;

/**
 * 
 */
/**
 * @author sotirisbaratsas
 *
 */

public class Q3 {

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
		
		
		/* 
		 * ##################
		 * # INITIALIZATION #
		 * ##################
		 * */
		
		// Setting a count for customers who are not inserted in the solution yet
		int notInserted = numberOfCustomers;
				
		for (int j=0; j < numberOfVehicles; j++)
		{
			ArrayList <Customer> nodeSequence = rtList.get(j).customers;
			nodeSequence.add(depot);	
			
			int capacity = rtList.get(j).capacity; // The capacity of this vehicle
			int load = rtList.get(j).load; // The initial load of this vehicle
			// Setting a boolean variable that shows the assignment is not final yet.
			boolean isFinal = false;
			// If we have no more customers to insert, we add the depot at the end of the sequence. 
			if (notInserted == 0) {
				isFinal = true;
				nodeSequence.add(depot);
			}
			while (isFinal == false)			{
				//this will be the position of the nearest neighbor customer -- initialization to -1
				int positionOfTheNextOne = -1;
				// This will hold the minimal cost for moving to the next customer - initialized to something very large
				double bestCostForTheNextOne = Double.MAX_VALUE;
				//This is the last customer of the route (or the depot if the route is empty)
				Customer lastInTheRoute = nodeSequence.get(nodeSequence.size() - 1);
				//identify nearest non-routed customer
				for (int k = 0 ; k < customers.size(); k++)
				{
					// The examined customer is called candidate
					Customer candidate = customers.get(k);
					// if this candidate has not been visited by a vehicle
					if (candidate.isRouted == false)					{
						//This is the cost for moving from the last visited customer to the candidate customer
						double trialCost = distanceMatrix[lastInTheRoute.ID][candidate.ID];
						//If this is the minimal cost found so far -> store this cost and the position of this best candidate
						if (trialCost < bestCostForTheNextOne && candidate.demand<= capacity)
						{
							positionOfTheNextOne = k;
							bestCostForTheNextOne = trialCost;
						}
					}
				} // moving on to the next (candidate) customer
				
				// Step 2: Push the customer in the solution
				// We have found the customer to be pushed.
				// He is located in the positionOfTheNextOne position of the customers list
				// Let's inert him and update the cost of the solution and of the route, accordingly
				
				if (positionOfTheNextOne != -1 )
				{
					Customer insertedNode = customers.get(positionOfTheNextOne);
					//Push the customer in the sequence
					nodeSequence.add(insertedNode);
					
					rtList.get(j).cost = rtList.get(j).cost + bestCostForTheNextOne;
					s.cost = s.cost + bestCostForTheNextOne;
					insertedNode.isRouted = true;
					capacity = capacity - insertedNode.demand;
					rtList.get(j).load = load + insertedNode.demand;
					load = load + insertedNode.demand;
					notInserted = notInserted - 1;
					
				} else 
				{ 
					//if the positionOfTheNextOne = -1, it means there is no suitable candidate for this vehicle. So, we add the depot.
					nodeSequence.add(depot);
					rtList.get(j).cost = rtList.get(j).cost + distanceMatrix[lastInTheRoute.ID][0];
					s.cost = s.cost + distanceMatrix[lastInTheRoute.ID][0];
					isFinal = true;
				}
			}
		}
		
		/* 
		 * ###########
		 * # RESULTS #
		 * ###########
		 * */
		
		
		for (int j=0; j<numberOfVehicles; j++)
		{
			int vehicle_number = j+1;
			System.out.println("Route for Vehicle #" + vehicle_number);
			for (int k=0; k<s.routes.get(j).customers.size(); k++) 
			{
				System.out.print(s.routes.get(j).customers.get(k).ID + "  ");
			}
			System.out.println("");
			System.out.println("Route Cost = " + s.routes.get(j).cost);
			System.out.println("Final Load: " + s.routes.get(j).load);
			System.out.println("Final Remaining Capacity = " + (rtList.get(j).capacity - s.routes.get(j).load));
			System.out.println("----------------------------------------");

		}		
		System.out.println("Total Solution Cost = " + s.cost);
		
		
		
		
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
