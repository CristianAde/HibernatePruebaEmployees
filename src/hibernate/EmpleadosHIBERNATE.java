/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernate;

import hibernate.SessionFactorySingleton;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import modelo.Employees;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


/**
 *
 * @author Cristian
 */
public class EmpleadosHIBERNATE {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        SessionFactory creadorSesiones = SessionFactorySingleton.getSessionFactory();
        Session sesion = creadorSesiones.openSession();
        Transaction tr = sesion.beginTransaction();
        Employees empl;
        try{
        empl = new Employees(207, null, "Cristian", "Ade", 
                "cristian1234@hotmail.com", "686574637", new Date(2021-11-22), "AC_MGR",(new BigDecimal(15000.00)), 
                null, null, null);
        sesion.save(empl);  
        tr.commit();
        } catch (Exception Ex){
            System.out.println(Ex.toString());
            tr.rollback();
        }
        
        Query q = sesion.createQuery("from Employees");

        //Obtenemos el resultado de la consulta 
        List<Employees> lista = q.list();

        //Mostremos los datos de los articulos 
        System.out.println("INFORMACION DE LOS EMPLEADOS");
        Iterator<Employees> iter = lista.iterator();
        while (iter.hasNext()) {
            empl = (Employees) iter.next();
            System.out.println("ID\t" + empl.getEmployeeId()
                    + " Nombre\t " + empl.getFirstName()
                    + " Apellido\t " + empl.getLastName()
                    + " Salario\t " + empl.getSalary()
                    + " Job ID\t " + empl.getJobId());
        }
        //cerramos la conexión 
        sesion.close();
        creadorSesiones.close();
    }

}
    

