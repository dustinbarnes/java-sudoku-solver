package com.github.dustinbarnes;

public class EightQueensStyleSolver implements Solver
{
    public void solve(SudokuPuzzle puzzle)
    {
        solve(puzzle, 1, 1);
    }

    private boolean solve(SudokuPuzzle puzzle, int row, int col)
    {
        if ( row == 9 && col == 10 )
        {
            // Base case: done with puzzle
            return true;
        }
        else
        {
            if ( col == 10 )
            {
                // Next row!
                return solve(puzzle, row+1, 1);
            }
            else
            {
                if ( puzzle.getCell(row, col).getValue() == 0 )
                {
                    // We have to figure this one out.
                    for ( int i = 1; i <= 9; i++ )
                    {
                        puzzle.getCell(row, col).setValue(i);
                        if ( puzzle.isValid() )
                        {
                            if ( solve(puzzle, row, col+1) )
                            {
                                return true;
                            }
                        }
                    }

                    // if we reach here, previous answers were wrong
                    puzzle.getCell(row, col).setValue(0);
                    return false;
                }
                else
                {
                    // We know this one.
                    return solve(puzzle, row, col+1);
                }
            }
        }
    }
}
