commands:
	javac WebServer.java
	java WebServer
Instructions&Assumptions:
	1.Assumed that 0 is the highest priority and so on.
	2.Processing times are assumed to be 1 or 2 or 3,generated randomly.
	3.Number of priorities and number of threads are taken as input.
	4.The requests are added to waiting queue only if the all threads are busy.
	5.The queue sorts the incoming requests based on the time stamp and their priorities,First sorted based on time tamps,if equal then sorted based on priority,so that there is no starvation.
	6.Priority level and number of priorities shouldnot be 0
	7.after each request generation the program waits for 1000/3*T to simulate the genaeration of 3*T requests in one time unit.
	8.For each request their time generated,time started processing,time completed processing are printed.
