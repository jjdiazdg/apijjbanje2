package jjBan.je.repositorios;

import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import jjBan.je.core.Ruta;

//@Repository
@CrossOrigin(origins = "http://localhost:4200") 
@RepositoryRestResource(path="rutas",
						//exported=false,
						itemResourceRel="ruta",
						collectionResourceRel="rutas")
public interface RutasDAO extends JpaRepository<Ruta, String>{
	
    @RestResource(path="nombre")
	List<Ruta> findByNombreContainingIgnoreCase(String txt);
    
    @RestResource(path="provincia")
   List<Ruta> findByProvinciaContainingIgnoreCase(String txt);
    
    @RestResource(path="desnivel")
   @Query("SELECT r FROM Ruta r WHERE r.datosTecnicos.desnivel > ?1")
    List<Ruta> findByDesnivelGreaterThan(int desnivel);
    
    @RestResource(path="kms-recorridos")
   @Query("SELECT r FROM Ruta r WHERE r.datosTecnicos.kmsRecorridos BETWEEN  ?1 AND ?2")
   List<Ruta> findByKmsRecorridosBetween(float start, float end);

    @RestResource(path="duracion")
    @Query("SELECT r FROM Ruta r WHERE r.datosTecnicos.duracion > ?1")
     List<Ruta> findByDuracionGreaterThan(LocalTime duracion);
    
    
//    @RestResource(exported=false)
//    void delete(Ruta entity);
  
}
