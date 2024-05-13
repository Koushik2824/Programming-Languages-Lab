--instructions:
The given program '210101069_Lab6.pl' is in prolog.(Save the file in prolog if it's in perl).
1.my_flatten predicate takes two arguments.
2.First argument must be a List,else error message would be printed along with false.In some cases syntactically wrong message could be printed.So,please ensure first argument to be a list.
3.If First argument is a List,then flattened list would be printed
4.Base case takes [] and returns [].
5.Recursive case checks if first element is a list,then it recursively flattens first element and rest of the list
  and stores the appended list in final solution
6.If first element is not alist,it flattens rest of the list and adds the first lement to this list and store sthe result in final solution(For addition of lement to list,another predicat eis written)

---Commands
%Run swipl in the terminal in the directory where the file is present.
consult('210101069_Lab6.pl').
my_flatten([a,b], X).
my_flatten([[[[[[[[a]]]]]]]], X).
my_flatten([a, [b, [c, d], e]], X).
my_flatten([a, [b,c],d,[e]], X).
my_flatten([], X).


