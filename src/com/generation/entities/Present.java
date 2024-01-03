package com.generation.entities;

public class Present 
{
    private int id;
    private String name;
    private double price;
    
    private int child_id;//F.K. (servono al DB)
    private int adult_id;

    private Child child;//RIFERIMENTI AI PADRI, li usiamo su Java
    private Adult adult;

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
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public int getChild_id() {
        return child_id;
    }
    public void setChild_id(int child_id) {
        this.child_id = child_id;
    }
    public int getAdult_id() {
        return adult_id;
    }
    public void setAdult_id(int adult_id) {
        this.adult_id = adult_id;
    }
    public Child getChild() {
        return child;
    }
    public void setChild(Child child) //quando impostiamo riferimento al padre vogliamo anche impostare chiave esterna
    {
        this.child = child;
        this.child_id = child.getId();
    }
    public Adult getAdult() {
        return adult;
    }
    public void setAdult(Adult adult) 
    {
        this.adult = adult;
        this.adult_id = adult.getId();
    }
    @Override
    public String toString() {
        return "Present [id=" + id + ", name=" + name + ", price=" + price + ", child_id=" + child_id + ", adult_id="
                + adult_id + "]";
    } 

    
}
