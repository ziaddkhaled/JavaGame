package model.characters;

import java.awt.Point;
import java.util.ArrayList;

import engine.Game;
import exceptions.InvalidTargetException;
import exceptions.MovementException;
import exceptions.NoAvailableResourcesException;
import exceptions.NotEnoughActionsException;
import model.collectibles.Supply;
import model.collectibles.Vaccine;
import model.world.CharacterCell;
import model.world.CollectibleCell;
import model.world.TrapCell;


public abstract class Hero extends Character {
	

		private int actionsAvailable;
		private int maxActions;
		private ArrayList<Vaccine> vaccineInventory;
		private ArrayList<Supply> supplyInventory;
		private boolean specialAction;
	
		
		public Hero(String name,int maxHp, int attackDmg, int maxActions) {
			super(name,maxHp, attackDmg);
			this.maxActions = maxActions;
			this.actionsAvailable = maxActions;
			this.vaccineInventory = new ArrayList<Vaccine>();
			this.supplyInventory=new ArrayList<Supply>();
			this.specialAction=false;
		
		}
		@Override
		public void attack()throws InvalidTargetException,NotEnoughActionsException {
			super.attack();
		//	actionsAvailable--;
		}

		
	

		

		public boolean isSpecialAction() {
			return specialAction;
		}



		public void setSpecialAction(boolean specialAction) {
			this.specialAction = specialAction;
		}



		public int getActionsAvailable() {
			return actionsAvailable;
		}



		public void setActionsAvailable(int actionsAvailable) {
			this.actionsAvailable = actionsAvailable;
		}



		public int getMaxActions() {
			return maxActions;
		}



		public ArrayList<Vaccine> getVaccineInventory() {
			return vaccineInventory;
		}


		public ArrayList<Supply> getSupplyInventory() {
			return supplyInventory;
		}





		public void addVaccine(Vaccine vaccine) {
			
			vaccineInventory.add(vaccine);
			
			
		}
       public void removeVaccine(Vaccine vaccine) {
			
			vaccineInventory.remove(vaccine);
			
			
		}
       public void addSupply(Supply supply) {
			
    	   supplyInventory.add(supply);
			
			
		}
       public void removeSupply(Supply supply) {
			
    	   supplyInventory.remove(supply);
			
			
		}
       public void visibleHelper(int x,int y) {
    	   if(x+1<15 && y-1>-1) {
    		   Game.map[x+1][y-1].setVisible(true);
    	   }
    	   if(x+1<15) {
    		   Game.map[x+1][y].setVisible(true);
    	   }
    	   if(x+1<15 && y+1<15) {
    		   Game.map[x+1][y+1].setVisible(true);
    	   }
    	   if(y-1>-1){
    		   Game.map[x][y-1].setVisible(true);

    	   }
    	   if(y+1<15) {
    		   Game.map[x][y+1].setVisible(true);

    	   }
    	   if(x-1>-1 && y-1>-1) {
    		   Game.map[x-1][y-1].setVisible(true);
    	   }
    	   if(x-1>-1) {
    		   Game.map[x-1][y].setVisible(true);

    	   }
    	   if(x-1>-1 && y+1<15) {
    		   Game.map[x-1][y+1].setVisible(true);

    	   }
    	   Game.map[x][y].setVisible(true);

       }
       public void move(Direction d) throws MovementException,NotEnoughActionsException {
    	   if(this.actionsAvailable==0) {
    		   throw new NotEnoughActionsException("no actions available");
    	   }
    	   int locX=(int) this.getLocation().getX();
		   int locY=(int) this.getLocation().getY();
    	   
    	   
    	   if(this.getCurrentHp()<=0){
    		   onCharacterDeath();
    		   return;
    	   }
    			   
    	   if (Direction.UP ==d ) {
    		   
    		   Point heroLoc= new Point(locX+1, locY);
    		   if(locX+1>=15) {
    			 throw new MovementException("Hero out of bound");
    		   }
    		   if(Game.map[locX+1][locY] instanceof CharacterCell) {
    			   if(((CharacterCell) Game.map[locX+1][locY]).getCharacter() != null) {
    				   throw new MovementException("Hero can not move to this place");
    			   }
    			   if(((CharacterCell) Game.map[locX+1][locY]).getCharacter() == null){
    				   ((CharacterCell) Game.map[locX+1][locY]).setCharacter(((CharacterCell) Game.map[locX][locY]).getCharacter());
    				   ((CharacterCell) Game.map[locX][locY]).setCharacter(null);
    				   this.setLocation(heroLoc);
    				   this.actionsAvailable--;
    			   }
    		   }
    		   if(Game.map[locX+1][locY] instanceof CollectibleCell) {
    			   ((CollectibleCell) Game.map[locX+1][locY]).getCollectible().pickUp(this);
				   //Hero h= (Hero) ((CharacterCell) Game.map[locX][locY]).getCharacter();
				   CharacterCell heroCell= new CharacterCell(this);
				   Game.map[locX+1][locY]=heroCell;
				   ((CharacterCell) Game.map[locX][locY]).setCharacter(null);
				   super.setLocation(heroLoc);
				   this.actionsAvailable--;
    			   
    		   }
    		   if(Game.map[locX+1][locY] instanceof TrapCell) {
    			   TrapCell t=(TrapCell)Game.map[locX+1][locY];
    			   this.actionsAvailable--;
    			   if (t.getTrapDamage()>=this.getCurrentHp()){
    				   this.onCharacterDeath();
    				   Game.map[locX+1][locY]= new CharacterCell(null);
    				   Game.map[locX+1][locY].setVisible(true);
    				   this.visibleHelper(locX+1, locY);
    				   return;
    			   }
    			   this.setCurrentHp(this.getCurrentHp()-t.getTrapDamage());
    			   Game.map[locX+1][locY]=new CharacterCell(null);
    			   Game.map[locX+1][locY]= new CharacterCell(this);
    			   ((CharacterCell) Game.map[locX][locY]).setCharacter(null);
				   super.setLocation(heroLoc);
				   
    		   }
    		   this.visibleHelper(locX+1,locY);
    		   
    		   
    		   
    		   
    		   
    	   }
    	   if (Direction.DOWN ==d ) {
    		   Point heroLoc= new Point(locX-1 , locY);
    		   if(locX-1==-1) {
      			 throw new MovementException("Hero out of bound");
    		   }
      			if(Game.map[locX-1][locY] instanceof CharacterCell) {
     			   if(((CharacterCell) Game.map[locX-1][locY]).getCharacter() != null) {
     				   throw new MovementException("Hero can not move to this place");
     			   }
     			  if(((CharacterCell) Game.map[locX-1][locY]).getCharacter() == null){
   				   ((CharacterCell) Game.map[locX-1][locY]).setCharacter(((CharacterCell) Game.map[locX][locY]).getCharacter());
   				   ((CharacterCell) Game.map[locX][locY]).setCharacter(null);
   				   this.setLocation(heroLoc);
   				   this.actionsAvailable--;
   			      }
     		   }
     		   if(Game.map[locX-1][locY] instanceof CollectibleCell) {
     			   ((CollectibleCell) Game.map[locX-1][locY]).getCollectible().pickUp(this);
 				  // Hero h= (Hero) ((CharacterCell) Game.map[locX][locY]).getCharacter();
 				   CharacterCell heroCell= new CharacterCell(this);
 				   Game.map[locX-1][locY]=heroCell;
 				   ((CharacterCell) Game.map[locX][locY]).setCharacter(null);
 				   super.setLocation(heroLoc);
 				   this.actionsAvailable--;
     			   
     		   }
     		  if(Game.map[locX-1][locY] instanceof TrapCell) {
     			  
     			 TrapCell t=(TrapCell)Game.map[locX-1][locY];
     			 this.actionsAvailable--;
  			   if (t.getTrapDamage()>=this.getCurrentHp()){
  				 this.onCharacterDeath();
  				 Game.map[locX-1][locY]= new CharacterCell(null);
  				 Game.map[locX-1][locY].setVisible(true);
  				 this.visibleHelper(locX-1, locY);
  				 return;
  			   }
  			   this.setCurrentHp(this.getCurrentHp()-t.getTrapDamage());
  			   Game.map[locX-1][locY]=new CharacterCell(null);
  			   Game.map[locX-1][locY]= new CharacterCell(this);
  			   ((CharacterCell) Game.map[locX][locY]).setCharacter(null);
			   super.setLocation(heroLoc);
			   
   		   }
     		   
   		   this.visibleHelper(locX-1,locY);

      		   }
    		   
    	   
    	   if (Direction.RIGHT ==d  ) {
    		   Point heroLoc= new Point(locX , locY+1);
    		   if(locY+1==15) {
      			 throw new MovementException("Hero out of bound");
      		   }
    		   if(Game.map[locX][locY+1] instanceof CharacterCell) {
    			   if(((CharacterCell) Game.map[locX][locY+1]).getCharacter() != null) {
    				   throw new MovementException("Hero can not move to this place");
    			   }
    			   if(((CharacterCell) Game.map[locX][locY+1]).getCharacter() == null){
    				   ((CharacterCell) Game.map[locX][locY+1]).setCharacter(((CharacterCell) Game.map[locX][locY]).getCharacter());
    				   ((CharacterCell) Game.map[locX][locY]).setCharacter(null);
    				   this.setLocation(heroLoc);
    				   this.actionsAvailable--;
    			   }
    		   }
    		   if(Game.map[locX][locY+1] instanceof CollectibleCell) {
    			   ((CollectibleCell) Game.map[locX][locY+1]).getCollectible().pickUp(this);
				   //Hero h= (Hero) ((CharacterCell) Game.map[locX][locY]).getCharacter();
				   CharacterCell heroCell= new CharacterCell(this);
				   Game.map[locX][locY+1]=heroCell;
				   ((CharacterCell) Game.map[locX][locY]).setCharacter(null);
				   super.setLocation(heroLoc);
				   this.actionsAvailable--;
    			   
    		   }
    		   if(Game.map[locX][locY+1] instanceof TrapCell) {
      			  
    			   TrapCell t=(TrapCell)Game.map[locX][locY+1];
    			   this.actionsAvailable--;
    			   if (t.getTrapDamage()>=this.getCurrentHp()){
    				   this.onCharacterDeath();
    				   Game.map[locX][locY+1]= new CharacterCell(null);
    				   Game.map[locX][locY+1].setVisible(true);
    				   this.visibleHelper(locX, locY+1);
    				   return;
    			   }
    			   this.setCurrentHp(this.getCurrentHp()-t.getTrapDamage());
    			   Game.map[locX][locY+1]=new CharacterCell(null);
    			   Game.map[locX][locY+1]= new CharacterCell(this);
    			   ((CharacterCell) Game.map[locX][locY]).setCharacter(null);
				   super.setLocation(heroLoc);
				   
       		   }
    		   this.visibleHelper(locX,locY+1);

    		   
    	   }
    	   if (Direction.LEFT ==d) {
    		   Point heroLoc= new Point(locX , locY-1);
    		   if(locY-1==-1) {
      			 throw new MovementException("Hero out of bound");
      		   }
    		   if(Game.map[locX][locY-1] instanceof CharacterCell) {
    			   if(((CharacterCell) Game.map[locX][locY-1]).getCharacter() != null) {
    				   throw new MovementException("Hero can not move to this place");
    			   }
    			   if(((CharacterCell) Game.map[locX][locY-1]).getCharacter() == null){
    				   ((CharacterCell) Game.map[locX][locY-1]).setCharacter(((CharacterCell) Game.map[locX][locY]).getCharacter());
    				   ((CharacterCell) Game.map[locX][locY]).setCharacter(null);
    				   this.setLocation(heroLoc);
    				   this.actionsAvailable--;
    			   }
    		   }
    		   if(Game.map[locX][locY-1] instanceof CollectibleCell) {
    			   ((CollectibleCell) Game.map[locX][locY-1]).getCollectible().pickUp(this);
				  // Hero h= (Hero) ((CharacterCell) Game.map[locX][locY]).getCharacter();
				   CharacterCell heroCell= new CharacterCell(this);
				   Game.map[locX][locY-1]=heroCell;
				   ((CharacterCell) Game.map[locX][locY]).setCharacter(null);
				   super.setLocation(heroLoc);
				   this.actionsAvailable--;
    			   
    		   }
    		   if(Game.map[locX][locY-1] instanceof TrapCell) {
      			  
    			   TrapCell t=(TrapCell)Game.map[locX][locY-1];
    			   this.actionsAvailable--;
    			   if (t.getTrapDamage()>=this.getCurrentHp()){
    				   this.onCharacterDeath();
    				   Game.map[locX][locY-1]= new CharacterCell(null);
    				   Game.map[locX][locY-1].setVisible(true);
    				   this.visibleHelper(locX,locY-1);
    				   return;
    			   }
    			   this.setCurrentHp(this.getCurrentHp()-t.getTrapDamage());
    			   Game.map[locX][locY-1]=new CharacterCell(null);
    			   Game.map[locX][locY-1]= new CharacterCell(this);
    			   ((CharacterCell) Game.map[locX][locY]).setCharacter(null);
				   this.setLocation(heroLoc);
       			   
       		   }
    		   this.visibleHelper(locX,locY-1);

    	   }
    	   
       }
       public void useSpecial() throws NoAvailableResourcesException,InvalidTargetException {
    	   if(this.supplyInventory.size()==0){
    		   throw new NoAvailableResourcesException("No supplies available");
    	   }
    	   this.setSpecialAction(true);
    	  
       }
       public void cure() throws NoAvailableResourcesException, NotEnoughActionsException, InvalidTargetException{
    	   if(this.actionsAvailable == 0){
    		   throw new NotEnoughActionsException("No actions available");
    	   }
    	   if(this.vaccineInventory.size()==0){
    		   throw new  NoAvailableResourcesException("No vaccines available");
    	   }
    	   if(this.getTarget() == null){
    		   throw new InvalidTargetException("nothing to cure");
    	   }
    	   if(this.getTarget() instanceof Hero){
    		   throw new InvalidTargetException("Can not cure hero");
    	   }
    	   if(!(this.getTarget() instanceof Zombie)){
    		   throw new InvalidTargetException("Can not cure collectible");
    	   }
    	   int x=(int) this.getLocation().getX();
		   int y=(int) this.getLocation().getY();
		   int locX=(int) super.getTarget().getLocation().getX();
		   int locY=(int) super.getTarget().getLocation().getY();
		   if(locX == x){
			   if (locY>=y && locY-y<=1 ){
				   //Game.zombies.remove(this.getTarget());
				   //Hero h=Game.availableHeroes.get(0);
				   //Game.availableHeroes.remove(0);
				   //Game.heroes.add(h);
				   actionsAvailable--;
				   vaccineInventory.get(0).use(this);
				   return;
			   }
			   if(y>=locY && y-locY<=1){
				   //Game.zombies.remove(this.getTarget());
				   //Hero h=Game.availableHeroes.get(0);
				   //Game.availableHeroes.remove(0);
				   //Game.heroes.add(h);
				   actionsAvailable--;
				   vaccineInventory.get(0).use(this);
				   return;
			   }
       } 
		  if(locY == y){
			  if(locX>=x && locX-x<=1){
				  //Game.zombies.remove(this.getTarget());
				  //Hero h=Game.availableHeroes.get(0);
				  //Game.availableHeroes.remove(0);
				  //Game.heroes.add(h);
				  actionsAvailable--;
				  vaccineInventory.get(0).use(this);
				  return;
			  }
			  if(x>=locX && x-locX <=1){
				  //Game.zombies.remove(this.getTarget());
				  //Hero h=Game.availableHeroes.get(0);
				  //Game.availableHeroes.remove(0);
				  //Game.heroes.add(h);
				  actionsAvailable--;
				  vaccineInventory.get(0).use(this);
				  return;
			  }
		  }
		  if(((locX - x ==1) || (x-locX ==1)) && ((locY - y ==1) || (y-locY ==1))){
		      //Game.zombies.remove(this.getTarget());
			  //Hero h=Game.availableHeroes.get(0);
			  //Game.availableHeroes.remove(0);
			  //Game.heroes.add(h);
			  actionsAvailable--;
			  vaccineInventory.get(0).use(this);
			  return;
		  }
		  throw new InvalidTargetException("Hero can not reach zombie");
		  
    } 
}      
 


		

	
    
