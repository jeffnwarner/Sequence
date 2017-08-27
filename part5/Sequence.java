class Sequence extends Element {
    public Sequence(){
        myLength = 0;
        myPos = 0;
    }
    
    public Sequence(int pos, int length, Sequence previous) {
        this.myPos = pos;
        this.myLength = length;
        this.prev = previous;
        hasPrev = true;
    }
    
    public Sequence(Sequence previous) {
        this.prev = previous;
        hasPrev = true;
    }
    
    public Element first(){
        return this.myElm;
    }
    
    public Sequence rest(){
        //this.next.myLength--;
        updateLength(myLength);
        return this.next;
    }
    
    public Element index(int pos) {
        
        if (myPos == 0) {//error message
            if (pos < 0 || pos > myLength) {
                System.err.println("pos not between 0 and length " + " Length: " + myLength + " pos: " + pos + " myPos: " + myPos);
                System.exit(0);
            }
        }
        
        if (pos == myPos) {
            Element e = myElm;
            return e;
        }
        
        else {
            return next.index(pos);
        }
    }
   /* public Element indexSequence(int pos) {
        if (pos == myPos) {
            if (myElm instanceof Sequence) {
                Sequence slm = new Sequence();
                slm = (Sequence) myElm;
                slm.indexSequence(pos);
                for (int i = 0; i <= slm.myLength; i++){
                    add(slm.index(i), myLength + i);
                }
                return this;
            }
            else {
                return this;
            }
        }
        else {
            return next.indexSequence(pos);
        }
        
    }*/
    
    public Sequence flatten() {
        Sequence s = new Sequence(); 
        Sequence sE = new Sequence();
        Sequence sR = new Sequence();
        int k = 0;
        for (int i = 0; i < myLength; i++) {
            Element e = index(i);
            if (e instanceof Sequence) {
                //System.out.println("SEQUENCE");
                sE = (Sequence) e;
                //sE.Print();
                //System.out.println();
                //System.out.println("Stop");
                sR = sE.flatten();
                //System.out.println("SEQUENCE");
                for (int j = 0; j < sR.myLength; j++) {
                    Element e2 = sR.index(j); 
                    s.add(e2, k);
                    k++;
                }
            }
            else {
                //System.out.println("OTHER");
                //e.Print();
                //System.out.println();
                s.add(e, k);
                k++;
                
            }
        }
     /*    System.out.println("INDEX PRINT 0");
        index(myLength - 2).Print();
        System.out.println();
        System.out.println("INDEX PRINT 1");
        index(myLength - 1).Print();
        System.out.println();*/
        return s;
    }
    public Sequence copy() {
        Sequence s = new Sequence();
        //System.out.println("HELLO OLD");
        //Print();
        //System.out.println();
        for (int i = 0; i < myLength; i++) {
            if (index(i) instanceof MyChar) {
                MyChar eC = new MyChar();
                MyChar indexC = new MyChar();
                indexC = (MyChar) index(i);
                eC.Set(indexC.Get());
                s.add(eC, i);
            }
            else if (index(i) instanceof MyInteger) {
                MyInteger eP = new MyInteger();
                MyInteger indexP = new MyInteger();
                indexP = (MyInteger) index(i);
                eP.Set(indexP.Get());
                s.add(eP, i);
            }
            
            else if (index(i) instanceof Sequence) {
                Sequence eS = new Sequence();
                eS = (Sequence) index(i);
                s.add(eS, i);
            }
        }
        //System.out.println("HELLO NEW");
        //s.Print();
        //System.out.println();
        return s;
    }
    
    public int length(){
        if (myPos == 0) {
            return myLength - 0;
        }
        else {
            return myLength - 0;
        }
    }
    
    public void add(Element elm, int pos) {
        myLength++;
        //System.out.println("MYPOS " + myPos + " MYLENGTH " + myLength);
        if (myPos == 0) {//error message
            if (pos < 0 || pos > myLength + 1) {
                System.err.println("pos not between 0 and length " + " Length: " + myLength + " pos: " + pos + " myPos: " + myPos);
                System.exit(0);
            }
            
            
        }
        //updateLength(myLength);
        if(myPos == pos) {
            if(hasElm) {
                moveRight();
            }

            setElm(elm);
            setPos(pos);
            hasElm = true;
        }
        
        else {
            if (pos == myPos + 1 && !hasNext) {//If there is no next sequence then create one.
                next = new Sequence(myPos + 1, myLength - 1, this);
                hasNext = true;
                //myLength++;
            }
            
            /*else if (myPos + 1 == pos && next.hasNext) {
            moveRight(elm, pos);
            }*/
            
            next.add(elm, pos);
        }
    }
    
   /* public void add(Sequence seq, int pos, int placeholder) {
        Sequence iS = new Sequence();
        for (int i = 0; i < seq.myLength; i++) {
            if (seq.index(i) instanceof Sequence) {
                iS = (Sequence) seq.index(i); 
                add(iS, myPos + i, 0);
            }
            else {
                add(seq.index(i), myPos + i);
            }
        }
    }
*/    
    public void delete(int pos) {
        myLength--;
        if(pos == myPos) {
            moveLeft();
        }
        
        else {
            if (hasNext) {
                next.delete(pos);
            }
            
        }
    }
    
    public void moveRight() {
        Sequence n1 = new Sequence();
        n1.setElm(myElm);
        if (hasNext) {
            n1.setNext(next);
            n1.hasNext = true;
        }
        setNext(n1);
        hasNext = true;
        n1.hasElm = true;
        updatePos(myPos);
        updateLength(myLength);
    }
    
    public void moveLeft() {
        if (this.hasNext) {
            /*Sequence n1 = this.next;
            if (n1.hasNext) {
                Sequence n2 = n1.next;
                this.setNext(n2);
                updatePos(myPos);
                updateLength(myLength);
            }*/
            
            Sequence n3 = next;
            if (n3.hasNext){
                this.setNext(n3.next);
            }
            else {
                this.hasNext = false;
            }
            this.setElm(n3.myElm);
            updateLength(myLength);
            updatePos(myPos);
            
        }
        
        else {
            hasNext = false;
            hasElm = false;
        }
    }
    
    public void Print() {
        System.out.print("[ ");
        PrintRest();
        System.out.print("]");
    }
    
    public void PrintRest() {
        //System.out.println(myPos);
        if (hasNext && hasElm){
            myElm.Print(); 
            System.out.print(" ");
            next.PrintRest();
        }
        else{
            if(hasElm) {
                myElm.Print(); 
                System.out.print(" ");
            }
            
        }
    }
    
    public void setElm(Element elm) {
        myElm = elm;
    }
    
    public void setLength(int length) {
        myLength = length;
    }
    
    public void setPos(int pos) {
        myPos = pos;
    }
    
    public void setNext(Sequence s) {
        next = s;
    }
    
    public void updatePos(int pos) {
        myPos = pos;
        if (hasNext == true){
            next.updatePos(pos + 1);
        }
    }
    
    public void updateLength(int length) {
        myLength = length;
        //System.out.println("HELLLLLLLOOOOOO");
        //System.out.println("MYPOS " + myPos + " MYLENGTH " + myLength);
        if (hasNext){
            //System.out.println("PPPOOOOOOOOOOOOOOOOOOPP");
            next.updateLength(length - 1);
        }
    }
    
    public SequenceIterator begin(){
        return new SequenceIterator(this);
    }
    
    public SequenceIterator end(){
        return new SequenceIterator(findEnd(myLength)); 
    }
    
    public Sequence findEnd(int length) {
        if (myPos + 1 != length) {
            return next.findEnd(length);
        }
        else {
            return next;
        }
    }
    
    Element myElm;
    int myLength;
    Sequence next; 
    Sequence prev;
    int myPos;
    boolean hasNext = false;
    boolean hasElm = false;
    boolean startedHere = false;
    boolean hasPrev = false;
}