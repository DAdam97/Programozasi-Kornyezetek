package beadando.model;

import beadando.model.exceptions.invalidAttackPower;
import beadando.model.exceptions.invalidDefense;
import beadando.model.exceptions.invalidHealth;
import beadando.model.exceptions.invalidRange;
import org.apache.log4j.Logger;

import java.time.LocalDate;
import java.util.Objects;

public class Ork {
    private String name;
    private Weapon weapon;
    private int health;
    private double range;
    private int attackPower;
    private int defense;
    private LocalDate bornt;

    private Logger logger = Logger.getLogger(Ork.class);


    //region Constructors
    public Ork() {
    }

    public Ork(String name, Weapon weapon, int health, double range,
               int attackPower, int defense, LocalDate bornt) {
        this.name = name;
        this.weapon = weapon;
        this.health = health;
        this.range = range;
        this.attackPower = attackPower;
        this.defense = defense;
        this.bornt = bornt;
       // logger.info("Uj Ork szuletett: " + this);
    }

    //endregion

 //region Properties

 public LocalDate getBornt() {
     return bornt;
 }

    public void setBornt(LocalDate bornt) {
        this.bornt = bornt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) throws invalidHealth {
        if (health >= 1 && 50 >= health) {
            this.health = health;
        }
        else {
            throw new invalidHealth();
        }
    }

    public double getRange() {
        return range;
    }

    public void setRange(double range) throws invalidRange {
        if (range >= 0.5 && 100.0 >= range) {
            this.range = range;
        }
        else{
            throw  new invalidRange();
        }
    }

    public int getAttackPower() {
        return attackPower;
    }

    public void setAttackPower(int attackPower) throws invalidAttackPower {
        if (attackPower >= 1 && 15 >= attackPower) {
            this.attackPower = attackPower;
        }
        else {
            throw new invalidAttackPower();
        }
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) throws invalidDefense {
        if (0 >= defense && 20 >= defense) {
            this.defense = defense;
        }
        else {
            throw new invalidDefense();
        }
    }
    //endregion

    //region Methods
    @Override
    public String toString(){

        return "main.model.Ork{"+
                "name='" + name + '\''+
                "fegyver='" + weapon + '\''+
                "health='" + health + '\''+
                "range='" + range + '\''+
                "attackPower='" + attackPower + '\''+
                "defense='" + defense + '\''+
                "bornt='" + bornt + '\''+
                "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ork ork = (Ork) o;
        return health == ork.health &&
                Double.compare(ork.range, range) == 0 &&
                attackPower == ork.attackPower &&
                defense == ork.defense &&
                Objects.equals(name, ork.name) &&
                weapon == ork.weapon;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, weapon, health, range, attackPower, defense);
    }
    //endregion

}
