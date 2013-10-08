package br.ufpb.aps.jogo.persistencia;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Serializador {

	public Serializador() {
	}
	// A ideia e informar o caminho para onde guardar e o objeto
	public void serializar(String path, Object obj) throws Exception {
		FileOutputStream outFile = new FileOutputStream(path);
		ObjectOutputStream s = new ObjectOutputStream(outFile);
		s.writeObject(obj);
		s.close();
	}

	// Passar o caminho ate onde se localiza o arquivo salvo
	public Object deserializar(String path) throws Exception {
		FileInputStream inFile = new FileInputStream(path);
		ObjectInputStream d = new ObjectInputStream(inFile);
		Object o = d.readObject();
		d.close();
		return o;
	}
}
