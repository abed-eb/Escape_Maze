import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;
import org.junit.jupiter.api.Test;

public class EscapeTest {
    private final Escape escape = new EscapeImpl();

    final char[][] maze1 = new char[][]{
            {' ', ' ', ' ', ' ', ' '},
            {' ', ' ', 'W', 'W', ' '},
            {' ', 'W', 'X', ' ', ' '},
            {'E', ' ', ' ', ' ', ' '},
    };
    final char[][] maze2 = new char[][]{
            {'E', ' ', ' ', ' ', ' '},
            {' ', ' ', 'W', 'W', ' '},
            {' ', 'W', 'X', ' ', ' '},
            {' ', ' ', ' ', ' ', ' '},
    };
    final char[][] maze3 = new char[][]{
            {' ', ' ', ' ', ' ', ' '},
            {' ', 'E', 'X', 'W', ' '},
            {' ', 'W', 'W', ' ', ' '},
            {' ', ' ', 'W', 'W', ' '},
    };

    @Test
    public void testCase1() {
        final List<Pair<Integer, Integer>> result = escape.escape(maze1);
        assertEquals(true, testPath(result, maze1));
    }

    @Test
    public void testCase2() {
        final List<Pair<Integer, Integer>> result = escape.escape(maze2);
        assertEquals(true, testPath(result, maze2));
    }

    @Test
    public void testCase3() {
        final List<Pair<Integer, Integer>> result = escape.escape(maze3);
        assertEquals(true, testPath(result, maze3));
    }

    private boolean testPath(List<Pair<Integer, Integer>> path, char[][] maze) {
        final Pair<Integer, Integer> exitCoordinate = path.remove(0);
        if (maze[exitCoordinate.first][exitCoordinate.second] != 'X') {
            return false;
        }
        while (path.size() > 1) {
            final Pair<Integer, Integer> coordinate = path.remove(0);
            if (maze[coordinate.first][coordinate.second] != ' ') {
                return false;
            }
        }
        final Pair<Integer, Integer> entranceCoordinate = path.remove(0);
        if (maze[entranceCoordinate.first][entranceCoordinate.second] != 'E') {
            return false;
        }
        return true;
    }
}
