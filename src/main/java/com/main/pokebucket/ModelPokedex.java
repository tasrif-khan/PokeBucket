package com.main.pokebucket;

public class ModelPokedex {

    String name, url, type1, type2 , ability1, ability2, hiddenability, egggroup1, egggroup2;
    int id, key, eggcycles;

    public ModelPokedex(int key, int id, String name, String url, String type1, String type2, String ability1, String ability2,
                        String hiddenability, String egggroup1, String egggroup2, int eggcycles) {

        this.key = key;
        this.id = id;
        this.name = name;
        this.url = url;
        this.type1 = type1;
        this.type2 = type2;
        this.ability1 = ability1;
        this.ability2 = ability2;
        this.hiddenability = hiddenability;
        this.egggroup1 = egggroup1;
        this.egggroup2 = egggroup2;
        this.eggcycles = eggcycles;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType1() {
        return type1;
    }

    public void setType1(String type1) {
        this.type1 = type1;
    }

    public String getType2() {
        return type2;
    }

    public void setType2(String type2) {
        this.type2 = type2;
    }

    public String getAbility1() {
        return ability1;
    }

    public void setAbility1(String ability1) {
        this.ability1 = ability1;
    }

    public String getAbility2() {
        return ability2;
    }

    public void setAbility2(String ability2) {
        this.ability2 = ability2;
    }

    public String getHiddenability() {
        return hiddenability;
    }

    public void setHiddenability(String hiddenability) {
        this.hiddenability = hiddenability;
    }

    public String getEgggroup1() {
        return egggroup1;
    }

    public void setEgggroup1(String egggroup1) {
        this.egggroup1 = egggroup1;
    }

    public String getEgggroup2() {
        return egggroup2;
    }

    public void setEgggroup2(String egggroup2) {
        this.egggroup2 = egggroup2;
    }

    public int getEggcycles() {
        return eggcycles;
    }

    public void setEggcycles(int eggcycles) {
        this.eggcycles = eggcycles;
    }
}
