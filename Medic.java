package model.characters;

import exceptions.InvalidTargetException;
import exceptions.NoAvailableResourcesException;
import exceptions.NotEnoughActionsException;



public class Medic extends Hero {
	//Heal amount  attribute - quiz idea
	

	public Medic(String name,int maxHp, int attackDmg, int maxActions) {
		super( name, maxHp,  attackDmg,  maxActions) ;
		
		
	}
	@Override
	public void useSpecial() throws NoAvailableResourcesException,InvalidTargetException{
		if(this.getTarget() instanceof Zombie){
			   throw new InvalidTargetException("can not heal zombie");
		   }
		int x=(int) this.getLocation().getX();
		   int y=(int) this.getLocation().getY();
		   int locX=(int) super.getTarget().getLocation().getX();
		   int locY=(int) super.getTarget().getLocation().getY();
		
		   if(locX == x){
			   if (locY>=y && locY-y<=1 ){
				 this.getTarget().setCurrentHp(this.getTarget().getMaxHp());
	    		 this.getSupplyInventory().get(0).use(this);
	    		 super.useSpecial();
	    			
	    		 return;

			   }
			   if (y>=locY && y-locY<=1 ){
				 this.getTarget().setCurrentHp(this.getTarget().getMaxHp());
	    		 this.getSupplyInventory().get(0).use(this);  
	    		 super.useSpecial();
	    			
	    		return;
			   }
		   }
		   if(locY==y){
			   if(locX>=x && locX-x<=1){
				 this.getTarget().setCurrentHp(this.getTarget().getMaxHp());
	    		 this.getSupplyInventory().get(0).use(this);
	    		 super.useSpecial();
	    			
	    		return;
				   
			   }
			   if(x>=locX && x-locX <=1){
				 this.getTarget().setCurrentHp(this.getTarget().getMaxHp());
	    		 this.getSupplyInventory().get(0).use(this);
	    		 super.useSpecial();
	    			
	    		return;
			   }
		   }
			if(((locX - x ==1) || (x-locX ==1)) && ((locY - y ==1) || (y-locY ==1))) {
				this.getTarget().setCurrentHp(this.getTarget().getMaxHp());
	    		this.getSupplyInventory().get(0).use(this);
	    		super.useSpecial();
	    		
	    		return;

				
			}
			throw new InvalidTargetException("hero can not reach zombie"); 

	}
	@Override
	public void attack() throws InvalidTargetException,NotEnoughActionsException{
		super.attack();
		this.setActionsAvailable(this.getActionsAvailable() -1);
	}


}
