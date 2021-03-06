package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

public class Pawn extends ChessPiece {

	private ChessMatch chessMatch;

	public Pawn(Board board, Color color, ChessMatch chassMatch) {
		super(board, color);
		this.chessMatch = chassMatch;
	}

	@Override
	public String toString() {
		return "P";
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];

		Position p = new Position(0, 0);

		if (getColor() == Color.WHITE) {
			p.setValues(position.getRaw() - 1, position.getColumn());
			if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
				mat[p.getRaw()][p.getColumn()] = true;
			}
			p.setValues(position.getRaw() - 2, position.getColumn());
			Position p2 = new Position(position.getRaw() - 1, position.getColumn());
			if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p) && getBoard().positionExists(p2)
					&& !getBoard().thereIsAPiece(p2) && getMoveCount() == 0) {
				mat[p.getRaw()][p.getColumn()] = true;
			}
			p.setValues(position.getRaw() - 1, position.getColumn() - 1);
			if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
				mat[p.getRaw()][p.getColumn()] = true;
			}
			p.setValues(position.getRaw() - 1, position.getColumn() + 1);
			if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
				mat[p.getRaw()][p.getColumn()] = true;
			}

			// #specialmove en passant white
			if (position.getRaw() == 3) {
				Position left = new Position(position.getRaw(), position.getColumn() - 1);
				if (getBoard().positionExists(left) && isThereOpponentPiece(left)
						&& getBoard().piece(left) == chessMatch.getEnPassantVunerable()) {
					mat[left.getRaw() - 1][left.getColumn()] = true;
				}
				Position right = new Position(position.getRaw(), position.getColumn() + 1);
				if (getBoard().positionExists(right) && isThereOpponentPiece(right)
						&& getBoard().piece(right) == chessMatch.getEnPassantVunerable()) {
					mat[right.getRaw() - 1][right.getColumn()] = true;
				}
			}

		} else {
			p.setValues(position.getRaw() + 1, position.getColumn());
			if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
				mat[p.getRaw()][p.getColumn()] = true;
			}
			p.setValues(position.getRaw() + 2, position.getColumn());
			Position p2 = new Position(position.getRaw() + 1, position.getColumn());
			if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p) && getBoard().positionExists(p2)
					&& !getBoard().thereIsAPiece(p2) && getMoveCount() == 0) {
				mat[p.getRaw()][p.getColumn()] = true;
			}
			p.setValues(position.getRaw() + 1, position.getColumn() - 1);
			if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
				mat[p.getRaw()][p.getColumn()] = true;
			}
			p.setValues(position.getRaw() + 1, position.getColumn() + 1);
			if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
				mat[p.getRaw()][p.getColumn()] = true;
			}
			// #specialmove en passant black
			if (position.getRaw() == 4) {
				Position left = new Position(position.getRaw(), position.getColumn() - 1);
				if (getBoard().positionExists(left) && isThereOpponentPiece(left)
						&& getBoard().piece(left) == chessMatch.getEnPassantVunerable()) {
					mat[left.getRaw() + 1][left.getColumn()] = true;
				}
				Position right = new Position(position.getRaw(), position.getColumn() + 1);
				if (getBoard().positionExists(right) && isThereOpponentPiece(right)
						&& getBoard().piece(right) == chessMatch.getEnPassantVunerable()) {
					mat[right.getRaw() + 1][right.getColumn()] = true;
				}
			}
		}

		return mat;
	}
}
