package com.github.dustinbarnes;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;


public class SolverTests
{
    private List<Solver> algorithms;

    @Before
    public void createAlgorithms()
    {
        algorithms = new ArrayList<Solver>();
        algorithms.add(new EightQueensStyleSolver());
    }

    @Test
    public void testAlgorithms()
    {
        for ( int i = 0; i < PuzzleFactory.getNumberOfPuzzles(); i++ )
        {
            for ( Solver solver : algorithms )
            {
                // run each one 5 times to amortize runtimes
                long start = System.currentTimeMillis();

                for ( int j = 0; j < 5; j++ )
                {
                    SudokuPuzzle puzzle = PuzzleFactory.buildPuzzle(i);
                    solver.solve(puzzle);
                    assertTrue(puzzle.isComplete());
                }

                long end = System.currentTimeMillis();
                System.out.println("Algorithm " + solver.getClass().getSimpleName() +
                        " took " + (end - start) + " seconds.");
            }
        }
    }
}
