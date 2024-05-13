Number of pens,caps pairs to be added as input
Number of robotic arms is also taken as input.
Colors of pens and caps are generated randomly(as many number of pens,caps can be taken as input)
3 Semaphores are created.
1 for pens caps list,only that robotic arm which has acquired this semaphore can access 
the list to remove some pen and cap(which are picked randomly from 2 lists.
Then the robtic arm releases the above semaphore
2 for Matchmachine which acquires can test for the colour,then release it.
If same then acquire 3 lock for Shelfmanaging robot,which places the pair on shelf.
If not same then acquire 1st semaphore,add the pen,cap back to the list.
This process is continued in each thread until the list are empty
commands:
         javac PenCapSorting.java
         java PenCapSorting
