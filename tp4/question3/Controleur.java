package question3;

import question3.tp3.PileI;
import question3.tp3.PilePleineException;
import question3.tp3.PileVideException;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
 * Décrivez votre classe Controleur ici.
 * 
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class Controleur extends JPanel {

    private JButton push, add, sub, mul, div, clear;
    private PileModele<Integer> pile;
    private JTextField donnee;
    private ActionListener pushlistener,addlistener,sublistener,mullistener,
                           divlistener,clearlistener;
              

    public Controleur(PileModele<Integer> pile) {
        super();
        this.pile = pile;
        this.donnee = new JTextField(8);

        this.push = new JButton("push");
        this.add = new JButton("+");
        this.sub = new JButton("-");
        this.mul = new JButton("*");
        this.div = new JButton("/");
        this.clear = new JButton("[]");
        
        pushlistener = new PushListener();
        addlistener = new AddListener();
        sublistener = new SubListener();
        mullistener = new MulListener();
        divlistener = new DivListener();
        clearlistener = new ClearListener();

        setLayout(new GridLayout(2, 1));
        add(donnee);
        //donnee.addActionListener(null /* null est à remplacer */);
        JPanel boutons = new JPanel();
        boutons.setLayout(new FlowLayout());
        boutons.add(push);  push.addActionListener(pushlistener);
        boutons.add(add);   add.addActionListener(addlistener);
        boutons.add(sub);   sub.addActionListener(sublistener);
        boutons.add(mul);   mul.addActionListener(mullistener);
        boutons.add(div);   div.addActionListener(divlistener);
        boutons.add(clear); clear.addActionListener(clearlistener);
        add(boutons);
        boutons.setBackground(Color.red);
        actualiserInterface();
    }

    public void actualiserInterface() {
        
        if(pile.estPleine())push.setEnabled(false);
        else {
            push.setEnabled(true);
        }
        
        if(pile.taille()<2){
            add.setEnabled(false);
            sub.setEnabled(false);
            mul.setEnabled(false);
            div.setEnabled(false);
        }
        else{
            add.setEnabled(true);
            sub.setEnabled(true);
            mul.setEnabled(true);
            div.setEnabled(true);
        }
        if(pile.estVide())clear.setEnabled(false);
        else{
            clear.setEnabled(true);
        }
    }

    private Integer operande() throws NumberFormatException {
        return Integer.parseInt(donnee.getText());
    }
  
public class PushListener implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            try{
                pile.empiler(operande());     
            }
            catch(NumberFormatException e){}
            
            catch(PilePleineException e){}
            actualiserInterface();
        }
    }
        
public class AddListener implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            try{
                pile.empiler(pile.depiler()+pile.depiler());
            }
            catch(PileVideException e){}
            
            catch(PilePleineException e){}
            actualiserInterface();
        }
    }
        
public class SubListener implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            try{
                pile.empiler(pile.depiler()-pile.depiler());
            }
            catch(PileVideException e){}
            
            catch(PilePleineException e){}
            actualiserInterface();
        }
    } 
        
public class MulListener implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            try{
                pile.empiler(pile.depiler()*pile.depiler());
            }
            catch(PileVideException e){}
            
            catch(PilePleineException e){}
            actualiserInterface();
        }
    }
        
public class DivListener implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            try{
                int numerateur = pile.depiler();
                int denominateur = pile.depiler();
                if(denominateur==0){
                    pile.empiler(denominateur);
                    pile.empiler(numerateur);
                }else{
                    pile.empiler(numerateur/denominateur);
                }
            }
            catch(PileVideException e){}
            
            catch(PilePleineException e){}
            actualiserInterface();
        }
    }
        
public class ClearListener implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            try{
                for(int i=0;i<pile.taille();i++){
                    pile.depiler();
                }
            }
            catch(PileVideException e){}
            actualiserInterface();
        }
    }
}
        