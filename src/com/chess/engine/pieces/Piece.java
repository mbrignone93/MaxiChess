/*
 * Powered by Maximiliano Brignone
 * © 2021 https://github.com/mbrignone93
 * 
 * */

package com.chess.engine.pieces;

import java.util.Collection;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.Move;

public abstract class Piece 
{
	protected final int piecePosition;
	protected final Alliance pieceAlliance;
	protected final boolean isFirstMove;
	
	public Piece(final Alliance pieceAlliance,
		  		 final int piecePosition)
	{
		this.pieceAlliance = pieceAlliance;
		this.piecePosition = piecePosition;
		// TODO more work here!!
		this.isFirstMove = false;
	}
	
	public int getPiecePosition() 
	{
		return this.piecePosition;
	}
	
	public Alliance getPieceAlliance() 
	{
		return this.pieceAlliance;
	}

	public boolean isFirstMove()
	{
		return this.isFirstMove;
	}
	
	public abstract Collection<Move> calculateLegalMoves(final Board board);
}
