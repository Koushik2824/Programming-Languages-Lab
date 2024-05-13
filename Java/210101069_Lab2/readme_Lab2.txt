Assumptions:
        Since no sequence was given as an example,but was asked to print the numbers,"fizz","buzz",
        "fizzbuzz" whenever the sequence needs it,I have assumed the required sequence to be as 
        follows:
               The required number of words are taken as input from the user.
               Initialised a public volatile variable to 1.
               m,n represnt when fizz,buzz should appear these must be taken different.Input must be given different.
               if m=3,n=5 then:
               Whenever the above variable is divisible by only 3 but not 5,print fizz.
               If the above variable is only divisible by 5 but not 3,then print buzz.
               If the above variable is divisible by both 3 and 5,then print fizzbuzz.
               If the above variable is not divisble by 3 and 5,then print the number.
               printFizz,printBuzz,printFizzBuzz,printNumber are all assumed to be methods which
               print required string.
               Threads are created by passing runnable interfaces run method using lambda function,
               these run methods are given by FizzBuzz objects methods.
               Space is given between strings to ensure fizzbuzz is not confused as fizz,buzz.
               
commands:
        javac FizzBuzz.java
        java FizzBuzz
        Now enter the desired length.(length represents number of words)
other comments:
        Each function is a synchronized block which implements a while loop,inside which if a condition is not met,lock.wait is called,which makes thread to wait,until other thread release the lock.
        
