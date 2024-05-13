import Data.List (maximumBy)
import Data.Function

-- Define types for tasks and dependencies
type Task = (Char, Int)
type Dependency = (Char, Char)

-- Function to perform depth-first search (DFS) on the graph
dfs :: [(Char, [Char])] -> Char -> [(Char, Int)] -> Int
dfs graph taskId durations =
    let dependencies = case lookup taskId graph of
                            Just deps -> deps
                            Nothing -> []
        -- Calculate the maximum time taken by dependencies
        maxTimeDependency = case dependencies of
                                [] -> 0
                                _ -> maximum (map (\dep -> dfs graph dep durations) dependencies)
        -- Get the duration of the current task
        taskDuration = case lookup taskId durations of
                            Just dur -> dur
                            Nothing -> 0
    in taskDuration + maxTimeDependency

-- Function to calculate the minimum time required for all tasks to complete
minimumTime :: [Task] -> [Dependency] -> Int
minimumTime tasks dependencies =
    let -- Construct the dependency graph
        graph = foldl (\acc (x, y) -> addToMap x y acc) [] dependencies
        addToMap key val [] = [(key, [val])]
        addToMap key val ((k, v) : rest)
            | key == k = (k, val : v) : rest
            | otherwise = (k, v) : addToMap key val rest
        -- Calculate the maximum completion time for each task using DFS
        maxTimes = map (\(taskId, _) -> dfs graph taskId tasks) tasks
    -- Return the maximum completion time among all tasks
    in maximum maxTimes

main :: IO ()
main = do
    -- Input tasks and dependencies
    let tasks = [('A', 5), ('B', 3), ('C', 4)]
        dependencies = [('A', 'B'), ('A', 'C')]
    -- Output the minimum time required for all tasks to complete
    putStrLn $ "Minimum time required: " ++ show (minimumTime tasks dependencies)

