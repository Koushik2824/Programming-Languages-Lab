%In the Netherlands, a patriotic sorting algorithm was devised inspired by the colors of the Dutch national flag: red, white, and blue. The algorithm rearranges an array of elements into three sections, each corresponding to one of the flag's colors. Here's a problem related to the Dutch Flag Sorting:
%Problem Statement:
%You are given an array of elements, each represented by a color (red, white, or blue). Implement the Dutch Flag Sorting algorithm to rearrange the elements such that all red elements come first, followed by all white elements, and finally, all blue elements.
%Requirements:
%1. Define a predicate `dutch_flag_sort/2` that takes an input list and outputs the sorted list based on the Dutch Flag Sorting algorithm.
%Example:
%?- dutch_flag_sort(['white', 'red', 'blue', 'red', 'white', 'blue'], Sorted).
% Should return Sorted = ['red', 'red', 'white', 'white', 'blue', 'blue'].
%Note:
%- Ensure that your implementation correctly sorts the elements in linear time, as the Dutch Flag Sorting algorithm is known for its efficiency.
%- Provide additional test cases to demonstrate the effectiveness of your sorting algorithm.


%for check/1, to check for invalid entries
check([]).
check([OtherColor|Tail]):-
	(
	member(OtherColor,['red','white','blue']) ->
	check(Tail);
	format('~w is not allowed.',[OtherColor]),
	false
	).
%for count_color/3, it counts the number of occurences of each color
count_color([],_,0).
count_color([Color|SubList],Color,Count):-
	count_color(SubList,Color,TailCount),
	Count is TailCount+1.
count_color([OtherColor|SubList],Color,Count):-
	OtherColor \= Color,
	count_color(SubList,Color,Count).
%for append_color/3 appends each color to get the final list
append_color(0,_,[]).
append_color(Count,Color,List):-
	Count \= 0,
	NextCount is Count-1,
	append_color(NextCount,Color,TempList),
	add_to_start(Color,TempList,List).
%Predicate: add_to_start/3
%This predicate adds Element to OldList and store it in the new list
%This first checks if the first element is not a list and second element is a list ,then adds the first element to the rest of the list
add_to_start(Element, OldList,NewList) :-
	\+ is_list(Element),
	is_list(OldList),
	NewList = [Element|OldList].
%for dutch_flag_sort/2 sorts based on colors
dutch_flag_sort([],[]).
dutch_flag_sort(List,Sorted):-
	(
	is_list(List) ->
	check(List),
	count_color(List,'red',RedColor),
	count_color(List,'white',WhiteColor),
	count_color(List,'blue',BlueColor),
	append_color(RedColor,'red',RedSorted),
	append_color(WhiteColor,'white',WhiteSorted),
	append_color(BlueColor,'blue',BlueSorted),
	append(RedSorted,WhiteSorted,Temp),
	append(Temp,BlueSorted,Sorted);
	format("The first argument is not a list"),
	false
	).
%test cases
%dutch_flag_sort(['white', 'red', 'blue', 'red', 'white', 'blue'], Sorted).
%dutch_flag_sort(['red', 'blue', 'red', 'white', 'blue'], Sorted).
%dutch_flag_sort(['white', 'red', 'blue', 'red'], Sorted).
