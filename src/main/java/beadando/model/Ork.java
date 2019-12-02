package beadando.model;

import beadando.model.exceptions.InvalidAttackPower;
import beadando.model.exceptions.InvalidDefense;
import beadando.model.exceptions.InvalidHealth;
import beadando.model.exceptions.InvalidRange;
import org.apache.log4j.Logger;

import java.time.LocalDate;
import java.util.Objects;

public class Ork {

    //region Fields
    private String name;
    private Weapon weapon;
    private int health;
    private double range;
    private int attackPower;
    private int defense;
    private LocalDate bornt;

    private Logger logger = Logger.getLogger(Ork.class);
    //endregion

    //region Constructors
    public Ork() {
    }

    public Ork(String name, Weapon weapon, int health, double range,
               int attackPower, int defense, LocalDate bornt) throws InvalidHealth, InvalidRange, InvalidAttackPower, InvalidDefense {

        setName(name);
        setWeapon(weapon);
        setHealth(health);
        setRange(range);
        setAttackPower(attackPower);
        setDefense(defense);
        setBornt(bornt);

        logger.info("Uj Ork szuletett: " + this);
    }

    //endregion

    //region Properties

 public LocalDate getBornt() {
     return bornt;
 }

    public void setBornt(LocalDate bornt) {
        logger.debug(name+ ": bornt successfully set from:" + this.bornt+ "  to: " + bornt);
        this.bornt = bornt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        logger.debug("name successfully set from:" + this.name + "  to: " + name);
        this.name = name;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        logger.debug(name+ ": weapon successfully set from:" + this.weapon + "  to: " + weapon);
        this.weapon = weapon;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) throws InvalidHealth {
        if (health >= 1 && 50 >= health) {
            logger.debug(name+ ": health successfully set from:" + this.health + "  to: " + health);
            this.health = health;
        }
        else {
            logger.error(name+ ": invalidHealth: " + health);
            throw new InvalidHealth();
        }
    }

    public double getRange() {
        return range;
    }

    public void setRange(double range) throws InvalidRange {
        if (range >= 0.5 && 100.0 >= range) {
            logger.debug(name+ ": range successfully set from:" + this.range + "  to: " + range);
            this.range = range;
        }
        else{
            logger.error(name+ ": invalidRange: " + range);
            throw  new InvalidRange();
        }
    }

    public int getAttackPower() {
        return attackPower;
    }

    public void setAttackPower(int attackPower) throws InvalidAttackPower {
        if (attackPower >= 1 && 15 >= attackPower) {
            logger.debug(name+ ": attackPower successfully set from:" + this.attackPower + "  to: " + attackPower);
            this.attackPower = attackPower;
        }
        else {
            logger.error(name+ ": invalidAP: "+ attackPower);
            throw new InvalidAttackPower();
        }
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) throws InvalidDefense {
        if (defense >= 0 && 20 >= defense) {
            logger.debug(name+ ": attackPower successfully set from:" + this.defense + "  to: " + defense);
            this.defense = defense;
        }
        else {
            logger.error(name+ ": invalidDefense: " + defense);
            throw new InvalidDefense();
        }
    }
    //endregion

    //region Methods
    @Override
    public String toString(){

        return "main.model.Ork{"+
                "name='" + name + '\''+
                "weapon='" + weapon + '\''+
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
                weapon == ork.weapon &&
                Objects.equals(bornt, ork.bornt) &&
                Objects.equals(logger, ork.logger);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, weapon, health, range, attackPower, defense, bornt, logger);
    }
    //endregion

}
