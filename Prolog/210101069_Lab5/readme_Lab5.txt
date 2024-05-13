--instructions
The given program '210101069_Lab5.pl' is in prolog.(Save the file in prolog if it's in perl).
Facts are made dynamic as their availability status must be updated.
It has 4 books available in its database.
These are atoms in quotes 'Book1','Book2','Book3','Book4'.
Simple names are used for user convenience.
3 queries are implemented.
checkout_book and return_book must have arguments in single quotes
i.e for example checkout_book('Book1').
Both the above functions retract i.e remove the current fact and add a new fact.

If the book is already checked out or cannot be returned as it's already available,
appropriate messages are printed.

Length of the list is also printed along with the list.

---Commands
%Run swipl in the terminal in the directory where the file is present.
consult('210101069_Lab5.pl').
checkout_book('Book1').
list_available_books(AvailableBooks).
return_book('Book1').
list_available_books(AvailableBooks).
