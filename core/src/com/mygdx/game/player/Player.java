package com.mygdx.game.player;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g3d.particles.influencers.ColorInfluencer;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.game.board.Board;
import com.mygdx.game.ship.Dron;
import com.mygdx.game.ship.Ship;
import com.mygdx.game.ship.TwoPlaceShip;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by zenek on 15.03.2017.
 */

public class Player  {
    private Board myBoard;
    private int points;
    private boolean play;
    private boolean lost;

    private String name;
    private ArrayList<Ship> ships;


    public Player(){
    this.myBoard=new Board();
        this.points=0;
        this.play=false;
        this.lost=false;
        this.ships=new ArrayList<Ship>();
    }
    public void deleteShips(){
        ships.clear();
        System.out.print("N SHIPS: "+ships.size()+ " ");

    }

    public void generateRandomBoard(){
        Random random=new Random();

        Ship[] createShips={
                new Dron(random.nextInt(10),random.nextInt(10),random.nextInt(2)),
                new TwoPlaceShip(random.nextInt(10),random.nextInt(10),random.nextInt(2))

        };
        //dodajemy statki w tym przypadku 2x po jednej sztuce
        for(int i=0;i<2;i++){
            ships.add(createShips[i]);

            // jezeli nie ma miejsca lub zly statek to usun i dodaj na nowym miejscu
            while (!validShip(i)||!fillBoardShip(i)){
                Ship ship=ships.get(i);
                ships.remove(i);

                ship.setPositionX(random.nextInt(10));
                ship.setPositionY(random.nextInt(10));
                ship.setDirection(random.nextInt(2));

                ships.add(i,ship);
            }
        }

    }
    public boolean fillBoardShip(int i){
        Ship ship=ships.get(i);

        if(ship.getDirection()==0){
            if(ship.getPositionX()+ship.getSize()-1>9)
                return false;

            for (int j=0;j<ship.getSize();j++){
                if(this.getMyBoard().getBoard()[ship.getPositionX()+j][ship.getPositionY()]!=0)
                    return false;
            }
            for (int j=0;j<ship.getSize();j++){
                this.getMyBoard().getBoard()[ship.getPositionX()+j][ship.getPositionY()]=i;

            }

        }else {
            if(ship.getPositionY()+ship.getSize()-1>9){
                return false;
            }
            for (int j = 0; j < ship.getSize(); j++)
                if (this.getMyBoard().getBoard()[ship.getPositionX()][ship
                        .getPositionY() + j] != 0)
                    return false;

            for (int j = 0; j < ship.getSize(); j++)
                this.getMyBoard().getBoard()[ship.getPositionX()][ship.getPositionY()
                        + j] = i;
        }
        return true;
    }
    public void play(int pX,int pY,Player player)
    {
        if(pX<0||pY>9||pX>9||pY<0)
            return;
        for (int i=0;i<ships.size();i++){
            if(player.getMyBoard().getBoard()[pX][pY]==-8)
                player.getMyBoard().getBoard()[pX][pY]=-9;
            else if(player.getMyBoard().getBoard()[pX][pY]==-9){
                return;
            }
            else if(player.getMyBoard().getBoard()[pX][pY]==i){
                player.getMyBoard().getBoard()[pX][pY]=(float)(i+0.5);
                Ship ship=player.getShips().get(i);
                for (int j = 0; j < ship.getSize(); j++) {
                    if (ship.getDirection() == 0){
                        if (ship.getPositionX()+ship.getSize()-1 > 9){
                            return;
                        }
                        if (player.getMyBoard().getBoard()[ship.getPositionX() + j][ship.getPositionY()] == i){
                            return;
                        }
                    }else {
                        if (ship.getPositionY()+ship.getSize()-1 > 9)
                            return;
                        if (player.getMyBoard().getBoard()[ship.getPositionX()][ship.getPositionY() + j] == i)
                            return;
                    }
                }
                player.getShips().get(i).setSink(true);
                player.surrondSinkedShip(i);


            } else if (player.getMyBoard().getBoard()[pX][pY] == (i + 0.5))
        return;
        }
    }
    public void surrondSinkedShip(int i) {
        Ship ship=this.getShips().get(i);

        if(ship.getDirection()==0){
            if(ship.getPositionX()-1>=0){
                this.getMyBoard().getBoard()[ship.getPositionX()-1][ship.getPositionY()]=-9;
                if(ship.getPositionX()+ship.getSize()<=9)
                    this.getMyBoard().getBoard()[ship.getPositionX()+ship.getSize()][ship.getPositionY()]=-9;
                int j=0;
                int size=ship.getSize();
                int c=0;
                int term=0;
                //jezeli jest miejsce od dolu i gory
                if(ship.getPositionY()-1>=0 && ship.getPositionY()+1<=9){
                    c=-1;
                    term=2;
                }else if(ship.getPositionY()-1>=0){
                    c=-1;
                    term=0;
                }else {
                    c=1;
                    term=2;
                }
                if(ship.getPositionX()-1>=0){
                    j=-1;
                }
                 int jBak=j;

                for(;c<term;c+=2){
                    j=jBak;
                    for(;j<size;j++){
                        this.getMyBoard().getBoard()[ship.getPositionX()+j][ship.getPositionY()+c]=-9;

                    }
                }
            }else {

                if (ship.getPositionY() - 1 >= 0)
                    this.getMyBoard().getBoard()[ship.getPositionX()][ship
                            .getPositionY()-1] = -9;
                if (ship.getPositionY() + ship.getSize() <= 9)
                    this.getMyBoard().getBoard()[ship.getPositionX()][ship.getPositionY() + ship.getSize()] = -9;

                int j = 0;
                int size = ship.getSize();
                int c = 0;
                int term = 0;

                if (ship.getPositionX() - 1 >= 0 && ship.getPositionX() + 1 <= 9) {
                    c = -1;
                    term = 2;
                } else if (ship.getPositionX() - 1 >= 0) {
                    c = -1;
                    term = 0;
                } else {
                    c = 1;
                    term = 2;
                }

                if (ship.getPositionY() - 1 >= 0) {
                    j = -1;
                    //	size++;
                }

                if (ship.getPositionY() + ship.getSize() <= 9)
                    size++;

                int jBak = j;

                for (; c < term; c += 2) {
                    j = jBak;
                    for (; j < size; j++)
                        this.getMyBoard().getBoard()[ship.getPositionX() + c][ship
                                .getPositionY() + j] = -9;
                }

            }
        }
    }



        public Board getMyBoard() {
        return myBoard;
    }
    public boolean validShipPosition(int posX, int posY, int direction, int size) {

        if (posX < 0 || posX > 9 || posY < 0 || posY > 9)
            return false;

        if (direction == 0) {
            if (posX + size > 9)
                return false;
        } else {
            if (posY + size > 9)
                return false;
        }

        return true;
    }
    public boolean validShip(int i) {
    Ship ship=ships.get(i);
    if(ship.getDirection()==0){
        //horizont

        if(ship.getPositionX()+ship.getSize()-1>9)
            return false;
        if(ship.getPositionX()+ship.getSize()<=9)
            if(this.getMyBoard().getBoard()[ship.getPositionY()+ship.getSize()][ship.getPositionY()]!=8)
                return false;
        if(ship.getPositionX()-1>=0)
            if(this.getMyBoard().getBoard()[ship.getPositionX()-1][ship.getPositionY()]!=-8)
                return false;

        int j=0;
        int size =ship.getSize();
        int c=0;
        int term=0;

        if(ship.getPositionY()-1>=0 && ship.getPositionY()+1>=9){
            c=-1;
            term=2;
        }else if (ship.getPositionY()-1>=0){
            c=-1;
            term=0;
        }else {
            c=1;
            term=2;
        }
        if (ship.getPositionX()-1>=0){
            j=-1;
        }
        if(ship.getPositionX()+ship.getSize()<=9)
            size++;

        int back=j;
        for(;c<term;c+=2){
            j=back;
            for (;j<size;j++)
                if(this.getMyBoard().getBoard()[ship.getPositionX()+j][ship.getPositionY()+c]!=-8)
                    return false;
        }



    }else {
        if(ship.getPositionY()+ship.getSize()-1>9)
            return false;
        if(ship.getPositionY()+ship.getSize()<=9)
            if(this.getMyBoard().getBoard()[ship.getPositionX()][ship.getPositionY()+ship.getSize()]!=-8)
                return false;
        if(ship.getPositionY()-1>=0)
            if(this.getMyBoard().getBoard()[ship.getPositionX()][ship.getPositionY()-1]!=-8)
                return false;
        int j=0;
        int size=ship.getSize();
        int c=0;
        int term=0;

        if(ship.getPositionX()-1>=0 && ship.getPositionX()+1<=9) {
            c = -1;
            term = 2;
        }else if(ship.getPositionX()-1>=0 ){
            c=-1;
            term=0;
        }else {
            c=1;
            term=2;
        }
        if(ship.getPositionY()-1>=0){
            j=-1;
        }
        int back=j;

        for(;c<term;c+=2){
            j=back;
            for(;j<size;j++)
                if(this.getMyBoard().getBoard()[ship.getPositionX()+c][ship.getPositionY()+j]!=-8)
                    return false;
        }
    }
    return true;
    }

    public boolean validBoard(boolean total){
        for(int i=0;i<ships.size();i++)
            if(validShip(i)==false)
                return false;
        if(total==true)
            if(ships.size()!=10)
                return false;

        return true;
    }

    public boolean checkLost(){
        for(int i=0;i<ships.size();i++){
            if(!ships.get(i).isSink()){
                lost=false;
                return false;
            }
        }
        lost=true;
        return true;
    }

    public void setMyBoard(Board myBoard) {
        this.myBoard = myBoard;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public boolean isPlay() {
        return play;
    }

    public void setPlay(boolean play) {
        this.play = play;
    }

    public boolean isLost() {
        return lost;
    }

    public void setLost(boolean lost) {
        this.lost = lost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Ship> getShips() {
        return ships;
    }

    public void setShips(ArrayList<Ship> ships) {
        this.ships = ships;
    }
}
