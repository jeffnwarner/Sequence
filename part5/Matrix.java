class Matrix extends Sequence {
    public Matrix (int rowSize, int colSize) {
        row = rowSize;
        col = colSize;
        Sequence mat = new Sequence();
        mat.setElm(new MyInteger());
        for(int i = 0; i < row * col; i++){
            mat.add(new MyInteger(),i);
        }
        
    }
    
    public void Set(int row, int col, int value) {
      int loc = row * col + col;
      MyInteger setInt = new MyInteger();
      //System.out.println();
      //mat.Print();
      setInt = (MyInteger)mat.index(loc);
      setInt.Set(value);
    }
    public int Get(int rowsize, int colsize) {
        int loc = row * col + col;
        MyInteger getInt = new MyInteger();
        getInt = (MyInteger)mat.index(loc);
        return getInt.Get();
    }
    
    public Matrix Sum(Matrix m) {
        Matrix matSum = new Matrix(row,col);
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                matSum.Set(i, j, this.Get(i, j) + m.Get(i, j));
            }
        }
        return matSum;
    }
    
    public Matrix Product(Matrix m) {
        Matrix matMult = new Matrix(row, m.col);
        if (col != m.row) {
            System.err.println("Unsolvable");
            System.exit(0);
        }
        int sumofMatMult = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < m.col; j++) {
                for (int k = 0; k < m.row; k++) {
                    sumofMatMult += this.Get(i, k) * m.Get(k, j);
                }
                
                matMult.Set(i, j, sumofMatMult);
            }
        }
        return matMult;
    }
    
    public void Print() {
        mat.Print();
    }
    
    
    Sequence mat;
    Sequence rInput;
    Sequence cInput;
    int row, col;
}