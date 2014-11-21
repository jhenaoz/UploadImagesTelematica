package Service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import Entity.Imagenes;

/**
 * Clase de servicios de BD de imagenes.
 * @author jhenaoz
 */
public class ServiceImages {

	/**
	 * @param ruta
	 * @return
	 */
	public String addImage(String ruta){
		EntityManagerFactory emf = null;
		EntityManager em = null ;
		EntityTransaction et = null;
		try {
			emf = Persistence.createEntityManagerFactory("ImagenesTelematica");
			em = emf.createEntityManager();
			et = em.getTransaction();
			et.begin();
			Imagenes img = new Imagenes(ruta);
			em.persist(img);;
			et.commit();
			System.out.println("imagen Guardada");
			return "ruta almacenada con exito";
		} catch (Exception e) {
			e.printStackTrace();
			return "algun problema paso";
		}finally{
			if(em != null){em.close();}
			if (emf != null) {emf.close();}
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<String> retriveImages(){
		EntityManagerFactory emf = null;
		EntityManager em = null ;
		List <Object> resultado = null;
		List<String> response =  new ArrayList<String>();
		try {
			emf = Persistence.createEntityManagerFactory("ImagenesTelematica");
			em = emf.createEntityManager();
			String query = "Select i.name from Imagenes i";
			Query q =em.createQuery(query);
			resultado =q.getResultList();
			
			for (Object obj : resultado) {
				String ruta = obj.toString();
				response.add(ruta);
			}
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			return response;
		}finally{
			if(em != null){em.close();}
			if (emf != null) {emf.close();}
		}
	}
	
}
