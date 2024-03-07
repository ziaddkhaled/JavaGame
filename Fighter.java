package model.characters;

import exceptions.InvalidTargetException;
import exceptions.NoAvailableResourcesException;
import exceptions.NotEnoughActionsException;


public class Fighter extends Hero{

	
	public Fighter(String name,int maxHp, int attackDmg, int maxActions) {
		super( name, maxHp,  attackDmg,  maxActions) ;
		
	}
	@Override
	public void useSpecial() throws NoAvailableResourcesException,InvalidTargetException{
		super.useSpecial();
		this.getSupplyInventory().get(0).use(this);
	}

	@Override
	public void attack() throws InvalidTargetException,NotEnoughActionsException{
		if(this.getActionsAvailable() ==0  && this.isSpecialAction() == false){
			throw new NotEnoughActionsException("No actions available");
		}
		super.attack();
		if(this.isSpecialAction() == false){
			this.setActionsAvailable(this.getActionsAvailable()-1);
		}
	}

	
	
	
	

}
