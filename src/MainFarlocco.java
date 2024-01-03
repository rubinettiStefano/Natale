import com.generation.entities.Child;
import com.generation.library.Console;

public class MainFarlocco 
{
    public static void main(String[] args)
    {
        
        //fare THROWS(sssssssssssssss) di un eccezione significa
        //PROPAGARLA (COMPORTAMENTO DI DEFAULT,implicito, se non scrivo nulla)
        //fare TRY/CATCH significa GESTIRLA

        try
        {
            //CONTIENE IL CODICE CALDO
            //il codice che può PROPAGARE ECCEZIONI
            metodoIntermedio();
        }
        catch(RuntimeException e)
        {
            //Nel momento in cui viene propagata un eccezione DENTRO il try
            //CATTURA l'oggetto eccezione, lo mette nella variabile e
            //ed esegue il codice tra graffe, che viene detto BLOCCO DI GESTIONE DELL'ECCEZIONE
            Console.print(e.getMessage());
        }
        catch(Exception e)
        {
            Console.print("Eccezione DEVASTANTE, TERMINO");
            System.exit(-1);
        }
    }

    public static void metodoIntermedio() throws RuntimeException,Exception
    {
        Child c = new Child();
        c.setId(-1);
        String nuovoNome = "ciao";
        if(!nuovoNome.isBlank())
            c.setName("");
    }
}
