package org.jboss.tools.example.springmvc.data;

import java.io.Serializable;

import org.jboss.tools.example.springmvc.model.rd.Inspecao;

public interface InspecaoDAO extends Serializable{

	public Inspecao merge (Inspecao inspecao);
}
