# VRP-Java
##### Tackling the Vehicle Routing Problem in Java

### COMPONENT 1

Prepare a java application for dealing with the Vehicle Routing Problem. This representation should include the depot, the customers, the set of routes, the solution and the distance matrix.

Contrary to the TSP which considers only one route, the VRP considers a set of routes. So this should be the basic difference between the TSP and the VRP representations. In addition, each route should be associated with a capacity variable which corresponds to the maximum load assigned to each route. Moreover, each customer should be associated with an integer product demand (obviously the demand of the depot is equal to zero). Finally, it is helpful to associate another variable called load with each route. This variable should reflect the total demand of customers that are visited by this route. Obviously, this load variable should be initialized to zero, as for an empty route the load assigned to it is zero.


### COMPONENT 2

Extending the code of Step 1, generate a VRP instance with the following characteristics:
1. Number of customers: 30 & Number of nodes: 31 (customers plus the depot).
2. Ten vehicles each of them with capacity of 50 product units.
3. The depot is located at (x, y) = 50
4. For generating the coordinates and demands of the 30 customers we use the following code which constructs the thirty customer nodes and stores them in an arraylist called customers:

```
Random ran = new Random (myBirthNumber);
for (int i = 1 ; i <= 30; i++)
Node cust = new Node();
cust.x = ran.nextInt(100);
cust.y = ran.nextInt(100); 
cust.demand = 4 + ran.nextInt(7); 
cust.ID = i;
customers.add(cust);
}
```

The value myBirthNum is obtained by translating the user's birthday into an integer value, using the following scheme:
Birthday: ab/cd/ef (dd/mm/yy) -> myBirthNum = abcdef;
i.e. Birthday: 15/12/92 -> int myBirthNum = 151292;


### COMPONENT 3

Extending the Components 1 and 2, develop a constructive heuristic based on Nearest Neighbor to produce an initial solution for the VRP of Component 2. The logic of your algorithm is presented in the following Table. It is based on the nearest neighbor heuristic for the TSP. However, you have to make sure that the capacity constraints of the routes are satisfied by the insertion of the next customer. If no neighbor that respects the capacity constraints exist, then the current route is finalized and the method continues by building the second route and so on. Obviously the method terminates when all customers have been inserted into the solution.


### COMPONENT 4

Design a local search method for improving the initial solution generated in Component 3. This local search method will consider intra-route relocations. This means that at each iteration, the method should explore all potential relocations of the customers within their routes. The relocation yielding the best solution cost improvement should be selected to be applied to the candidate solution. The method should terminate if no improving intra-route relocation can be identified.

Note 1: You should use the TSP local-search code given in the LAB session which explores all potential relocations of customers within their routes (for the TSP only one route exists).
Note 2: This code should be extended to explore all routes present in the VRP solution.


### COMPONENT 5

Design a local search method for improving the initial solution generated in Component 3. This local search method will consider all possible customer relocations (both intra- and inter-route). This means that at each iteration, the method should explore all potential relocations of customers to any point of the existing solution.

Example for a two vehicle VRP:
Route 1: 0-A-B-C-0
Route 2: 0-D-E-0

A can be relocated (i) after B, (ii) after C, (iii) after 0 (of the second route), (iv) after D and (v) after E. Note that (i) and (ii) are intra-route relocations and (iii) (iv) and (v) are inter-route relocations.

The relocation yielding the best solution cost improvement and satisfying the capacity constraints should be selected to be applied to the candidate solution. The method should terminate if no improving intra-route relocation can be identified.
