# RecursionVisualization
Visualize recursion and demonstrate why memoization or tabulation is needed (to avoid tracing the same path)

Problem:
Find the number of ways to exit a cube of size n*n*n (entering at (0,0,0) and exiting at (n,n,n))

Run:
java Cube [CubeSize] [Latency]

Example:
java Cube 2 200


The same path is repeatedly traced while finding the solution.
By using tabulation or memoization this can be avoided and time can be saved.
