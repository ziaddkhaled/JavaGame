package model.collectibles;


import java.awt.Point;
import java.util.Random;

import engine.Game;
import model.characters.Hero;
import model.world.CharacterCell;

public class Vaccine implements Collectible {

	public Vaccine() {
		
		
	}
	@Override
	public void pickUp(Hero h) {
        h.addVaccine(this);
    }
	@Override
	public void use(Hero h) {
        h.removeVaccine(this);
        //if(h.getTarget() != null && Game.availableHeroes.get(0) != null){
        //Point newLoc= h.getTarget().getLocation();
        int newLocX= (int) h.getTarget().getLocation().getX();
        int newLocY= (int) h.getTarget().getLocation().getY();
        Game.zombies.remove(h.getTarget());
        Random r = new Random();
        int x= r.nextInt(Game.availableHeroes.size()-1);
        Hero temp=Game.availableHeroes.remove(x);
        temp.setLocation(new Point(newLocX,newLocY));
        Game.map[newLocX][newLocY] = new CharacterCell(temp);
        Game.heroes.add(temp);
        //temp.setLocation(p);
        
        //Game.availableHeroes.get(0).setLocation(newLoc);
        //Game.availableHeroes.get(0).setActionsAvailable(Game.availableHeroes.get(0).getMaxActions());
        //Game.heroes.add(Game.availableHeroes.get(0));
        //Game.map[newLoc.x][newLoc.y] = new CharacterCell(Game.availableHeroes.get(0));
        //Game.availableHeroes.remove(0);
        //Game.checkWin();
        //}
        
      }
    
}
