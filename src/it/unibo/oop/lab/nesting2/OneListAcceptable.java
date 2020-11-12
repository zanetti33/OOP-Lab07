package it.unibo.oop.lab.nesting2;

import java.util.Iterator;
import java.util.List;

public class OneListAcceptable<T> implements Acceptable<T> {
	
	private final List<T> listToAccept;

	public OneListAcceptable(final List<T> listToAccept) {
		this.listToAccept = listToAccept;
	}

	public Acceptor<T> acceptor() {
		return new Acceptor<T> () {
			
			private final Iterator<T> iterator = OneListAcceptable.this.listToAccept.iterator();
			
			public void accept(T newElement) throws Acceptor.ElementNotAcceptedException {
				try {
					if(newElement.equals(this.iterator.next())) {
	                    return;
					}
                }catch(Exception e) {
                    System.out.println("this element doesn't belong in the list");
                    throw new Acceptor.ElementNotAcceptedException(newElement);
                }
			}

			public void end() throws Acceptor.EndNotAcceptedException {
				try{
					if (!this.iterator.hasNext()) {
                        return;
					}
                }catch(Exception e) {
                    System.out.println("the list is not finished");
                }
				throw new Acceptor.EndNotAcceptedException();
			}
			
		};
	}
	
	
}
