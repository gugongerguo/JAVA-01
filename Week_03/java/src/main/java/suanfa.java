import java.util.*;

public class suanfa {

    public static void main(String[] args) {
//        char[][] grid = new char[4][];
//        grid[0]=new char[]{'1','1','1','1','0'};
//        grid[1]=new char[]{'1','1','0','1','0'};
//        grid[2]=new char[]{'1','1','0','0','0'};
//        grid[3]=new char[]{'0','0','0','0','0'};
//        int i = numIslands(grid);
//        int[] commands = new int[]{4,-1,4,-2,4};
//        int[][] obstacles = new int[1][];
//        obstacles[0]= new int[]{2,4};
//        System.out.println(robotSim(commands,obstacles));
        int[] nums = {2, 1, 1, 1, 4};
        System.out.println(jump(nums));

    }

    public static int numIslands(char[][] grid) {
        int island = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '1') {
                    proccess(i, j, grid);
                    island++;
                }
            }
        }
        return island;
    }

    private static void proccess(int i, int j, char[][] grid) {
        if (i < 0 || j < 0 || i > grid.length - 1 || j > grid[0].length - 1 || grid[i][j] == '0') {
            return;
        }
        grid[i][j] = '0';
        proccess(i - 1, j, grid);
        proccess(i + 1, j, grid);
        proccess(i, j - 1, grid);
        proccess(i, j + 1, grid);
    }

    public static int robotSim(int[] commands, int[][] obstacles) {
        String[] direction = new String[]{"up", "right", "down", "left"};
        int[] dx = new int[]{0, 1, 0, -1};
        int[] dy = new int[]{1, 0, -1, 0};
        int result = 0, x = 0, y = 0, directionIndex = 0;
        HashSet<String> set = new HashSet<>();
        for (int[] obstacle : obstacles) {
            set.add(obstacle[0] + "," + obstacle[1]);
        }
        for (int i = 0; i < commands.length; i++) {
            if (commands[i] == -1) {
                directionIndex++;
            } else if (commands[i] == -2) {
                directionIndex--;
            } else {
                int index = (directionIndex % 4 + 4) % 4;
                for (int s = 0; s < commands[i]; s++) {
                    int tmpx = x + dx[index];
                    int tmpy = y + dy[index];
                    String tmpxy = tmpx + "," + tmpy;
                    if (set.contains(tmpxy)) break;
                    x = tmpx;
                    y = tmpy;
                    result = Math.max(result, x * x + y * y);
                }
            }

        }
        return result;
    }

    public static int jump(int[] nums) {
        return _jump(1, nums, 1, nums[0], nums[0]);
    }

    public static int _jump(int step, int[] nums, int left, int right, int target) {
        for (int i = left; i <= right; i++) {
            target = Math.max(target, i + nums[i]);
            if (target >= nums.length - 1) {
                step++;
                return step;
            }
        }
        step++;
        step = _jump(step, nums, right + 1, target, target);
        return step;
    }

    public int longestValidParentheses(String s) {
        int result = 0;
        Deque<Integer> stack = new LinkedList<Integer>();
        stack.push(-1);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            }else{
                stack.pop();
                if(stack.isEmpty()){
                    stack.push(i);
                }else{
                    result = Math.max(i-stack.peek(), result);
                }
            }
        }
        return result;
    }
}
