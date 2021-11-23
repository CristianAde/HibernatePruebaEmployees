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
import javax.swing.JOptionPane;
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
        EmpleadosHIBERNATE e = new EmpleadosHIBERNATE();  
        //e.insercion();
        //e.consulta();
        e.consultaSalario();
        //e.consultaNombre();
        //e.modificacion();
        //e.eliminacion(207);     
    }

    public void insercion(){
        
        //Creación de objetos
        SessionFactory creadorSesiones = 
                SessionFactorySingleton.getSessionFactory();
        Session sesion = creadorSesiones.openSession();
        Transaction tr = sesion.beginTransaction();
        Employees empl;
        
        //Instanciación del empleado a insertar. Lanza Excepción.
        try{
            empl = new Employees(207, null, "Cristian", "Ade",
                    "cristian1234@hotmail.com", "686574637", new Date(2021 - 11 
                            - 22), "AC_MGR", (new BigDecimal(15000)),
                    null, null, null);
            sesion.save(empl);
            tr.commit();
            System.out.println("Empleado " + empl.getFirstName() 
                    + " insertado con éxito");
        } catch (Exception Ex) {
            System.out.println(Ex.toString());
            tr.rollback();
        }
        
        //cierre de Session y SessionFactory
        sesion.close();
        creadorSesiones.close();
    }
    
    public void consulta() {
        //Creación de objetos
        SessionFactory creadorSesiones = SessionFactorySingleton.getSessionFactory();
        Session sesion = creadorSesiones.openSession();
        Transaction tr;
        tr = sesion.beginTransaction();
        Employees empl;
        Query q = null;
        //Creación de la cosulta. Lanza Excepción
        try {
            q = sesion.createQuery("from Employees");
            //Obtención del resultado
            List<Employees> lista = q.list();
            if (lista.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No such Employee");
            } else {
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
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        //cierre de Session y SessionFactory
        sesion.close();
        creadorSesiones.close();
    }
    
    public void consultaNombre() {
        //Creación de objetos
        SessionFactory creadorSesiones = SessionFactorySingleton.getSessionFactory();
        Session sesion = creadorSesiones.openSession();
        Transaction tr;
        tr = sesion.beginTransaction();
        
        Employees empl;

        Query q = sesion.createQuery("from Employees where first_name = 'Cristian' and last_name = 'Ade'");
        //Obtenemos el resultado de la consulta 
        List<Employees> lista = q.list();
        if (lista.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No such Employee");
            tr.rollback();
        } else {
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
        }
        //cierre de Session y SessionFactory
        sesion.close();
        creadorSesiones.close();
    }
    
    public void consultaSalario() {
        //Creación de objetos
        SessionFactory creadorSesiones = SessionFactorySingleton.getSessionFactory();
        Session sesion = creadorSesiones.openSession();
        Transaction tr;
        tr = sesion.beginTransaction();
        
        Employees empl;

        Query q = sesion.createQuery("from Employees where salary > 20000");
        //Obtenemos el resultado de la consulta 
        List<Employees> lista = q.list();
        if (lista.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No such Employee");
            tr.rollback();
        } else {
            //Mostremos los datos de los articulos 
            System.out.println("INFORMACION DE LOS EMPLEADOS");
            Iterator<Employees> iter = lista.iterator();
            while (iter.hasNext()) {
                empl = (Employees) iter.next();
                System.out.println("ID\t" + empl.getEmployeeId()
                        + " Nombre\t " + empl.getFirstName()
                        + " Apellido\t " + empl.getLastName()
                        + " Salario\t " + empl.getSalary()
                        );
            }
        }
        //cierre de Session y SessionFactory
        sesion.close();
        creadorSesiones.close();
    }
    
    public void modificacion(){
        //Creación de objetos
        SessionFactory creadorSesiones = 
                SessionFactorySingleton.getSessionFactory();
        Session sesion = creadorSesiones.openSession();
        Transaction tr = sesion.beginTransaction();
        Employees empl  = new Employees();       
        //Creación de la cosulta para cargar el objeto a modificar
        Query q = sesion.createQuery("from Employees where first_name = "
                + "'Cristian' and last_name = 'Ade'");
        empl = (Employees) q.uniqueResult();
        //Estructura condicional para realizar la modificacion del objeto
        if(empl==null){ 
            JOptionPane.showMessageDialog(null, "No such Employee");
        }else{
            //Modificación persistente del objeto
            empl.setSalary(new BigDecimal(30000));
            tr.commit();
            System.out.println("Empleado " + empl.getFirstName() 
                    + " modificado con éxito");
        }      
       //cierre de Session y SessionFactory 
        sesion.close();
        creadorSesiones.close();
    }
    
    public void eliminacion(int empId){
        int id = empId;
        //Creación de objetos
        SessionFactory creadorSesiones = SessionFactorySingleton.getSessionFactory();
        Session sesion = creadorSesiones.openSession();
        Transaction tr = sesion.beginTransaction();
        Employees empl;
        
        empl = new Employees();
         
        // Get the Employee with the ID = 22
        empl = (Employees) sesion.get(Employees.class, id);
        if(empl==null){ 
            JOptionPane.showMessageDialog(null, "No such Employee");
            tr.rollback();
        }else{
            sesion.delete(empl);
            tr.commit();
            System.out.println("Empleado " + empl.getFirstName() + " eliminado con éxito");
        }
        
        //cierre de Session y SessionFactory
        sesion.close();
        creadorSesiones.close();
    }
    
}
    

