package it.polito.tdp.seriea.model;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.seriea.db.SerieADAO;

public class Model {

	
	SerieADAO dao;
	Map<String,Team> idMap;
	Graph <Team,DefaultWeightedEdge> grafo;
	
	public Model() {
		dao= new SerieADAO();
		idMap= new HashMap<String, Team>();
		dao.listTeams(idMap);
	}
	
	public void creaGrafo() {
		grafo= new SimpleWeightedGraph<Team, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		
		Graphs.addAllVertices(grafo, idMap.values());
		List<PartiteGiocate> pg=dao.getPartiteGiocateEpeso(idMap);
		
		for(PartiteGiocate p: pg) {
			Graphs.addEdgeWithVertices(grafo, p.getT1(), p.getT2(), p.getPeso());
		}
		System.out.println("vertici: " +grafo.vertexSet().size());
		System.out.println("vertici: " +grafo.edgeSet().size());
		for(DefaultWeightedEdge edge: grafo.edgeSet()) {
			System.out.println(edge +" " +grafo.getEdgeWeight(edge));
		}
	}

	public Graph<Team, DefaultWeightedEdge> getGrafo() {
		return grafo;
	}

	public String getConnessioni(Team teamInput) {
		
		List<Team> vicini=Graphs.neighborListOf(grafo, teamInput);
		Collections.sort(vicini, new Comparator<Team>() {

			@Override
			public int compare(Team o1, Team o2) {
				DefaultWeightedEdge arco1= grafo.getEdge(o1, teamInput);
				double peso1= grafo.getEdgeWeight(arco1);
				
				DefaultWeightedEdge arco2 = grafo.getEdge(o2, teamInput);
				double peso2= grafo.getEdgeWeight(arco2);
				
				return (int) (peso2-peso1);
			}
		});
		String ris="";
		for(Team t:vicini) {
			DefaultWeightedEdge edge= grafo.getEdge(t, teamInput);
			double peso= grafo.getEdgeWeight(edge);
			ris+= t.getTeam() +" "+ peso+"\n";
		}
		return ris;
	}
	
	
}
