class MyInteger extends Element {
    public MyInteger(){
        this.myVal = 0;
    }
    
    public int Get(){
        return this.myVal;
    }
    
    public void Set(int val){
        this.myVal = val;
    }
    
    public void Print(){
        System.out.print(this.myVal);
    }
    
    private int myVal; 
}