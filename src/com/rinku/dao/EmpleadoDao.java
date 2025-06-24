package com.rinku.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.rinku.util.HibernateUtil;
import com.rinku.model.Empleado;

public class EmpleadoDao {
	 public boolean guardaEmpleado(Empleado empleado) {
        Transaction transaction = null;
        boolean guardado=false;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(empleado);
            guardado=true;
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                guardado=false;
            }
            System.out.println("<<<Error al insertar registro: "+e.getCause());
            e.printStackTrace();
        }
        return guardado;
    }
	 public Empleado buscaEmpleado(int numEmpleado) {
	        Transaction transaction = null;
	        Empleado result= null;
	        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	            transaction = session.beginTransaction();
	            result = session.get(Empleado.class, numEmpleado);
	            transaction.commit();
	        } catch (Exception e) {
	            if (transaction != null) {
	                transaction.rollback();
	            }
	            System.out.println("<<<Error al buscar registro: "+e.getCause());
	            e.printStackTrace();
	        }
	        return result;
	    } 
	 
	 public boolean eliminaEmpleado(int numEmpleado) {
	        Transaction transaction = null;
	        Empleado result= null;
	        boolean eliminado= false;
	        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	            transaction = session.beginTransaction();
	            result = session.get(Empleado.class, numEmpleado);
	            if(result!=null) {
	            	session.delete(result);
	            	eliminado=true;
	            }
	            transaction.commit();
	        } catch (Exception e) {
	            if (transaction != null) {
	                transaction.rollback();
	            }
	            System.out.println("<<<Error al eliminar registro: "+e.getCause());
	            e.printStackTrace();
	        }
	        return eliminado;
	   } 
	 public boolean modificaEmpleado(Empleado nuevo) {
	        Transaction transaction = null;
	        Empleado result= null;
	        boolean modificado= false;
	        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	            transaction = session.beginTransaction();
	            result = session.get(Empleado.class, nuevo.getNumeroEmpleado());
	            if(result!=null) {
	            	session.persist(nuevo);
	            	modificado=true;
	            }
	            transaction.commit();
	        } catch (Exception e) {
	            if (transaction != null) {
	                transaction.rollback();
	            }
	            System.out.println("<<<Error al actualizar registro: "+e.getCause());
	            e.printStackTrace();
	        }
	        return modificado;
	   } 
}
