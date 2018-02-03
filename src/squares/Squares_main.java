package squares;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

public class Squares_main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//double x[] = {0.0, 0.2, 0.4, 0.6, 0.8, 1.0, 1.2};
		//double y[] = {1.0, 1.9, 3.2, 4.3, 4.8, 6.1, 7.2};
		//double x[] = {0.0, 0.3, 0.45, 0.6, 0.73, 1.1, 1.5};
		//double y[] = {0.0, 1.0, 2.1, 1.8, 2.9, 3.1, 5.3};
		Squares_main smain = new Squares_main();
		double x[] = smain.getCSV1("csv/x.csv", 7);
		double y[] = smain.getCSV1("csv/y.csv", 7);
		double theta[] = new double[2];
		
		Squares_lib slib = new Squares_lib(x, y);
		double value[] = new double[100];
		double value2[][] = new double[100][4];
		for(int i =0; i < 100; i++) {
			theta = slib.getDx();
			System.out.println(i + "theta[0] = " + theta[0]);
			System.out.println(i + "theta[1] = " + theta[1]);
			System.out.println(i + "Objective function = " + slib.getObject());
			value[i] = slib.getObject();
			value2[i][0] = i;
			value2[i][1] = slib.getObject();
			value2[i][2] = theta[0];
			value2[i][3] = theta[1];
		}
		
		Graph graph = new Graph(value);
		graph.setBounds(5, 5, 655, 455);
		graph.setVisible(true);
		
		smain.writeCSV("csv/result.csv", value2);

	}
	
	public double[] getCSV1(String path, int n) {
		//CSVから取り込み
		double csvdata[] = new double[n];
		try {
			File f= new File(path);
			BufferedReader br = new BufferedReader(new FileReader(f));
			
			String[] data = new String[n];
			String line = br.readLine();
			data = line.split(",", 0);
			line = br.readLine();
			br.close();
			//CSVから読み込んだ配列の中身を取り込み
			for(int i = 0; i < data.length; i++) {
				csvdata[i] = Double.parseDouble(data[i]);
			}
		}catch(IOException e) {
			System.out.println(e);
		}
		System.out.println("CSV取り込み = " + Arrays.toString(csvdata));
		return csvdata;
		//CSVから取り込みここまで
	}
	
	public void writeCSV(String path, double[][] data) {
		try {
			FileWriter fw = new FileWriter(path, true);//true:追記 false:上書き
			PrintWriter pw = new PrintWriter(new BufferedWriter(fw));
			//内容を指定する
			for(int i = 0; i < data.length; i++) {
				for(int j = 0; j < data[0].length; j++) {
					pw.print(data[i][j]);
					pw.print(",");
				}
				pw.println();
			}
			pw.println();
			//ファイルに書き出す
			pw.close();
			//終了メッセージを画面に出力する
			System.out.println("出力完了:" + path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
