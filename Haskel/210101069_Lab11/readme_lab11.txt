q1.
 It implements a depth-first search (DFS) algorithm to traverse task dependencies. Users input tasks and their durations, along with task dependencies.You can change tasks and dependencies in main function as required.Minimum possible time was calculated assuming maximum number of workers are available as number of workers is not asked in the input example also
--Commands 
1.ghci 210101069_Lab9.hs
2.main

--example usage

tasks = [('A', 5), ('B', 3), ('C', 4)]
dependencies = [('A', 'B'), ('A', 'C')]

ouput is 9
q2.
--Instructions
Given many nodes connected by weighted edges, required to find maximum distance between two given source and destination nodes.Approach
used is dynamic programming.Starting with source all nodes are covered while storing the maximum distance from source, if any path with more weights is found the dp is updated.To change the graph , please change the testGraph variable in the code.The distance is given and path of the nodes from source to destination are printed.Change source and destination nodes in main.

--Commands
1.ghci 210101069_Lab9.hs
2.main

--example usage 
[("A", "B", 2), ("A", "C", 3), ("B", "D", 5), ("C", "D", 7), ("D", "E", 4)]
source-"A"
destination-"E"

distance is 14
path is "A","C","D","E"











