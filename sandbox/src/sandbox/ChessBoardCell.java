package sandbox;

import java.util.Objects;

public class ChessBoardCell {
	int x;
	int y;
	int distnace;

	public ChessBoardCell(int x, int y, int distnace) {
		this.x = x;
		this.y = y;
		this.distnace = distnace;
	}

	@Override
	public int hashCode() {
		return Objects.hash(distnace, x, y);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof ChessBoardCell)) {
			return false;
		}
		ChessBoardCell other = (ChessBoardCell) obj;
		return distnace == other.distnace && x == other.x && y == other.y;
	}

}
