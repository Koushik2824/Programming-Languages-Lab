%Write a prolog predicate to generate all possible combinations of K unique items selected from a list of N 
%elements.For instance, if we have a group of 10 people and want to form a committess of 3, there are
%C(10,3)=120 potential combinations(where C(N,K) represents binomial coefficients).While this mathematical
%result is known, the aim here is to potentially enumerate all these possibilities using backtracking approach

%combination/3
combination(0,_,[]).%K=0, then no elements to choose
combination(K,List,[Head|Rest]) :- 
	K>0,
	(
	is_list(List)->%check for list
	choose(List,[Head|RemainderList]),%chooses some element from all possible elements and stores in H and rest of the available elements in T,H also becomes part of Final Lists,first element
	%writeln(H),
	NextLength is K-1,
	%writeln(NextLength),
	%writeln(RemainderList),
	combination(NextLength,RemainderList,Rest);%Rest of the final result through recursive call
	format('Given wrong argument as list(~w)',[List]),
	false
	).
	
%choose/2
%Here both are applicable, so (or) is used.All possible First elements are checked using below code
choose(List,List).%Choose the first element
choose(List,T):- 
	[_|Rest]=List,
	choose(Rest,T).%Not Choose

%To get next element keep entering ';'.After all possible combinations 'false' would be shown.
