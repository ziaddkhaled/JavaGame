package engine;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import exceptions.InvalidTargetException;
import exceptions.NotEnoughActionsException;
import model.characters.Explorer;
import model.characters.Fighter;
import model.characters.Hero;
import model.characters.Medic;
import model.characters.Zombie;
import model.collectibles.Supply;
import model.collectibles.Vaccine;
import model.world.Cell;
import model.world.CharacterCell;
import model.world.CollectibleCell;
import model.world.TrapCell;

public class Game {
	
	public static  Cell [][] map = new Cell[15][15]  ;
	public static ArrayList <Hero> availableHeroes = new ArrayList<Hero>();
	public static ArrayList <Hero> heroes =  new ArrayList<Hero>();
	public static ArrayList <Zombie> zombies =  new ArrayList<Zombie>();
	
	
	
	
	
		
	public static void loadHeroes(String filePath)  throws IOException {
		
		
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		String line = br.readLine();
		while (line != null) {
			String[] content = line.split(",");
			Hero hero=null;
			switch (content[1]) {
			case "FIGH":
				hero = new Fighter(content[0], Integer.parseInt(content[2]), Integer.parseInt(content[4]), Integer.parseInt(content[3]));
				break;
			case "MED":  
				hero = new Medic(content[0], Integer.parseInt(content[2]), Integer.parseInt(content[4]), Integer.parseInt(content[3])) ;
				break;
			case "EXP":  
				hero = new Explorer(content[0], Integer.parseInt(content[2]), Integer.parseInt(content[4]), Integer.parseInt(content[3]));
				break;
			}
			availableHeroes.add(hero);
			line = br.readLine();
			
			
		}
		br.close();

		
		
	}
	public static void startGame(Hero h) {
		for(int i=0; i< 15;i++) {
			for(int j=0; j< 15; j++) {
				map [i][j] = new CharacterCell(null);
			}
		}
		
		map[0][0]= new CharacterCell(h);
		availableHeroes.remove(h);
		heroes.add(h);
		h.setLocation(new Point(0,0));
		h.setActionsAvailable(h.getMaxActions());
	    int i =0;
	  while ( i<10) {
		
		Random r = new Random();
		int x = r.nextInt(14);
		int y = r.nextInt(14);
		if(map[x][y] instanceof CharacterCell && (((CharacterCell)map[x][y]).getCharacter()==null)) {
			Zombie z= new Zombie();
			z.setLocation(new Point(x,y));
			map[x][y]=new CharacterCell(z);
			zombies.add(z);
			i++;
		}
	}
	int j =0;
	while(j < 5) {
			
		Random r1 = new Random();
		int x1 = r1.nextInt(14);
		int y1 = r1.nextInt(14);
		if(map[x1][y1] instanceof CharacterCell && (((CharacterCell)map[x1][y1]).getCharacter()==null)) {
			map[x1][y1] = new CollectibleCell(new Vaccine());
			j++;
		}
	}
	int k=0;
	while(k<5) {
		
		Random r2 = new Random();
		int x2= r2.nextInt(14);
		int y2= r2.nextInt(14);
		if(map[x2][y2] instanceof CharacterCell && (((CharacterCell)map[x2][y2]).getCharacter()==null)) {
			map[x2][y2] = new CollectibleCell(new Supply());
			k++;
					
					
				
			}
		}
	int l=0;
    while(l<5) {
		
		Random r3 = new Random();
		int x3= r3.nextInt(14);
		int y3= r3.nextInt(14);
		if(map[x3][y3] instanceof CharacterCell && (((CharacterCell)map[x3][y3]).getCharacter()==null)) {
			map[x3][y3] = new TrapCell();
			l++;
					
					
				
			}
		}
    map[0][0].setVisible(true);
	map[0][1].setVisible(true);
    map[1][0].setVisible(true);
	map[1][1].setVisible(true);
			
		}
	 public static boolean checkWin(){
		 for(int i=0;i<15;i++){
			 for(int j =0 ;j<15;j++){
				if( map[i][j] instanceof CollectibleCell){
					if(((CollectibleCell)map[i][j]).getCollectible() instanceof Vaccine){
						return false;
					}
				}
			 }
			 
		 }
		 for(int k=0;k<heroes.size();k++){
		    Hero h = heroes.get(k);
		    if(h.getVaccineInventory().size()!=0){
			   return false;
		   }
		
		 
		 }
		 if(heroes.size()<5){
			 return false;
		 }
		 
		 return true;
	 }
			
	 public static boolean checkGameOver(){
		 
		 for(int i=0;i<15;i++){
			 for(int j =0 ;j<15;j++){
				if( map[i][j] instanceof CollectibleCell){
					if(((CollectibleCell)map[i][j]).getCollectible() instanceof Vaccine){
						return false;
					}
				}
			 }
			 
		 }
		 for(int k=0;k<heroes.size();k++){
			    Hero h = heroes.get(k);
			    if(h.getVaccineInventory().size()!=0){
				   return false;
			   }
			
			 
			 }
		 if(heroes.size()<5){
			 return true;
		 }
		return checkWin();
	 }
	 public static void visibilityHelper(Hero h){
		 int x=(int) h.getLocation().getX();
		 int y=(int) h.getLocation().getY();
		 if(x+1<=14  && y+1<=14){
			 map[x+1][y+1].setVisible(true);
		 }
		 if(x+1<=14  &&  y-1>=0){
			 map[x+1][y-1].setVisible(true);
		 }
		 if(x+1<=14){
			 map[x+1][y].setVisible(true);
		 }
		 if(y+1<=14){
			 map[x][y+1].setVisible(true);
		 }
		 if(x-1>=0  && y+1<=14){
			 map[x-1][y+1].setVisible(true);
		 }
		 if(x-1>=0){
			map[x-1][y].setVisible(true); 
		 }
		 if(y-1>=0){
			 map[x][y-1].setVisible(true);
		 }
		 if(x-1>=0  && y-1>=0){
			 map[x-1][y-1].setVisible(true);
		 }
		 map[x][y].setVisible(true);
	 }
	 public static void endTurn() throws NotEnoughActionsException, InvalidTargetException{
		 for(int i=0; i< zombies.size(); i++){
			 if(zombies.get(i)!= null){
			    zombies.get(i).attack();
			    zombies.get(i).setTarget(null);
			 }
		 }
		 for (int i=0; i<15; i++){
			 for(int j=0; j<15 ; j++){
				 map[i][j].setVisible(false);
			 }
		 }
		 for(int i=0; i< heroes.size(); i++){
			 if(heroes.get(i)!= null){
			    heroes.get(i).setActionsAvailable(heroes.get(i).getMaxActions());
			    heroes.get(i).setTarget(null);
			    heroes.get(i).setSpecialAction(false);
			    visibilityHelper(heroes.get(i));
			 }
			 
		 }
		 int i =0;
		  while ( i<1) {
			
			Random r = new Random();
			int x = r.nextInt(14);
			int y = r.nextInt(14);
			if(map[x][y] instanceof CharacterCell && (((CharacterCell)map[x][y]).getCharacter()==null)) {
				Zombie z= new Zombie();
				z.setLocation(new Point(x,y));
				map[x][y]=new CharacterCell(z);
				zombies.add(z);
				i++;
			}
		}
		 
	 }
	}


