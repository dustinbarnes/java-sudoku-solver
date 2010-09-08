package com.github.dustinbarnes;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;


public final class PuzzleFactory
{
    private PuzzleFactory()
    {
    }

    public static int getNumberOfPuzzles()
    {
        return 2;
    }

    public static SudokuPuzzle buildPuzzle(int puzzleNumber)
    {
        SudokuPuzzle puzzle = new SudokuPuzzle();

        switch ( puzzleNumber )
        {
            case 1:
                // From: http://www.websudoku.com/?level=4&set_id=7280021221
                parse(puzzle, Arrays.asList(
                        "072000030",
                        "000500702",
                        "500000006",
                        "020031900",
                        "090000010",
                        "005980070",
                        "600000008",
                        "309002000",
                        "050000690"));
            break;

            default:
                // From: http://www.websudoku.com/?level=4&set_id=7074607966
                parse(puzzle, Arrays.asList(
                        "008000301",
                        "000008000",
                        "170002040",
                        "001080523",
                        "000000000",
                        "732050100",
                        "010500082",
                        "000600000",
                        "403000700"));
            break;
        }

        return puzzle;
    }

    private static void parse(SudokuPuzzle puzzle, List<String> definitions)
    {
        assertTrue(definitions.size() == 9);

        for ( int row = 1; row <= 9; row++ )
        {
            String definition = definitions.get(row-1);
            assertTrue(definition.length() == 9);

            for ( int col = 1; col <= definition.length(); col++ )
            {
                puzzle.getCell(row, col).setValue(Integer.parseInt("" + definition.charAt(col-1)));
            }
        }
    }
}
