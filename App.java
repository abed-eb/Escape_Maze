import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        long startTime = System.nanoTime();
        final char[][] maze = new char[][]{
                {'E', 'W', 'X', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', 'W', ' ', 'W', 'W', ' '},
                {' ', 'W', 'W', ' ', 'W', ' ', ' ', ' '},
                {'W', ' ', ' ', 'W', ' ', ' ', ' ', 'W'},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', 'W', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', 'W', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
        };
        final Escape escape = new EscapeImpl();
        final List<Pair<Integer, Integer>> result = escape.escape(maze);
        if (result.isEmpty())
            System.out.println(result);
        for (int i = 0; i <= result.size() - 1; i++) {
            Pair<Integer, Integer> pair = result.get(i);
            System.out.println(
                    "(" + pair.first + ", " + pair.second + ")"
            );
        }
        long endTime   = System.nanoTime();
        long totalTime = endTime - startTime;
        System.out.println(totalTime);
    }
}
