/*
 * Powered by Maximiliano Brignone
 * © 2021 https://github.com/mbrignone93
 * 
 * */

package com.chess.engine.pieces;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.BoardUtils;
import com.chess.engine.board.Move;
import com.chess.engine.board.Tile;
import com.chess.engine.board.Move.AttackMove;
import com.chess.engine.board.Move.MajorMove;
import com.google.common.collect.ImmutableList;

// https://en.wikipedia.org/wiki/King_(chess)

public class King extends Piece{
	
	private final static int[] CANDIDATE_MOVE_COORDINATE = { -9, -8, -7, -1, 1, 7, 8, 9 };

	public King(final Alliance pieceAlliance, 
				final int piecePosition) 
	{
		super(pieceAlliance, piecePosition);
	}

	@Override
	public Collection<Move> calculateLegalMoves(final Board board)
	{
		final List<Move> legalMoves = new ArrayList<>();
		
		for (final int currentCandidateOffset : CANDIDATE_MOVE_COORDINATE) 
		{
			final int candidateDestinationCoordinate = this.piecePosition + currentCandidateOffset;
		
			if (isFirstColumnExclusion(this.piecePosition, currentCandidateOffset) || 
				isEighthColumnExclusion(this.piecePosition, currentCandidateOffset)) 
				continue;
			
			if (BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)) 
			{
				final Tile candidateDestinationTile = board.getTile(candidateDestinationCoordinate);
			
				if (!candidateDestinationTile.isTileOccupied()) 
					legalMoves.add(new MajorMove(board, this, candidateDestinationCoordinate));
				else
				{
					final Piece pieceAtDestination = candidateDestinationTile.getPiece();
					final Alliance pieceAlliance = pieceAtDestination.getPieceAlliance();
				
					if (this.pieceAlliance != pieceAlliance) 
						legalMoves.add(new AttackMove(board, this, candidateDestinationCoordinate, pieceAtDestination));
				}
			}
		}
		
		return ImmutableList.copyOf(legalMoves);
	}
	
	private static boolean isFirstColumnExclusion(final int currentPosition, final int candidateOffset) 
	{
		return BoardUtils.FIRST_COLUMN[currentPosition] && (candidateOffset == -9 || candidateOffset == -1 || 
				candidateOffset == 7);
	}
	
	private static boolean isEighthColumnExclusion(final int currentPosition, final int candidateOffset) 
	{
		return BoardUtils.EIGHTH_COLUMN[currentPosition] && (candidateOffset == -7 || candidateOffset == 1 || 
				candidateOffset == 9);
	}
}
