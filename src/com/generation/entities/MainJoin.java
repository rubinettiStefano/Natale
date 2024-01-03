package com.generation.entities;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;

import com.generation.library.Console;
import com.generation.library.List;
import com.generation.util.DbUtil;

public class MainJoin 
{


    public static void main(String[] args) throws Exception
    {
        List<Child> children = new List<Child>();   
        List<Present> presents = new List<Present>(); 
        List<Adult> adults = new List<Adult>(); 

        Connection con = DbUtil.connectToDB("natale");

        Statement s = con.createStatement();
        String query = "SELECT * FROM child";
        ResultSet rs = s.executeQuery(query);

        while(rs.next())
        {
            Child c = new Child();
            c.setId(rs.getInt("id"));
            c.setName(rs.getString("name"));
            c.setSurname(rs.getString("surname"));
            c.setDob(LocalDate.parse(rs.getString("dob")));

            children.add(c);
        }

        Statement s1 = con.createStatement();
        String query1 = "SELECT * FROM present";
        ResultSet rs1 = s1.executeQuery(query1);

        while(rs1.next())
        {
            Present p = new Present();
            p.setId(rs1.getInt("id"));
            p.setName(rs1.getString("name"));
            p.setChild_id(rs1.getInt("child_id"));
            p.setAdult_id(rs1.getInt("adult_id"));
            p.setPrice(rs1.getDouble("price"));
            
            presents.add(p);
        }

        Statement s2 = con.createStatement();
        String query2 = "SELECT * FROM adult";
        ResultSet rs2 = s2.executeQuery(query2);

        while(rs2.next())
        {
            Adult a = new Adult();
            a.setId(rs2.getInt("id"));
            a.setName(rs2.getString("name"));
            a.setSurname(rs2.getString("surname"));
            a.setDob(LocalDate.parse(rs2.getString("dob")));
            a.setRal(rs2.getInt("ral"));
            adults.add(a);
        }


        for(Child c:children)
        {
            for(Present p: presents)
                if(c.getId()==p.getChild_id())
                {
                    c.addPresent(p);
                    p.setChild(c);
                }
        }

        for(Adult a:adults)
        {
            for(Present p: presents)
                if(a.getId()==p.getAdult_id())
                {
                    a.addPresent(p);
                    p.setAdult(a);
                }
        }

       String nomeBambino =         adults                  //List<Adult>
                                    .get(0)           //get(0), restituisce un elemento della lista,quindi Adult
                                    .getGivenPresents()     //getGivenPresents(),restituisce una List<Present>
                                    .get(0)           //prendiamo dalla lista il primo elemento, quindi un Present
                                    .getChild()             //prendiamo suo Padre, un Child
                                    .getName();             //dal child prendiamo il nome, che è una String
        
        Console.print(nomeBambino);
    }
}
