class MyChar extends Element {
    public MyChar(){
        myVal = '0';
    }
    
    public char Get(){
        return myVal;
    }
    
    public void Set(char val){
        this.myVal = val;
    }
    
    public void Print(){
        System.out.print("\'" + this.myVal + "\'");
    }
    
    private char myVal;
}