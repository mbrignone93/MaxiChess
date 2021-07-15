/*
 *  Desarrollo por Maximiliano Brignone © 2021
 *  https://github.com/mbrignone93
 *
 * */

package com.chess.engine.player.ai;

import com.chess.engine.board.Board;
import com.chess.engine.board.Move;

public interface MoveStrategy {

    long getNumBoardsEvaluated();

    Move execute(Board board);

}
