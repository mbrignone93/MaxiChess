/*
 *  Desarrollo por Maximiliano Brignone © 2021
 *  https://github.com/mbrignone93
 *
 * */

package com.chess.engine.board;

import static com.chess.engine.board.Move.*;

public enum MoveUtils {

    INSTANCE;

    public static int exchangeScore(final Move move) {
        if(move == MoveFactory.getNullMove()) {
            return 1;
        }
        return move.isAttack() ?
                5 * exchangeScore(move.getBoard().getTransitionMove()) :
                exchangeScore(move.getBoard().getTransitionMove());

    }

}
