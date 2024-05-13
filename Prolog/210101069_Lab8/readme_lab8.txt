--instructions--

The given program '210101069_Lab8.pl' is in prolog.(Save the file in prolog if it's in perl).
1.combination/3 takes 3 arguments, if not given false would be shown
2.First element is the number of elements you want to choose from the given list.It must be integer.
3.Second argument is the List.This should be in correct format i.e only valid list should be given.
4.Third argument stores the final chosen list.This must be given as a variable.
5.Here we store the First of the result, chosen one from the List, Rest will come through recursive call.
6.The Output is printed, such that sequence wise elements are chosen.
7.If K<length of list, then only false would be shown.
8.Base case is shown at the start.
9.You can end the Results generation using full stop'.'.

---Commands---

%Run swipl in the terminal in the directory where the file is present.
consult('210101069_Lab8.pl').
combination(3,[p,q,r,s,t],L).%Here keep entering ';', to get next elements.
combination(3,[p,q,r,s],L).
combination(2,[p],L).
combination(2,p,L).


