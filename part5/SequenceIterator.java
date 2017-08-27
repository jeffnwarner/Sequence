class SequenceIterator extends Element{
	public SequenceIterator(Sequence s){
		this.beginning = s;
	}
	
	public void Print(){
		this.beginning.Print();
	}
	
	public boolean equal(SequenceIterator other){
		if(this.beginning == other.beginning){
			return true;
		}
		else{
			return false;
		}
	}
	
	public SequenceIterator advance(){
		this.beginning = beginning.next;
		return this;
	}
	
	public Element get(){
		return this.beginning.myElm;
	}
	Sequence beginning;
}
