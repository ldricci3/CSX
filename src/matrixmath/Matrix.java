package matrixmath;

public class Matrix extends java.lang.Object{
	int rows;
	int columns;
	
	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getColumns() {
		return columns;
	}

	public void setColumns(int columns) {
		this.columns = columns;
	}

	public Matrix(int r, int c) {
		mymatrix = new double[r][c];
		this.setColumns(c);
		this.setRows(r);
	}
	
	double[][] mymatrix;
	
	public void setEntry(int c, int r, double value) {
		mymatrix[c][r] = value;
	}
	
	public void print() {
		for (int i = 0; i < this.getRows(); i++) {
			for (int j = 0; j < this.getColumns(); j++) {
				System.out.print(mymatrix[i][j]);
				System.out.print("	");
			}
			System.out.println();
		}
	}
	
	public Matrix equals (Matrix copy) {
		for (int i = 0; i < copy.getRows(); i++) {
			for (int j = 0; j < copy.getColumns(); j++) {
				this.mymatrix[i][j] = copy.mymatrix[i][j];
			}
		}
		
		return copy;
	}
	public Matrix augment() {
		Matrix augment = new Matrix(this.getRows(), 2*this.getColumns());
		for (int i = 0; i < this.getRows(); i++) {
			for (int j = 0; j < this.getRows(); j++) {
				augment.mymatrix[i][j] = this.mymatrix[i][j];
			}
		}
		for (int i = 0; i < this.getRows(); i++) {
			for (int j = this.getColumns(); j < 2*this.getColumns(); j++) {
				if (i == j - this.getColumns()) {
					augment.mymatrix[i][j] = 1;
				} else {
					augment.mymatrix[i][j] = 0;
				}
			}
		}
		
		return augment;
	}
	
	
	public Matrix plus(Matrix matrix1) {
		Matrix summatrix = new Matrix(this.getRows(), this.getColumns());
		if (this.getRows() == matrix1.getRows() && this.getColumns() == matrix1.getColumns()) {
			for (int i = 0; i < this.getRows(); i++) {
				for (int j = 0; j < this.getColumns(); j++) {
					summatrix.mymatrix[i][j] = this.mymatrix[i][j] + matrix1.mymatrix[i][j];
				}
			}
			return summatrix;
		} else {
			return null;
		}
		
		
	}
	
	public Matrix scalarTimesRow(double scalar, int rownumber) {
		Matrix scalarrowmatrix = new Matrix(this.getRows(), this.getColumns());
		for (int i = 0; i < this.getRows(); i++) {
			for (int j = 0; j < this.getColumns(); j++) {
				scalarrowmatrix.mymatrix[i][j] = this.mymatrix[i][j];
			}
		}
		for (int i = 0; i < this.getColumns(); i++) {
			scalarrowmatrix.mymatrix[rownumber][i] = this.mymatrix[rownumber][i]*scalar;
		}
		return scalarrowmatrix;
		
	}
	public Matrix times(Matrix matrix1) {
		Matrix productmatrix = new Matrix(this.getRows(), matrix1.getColumns());
		double sum = 0;
		if (this.getColumns() == matrix1.getRows()) {
			for (int i = 0; i < this.getRows(); i++) {
				for (int j = 0; j < matrix1.getColumns(); j++) {
					for (int k = 0; k < matrix1.getRows(); k++) {
						sum = sum + this.mymatrix[i][k]*matrix1.mymatrix[k][j];
					}
					productmatrix.mymatrix[i][j] = sum;
					sum = 0;
				}
				
			}
			return productmatrix;
		} else {
			return null;
		}
	}
	public Matrix switchRows(int firstrow, int secondrow) {
		Matrix switchedrowmatrix = new Matrix(this.getRows(), this.getColumns());
		for (int i = 0; i < this.getRows(); i++) {
			for (int j = 0; j < this.getColumns(); j++) {
				switchedrowmatrix.mymatrix[i][j] = this.mymatrix[i][j];
			}
		}
		for (int i = 0; i < this.getColumns(); i++) {
			switchedrowmatrix.mymatrix[firstrow][i] = this.mymatrix[secondrow][i];
			switchedrowmatrix.mymatrix[secondrow][i] = this.mymatrix[firstrow][i];
		}
		
		return switchedrowmatrix;
	}
	public Matrix linearCombRows(double scalar, int firstrow, int secondrow) {
		Matrix linearCombRows = new Matrix(this.getRows(), this.getColumns());
		for (int i = 0; i < this.getColumns(); i++) {
			linearCombRows.mymatrix[secondrow][i] = this.scalarTimesRow(scalar, firstrow).mymatrix[firstrow][i] + this.mymatrix[secondrow][i];
		}
		return this.plus(linearCombRows);
	}
	public Matrix rowreduce() {
		Matrix reduce = new Matrix(this.getRows(), this.getColumns());
		
		//copy matrix to invert
		for (int i = 0; i < this.getRows(); i++) {
			for (int j = 0; j < this.getColumns(); j++) {
				reduce.mymatrix[i][j] = this.mymatrix[i][j];
			}
		}
		
		//invert the matrix
		for (int i = 0; i < reduce.getRows(); i++) {
			boolean switched = true;
			//switch rows if necessary
			if (reduce.mymatrix[i][i] == 0) {
				switched = false;
				for (int j = 0; j < reduce.getRows() - i; j++) {
					if (reduce.mymatrix[j][j] != 0 && switched == false) {
						reduce.voidswitchRows(i, j);
						switched = true;
						
					}
				}
			}
			//multiply row by multiplicative inverse
			reduce.voidscalarTimesRow(1/reduce.mymatrix[i][i], i);
			//Comb through lower rows
			for (int j = i + 1; j < reduce.getRows(); j++) {
				if (reduce.mymatrix[j][i] != 0) {
					reduce.voidlinearCombRows((0-reduce.mymatrix[j][i]), i, j);
				}
			}
			
		}
		
		for (int i = 0; i < reduce.getRows() - 1; i++) {
			for (int j = 0; j < i+1; j++) {
				reduce.voidlinearCombRows(-reduce.mymatrix[j][i+1], i+1, j);
			}
		}
		
		
		return reduce;
	}
	public Matrix invert() {
		if (this.getRows() == this.getColumns()) {
			Matrix rref = new Matrix(this.getRows(), 2*this.getColumns());
			
			rref.equals(this.augment());
			
			Matrix invert = new Matrix(this.getRows(), this.getColumns());
			
			for (int i = 0; i < this.getRows(); i++) {
				for (int j = 0; j < this.getColumns(); j++) {
					invert.mymatrix[i][j] = rref.rowreduce().mymatrix[i][this.getRows() + j];
				}
			}
			
			return invert;
		} else {
			return null;
		}
		
	}
	public void voidscalarTimesRow(double scalar, int rownumber) {
		for (int i = 0; i < this.getColumns(); i++) {
			this.mymatrix[rownumber][i] = this.mymatrix[rownumber][i]*scalar;
		}
		
	}
	public void voidswitchRows(int firstrow, int secondrow) {
		Matrix switchedrowmatrix = new Matrix(this.getRows(), this.getColumns());
		for (int i = 0; i < this.getRows(); i++) {
			for (int j = 0; j < this.getColumns(); j++) {
				switchedrowmatrix.mymatrix[i][j] = this.mymatrix[i][j];
			}
		}
		for (int i = 0; i < this.getColumns(); i++) {
			this.mymatrix[firstrow][i] = switchedrowmatrix.mymatrix[secondrow][i];
			this.mymatrix[secondrow][i] = switchedrowmatrix.mymatrix[firstrow][i];
		}
	}
	public void voidlinearCombRows(double scalar, int firstrow, int secondrow) {
		for (int i = 0; i < this.getColumns(); i++) {
			this.mymatrix[secondrow][i] = this.scalarTimesRow(scalar, firstrow).mymatrix[firstrow][i]  + this.mymatrix[secondrow][i];
		}
	}

}
