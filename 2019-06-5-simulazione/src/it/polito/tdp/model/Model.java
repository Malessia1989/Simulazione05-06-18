package it.polito.tdp.model;

import java.time.Year;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit;

import it.polito.tdp.db.EventsDao;

public class Model {
	
	EventsDao dao;
	Graph<Distretto, DefaultWeightedEdge> grafo;
	
	
	public Model() {
		dao= new EventsDao();
	}
	

	public List<Anno> getAnno() {
		
		return dao.getAnno();
	}
	
	public String creaGrafo(Anno anno) {
		grafo= new SimpleWeightedGraph<Distretto, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		List<Distretto> d=dao.getDistretti(anno.getAnno());
		String ris="";
	
			Graphs.addAllVertices(grafo, d);
			
			for (Distretto d1 : grafo.vertexSet()) {
				for (Distretto d2 : grafo.vertexSet()) {
					if (!d1.equals(d2)) {
						DefaultWeightedEdge edge = grafo.getEdge(d1, d2);
						if (edge == null) {
							edge = grafo.addEdge(d1, d2);
							double distanza= LatLngTool.distance(d1.getLat(), d2.getLat(), LengthUnit.KILOMETER);
							grafo.setEdgeWeight(edge, distanza);
							//Graphs.addEdgeWithVertices(grafo, d1, d2, distanza);
						}
					}					
					
				}
			}
			for(Distretto dtemp: grafo.vertexSet()) {
				List<Distretto> vicini= Graphs.neighborListOf(grafo, dtemp);
				Collections.sort(vicini, new Comparator<Distretto>() {

					@Override
					public int compare(Distretto d1, Distretto d2) {
						DefaultWeightedEdge arco1= grafo.getEdge(d1, dtemp);
						double peso1= grafo.getEdgeWeight(arco1);
						
						DefaultWeightedEdge arco2 = grafo.getEdge(d2, dtemp);
						double peso2= grafo.getEdgeWeight(arco2);
						
						return Double.compare(peso1, peso2);
					}
				});
			
				for(Distretto dtempp: vicini) {
					DefaultWeightedEdge edge= grafo.getEdge(dtemp,dtempp);
					double peso=grafo.getEdgeWeight(edge);
					ris+= dtempp.getId()+ ": " +peso+"\n";
				}
			}
			
		
		
		return ris;
	}
	
}
