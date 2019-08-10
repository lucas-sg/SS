package engine;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;

import models.CIMInput;
import models.Particle;
import models.Board;

public class Engine {
    private Board board;

    public Output applyCIMAlgorithm(final CIMInput input){
        Instant start, end;
        Duration timeElapsed;
        ArrayList<Particle> neighbours;

        setEnvironment(input);

        start       = Instant.now();
        neighbours  = cellIndexMethod(board);
        end         = Instant.now();
        timeElapsed = Duration.between(start, end);
        
        return new Output(neighbours, timeElapsed);
    }

    private ArrayList<Particle> cellIndexMethod(final Board board) {
        return new ArrayList<>(); // TODO: Implement the actual algorithm
    }

    private void setEnvironment(final CIMInput input) {
        this.board = new Board(input.getL(), input.getM());

        // TODO: generate Particles with input.getN() and input.getRC()
    }
}