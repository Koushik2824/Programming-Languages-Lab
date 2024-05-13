import System.IO
--Implement a Haskell function that recursively generates all permutations of a given list of integers. The output should be a list of lists, where each inner list represents a permutation of the original lists

generatePermutations :: [Int] -> [[Int]]
generatePermutations [] = [[]]
--base case of empty list giving list containing only empty list,as no other permutation is present
generatePermutations xs = [x:ys | x <- xs , ys <- generatePermutations (delete x xs) ]
--here xs given is a list, is given as argument to the function
--x iterates through all elements of xs and ys iterates through a list generated from all possible permutations
--of remaining elements of xs (without x){ we got this list through recursive call}

delete :: Int -> [Int] -> [Int]
delete _ [] = []
delete y (x:xs)
    | y == x    = xs
    | otherwise = x : delete y xs
-- the above function delete y from the given list, it returns same list if the element is not present, only written for completeness


-- To check if all the elements of list are integers or strings
allIntegers :: [String] -> Bool
--checks if each string is integer
allIntegers = all isInteger
  where
    isInteger :: String -> Bool
    isInteger [] = True
    isInteger (x:xs)
        | x == '-' && not (null xs) = all isDigit xs
        | otherwise = all isDigit (x:xs)
    isDigit :: Char -> Bool
    isDigit c = elem c "0123456789"

nubLists :: [[Int]] -> [[Int]]
nubLists [] = []
nubLists (x:xs) = x : nubLists (filter (/= x) xs)--filter removes all those lists which are equal to x

main :: IO()--takes input
main = do
    putStrLn "Enter a list of elements separated by spaces:"
    input <- getLine
    let elements = words input
    if allIntegers elements then
        let inputList = map read elements :: [Int]--map read converts each element to integer
            permutedList = nubLists (generatePermutations inputList) -- nub removes duplicate lists
        in do
            putStrLn "List of all possible permutations is :"
            print permutedList
    else
        putStrLn "The entered list is not valid, please recheck that all elements are integers"





