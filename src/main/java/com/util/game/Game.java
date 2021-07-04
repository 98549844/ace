package com.util.game;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private static final int x = 10;
    private static final int y = 8;
    public static final String ANSI_RED = "\u001B[31m";
    public static final String RESET_CONSOLE_COLOR = "\033[0m";
    public static final String BLACK = "\033[0;30m";   // BLACK
    public static final String RED = "\033[0;31m";     // RED //1
    public static final String GREEN = "\033[0;32m";   // GREEN //2
    public static final String YELLOW = "\033[0;33m";  // YELLOW //3
    public static final String BLUE = "\033[0;34m";    // BLUE //4
    public static final String PURPLE = "\033[0;35m";  // PURPLE //5
    public static final String CYAN = "\033[0;36m";    // CYAN //6
    public static final String WHITE = "\033[0;37m";   // WHITE

    public static void main(String[] args) {
        Game game = new Game();
        int[][] board = game.createGameBoard(x, y);
        game.printGameBoard(board);
        System.out.println("=========================================");
        List<Zhu> step1 = game.initSingleZhuValue(board);
        List<Zhu> step2 = game.secondStep(step1);
        List<Zhu> step3 = game.thirdStep(step2);
        List<Zhu> step4 = game.finalStep(step3);
        game.printYNGameBoard(step4);
        System.out.println("=========================================");
        //最後既答案
        game.printBoardWithNumAndColor(board, step4);
    }


    //1.創建單粒珠子數值
    public List<Zhu> initSingleZhuValue(int[][] board) {
        List<Zhu> zhuList = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                Zhu zhu = new Zhu();
                zhu.setX(i);
                zhu.setY(j);
                zhu.setZhuValue(board[i][j]);
                zhuList.add(zhu);
            }
        }

        return zhuList;
    }

    //2.為每粒珠分配右珠
    public List<Zhu> secondStep(List<Zhu> zhus) {
        for (int i = 0; i < zhus.size() - 1; i++) {
            //分配右珠
            if (zhus.get(i).getX() == zhus.get(i + 1).getX()) {
                zhus.get(i).setRightZhu(zhus.get(i + 1));
            }
        }

        return zhus;
    }

    //下珠分組
    public List<Zhu> thirdStep(List<Zhu> zhus) {
        //先分組
        //總list
        List<List<Zhu>> zongList = new ArrayList<>();
        for (int i = 0; i < x; i++) {
            //分List
            List<Zhu> fenList = new ArrayList<>();
            for (int j = 0; j < zhus.size(); j++) {
                if (zhus.get(j).getY() == i) {
                    fenList.add(zhus.get(j));
                }
            }
            zongList.add(fenList);
        }

        for (List<Zhu> fenList : zongList) {
            for (int i = 0; i < fenList.size() - 1; i++) {
                fenList.get(i).setBottomZhu(fenList.get(i + 1));
            }
        }
        return zhus;
    }

    //總計判斷
    public List<Zhu> finalStep(List<Zhu> zhus) {
        //temp result list
        List<Zhu> tempResultR = new ArrayList<>();
        List<Zhu> tempResultT = new ArrayList<>();
        int countR = 0;
        int countT = 0;
        for (Zhu zhu : zhus) {
            //右三連
            if (zhu.getRightZhu() != null && zhu.getZhuValue() == zhu.getRightZhu().getZhuValue()) {
                ++countR;
                tempResultR.add(zhu);
                tempResultR.add(zhu.getRightZhu());

                if (countR >= 2) {
                    for (Zhu z : tempResultR) {
                        z.setSanLian(true);
                    }
                }

            } else {
                countR = 0;
                tempResultR.clear();
            }
        }
        //下三連
        List<List<Zhu>> zongList = new ArrayList<>();
        for (int i = 0; i < x; i++) {
            //分List
            List<Zhu> fenList = new ArrayList<>();
            for (int j = 0; j < zhus.size(); j++) {
                if (zhus.get(j).getY() == i) {
                    fenList.add(zhus.get(j));
                }
            }
            zongList.add(fenList);
        }
        for (List<Zhu> zhuList : zongList) {
            for (Zhu zhu : zhuList) {
                if (zhu.getBottomZhu() != null && zhu.getZhuValue() == zhu.getBottomZhu().getZhuValue()) {
                    ++countT;
                    tempResultT.add(zhu);
                    tempResultT.add(zhu.getBottomZhu());
                    if (countT >= 2) {
                        for (Zhu zhu1 : tempResultT) {
                            zhu1.setSanLian(true);
                        }
                    }
                } else {
                    countT = 0;
                    tempResultT.clear();
                }
            }
        }
        return zhus;
    }

    //創建棋盤
    public int[][] createGameBoard(int x, int y) {
        int[][] board = new int[x][y];
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                int randomNum = (int) (Math.random() * 6) + 1;
                board[i][j] = randomNum;
            }
        }
        return board;
    }

    //打印棋盤
    public void printGameBoard(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                printColor(board[i][j]);
                System.out.print(board[i][j] + " " + RESET_CONSOLE_COLOR);
            }
            System.out.println();
        }
    }

    //打印YN棋盤
    public void printYNGameBoard(List<Zhu> zhus) {
        String[][] YNBoard = new String[x][y];
        for (Zhu zhu : zhus) {
            if (zhu.isSanLian()) {
                YNBoard[zhu.getX()][zhu.getY()] = "Y";
            } else {
                YNBoard[zhu.getX()][zhu.getY()] = "N";
            }
//            System.out.println(YNBoard.length);
//            System.out.println(YNBoard[0].length);
        }
        for (int i = 0; i < YNBoard.length; i++) {
            for (int j = 0; j < YNBoard[i].length; j++) {
                if ("Y".equals(YNBoard[i][j])) {
                    System.out.print(RED + YNBoard[i][j] + " " + RESET_CONSOLE_COLOR);
                } else {
                    System.out.print(YNBoard[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

    //數字版上色輸出:答案
    public void printBoardWithNumAndColor(int[][] board, List<Zhu> zhus) {
        int count = 0;
        for (Zhu zhu : zhus) {
            if (count == y) {
                count = 0;
                System.out.println();
            }
            if (zhu.isSanLian()) {
                printColor(board[zhu.getX()][zhu.getY()]);
                System.out.print(board[zhu.getX()][zhu.getY()] + RESET_CONSOLE_COLOR);
            } else {
                System.out.print(board[zhu.getX()][zhu.getY()]);
            }
            System.out.print(" ");
            ++count;
        }
    }

    public void printColor(int num) {
        switch (num) {
            case 1:
                System.out.print(RED);
                break;
            case 2:
                System.out.print(GREEN);
                break;
            case 3:
                System.out.print(YELLOW);
                break;
            case 4:
                System.out.print(BLUE);
                break;
            case 5:
                System.out.print(PURPLE);
                break;
            case 6:
                System.out.print(CYAN);
                break;
        }
    }
}
