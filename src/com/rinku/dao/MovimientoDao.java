package com.rinku.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.rinku.model.Movimiento;
import com.rinku.util.HibernateUtil;
import java.util.Date;

public class MovimientoDao {
	public boolean guardaMovimiento(Movimiento movimiento) {
		Transaction transaction = null;
		boolean guardado=false;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
		    transaction = session.beginTransaction();
		    session.save(movimiento);
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

	public List<Movimiento> buscaMovimientosEmpleado(int numEmpleado) {
        Transaction transaction = null;
        List<Movimiento> result= null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            result = (List<Movimiento>)session.createQuery("FROM Movimiento WHERE IdEmpleado = "+numEmpleado, Movimiento.class).getResultList();
            transaction.commit();
        } catch (Exception e) {
            if (session!= null && transaction != null) {
                transaction.rollback();
            }
            System.out.println("<<<Error al buscar registro: "+e.getCause());
            e.printStackTrace();
        } finally {
        	session.close();
        }
        return result;
    } 
	
	@SuppressWarnings("unchecked")
	public List<Movimiento> buscaMovsEmpPorFecha(int numEmpleado, Date fechaIni, Date fechaFin ) {
        Transaction transaction = null;
        List<Movimiento> result= null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            Query query=session.createQuery("FROM Movimiento WHERE IdEmpleado = :numEmpleado and fecha BETWEEN :fechaIni and :fechaFin");
            query.setParameter("numEmpleado", numEmpleado);
            query.setParameter("fechaIni", fechaIni);
            query.setParameter("fechaFin", fechaFin);
            result = (List<Movimiento>)query.getResultList();
            transaction.commit();
        } catch (Exception e) {
            if (session!= null && transaction != null) {
                transaction.rollback();
            }
            System.out.println("<<<Error al buscar registro: "+e.getCause());
            e.printStackTrace();
        } finally {
        	session.close();
        }
        return result;
    } 
}
