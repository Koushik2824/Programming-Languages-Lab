import Data.List (maximumBy)
import Data.Function (on)
import Data.List (nub)
type Node = String
type Weight = Int
type Edge = (Node, Node, Weight)
type Graph = [Edge]

-- Function to find the longest path from a given source to target node
longestPath :: Graph -> Node -> Node -> (Weight, [Node])
longestPath graph src target = dp src []
  where
    -- Dynamic programming function
    dp node visited
      | node == target = (0, [node])
      | otherwise =
          let neighbors = filter (\(s, _, _) -> s == node) graph
              unvisitedNeighbors = filter (\(_, n, _) -> n `notElem` visited) neighbors
              paths = [(w + w', n : ns) | (_, n, w) <- unvisitedNeighbors, let (w', ns) = dp n (node:visited)]
              maxPath = maximumBy (compare `on` fst) paths
              accumulatedWeight = fst maxPath
          in (accumulatedWeight, node : snd maxPath)

-- Function to find the weight of an edge
findWeight :: Graph -> Node -> Weight
findWeight graph node = case filter (\(s, d, _) -> s == node) graph of
                          [] -> 0
                          (_, _, w) : _ -> w
--change graph here
testGraph :: Graph
testGraph = [("A", "B", 2), ("A", "C", 3), ("B", "D", 5), ("C", "D", 7), ("D", "E", 4)]

-- Example usage
main :: IO ()
main = do
  putStrLn "Enter Source and Destination line after line below:"
  src <- getLine
  target <- getLine
  let (length, path) = longestPath testGraph src target
  print $ "Longest Path is " ++ src ++ " to " ++ target ++ ": " ++ show length
  print $ nub path

