package model.collectibles;

import model.characters.Hero;

public class Supply implements Collectible  {

	

	
	public Supply() {
		
	}
	@Override
	public void pickUp(Hero h) {
        h.addSupply(this);
    }
	@Override
	public void use(Hero h) {
        h.removeSupply(this);
    }


	
		
		

}
