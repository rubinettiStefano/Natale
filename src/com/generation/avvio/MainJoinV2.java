package com.generation.avvio;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;

import com.generation.entities.Adult;
import com.generation.entities.Child;
import com.generation.entities.Present;
import com.generation.library.Console;
import com.generation.library.List;
import com.generation.library.Map;
import com.generation.util.DbUtil;

public class MainJoinV2 
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

        Map<Integer,List<Present>> childIdToPresents = new Map<Integer,List<Present>>();
        Map<Integer,List<Present>> adultIdToPresents = new Map<Integer,List<Present>>();

        //Chiave: 1
        //valore: una lista contenente palla,macchinina e libri
        //Chiave: 2
        //valore: una lista contenente doll,science kit e guitar
        //ecc....

        //Algoritmo di complessità , un giro per ogni present
        for(Present p:presents)
        {
            if(childIdToPresents.containsKey(p.getChild_id()))//se ha già quella chiave
            {
                childIdToPresents           //prendo la mappa
                .get(p.getChild_id())       //prendo la lista di regali collegata alla chiave p.getChild_id()
                .add(p);                    //aggiungo questo regalo alla lista
            }
            else
            {
                List<Present> regaliPerBambinoConMioId = new List<Present>();
                regaliPerBambinoConMioId.add(p);
                childIdToPresents.put(p.getChild_id(), regaliPerBambinoConMioId);
            }

            if(adultIdToPresents.containsKey(p.getAdult_id()))//se ha già quella chiave
            {
                adultIdToPresents           //prendo la mappa
                .get(p.getAdult_id())       //prendo la lista di regali collegata alla chiave p.getChild_id()
                .add(p);                    //aggiungo questo regalo alla lista
            }
            else
            {
                List<Present> regaliFattiDaAdultoConMioId = new List<Present>();
                regaliFattiDaAdultoConMioId.add(p);
                adultIdToPresents.put(p.getAdult_id(), regaliFattiDaAdultoConMioId);
            }
        }

        //Complessità NxM
        //numeroChildren X numeroPresents

        //N = numeroChildren
        //M = numeroRegali
        //Complessità: N + 2M
        //1 M (un giro di tutti i regali) se ne va per riempire le Mappe
        //1 N (un giro di tutti i bambini) se ne va per scorrere child per child
        //1 M se ne va alla riga 123

        //Sempre supponendo 1000 bambini e 1000 regali
        //prima dovevamo fare 1 milione di giri
        //ora 3000


        //C + A + 3P 

        //CxP + AxP
        for(Child c:children)
        {
            List<Present> regali = childIdToPresents.get(c.getId());
            c.setReceivedPresents(presents);
            for(Present p:regali)
                p.setChild(c);
        }

        for(Adult a:adults)
        {
            List<Present> regali = adultIdToPresents.get(a.getId());
            a.setGivenPresents(presents);
            for(Present p:regali)
                p.setAdult(a);
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
