
public class MyImage extends Image{

	static protected boolean[][] imageLabel;
	static protected int componentSize;
	public MyImage(int rows, int cols) {
		super(rows,cols);
	}

	public MyImage(boolean[][] data) {
		super(data);
	}

	@Override
	public Position find() {
		return findHelper(true);
	}


	private Position findHelper(boolean clr) {
		for(int r=0; r<rows;r++)
			for(int c=0; c<cols; c++)
				if(pixels[r][c] == clr)
					return new Position(r, c);
		return null;
	}

	@Override
	public int sizeOfConnectedComponent(Position p) {
		imageLabel = new boolean[this.rows][this.cols];
		componentSize = 0;
		// make an empty imagelabel		
		for(int r=0; r< this.rows;r++)
			for(int c=0; c < this.cols; c++)
				imageLabel[r][c]= false;
		// start from the point p and spread recursively
		checkdNeighbour(p, 0, 0);
		return componentSize;
	}

	@Override
	public boolean isConnectedWithoutHoles() {
		//		if(this.rows < 3 || this.cols < 3)
		//			return true;
		int totalSize= this.rows * this.cols;

		//
		// Size of black component
		//
		Position pb = findHelper(true);
		int s1 = sizeOfConnectedComponent(pb);
		//
		//  Size of white component
		//
		Position pw = findHelper(false);
		int s2	=	sizeOfConnectedComponent(pw);
		//
		//   found a single point
		//   check around it
		//
		if(s1 == 0 || s2 == 0)
			return true;

		if(s1+s2 == totalSize)
			return true;
		else
			return false;
	}

	@Override
	public Position findPinHole(Position p) {
		imageLabel = new boolean[this.rows][this.cols];
		for(int r=0; r< this.rows;r++)
			for(int c=0; c < this.cols; c++)
				imageLabel[r][c]= false;
		return checkPinHole(p, 0, 0);

	}
	private void checkdNeighbour(Position p, int dr, int dc)
	{
		if(p == null)
			return;
		boolean clr = this.getPixel(p);
		int r = p.getX()+dr;
		int c = p.getY()+dc;
		Position newPosition = new Position(r,c);
		//
		// outside the rectangle
		//
		if(r >= this.rows || r < 0)
			return;
		if(c >= this.cols || c < 0)
			return;
		//
		// is not the same color
		//
		if( this.getPixel(r, c) != clr)
			return;
		//
		// The pixel already is in the list
		//
		if(imageLabel[r][c])
			return;
		else
		{
			imageLabel[r][c] = true;
			componentSize++;
		}
		// check 4 points
		checkdNeighbour(newPosition, 1, 0);
		checkdNeighbour(newPosition, 0, 1);
		checkdNeighbour(newPosition, -1, 0);
		checkdNeighbour(newPosition, 0, -1);
		return;
	}


	private Position checkPinHole(Position p, int dr, int dc)
	{
		boolean clr = this.getPixel(p);
		int r = p.getX()+dr;
		int c = p.getY()+dc;
		Position newPosition = new Position(r,c);
		//
		// outside the rectangle
		//
		if(r >= this.rows || r < 0)
			return null;
		if(c >= this.cols || c < 0)
			return null;
		//
		// is not the same color
		//
		if( this.getPixel(r, c) != clr)
		{
			// check perimeter 
			if(r==0)
				return null;
			if(r==this.rows-1)
				return null;
			if(c==0)
				return null;
			if(c==this.cols-1)
				return null;
			//
			// Check 8 points
			//
			if(this.getPixel(r-1,c-1) != clr) return null;
			if(this.getPixel(r-1,c) != clr) return null;
			if(this.getPixel(r-1,c+1) != clr) return null;

			if(this.getPixel(r,c-1) != clr) return null;
			if(this.getPixel(r,c+1) != clr) return null;

			if(this.getPixel(r+1,c-1) != clr) return null;
			if(this.getPixel(r+1,c) != clr) return null;
			if(this.getPixel(r+1,c+1) != clr) return null;
			return new Position(r, c);

		}
		//
		// The pixel already is in the list
		//
		if(imageLabel[r][c])
			return null;
		else
		{
			imageLabel[r][c] = true;
			componentSize++;
		}
		Position temp;
		//temp = checkPinHole(newPosition, 0, 0);
		//if (temp != null) return temp;
		temp = checkPinHole(newPosition, 1, 0);
		if (temp != null) return temp;
		temp = checkPinHole(newPosition, 0, 1);
		if (temp != null) return temp;
		temp = checkPinHole(newPosition, -1, 0);
		if (temp != null) return temp;
		temp = checkPinHole(newPosition, 0, -1);
		if (temp != null) return temp;
		return null;
	}


}
