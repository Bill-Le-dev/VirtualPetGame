import java.io.Serializable;

public class Dog implements Serializable {

    private int hunger_lvl;
    private int happiness_lvl;
    private int energy_lvl;
    private String name;
    private String mood; // Sad, Hungry, Tired

    public Dog() {
        hunger_lvl = 100;
        happiness_lvl = 100;
        energy_lvl = 100;
    }

    public Dog (int hunger_lvl, int happiness_lvl, int energy_lvl, String name) {
        this.hunger_lvl = hunger_lvl;
        this.happiness_lvl = happiness_lvl;
        this.energy_lvl = energy_lvl;
        this.name = name;
        mood = "";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHunger_lvl() {
        return hunger_lvl;
    }

    public int getHappiness_lvl() {
        return happiness_lvl;
    }

    public int getEnergy_lvl() {
        return energy_lvl;
    }

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }

    public void feed() {
        if (hunger_lvl+28<=100) {
            hunger_lvl+=28;
        } else {
            hunger_lvl = 100;
        }
        if (happiness_lvl+15<=100) {
            happiness_lvl+=15;
        } else {
            happiness_lvl = 100;
        }
        if (hunger_lvl!=0||happiness_lvl!=0) {
            if (energy_lvl - 4 >= 0) {
                energy_lvl -= 4;

            } else {
                energy_lvl = 0;
            }
        }
    }

    public void walk() {
        if (happiness_lvl+25<=100) {
            happiness_lvl+=25;
        } else {
            happiness_lvl = 100;
        }

        if (hunger_lvl-13>=0) {
            hunger_lvl-=13;
        } else {
            hunger_lvl = 0;
        }
        if (happiness_lvl!=0||hunger_lvl!=0) {
            if (energy_lvl-19>=0) {
                energy_lvl -= 19;
            } else {
                energy_lvl = 0;
            }
        }
    }

    public void sleep() {
        if (hunger_lvl!=0||energy_lvl!=0) {
            if (happiness_lvl-15 >= 0) {
                happiness_lvl -= 15;
            } else {
                happiness_lvl = 0;
            }
        }

        if (hunger_lvl-12 >= 0) {
            hunger_lvl -= 12;
        } else {
            hunger_lvl = 0;
        }

        if (energy_lvl+21<=100) {
            energy_lvl+=21;
        }
        else {
            energy_lvl = 100;
        }
    }
}
