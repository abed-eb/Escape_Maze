import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.LinkedList;
import java.util.Queue;

public class EscapeImpl implements Escape {
    /**
     * Find a path from the entrance to the exit in a maze.
     */
    final List<Pair<Integer, Integer>> result = new ArrayList<>();
    final List<Pair<Integer, Integer>> final_result = new ArrayList<>();
    Pair<Integer, Integer> entrance;

    /**
     * @param maze is a 2D arrayList (matrix)
     * @return return A list of coordinates, from the exit point to the entrance point. The
     * first item of the list is the coordinate of exit point while the last item is the
     * coordinate of the entrance point.
     */
    @Override
    public List<Pair<Integer, Integer>> escape(final char[][] maze) {
        boolean[][] visited = new boolean[maze.length][maze[0].length];
        Queue<Pair> queue = new LinkedList<>();
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                if (maze[i][j] == 'E') {
                    entrance = new Pair<>(i, j);
                    queue.add(entrance);
                    result.add(new Pair<>(entrance.first, entrance.second));
                    visited[entrance.first][entrance.second] = true;
                    return myPerfectRecursionMethod(maze, queue, visited);
                }
            }
        }
        return new ArrayList<>();
    }

    /**
     * check where the cell is valid or not
     */
    private static boolean isValid(int i, int j, char[][] maze, boolean[][] visited) {
        return i >= 0 && j >= 0 && i < maze.length && j < maze[0].length && maze[i][j] != 'W' && !visited[i][j];
    }

    private List<Pair<Integer, Integer>> myPerfectRecursionMethod(char[][] maze, Queue<Pair> queue, boolean[][] visited) {
        Pair<Integer, Integer> p = queue.remove();
        // move up
        if (isValid(p.first - 1, p.second, maze, visited)) {
            queue.add(new Pair<>(p.first - 1, p.second));
            visited[p.first - 1][p.second] = true;
            result.add(new Pair<>(p.first - 1, p.second));
            if (maze[p.first - 1][p.second] == 'X') {
                return findPath(result, result.size() - 1);
            }
        }
        // move down
        if (isValid(p.first + 1, p.second, maze, visited)) {
            queue.add(new Pair<>(p.first + 1, p.second));
            visited[p.first + 1][p.second] = true;
            result.add(new Pair<>(p.first + 1, p.second));
            if (maze[p.first + 1][p.second] == 'X') {
                return findPath(result, result.size() - 1);
            }
        }
        // move left
        if (isValid(p.first, p.second - 1, maze, visited)) {
            queue.add(new Pair<>(p.first, p.second - 1));
            visited[p.first][p.second - 1] = true;
            result.add(new Pair<>(p.first, p.second - 1));
            if (maze[p.first][p.second - 1] == 'X') {
                return findPath(result, result.size() - 1);
            }
        }
        // move right
        if (isValid(p.first, p.second + 1, maze, visited)) {
            queue.add(new Pair<>(p.first, p.second + 1));
            visited[p.first][p.second + 1] = true;
            result.add(new Pair<>(p.first, p.second + 1));
            if (maze[p.first][p.second + 1] == 'X') {
                return findPath(result, result.size() - 1);
            }
        }
        // check if queue is empty and we can not reach to X
        if (queue.isEmpty()) {
            result.clear();
            return result;
        }
        // Destination found;
        if (maze[p.first][p.second] == 'X') {
            return findPath(result, result.size() - 1);
        } else return myPerfectRecursionMethod(maze, queue, visited);
    }

    /**
     * check where the cell is valid or not
     */
    private List<Pair<Integer, Integer>> findPath(List<Pair<Integer, Integer>> result, int pair_index) {
        final_result.add(result.get(pair_index));
        int next_index = pair_index;
        Pair<Integer, Integer> p = result.get(pair_index);
        for (Pair<Integer, Integer> pair : result) {
            if (pair.first == p.first - 1 && Objects.equals(pair.second, p.second)) {
                next_index = result.indexOf(pair);
                break;
            }
            if (pair.first == p.first + 1 && Objects.equals(pair.second, p.second)) {
                next_index = result.indexOf(pair);
                break;
            }
            if (pair.first.equals(p.first) && pair.second == p.second - 1) {
                next_index = result.indexOf(pair);
                break;
            }
            if (pair.first.equals(p.first) && pair.second == p.second + 1) {
                next_index = result.indexOf(pair);
                break;
            }
        }
        if (Objects.equals(p.first, entrance.first) && Objects.equals(p.second, entrance.second)) {
            return final_result;
        } else return findPath(result, next_index);
    }
}
