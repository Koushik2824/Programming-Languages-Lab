import System.IO
import Data.Char (isDigit)
import Data.Char (isAlpha)
-- The Ord type class is used for types that can be sorted, that is they can be compared with each other,
-- and thus can be classified less than or equal to greater than the other.
-- The below function takes a list as argument and results in other list.
mergeSort :: (Ord a) => [a] -> [a]
--Below are base cases for recursive function mentioned below.
mergeSort [] = []
mergeSort [x] = [x]
-- where is used to define local variables only used within that function
mergeSort xs = merge (mergeSort left) (mergeSort right)
  where
    (left, right) = splitAt (length xs `div` 2) xs
-- merge function which combines two sorted arrays, hence takes two lists as argument and gives list as result
merge :: (Ord a) => [a] -> [a] -> [a]
merge [] ys = ys
merge xs [] = xs
--merging based on whether the first element of first list is less or not
merge (x:xs) (y:ys)
    | x <= y    = x : merge xs (y:ys)
    | otherwise = y : merge (x:xs) ys
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
    isDigit c = c `elem` "0123456789"
--checks if each string is alphabetic
allStrings :: [String] -> Bool
allStrings = all isString
  where
    isString :: String -> Bool
    isString [] = True
    isString (x:xs) = all isAlpha (x:xs)

-- main function
main :: IO ()--it takes input
main = do
    putStrLn "Enter a list of elements separated by spaces:"
    input <- getLine
    let elements = words input--this is a list of strings
    --first check if all elements are integers
    if allIntegers elements
        then do
            let numbers = map read elements :: [Int]--map read converts each element to integer
            let sortedNumbers = mergeSort numbers
            putStrLn "Sorted list of integers:"
            print sortedNumbers
        -- checks if all elements are strings
        else if allStrings elements
            then do
                let sortedStrings = mergeSort elements
                putStrLn "Sorted list of strings:"
                print sortedStrings
            else putStrLn "The list contains incompatible types."

