package model.characters;

import java.awt.Point;
import java.util.Random;

import model.world.CharacterCell;
import engine.Game;
import exceptions.InvalidTargetException;
import exceptions.NotEnoughActionsException;




public abstract class Character {
	private String name;
	private Point location;
	private int maxHp;
	private int currentHp;
	private int attackDmg;
	private Character target;
	
	public Character() {
	}
	

	public Character(String name, int maxHp, int attackDmg) {
		this.name=name;
		this.maxHp = maxHp;
		this.currentHp = maxHp;
		this.attackDmg = attackDmg;
		
	}
		
	public Character getTarget() {
		return target;
	}

	public void setTarget(Character target) {
		this.target = target;
	}
	
	public String getName() {
		return name;
	}

	public Point getLocation() {
		return location;
	}

	public void setLocation(Point location) {
		this.location = location;
	}

	public int getMaxHp() {
		return maxHp;
	}

	public int getCurrentHp() {
		return currentHp;
	}

	public void setCurrentHp(int currentHp) {
		if(currentHp < 0) 
			this.currentHp = 0;
		else if(currentHp > maxHp) 
			this.currentHp = maxHp;
		else 
			this.currentHp = currentHp;
	}

	public int getAttackDmg() {
		return attackDmg;
	}

	public void attack() throws InvalidTargetException,NotEnoughActionsException {
		
			if (this instanceof Hero && target instanceof Hero) {
			    throw new InvalidTargetException("Hero Can't attack a friend");
			}
			if (this instanceof Zombie && target instanceof Zombie) {
				throw new InvalidTargetException("Zombie Can't attack a Zomble");
			}
			if (this instanceof Hero && target == null) {
			   throw new InvalidTargetException ("No target to attack");
			}
			if (this instanceof Zombie) {
				int xZombie = (int) this.getLocation().getX();
				int yZombie = (int) this.getLocation().getY();
				if(xZombie-1 >=0 && yZombie-1 >=0 && Game.map[xZombie-1][yZombie-1] instanceof CharacterCell) {
					if(((CharacterCell) Game.map[xZombie-1][yZombie-1]).getCharacter() instanceof Hero) {
						target = ((CharacterCell) Game.map[xZombie-1][yZombie-1]).getCharacter();
						if(this.getAttackDmg() >= target.getCurrentHp()){
							target.defend(this);
							target.onCharacterDeath();
							return;
						}
						target.setCurrentHp((target.currentHp) - (this.getAttackDmg()));
						defend(target);
						return;
					}
				}
				if(xZombie-1 >=0 && Game.map[xZombie-1][yZombie] instanceof CharacterCell) {
					if(((CharacterCell) Game.map[xZombie-1][yZombie]).getCharacter() instanceof Hero) {
						target = ((CharacterCell) Game.map[xZombie-1][yZombie]).getCharacter();
						if(this.getAttackDmg() >= target.getCurrentHp()){
							target.defend(this);
							target.onCharacterDeath();
							return;
						}
						target.setCurrentHp((target.currentHp) - (this.getAttackDmg()));
						defend(target);
						
						return;
					}
				}
				if(xZombie-1 >=0 && yZombie+1 <=14 && Game.map[xZombie-1][yZombie+1] instanceof CharacterCell) {
					if(((CharacterCell) Game.map[xZombie-1][yZombie+1]).getCharacter() instanceof Hero) {
						target = ((CharacterCell) Game.map[xZombie-1][yZombie+1]).getCharacter();
						if(this.getAttackDmg() >= target.getCurrentHp()){
							target.defend(this);
							target.onCharacterDeath();
							return;
						}
						target.setCurrentHp((target.currentHp) - (this.getAttackDmg()));
						defend(target);
						
						return;
					}
				}
				if( yZombie+1 <=14 && Game.map[xZombie][yZombie+1] instanceof CharacterCell) {
					if(((CharacterCell) Game.map[xZombie][yZombie+1]).getCharacter() instanceof Hero) {
						target = ((CharacterCell) Game.map[xZombie][yZombie+1]).getCharacter();
						if(this.getAttackDmg() >= target.getCurrentHp()){
							target.defend(this);
							target.onCharacterDeath();
							return;
						}
						target.setCurrentHp((target.currentHp) - (this.getAttackDmg()));
						defend(target);
						
						return;
					}
				}
				if(xZombie+1 <=14 && yZombie+1 <=14 && Game.map[xZombie+1][yZombie+1] instanceof CharacterCell) {
					if(((CharacterCell) Game.map[xZombie+1][yZombie+1]).getCharacter() instanceof Hero) {
					 	target = ((CharacterCell) Game.map[xZombie+1][yZombie+1]).getCharacter();
					 	if(this.getAttackDmg() >= target.getCurrentHp()){
					 		target.defend(this);
							target.onCharacterDeath();
							return;
						}
						target.setCurrentHp((target.currentHp) - (this.getAttackDmg()));
						defend(target);
						
						return;
					}
				}
				if(xZombie+1 <=14  && Game.map[xZombie+1][yZombie] instanceof CharacterCell) {
					if(((CharacterCell) Game.map[xZombie+1][yZombie]).getCharacter() instanceof Hero) {
						target = ((CharacterCell) Game.map[xZombie+1][yZombie]).getCharacter();
						if(this.getAttackDmg() >= target.getCurrentHp()){
							target.defend(this);
							target.onCharacterDeath();
							return;
						}
						target.setCurrentHp((target.currentHp) - (this.getAttackDmg()));
						defend(target);
						
						return;
					}
				}
				if(xZombie+1 <=14 && yZombie-1 >=0 && Game.map[xZombie+1][yZombie-1] instanceof CharacterCell) {
					if(((CharacterCell) Game.map[xZombie+1][yZombie-1]).getCharacter() instanceof Hero) {
						target = ((CharacterCell) Game.map[xZombie+1][yZombie-1]).getCharacter();
						if(this.getAttackDmg() >= target.getCurrentHp()){
							target.defend(this);
							target.onCharacterDeath();
							return;
						}
						target.setCurrentHp((target.currentHp) - (this.getAttackDmg()));
						defend(target);
						
						return;
					}
				}
				if( yZombie-1 >=0 && Game.map[xZombie][yZombie-1] instanceof CharacterCell) {
					if(((CharacterCell) Game.map[xZombie][yZombie-1]).getCharacter() instanceof Hero) {
						target = ((CharacterCell) Game.map[xZombie][yZombie-1]).getCharacter();
						if(this.getAttackDmg() >= target.getCurrentHp()){
							target.defend(this);
							target.onCharacterDeath();
						}
						target.setCurrentHp((target.currentHp) - (this.getAttackDmg()));
						defend(target);
						
						return;
					}
				}
				return;
			}
			if (this instanceof Hero && target instanceof Zombie) {
				int x =(int) this.location.getX();
				int y =(int) this.location.getY();
				int xTarget =(int) target.getLocation().getX();
				int yTarget =(int) target.getLocation().getY();
				
				if(xTarget == x) {
					if(yTarget > y && (yTarget - y) == 1) {
						
						if((((Hero)this).getActionsAvailable()==0)){
							throw new NotEnoughActionsException("No actions available");
						}
						if(this.getAttackDmg() >= target.getCurrentHp()){
							target.onCharacterDeath();
						}
						else{
							target.setCurrentHp((target.currentHp) - (this.getAttackDmg()));
						}
						this.defend(target);
						return;
					}
					if(yTarget < y && (y - yTarget) ==1) {
						
						if((((Hero)this).getActionsAvailable()==0)){
							throw new NotEnoughActionsException("No actions available");
						}
						if(this.getAttackDmg() >= target.getCurrentHp()){
							target.onCharacterDeath();
						}
						
						else{
							target.setCurrentHp((target.currentHp) - (this.getAttackDmg()));
						}
						this.defend(target);
						return;
					}
				}
				if(yTarget == y) {
					if(xTarget > x && (xTarget - x) ==1) {
						
						if((((Hero)this).getActionsAvailable()==0)){
							throw new NotEnoughActionsException("No actions available");
						}
						if(this.getAttackDmg() >= target.getCurrentHp()){
							target.onCharacterDeath();
						}
						else{
							target.setCurrentHp((target.currentHp) - (this.getAttackDmg()));
						}
						this.defend(target);
						return;
					}
					if(xTarget < x && (x - xTarget) == 1) {
						if((((Hero)this).getActionsAvailable()==0)){
							throw new NotEnoughActionsException("No actions available");
						}
						if(this.getAttackDmg() >= target.getCurrentHp()){
							target.onCharacterDeath();
						}
						else{
							target.setCurrentHp((target.currentHp) - (this.getAttackDmg()));
						}
						this.defend(target);
						return;
					}
				}
				if(((xTarget - x ==1) || (x-xTarget ==1)) && ((yTarget - y ==1) || (y-yTarget ==1))) {
					if((((Hero)this).getActionsAvailable()==0)){
						throw new NotEnoughActionsException("No actions available");
					}
					if(this.getAttackDmg() >= target.getCurrentHp()){
						target.onCharacterDeath();
					}
					else{
						target.setCurrentHp((target.currentHp) - (this.getAttackDmg()));
					}
					this.defend(target);
					return;
				}
				throw new InvalidTargetException("hero can not reach zombie"); 
	
			}
			
			}
	
	 public void defend(Character c){
		 
		 if(c.attackDmg/2 >= this.getCurrentHp()){
			 this.onCharacterDeath();
			 return;
		 }
		 this.setCurrentHp(this.getCurrentHp()-(c.attackDmg/2));
	 }
	 public void onCharacterDeath() {
		 if(this instanceof Hero){
			 Game.heroes.remove(this);
			 Game.checkGameOver();
			 this.setCurrentHp(this.getMaxHp());
			 Game.availableHeroes.add(((Hero)this));
			 int xHero =(int) this.getLocation().getX(); 
			 int yHero =(int) this.getLocation().getY();
			 ((CharacterCell) Game.map[xHero][yHero]).setCharacter(null);
			 //((CharacterCell) Game.map[xHero][yHero]).setVisible(true);
		 }
		 if(this instanceof Zombie){
			 Game.zombies.remove(this);
			 int xZombie =(int) this.getLocation().getX(); 
			 int yZombie =(int) this.getLocation().getY();
			 ((CharacterCell) Game.map[xZombie][yZombie]).setCharacter(null);
			 int i =0;
			 while ( i<1) {
					
				Random r = new Random();
				int x = r.nextInt(15);
				int y = r.nextInt(15);
				if(Game.map[x][y] instanceof CharacterCell && (((CharacterCell)Game.map[x][y]).getCharacter()==null)) {
					Zombie z= new Zombie();
					Game.map[x][y]=new CharacterCell(z);
					Game.zombies.add(z);
					i++;
					}
				}
		 }
		 
		 
		 
	 }
}

