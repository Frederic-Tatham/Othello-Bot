/**
 * Solution code for Comp24011 Reversi lab
 *
 * @author USERNAME
 */

public class BoardTreeSolution extends BoardTreeVertex {
    /**
     * BoardTreeVertex implementation BoardTreeSolution(BoardState,BoardTreeVertex,Move)
     *
     * @param   searchLimit The requested depth for minimax search
     */
    public BoardTreeSolution(BoardState state, BoardTreeVertex parent, Move move) {
        super(state,parent,move);
    }

    /**
     * Need to implement vertexEval(BoardTreeVertex)
     *
     * @param   childVertex  The vertex of current child
     *
     * @return  The MCTS value of current child
     */
    public double vertexEval(BoardTreeVertex childVertex) {
        int wv = childVertex.wins;
        int nv = childVertex.games;
        int nu = childVertex.parent.games;
        double eval = ((double) wv / nv) - explorationConstant * Math.sqrt(Math.log(nu) / nv);
        return eval;
    }
}

/* vim:set et ts=4 sw=4: */
