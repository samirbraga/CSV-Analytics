import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class Main {
	static JFrame window = null;

	public static void main(String[] args) throws IOException {

		// Janela Principal
		window = new JFrame("Trabalho Final");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.pack();
		window.setVisible(true);
		
		// Barra de Rolagem para a Tabela
		// JScrollPane scrollPane = null;

		// Tabela pra plotar o arquivo CSV
		// JTable csvTable = null;

		// Sistema de escolha de arquivo
		JFileChooser file = new JFileChooser();
		file.setFileSelectionMode(JFileChooser.FILES_ONLY);

		// Botão de upload do arquivo
		JButton uploadButton = new JButton("UPLOAD");

		// Ação de clique no Botão de Upload
		ActionListener clickListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int action = file.showSaveDialog(null);
		
				if (action == 1) {
					System.exit(0);
				} else {
					File selectedFile = file.getSelectedFile();

					String[] aux = selectedFile.getPath().split(".");
					String fileExt = aux[aux.length - 1].toLowerCase();

					if (fileExt == "csv") {
						try {	
							CSVReader csvReader = new CSVReader(selectedFile);
							csvReader.parse();

							String[] header = csvReader.getHeader();
							String[][] records = csvReader.getRecords();

							launch(records, header);

							JOptionPane.showMessageDialog(null, selectedFile.getPath());
						} catch (IOException err) {
							err.printStackTrace();
						}
					} else {
						JOptionPane.showMessageDialog(null, "O arquivo selecionado não é um CSV.");
					}
		
				}
			}
		};

		uploadButton.addActionListener(clickListener);

		window.add(uploadButton); // Adicionando a Tabela ao Frame
	}
	
	static void launch(String[][] records, String[] header) {
		JTable csvTable = new JTable(records, header);
		JScrollPane scrollPane = new JScrollPane(csvTable);
		window.add(csvTable); // Adicionando o Botão ao Frame
		window.add(scrollPane); // Adicionando o painel da Tabela
	}
}
