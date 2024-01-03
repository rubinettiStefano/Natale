package com.generation.entities;

import java.time.LocalDate;

import com.generation.library.List;

public class Child 
{

    private int id;
    private String name,surname;
    private LocalDate dob;

    private List<Present> receivedPresents = new List<Present>();

    public int getId() 
    {
        return id;
    }

    public void setId(int id) throws RuntimeException
    {
        if(id<0)
        	throw new RuntimeException("Id non valido, deve essere positivo");
            //con new RuntimeException("....") stiamo ISTANZIANDO un ECCEZIONE
            //facciamo THROW di quell'eccezione
            //fare THROW significa SPEDIRLA AL CHIAMANTE
            //fare THROW significa fare un RETURN ALTERNATIVO
        this.id = id;
        return;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws RuntimeException
    {
        if(name==null || name.isBlank())
            throw new RuntimeException("Id non valido, deve essere positivo");

        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public List<Present> getReceivedPresents() {
        return receivedPresents;
    }

    public void setReceivedPresents(List<Present> receivedPresents) {
        this.receivedPresents = receivedPresents;
    }

    public void addPresent(Present p)
    {
        receivedPresents.add(p);
    }

    @Override
    public String toString() {
        return "Child [id=" + id + ", name=" + name + ", surname=" + surname + ", dob=" + dob + "]";
    }

        
}
