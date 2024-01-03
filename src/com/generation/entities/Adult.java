package com.generation.entities;

import java.time.LocalDate;

import com.generation.library.List;

public class Adult 
{
    private int id;
    private String name,surname;
    private LocalDate dob;
    private int ral;//reddito annuo lordo

    private List<Present> givenPresents = new List<Present>();

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

    public List<Present> getGivenPresents() {
        return givenPresents;
    }

    public void setGivenPresents(List<Present> receivedPresents) {
        this.givenPresents = receivedPresents;
    }

    public void addPresent(Present p)
    {
        givenPresents.add(p);
    }

    public int getRal() {
        return ral;
    }

    public void setRal(int ral) {
        this.ral = ral;
    }

    @Override
    public String toString() {
        return "Adult [id=" + id + ", name=" + name + ", surname=" + surname + ", dob=" + dob + ", ral=" + ral + "]";
    }

    

}
