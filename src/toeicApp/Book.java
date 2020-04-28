package toeicApp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Book {
    private ArrayList<ToeicWords> data=new ArrayList<ToeicWords>();

    public Book(ArrayList<ToeicWords> data) {
        this.data = data;
    }

    public Book() throws IOException {
        loaddata();
    }


    public ArrayList<ToeicWords> getData() {
        return data;
    }

    public void setData(ArrayList<ToeicWords> data) {
        this.data = data;
    }

    public void testword()
    {
        Scanner scanner=new Scanner(System.in);

        while(true) {
            System.out.println("Nhap ma so chu de muon kiem tra\n");
            System.out.println("Exit press 0");
            int maso = scanner.nextInt();
            if(maso==0) return;
            scanner.skip("\n");
            for (int i = maso * 12 - 12; i < maso * 12; i++) {
                try {
                    if (data.get(i).getViedescriptions().contains("unknown")) {
                        System.out.println("missing");
                        continue;
                    }
                    System.out.println("Nghĩa: " + data.get(i).getViedescriptions() + "\n");
                    String inputWord;
                    int count = 0;
                    do {
                        System.out.println("Nhập câu trả lời:  ");
                        inputWord = scanner.nextLine();
                        count++;
                        if (count >= 3) {
                            System.out.println("Suggestions: /" + data.get(i).getPronounce() + "/\n");
                        }
                    } while (!inputWord.contains(data.get(i).getWord()) && count <= 5);
                    if (count > 5) {
                        System.out.println("Answer: " + data.get(i).getWord() + "\n");
                    } else if (inputWord.contains(data.get(i).getWord())) {
                        System.out.println("Correct\n");
                    }
                } catch (Exception e) {
                    System.out.println("missing data\n");
                    continue;
                }
            }
        }

    }

    public void printall()
    {
        for(ToeicWords w:data){
            System.out.println(w.toString()+"\n");
        }
    }

    public void storedata() throws IOException {
        Path path= Paths.get("data.txt");
        BufferedWriter br= Files.newBufferedWriter(path);
        try{
            Iterator<ToeicWords> ir=data.iterator();
            while(ir.hasNext())
            {
                ToeicWords word=ir.next();
                br.write(String.format("%s\t%s\t%s\t%s\t%s\t%s\t%s\t%d\t%s",word.getWord(),word.getPronounce(),word.getEngdescirption(),word.getViedescriptions(),word.getType(),word.getEngexample(),word.getVieexample(),word.getIndexofTopic(),word.getTopic()));
                br.newLine();
            }
        }finally {
            if(br!=null)
            {
                br.close();
            }
        }

    }

    public void loaddata() throws IOException {
        Path path=Paths.get("src/toeicApp/data.txt");
        BufferedReader br=Files.newBufferedReader(path);
        String input;
        try{
            while((input=br.readLine())!=null)
            {
                String[] word=input.split("\t");
                ToeicWords w=new ToeicWords(word[0],word[1],word[2],word[3],word[4],word[5],word[6],Integer.parseInt(word[7]),word[8]);
                data.add(w);
            }
        }
        finally {
            if(br!=null)
                br.close();
        }
    }
    public ArrayList<ToeicWords> getDataofTopic(int topic)
    {
        ArrayList<ToeicWords> datatopic=new ArrayList<>();
        for(int i=(topic-1)*12;i<topic*12;i++)
        {
            datatopic.add(data.get(i));
        }
        return datatopic;
    }

}


//    @Override
//    public void start(Stage primaryStage) throws InterruptedException {
//
//        Media media = new Media("https://600tuvungtoeic.com/audio/attract.mp3");
//        MediaPlayer mp = new MediaPlayer(media);
//        mp.play();
//
//
//        System.out.println("Playing...");
//    }