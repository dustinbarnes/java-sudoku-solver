package com.github.dustinbarnes;

import java.util.ArrayList;
import java.util.List;


public class SudokuPuzzle
{
    private List<SudokuCell> cells;

    public SudokuPuzzle()
    {
        this.cells = new ArrayList<SudokuCell>(81);
        for ( int i = 0; i < 81; i++ )
        {
            cells.add(new SudokuCell());
        }
    }

    public List<SudokuCell> getCells()
    {
        return cells;
    }

    public SudokuCell getCell(int row, int column)
    {
        return cells.get( ((row - 1) * 9) + column - 1);
    }

    public List<SudokuCell> getRow(int row)
    {
        List<SudokuCell> result = new ArrayList<SudokuCell>(9);
        for ( int i = 1; i <= 9; i++ )
        {
            result.add(getCell(row, i));
        }
        return result;
    }

    public List<SudokuCell> getColumn(int column)
    {
        List<SudokuCell> result = new ArrayList<SudokuCell>(9);
        for ( int i = 1; i <= 9; i++ )
        {
            result.add(getCell(i, column));
        }
        return result;
    }

    public List<SudokuCell> getBlock(int block)
    {
        int rowStart;
        int rowEnd;
        int colStart;
        int colEnd;
        switch ( block )
        {
            case 1:
                rowStart = 1;
                rowEnd = 3;
                colStart = 1;
                colEnd = 3;
            break;

            case 2:
                rowStart = 1;
                rowEnd = 3;
                colStart = 4;
                colEnd = 6;
            break;

            case 3:
                rowStart = 1;
                rowEnd = 3;
                colStart = 7;
                colEnd = 9;
            break;

            case 4:
                rowStart = 4;
                rowEnd = 6;
                colStart = 1;
                colEnd = 3;
            break;

            case 5:
                rowStart = 4;
                rowEnd = 6;
                colStart = 4;
                colEnd = 6;
            break;

            case 6:
                rowStart = 4;
                rowEnd = 6;
                colStart = 7;
                colEnd = 9;
            break;

            case 7:
                rowStart = 7;
                rowEnd = 9;
                colStart = 1;
                colEnd = 3;
            break;

            case 8:
                rowStart = 7;
                rowEnd = 9;
                colStart = 4;
                colEnd = 6;
            break;

            default:
                rowStart = 7;
                rowEnd = 9;
                colStart = 7;
                colEnd = 9;
            break;
        }

        List<SudokuCell> result = new ArrayList<SudokuCell>(9);

        for ( int row = rowStart; row <= rowEnd; row++ )
        {
            for ( int col = colStart; col <= colEnd; col++ )
            {
                result.add(getCell(row, col));
            }
        }

        return result;
    }

    public boolean isComplete()
    {
        if ( isValid() )
        {
            for ( SudokuCell cell : getCells() )
            {
                if ( cell.getValue() == 0 )
                {
                    return false;
                }
            }

            return true;
        }

        return false;
    }

    public boolean isValid()
    {
        for ( int i = 1; i <= 9; i++ )
        {
            List<SudokuCell> row = getRow(i);
            List<SudokuCell> col = getColumn(i);
            List<SudokuCell> block = getBlock(i);

            if ( hasCollisions(row) || hasCollisions(col) || hasCollisions(block) )
            {
                return false;
            }
        }

        return true;
    }

    private boolean hasCollisions(List<SudokuCell> cells)
    {
        boolean hits[] = new boolean[9];
        for ( int i = 1; i <= 9; i++ )
        {
            hits[i-1] = false;
        }

        for ( SudokuCell cell : cells )
        {
            if ( cell.getValue() != 0 )
            {
                if ( hits[cell.getValue()-1] )
                {
                    return true;
                }
                else
                {
                    hits[cell.getValue()-1] = true;
                }
            }
        }

        return false;
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();

        builder.append("+---+---+---+\n");

        for ( int rowNum = 1; rowNum <= 9; rowNum++ )
        {
            builder.append("|");

            for ( int colNum = 1; colNum <= 9; colNum++ )
            {
                SudokuCell cell = getCell(rowNum, colNum);
                if ( cell.getValue() != 0 )
                {
                    builder.append(cell.getValue());
                }
                else
                {
                    builder.append(" ");
                }

                if ( colNum % 3 == 0 )
                {
                    builder.append("|");
                }
            }

            builder.append("\n");

            if ( rowNum % 3 == 0 )
            {
                builder.append("+---+---+---+\n");
            }
        }

        return builder.toString();
    }
}
