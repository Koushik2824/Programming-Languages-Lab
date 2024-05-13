%this dynamic is made ,because we are changing the facts
:- dynamic(book/3).
% Facts: book/3
% first two arguments are atoms enclosed in single quotes
book('Book1', 'Author1', available).
book('Book2', 'Author2', available).
book('Book3', 'Author3', available).
book('Book4', 'Author4', available).
:- writeln("Instructions:"),
   writeln("1.checkout_book,return_book must have arguments enclosed in single quotes"),
   writeln("2.list_available_books must have argument as variable i.e starts with capital letter and not surrounded by single quotes").
%queries
% Predicate: checkout_book/1
%argument given must be enclosed in single quotes
checkout_book(BookName) :-
    (
    retract(book(BookName, Author, available)) ->
    asserta(book(BookName, Author, checked_out)),
    format('~w is checked out successfully,and it\'s availability status is updated ',[BookName])
    ; format('~w is not available for checkout.',[BookName]),
    false
    ).

% Predicate: return_book/1
%argument given must be enclosed in single quotes
return_book(BookName) :-
    (
    retract(book(BookName, Author, checked_out)) ->
    asserta(book(BookName, Author, available)),
    format('~w is returned successfully,and it\'s availability status is updated ',[BookName])
    ; format('~w is either already available or was never given in the database ',[BookName]),
    false
    ).

% Predicate: list_available_books/1
%Argument must be variable and not enclosed in any single quotes
list_available_books(AvailableBooks) :-
    findall(BookName, book(BookName, _, available), AvailableBooks),
    length(AvailableBooks,Length),
    format('Number of books available : ~w ~n',[Length]),
    writeln('All available book\'s title\'s are printed in the below list'),
    %this line prints the list with elements not surrounded in single quotes,this is default behaviour of write/1
    %other line prints in terminal which is the actual list
    write(AvailableBooks).

% Example Usage:
% checkout_book('Book1').
% list_available_books(AvailableBooks).
% return_book('Book1').
% list_available_books(AvailableBooks).

