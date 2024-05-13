%It is given that my_flatten query takes two arguments 
%Predicate: my_flatten/2
%The first argument must be List, Else false will be given,sometimes error can occur when syntax is wrong
%So,first argument must be a List
my_flatten(Argument,X) :-
	\+ is_list(Argument),
	X=[],
	format('"~w" is not a list,first argument must be a list',[Argument]),
	false.

%The below is to handle base case,and terminate the process when empty list is reached during recursion.
my_flatten([], []).
my_flatten([First|Rest], FlattenedList) :-
	(
	%writeln(First),
	%writeln(Rest),
    is_list(First) ->%If First element is list
    my_flatten(First, FlatFirst),%Flatten First element
    my_flatten(Rest, FlatRest),%Flatten rest of the list too
    append(FlatFirst, FlatRest, FlattenedList);%Append the resulting flattened lists to get the final solution
    my_flatten(Rest,FlatRest),%If First element is not a list,flatten the other part of the list
    add_to_start(First,FlatRest,FlattenedList)%add the First element to Flattened part of other List,store resulting list in the final solution
    ).
    
    
%Predicate: add_to_start/3
%This predicate adds Element to OldList and store it in the new list
%This first checks if the first element is not a list and second element is a list ,then adds the first element to the rest of the list
add_to_start(Element, OldList,NewList) :-
	\+ is_list(Element),
	is_list(OldList),
	NewList = [Element|OldList].
