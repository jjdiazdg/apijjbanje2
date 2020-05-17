package jjBan.je.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import jjBan.je.core.Actividad;
import jjBan.je.core.TipoActividad;

@CrossOrigin(origins = "http://localhost:4200") 
@RepositoryRestResource(path="actividades",
						//exported=false,
						itemResourceRel="actividad",
						collectionResourceRel="actividades")
public interface ActividadDAO extends JpaRepository<Actividad, String> {

	@RestResource(path="actividad")
	List<Actividad> findByNombreActividadContainingIgnoreCase(String txt);

	
	@RestResource(path="tipo")
    @Query("SELECT a FROM Actividad a WHERE a.tipoActividad = ?1")
	List<Actividad> findByTipoActividadContainingIgnoreCase(TipoActividad tipo);
	
}
