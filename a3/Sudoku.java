package cs445.a3;
import java.util.HashSet;
import java.util.List;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Sudoku {
    public static final int SIZE = 9;
    public static boolean lastElementIsZero = false;
    public static int[][] originalBoard;
    public static boolean isReject;

    static boolean isFullSolution(int[][] board) {
        // TODO: Complete this method
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == 0) { //if cell is unassigned
                    return false;
                }
            }
        }

        return true;
    }

    static boolean reject(int[][] board) {
        // TODO: Complete this method
        if (board.length > 9) {
            return true;
        }

        for (int i = 0; i < 9; i++) {
            if (board[i].length > 9) {
                return true;
            }
        }

        if (isInBox(board) == true || isInRowOrCol(board, 'r') == true || isInRowOrCol(board, 'c') == true) {
            return true;
        }

        return false;
    }

    static boolean isInRowOrCol(int[][] board, char rOrC) {
        for (int i = 0; i < SIZE; i++) {
            HashSet<Integer> set = new HashSet<>();
            for (int j = 0; j < SIZE; j++) {
                if (rOrC == 'r') {
                    if (set.contains(board[i][j]) && board[i][j] != 0) {
                        return true;
                    }
                    set.add(board[i][j]);
                } else if (rOrC == 'c') {
                    if (set.contains(board[j][i]) && board[j][i] != 0) {
                        return true;
                    }
                    set.add(board[j][i]);
                }
            }
        }
        return false;
    }

    static boolean isInBox(int[][] board) {
        for (int block = 0; block < SIZE; block++) {
            HashSet<Integer> set = new HashSet<>();
            for (int i = block / 3 * 3; i < block / 3 * 3 + 3; i++) {
                for (int j = block % 3 * 3; j < block % 3 * 3 + 3; j++) {
                    if (set.contains(board[i][j]) && board[i][j] != 0) {
                        return true;
                    }
                    set.add(board[i][j]);
                }
            }
        }
        return false;
    }


    static int[][] extend(int[][] board) {
        // TODO: Complete this method
        int[][] temp = new int[board.length][];
        for (int i = 0; i < board.length; i++) {
            temp[i] = board[i].clone();
        }

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (temp[i][j] == 0) { //if cell is unassigned
                    temp[i][j] = 1;
                    if (i == 8 && j == 8) {
                        lastElementIsZero = true;
                    }
                    return temp;
                }
            }
        }
        return null;
    }

    static int[] foundZero(int[][] board) {
        int[] position = new int[2];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == 0) {
                    position[0] = i;
                    position[1] = j;

                    return position;
                }
            }
        }
        return null;
    }

    static int[][] next(int[][] board) {
        // TODO: Complete this method
        int tempRow;
        int tempCol;
        int row;
        int col;
        int[] tempPostion;
        int[] position = foundZero(board);
        boolean existInOld = true;
        int oneDindex;
        if (position == null && lastElementIsZero) {
            if (board[8][8] < 9)
                board[8][8] += 1;
            return board;
        }
        if (position == null) {
            row = 8;
            col = 8;
        } else {
            row = position[0];
            col = position[1];

        }

        oneDindex = twoDtoOneD(row, col);
        while (existInOld) {
            oneDindex -= 1;
            tempPostion = oneDtoTwoD(oneDindex);
            tempRow = tempPostion[0];
            tempCol = tempPostion[1];

            if (exists(board, tempRow, tempCol)) {
                existInOld = true;
            } else {
                if (board[tempRow][tempCol] < 9) {
                    board[tempRow][tempCol] += 1;
                    return board;
                } else {
                    return null;
                }
            }

        }
        return null;
    }

    static int twoDtoOneD(int row, int col) {
        int temp = row * 9;
        int result = temp + col;
        return result;
    }

    static int[] oneDtoTwoD(int index) {
        int[] position = new int[2];
        position[0] = index / 9; //rowIndex
        position[1] = index % 9; //colIndex
        return position;
    }

    static boolean exists(int[][] board, int row, int col) {
        if (originalBoard[row][col] == board[row][col]) {
            return true;
        }
        return false;
    }

    static void testIsFullSolution() {
        // TODO: Complete this method
        //full not correct
        int[][] testBoardFullAndWrong = new int[][]{
                {6, 5, 1, 8, 7, 3, 5, 9, 8},
                {1, 3, 3, 2, 5, 4, 5, 6, 8},
                {9, 8, 9, 1, 5, 4, 3, 5, 7},
                {1, 6, 5, 3, 4, 7, 9, 8, 3},
                {4, 4, 5, 8, 9, 1, 3, 4, 2},
                {5, 6, 2, 3, 4, 6, 5, 7, 3},
                {5, 7, 8, 3, 3, 1, 9, 2, 6},
                {2, 6, 5, 4, 4, 8, 9, 3, 1},
                {1, 9, 3, 6, 2, 5, 2, 8, 1}
        };
        //not full
        int[][] testBoardNotFull = new int[][]{
                {6, 5, 0, 8, 7, 3, 0, 9, 0},
                {0, 0, 3, 2, 5, 0, 0, 0, 8},
                {9, 8, 0, 1, 0, 4, 3, 5, 7},
                {1, 0, 5, 0, 0, 0, 0, 0, 0},
                {4, 0, 0, 0, 0, 0, 0, 0, 2},
                {0, 0, 0, 0, 0, 0, 5, 0, 3},
                {5, 7, 8, 3, 0, 1, 0, 2, 6},
                {2, 0, 0, 0, 4, 8, 9, 0, 0},
                {0, 9, 0, 6, 2, 5, 0, 8, 1}
        };
        int[][] testBoardFullAndCorrect = {
                {8, 3, 5, 4, 1, 6, 9, 2, 7},
                {2, 9, 6, 8, 5, 7, 4, 3, 1},
                {4, 1, 7, 2, 9, 3, 6, 5, 8},
                {5, 6, 9, 1, 3, 4, 7, 8, 2},
                {1, 2, 3, 6, 7, 8, 5, 4, 9},
                {7, 4, 8, 5, 2, 9, 1, 6, 3},
                {6, 5, 2, 7, 8, 1, 3, 9, 4},
                {9, 8, 1, 3, 4, 5, 2, 7, 6},
                {3, 7, 4, 9, 6, 2, 8, 1, 5}};

        int[][] testBoardFullAndOneNumWrong = {
                {8, 3, 5, 4, 1, 6, 9, 2, 7},
                {2, 9, 6, 8, 5, 7, 4, 3, 1},
                {4, 1, 7, 2, 9, 3, 6, 5, 8},
                {5, 6, 9, 1, 8, 4, 7, 8, 2},
                {1, 2, 3, 6, 7, 8, 5, 4, 9},
                {7, 4, 8, 5, 2, 9, 1, 6, 3},
                {6, 5, 2, 7, 8, 1, 3, 9, 4},
                {9, 8, 1, 3, 4, 5, 2, 7, 6},
                {3, 7, 4, 9, 6, 2, 8, 1, 5}};

        int[][] testBoardFullAndManyNumWrong = {
                {8, 3, 5, 4, 1, 6, 9, 2, 4},
                {2, 9, 6, 8, 5, 7, 4, 3, 1},
                {4, 1, 7, 2, 9, 3, 6, 5, 8},
                {5, 6, 9, 1, 8, 5, 7, 8, 2},
                {1, 2, 3, 7, 7, 8, 5, 4, 9},
                {7, 4, 8, 4, 2, 9, 1, 6, 3},
                {6, 5, 5, 7, 8, 1, 3, 9, 4},
                {9, 8, 1, 3, 4, 5, 2, 7, 6},
                {3, 7, 4, 9, 6, 2, 8, 1, 5}};

        int[][] testBoardOneNumMissing = {
                {8, 3, 5, 4, 1, 6, 9, 2, 7},
                {2, 9, 6, 8, 5, 7, 4, 3, 1},
                {4, 1, 7, 2, 9, 3, 6, 5, 8},
                {5, 6, 9, 1, 3, 4, 7, 8, 2},
                {1, 2, 3, 6, 7, 0, 5, 4, 9},
                {7, 4, 8, 5, 2, 9, 1, 6, 3},
                {6, 5, 2, 7, 8, 1, 3, 9, 4},
                {9, 8, 1, 3, 4, 5, 2, 7, 6},
                {3, 7, 4, 9, 6, 2, 8, 1, 5}};
        int[][] testBoardNumMissing = {
                {0, 3, 5, 4, 1, 6, 9, 2, 7},
                {2, 9, 6, 8, 5, 7, 4, 3, 1},
                {4, 1, 7, 2, 9, 3, 6, 5, 8},
                {0, 6, 0, 1, 3, 0, 7, 8, 0},
                {1, 2, 3, 6, 7, 8, 5, 4, 9},
                {7, 4, 8, 0, 2, 9, 1, 6, 3},
                {6, 5, 2, 7, 8, 1, 3, 9, 0},
                {9, 8, 1, 3, 4, 5, 2, 7, 6},
                {3, 7, 4, 9, 6, 2, 8, 1, 0}};

        System.out.println("(testIsFullSolution) Test Board is full But Wrong:  " + isFullSolution(testBoardFullAndWrong));
        System.out.println("(testIsFullSolution) Test Board is not full:  " + isFullSolution(testBoardNotFull));
        System.out.println("(testIsFullSolution) Test Board is full But Correct:  " + isFullSolution(testBoardFullAndCorrect));
        System.out.println("(testIsFullSolution) Test Board is full But One Num Wrong:  " + isFullSolution(testBoardFullAndOneNumWrong));
        System.out.println("(testIsFullSolution) Test Board is full But Some Num Wrong:  " + isFullSolution(testBoardFullAndManyNumWrong));
        System.out.println("(testIsFullSolution) Test Board is missing one Num:  " + isFullSolution(testBoardOneNumMissing));
        System.out.println("(testIsFullSolution) Test Board is missing some Num:  " + isFullSolution(testBoardNumMissing));
    }

    static void testReject() {
        // TODO: Complete this method
        int[][] testRowNotFull = new int[][]{//test is correct, should not be rejected
                {6, 5, 0, 8, 7, 3, 0, 9, 0},
                {0, 0, 3, 2, 5, 0, 0, 0, 8},
                {9, 8, 0, 1, 0, 4, 3, 5, 7},
                {1, 0, 5, 0, 0, 0, 0, 0, 0},
                {4, 0, 0, 0, 0, 0, 0, 0, 2},
                {0, 0, 0, 0, 0, 0, 5, 0, 3},
                {5, 7, 8, 3, 0, 1, 0, 2, 6},
                {2, 0, 0, 0, 4, 8, 9, 0, 0},
                {0, 9, 0, 6, 2, 5, 0, 8, 1}
        };

        System.out.println("(testReject)(Not full And Correct) : Reject: " + reject(testRowNotFull));

        int[][] testBoardNotFullAndWrong = new int[][]{
                {4, 8, 5, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 6, 0, 2, 3, 8, 0},
                {0, 0, 2, 0, 0, 8, 0, 5, 0},
                {2, 0, 0, 0, 0, 0, 5, 4, 0},
                {0, 0, 5, 0, 7, 0, 6, 0, 0},
                {0, 3, 9, 0, 0, 0, 0, 0, 1},
                {0, 6, 0, 8, 0, 0, 2, 0, 0},
                {0, 7, 4, 9, 0, 6, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 5}
        };

        System.out.println("(testReject)(Not Full And Wrong): Reject : " + reject(testBoardNotFullAndWrong));

        int[][] testBoardCorrect = {//test is correct, should not be rejected
                {8, 3, 5, 4, 1, 6, 9, 2, 7},
                {2, 9, 6, 8, 5, 7, 4, 3, 1},
                {4, 1, 7, 2, 9, 3, 6, 5, 8},
                {5, 6, 9, 1, 3, 4, 7, 8, 2},
                {1, 2, 3, 6, 7, 8, 5, 4, 9},
                {7, 4, 8, 5, 2, 9, 1, 6, 3},
                {6, 5, 2, 7, 8, 1, 3, 9, 4},
                {9, 8, 1, 3, 4, 5, 2, 7, 6},
                {3, 7, 4, 9, 6, 2, 8, 1, 5}};
        System.out.println("(testReject)(Correct and full) : Reject: " + reject(testBoardCorrect));

        int[][] testBoardFull = new int[][]{//test is wrong, should be rejected
                {6, 5, 1, 8, 7, 3, 5, 9, 8},
                {1, 3, 3, 2, 5, 4, 5, 6, 8},
                {9, 8, 9, 1, 5, 4, 3, 5, 7},
                {1, 6, 5, 3, 4, 7, 9, 8, 3},
                {4, 4, 5, 8, 9, 1, 3, 4, 2},
                {5, 6, 2, 3, 4, 6, 5, 7, 3},
                {5, 7, 8, 3, 3, 1, 9, 2, 6},
                {2, 6, 5, 4, 4, 8, 9, 3, 1},
                {1, 9, 3, 6, 2, 5, 2, 8, 1}
        };

        System.out.println("(testReject)(Wrong and full) : Reject: " + reject(testBoardFull));

        int[][] testBoardTooLargeRow = {//too large row, should be rejected
                {8, 3, 5, 4, 1, 6, 9, 2, 7},
                {2, 9, 6, 8, 5, 7, 4, 3, 1},
                {4, 1, 7, 2, 9, 3, 6, 5, 8},
                {5, 6, 9, 1, 3, 4, 7, 8, 2, 1},
                {1, 2, 3, 6, 7, 8, 5, 4, 9},
                {7, 4, 8, 5, 2, 9, 1, 6, 3},
                {6, 5, 2, 7, 8, 1, 3, 9, 4},
                {9, 8, 1, 3, 4, 5, 2, 7, 6},
                {3, 7, 4, 9, 6, 2, 8, 1, 5}};

        System.out.println("(testReject)(Too Large Row): Reject : " + reject(testBoardTooLargeRow));

        int[][] testBoardTooLargeCol = new int[][]{// too large col, should be rejected
                {6, 5, 1, 8, 7, 3, 5, 9, 8},
                {1, 3, 3, 2, 5, 4, 5, 6, 8},
                {9, 8, 9, 1, 5, 4, 3, 5, 7},
                {1, 6, 5, 3, 4, 7, 9, 8, 3},
                {4, 4, 5, 8, 9, 1, 3, 4, 2},
                {5, 6, 2, 3, 4, 6, 5, 7, 3},
                {5, 7, 8, 3, 3, 1, 9, 2, 6},
                {2, 6, 5, 4, 4, 8, 9, 3, 1},
                {1, 9, 3, 6, 2, 5, 2, 8, 1},
                {1, 9, 3, 6, 2, 5, 2, 8, 1}

        };
        System.out.println("(testReject)(Too Large Col): Reject : " + reject(testBoardTooLargeCol));

        int[][] testBoardWrong = {// test is wrong, should be rejected
                {8, 3, 5, 4, 1, 6, 9, 2, 7},
                {2, 9, 6, 8, 5, 7, 4, 3, 1},
                {4, 1, 7, 2, 9, 3, 6, 5, 8},
                {5, 6, 9, 1, 3, 4, 2, 8, 2},
                {1, 2, 3, 6, 7, 8, 5, 4, 9},
                {7, 4, 8, 4, 2, 9, 1, 6, 3},
                {6, 5, 2, 7, 8, 1, 3, 9, 4},
                {9, 8, 1, 3, 4, 5, 2, 7, 6},
                {3, 7, 4, 9, 6, 2, 8, 1, 5}};
        System.out.println("(testReject)(Full And Wrong): Reject : " + reject(testBoardWrong));

    }

    static void testExtend() {
        // TODO: Complete this method
        // TODO: Complete this method
        System.out.println("(testExtend)");
        int[][] notFullInLast = new int[][]{
                {8, 3, 5, 4, 1, 6, 9, 2, 7},
                {2, 9, 6, 8, 5, 7, 4, 3, 1},
                {4, 1, 7, 2, 9, 3, 6, 5, 8},
                {5, 6, 9, 1, 3, 4, 7, 8, 2},
                {1, 2, 3, 6, 7, 8, 5, 4, 9},
                {7, 4, 8, 5, 2, 9, 1, 6, 3},
                {6, 5, 2, 7, 8, 1, 3, 9, 4},
                {9, 8, 1, 3, 4, 5, 2, 7, 6},
                {3, 7, 4, 9, 6, 2, 8, 1, 0}
        };

        System.out.println("(testExtend)The last element is 0, so it should be extended");
       	System.out.println("-----Original-----");
        printBoard(notFullInLast);
        System.out.println("-----Extend-----");
        printBoard(extend(notFullInLast));


        int[][] notFullInFirst = new int[][]{
                {0, 3, 5, 4, 1, 6, 9, 2, 7},
                {2, 9, 6, 8, 5, 7, 4, 3, 1},
                {4, 1, 7, 2, 9, 3, 6, 5, 8},
                {5, 6, 9, 1, 3, 4, 7, 8, 2},
                {1, 2, 3, 6, 7, 8, 5, 4, 9},
                {7, 4, 8, 5, 2, 9, 1, 6, 3},
                {6, 5, 2, 7, 8, 1, 3, 9, 4},
                {9, 8, 1, 3, 4, 5, 2, 7, 6},
                {3, 7, 4, 9, 6, 2, 8, 1, 5}
        };

        System.out.println("(testExtend)The first element is 0, so it should be extended");
       	System.out.println("-----Original-----");
        printBoard(notFullInFirst);
        System.out.println("-----Extend-----");
        printBoard(extend(notFullInFirst));

        int[][] notFullInSomeWhere = new int[][]{
                {8, 3, 5, 4, 1, 6, 9, 2, 7},
                {2, 9, 6, 8, 5, 7, 4, 3, 1},
                {4, 1, 0, 2, 9, 3, 6, 5, 8},
                {5, 6, 9, 1, 3, 4, 7, 8, 2},
                {1, 2, 3, 6, 0, 8, 5, 4, 9},
                {7, 4, 8, 5, 2, 9, 1, 6, 3},
                {6, 5, 2, 7, 8, 1, 3, 9, 4},
                {9, 8, 1, 3, 4, 5, 2, 7, 6},
                {3, 7, 4, 9, 6, 2, 8, 1, 5}
        };

        System.out.println("(testExtend)An element is 0 in somewhere, so it should be extended");
    	System.out.println("-----Original-----");
     	printBoard(notFullInSomeWhere);
        System.out.println("-----Extend-----");
        printBoard(extend(notFullInSomeWhere));


        int[][] full = new int[][]{
                {8, 1, 2, 7, 5, 3, 6, 4, 9},
                {9, 4, 3, 6, 8, 2, 1, 7, 5},
                {6, 7, 5, 4, 9, 1, 2, 8, 3},
                {1, 5, 4, 2, 3, 7, 8, 9, 6},
                {3, 6, 9, 8, 4, 5, 7, 2, 1},
                {2, 8, 7, 1, 6, 9, 5, 3, 4},
                {5, 2, 1, 9, 7, 4, 3, 6, 8},
                {4, 3, 8, 5, 2, 6, 9, 1, 7},
                {7, 9, 6, 3, 1, 8, 4, 5, 2},
        };

        System.out.println("(testExtend)The board is full, so it should not be extended");
        System.out.println("-----Original-----");
        printBoard(full);
        System.out.println("-----Extend-----");
        printBoard(extend(full));

    }

    static void testNext() {
        // TODO: Complete this method
        System.out.println("(testNext)");
        int[][] testBoardNotFull = new int[][]{
                {6, 5, 0, 8, 7, 3, 0, 9, 0},
                {0, 0, 3, 2, 5, 0, 0, 0, 8},
                {9, 8, 0, 1, 0, 4, 3, 5, 7},
                {1, 0, 5, 0, 0, 0, 0, 0, 0},
                {4, 0, 0, 0, 0, 0, 0, 0, 2},
                {0, 0, 0, 0, 0, 0, 5, 0, 3},
                {5, 7, 8, 3, 0, 1, 0, 2, 6},
                {2, 0, 0, 0, 4, 8, 9, 0, 0},
                {0, 9, 0, 6, 2, 5, 0, 8, 1}
        };
        originalBoard = new int[testBoardNotFull.length][];
        for (int i = 0; i < testBoardNotFull.length; i++) {
            originalBoard[i] = testBoardNotFull[i].clone();
        }
        System.out.println("-----Original-----");
        printBoard(originalBoard);
        int[][] testBoardOne = new int[][]{//the most recently place is row[0]col[2], and it is 9, so it cannot have next
                {6, 5, 9, 8, 7, 3, 0, 9, 0},
                {0, 0, 3, 2, 5, 0, 0, 0, 8},
                {9, 8, 0, 1, 0, 4, 3, 5, 7},
                {1, 0, 5, 0, 0, 0, 0, 0, 0},
                {4, 0, 0, 0, 0, 0, 0, 0, 2},
                {0, 0, 0, 0, 0, 0, 5, 0, 3},
                {5, 7, 8, 3, 0, 1, 0, 2, 6},
                {2, 0, 0, 0, 4, 8, 9, 0, 0},
                {0, 9, 0, 6, 2, 5, 0, 8, 1}
        };
        System.out.println("-----Next----");
        printBoard(testBoardOne);
        System.out.println("(testNext) the most recently place is 9 board[0][2], so it cannot have next and should be no assignment: ");
        printBoard(next(testBoardOne));

        int[][] testBoardTwo = new int[][]{//the most recently place is row[0]col[2], and it is 7, so it can have next
                {6, 5, 7, 8, 7, 3, 0, 9, 0},
                {0, 0, 3, 2, 5, 0, 0, 0, 8},
                {9, 8, 0, 1, 0, 4, 3, 5, 7},
                {1, 0, 5, 0, 0, 0, 0, 0, 0},
                {4, 0, 0, 0, 0, 0, 0, 0, 2},
                {0, 0, 0, 0, 0, 0, 5, 0, 3},
                {5, 7, 8, 3, 0, 1, 0, 2, 6},
                {2, 0, 0, 0, 4, 8, 9, 0, 0},
                {0, 9, 0, 6, 2, 5, 0, 8, 1}
        };
        System.out.println("-----Next----");
        printBoard(testBoardTwo);
        System.out.println("(testNext) the most recently place is 7 board[0][2], so it can have next(+1): ");
        printBoard(next(testBoardTwo));
    }

    static void printBoard(int[][] board) {
        if (board == null) {
            System.out.println("No assignment");
            return;
        }
        for (int i = 0; i < 9; i++) {
            if (i == 3 || i == 6) {
                System.out.println("----+-----+----");
            }
            for (int j = 0; j < 9; j++) {
                if (j == 2 || j == 5) {
                    System.out.print(board[i][j] + " | ");
                } else {
                    System.out.print(board[i][j]);
                }
            }
            System.out.print("\n");
        }
    }

    static int[][] readBoard(String filename) {
        List<String> lines = null;
        try {
            lines = Files.readAllLines(Paths.get(filename), Charset.defaultCharset());
        } catch (IOException e) {
            return null;
        }
        int[][] board = new int[9][9];
        int val = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                try {
                    val = Integer.parseInt(Character.toString(lines.get(i).charAt(j)));
                } catch (Exception e) {
                    val = 0;
                }
                board[i][j] = val;
            }
        }
        return board;
    }

    static int[][] solve(int[][] board) {
        isReject = reject((board));
        if (reject(board)) return null;
        if (isFullSolution(board)) return board;
        int[][] attempt = extend(board);
        while (attempt != null) {
            int[][] solution = solve(attempt);
            if (solution != null) return solution;
            attempt = next(attempt);
        }
        return null;
    }

    public static void main(String[] args) {
      long start = System.nanoTime();
        if (args[0].equals("-t")) {
            testIsFullSolution();
            testReject();
            testExtend();
            testNext();
        } else {
            int[][] board = readBoard(args[0]);

            originalBoard = new int[board.length][];
            for (int i = 0; i < board.length; i++) {
                originalBoard[i] = board[i].clone();
            }

            printBoard(board);
            System.out.println("Solution:");
            printBoard(solve(board));
            long end = System.nanoTime();
            long result = end - start;
            double resultNew = (double) result / 1000000000;
            System.out.println("The runtime (second) is: " + resultNew);
        }



    }
}
