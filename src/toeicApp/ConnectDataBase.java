package toeicApp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class ConnectDataBase {

    private  Connection conn=null;
    public ConnectDataBase()
    {
        try{
            String query1="use TOEIC_WORD\n";
            String connectcode="jdbc:sqlite:"+"TOIEC_WORD.db";
            //  Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(connectcode);
            if(conn!=null)
            {
                System.out.println("Connected");
//                Statement statement=conn.createStatement();
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public void closeConnect()  {
        try {
            System.out.println("Connect Closed");
            conn.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public ArrayList<ToeicWords> getWord(int topicnumber)  {
        ArrayList<ToeicWords> data = new ArrayList<>();
        String query="select * from toeicword join topic on toeicword.topicID=topic.topicID where toeicword.topicID="+topicnumber;
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet=statement.executeQuery(query);
            while(resultSet.next())
            {
                String word=resultSet.getString(1);
                String pronounce = resultSet.getString(2);
                String type=resultSet.getString(3);
                String engdes=resultSet.getString(4);
                String viedes=resultSet.getString(5);
                String engex=resultSet.getString(6);
                String vieex=resultSet.getString(7);
                int topicID=resultSet.getInt(8);
                String topic=resultSet.getString(10)+" - "+resultSet.getString(11);
                ToeicWords myword=new ToeicWords(word,pronounce,type,engdes,viedes,engex,vieex,topicID,topic);
                data.add(myword);
            }

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return data;
    }
    public ArrayList<String> getVieDescription()
    {
        ArrayList<String> data=new ArrayList<>();
        String query="select viedescription from toeicword";
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet=statement.executeQuery(query);
            while(resultSet.next())
            {
                String viedes=resultSet.getString(1);
                data.add(viedes);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return data;
    }
    public ArrayList<String> getallWord()
    {
        ArrayList<String> data=new ArrayList<>();
        String query="select word from toeicword";
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet=statement.executeQuery(query);
            while(resultSet.next())
            {
                String w=resultSet.getString(1);
                data.add(w);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return data;
    }
    public ArrayList<ToeicWords> searchWord(String word)
    {
        ArrayList<ToeicWords> data = new ArrayList<>();
        String query="select *from toeicword where word like '%"+word+"%';";
        try{
            Statement statement=conn.createStatement();
            ResultSet resultSet=statement.executeQuery(query);
            while(resultSet.next())
            {
                String sword=resultSet.getString(1);
                String pronounce = resultSet.getString(2);
                String type=resultSet.getString(3);
                String engdes=resultSet.getString(4);
                String viedes=resultSet.getString(5);
                String engex=resultSet.getString(6);
                String vieex=resultSet.getString(7);
                int topicID=resultSet.getInt(8);
                String topic=resultSet.getString(10)+" - "+resultSet.getString(11);
                ToeicWords myword=new ToeicWords(sword,pronounce,type,engdes,viedes,engex,vieex,topicID,topic);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return data;
    }

    public boolean addUserWord(UserWords word,int userID)
    {
        String query="INSERT INTO userwords values ('"+word.getWord()+"','"+word.getPronounce()+"','"+word.getDescription()+"','"+word.getExample()+"','"+ word.getNote()+"',"+userID+");";
        try{
            Statement statement=conn.createStatement();
            statement.executeUpdate(query);
            return true;
        }catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }

    public void deleteUserWord(String word,int userID)
    {
        String query="Delete from userwords where word='"+word+"' and ID="+userID;
        try{
            Statement statement=conn.createStatement();
            statement.executeUpdate(query);
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public ArrayList<Integer> submitLogin(String username,String password)
    {
        ArrayList<Integer> data=new ArrayList<>();
        try{
            Statement statement=conn.createStatement();
            ResultSet resultSet=statement.executeQuery("select ID,turn from users where userID like '"+username+"' and password='"+password+"'");
            if(resultSet.next())
            {
                int oldturn=resultSet.getInt(2);
                int ID=resultSet.getInt(1);
                statement.executeUpdate("update users " +
                        "set turn = "+String.valueOf(oldturn+1) +
                        " where ID="+ID);
                data.add(ID);
                data.add(oldturn+1);
                return data;
            }
            else return null;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public void newuser(String username,String password)
    {
        try{
            Statement statement=conn.createStatement();
           statement.execute("insert into users(userID,password,turn) VALUES ('"+username+"','"+password+"',0);");
           ResultSet res =statement.executeQuery("select ID from users where userID='"+username+"'");
           if(res.next())
           {
               int ID=res.getInt(1);
               for(int i=1;i<=50;i++)
               {
                   statement.execute("insert into ToeicTestResult values ("+ID+","+i+",0,0,0);");
               }
           }

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public ObservableList<UserWords> getUserWord(int userID)
    {
       ObservableList<UserWords> data= FXCollections.observableArrayList();
        String query="Select *from userwords where ID="+userID;
        try{
            Statement statement=conn.createStatement();
            ResultSet resultSet=statement.executeQuery(query);
            while(resultSet.next())
            {
                String word=resultSet.getString(1);
                String pro=resultSet.getString(2);
                String des=resultSet.getString(3);
                String exa=resultSet.getString(4);
                String note=resultSet.getString(5);
                data.add(new UserWords(word,pro,des,exa,note));
            }

        }catch (SQLException e)
        {
            e.printStackTrace();
        }
        return data;
    }

    public void updateUserChooseResult(int userID,int topicID,int result)
    {
        try
        {
            Statement statement=conn.createStatement();
            ResultSet rs=statement.executeQuery("select choose,write from ToeicTestResult where userID="+userID+" and topicID="+topicID);
            if(rs.next()){
                int choose=rs.getInt(1);
                int write=rs.getInt(2);
                if(choose<result)
                {
                    statement.executeUpdate("update ToeicTestResult set choose="+result+" where userID="+userID+" and topicID="+topicID);
                    if(write>60&&result>60)
                    {
                        statement.executeUpdate("update ToeicTestResult set passed=1 where userID="+userID+" and topicID="+topicID);
                    }
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void updateUserWriteResult(int userID,int topicID,int result)
    {
        try
        {
            Statement statement=conn.createStatement();
            ResultSet rs=statement.executeQuery("select write,choose from ToeicTestResult where userID="+userID+" and topicID="+topicID);
            if(rs.next()){
                int choose=rs.getInt(2);
                int write=rs.getInt(1);
                if(write<result)
                {
                    statement.executeUpdate("update ToeicTestResult set write ="+result+" where userID="+userID+" and topicID="+topicID);
                    if(write>60&&result>60)
                    {
                        statement.executeUpdate("update ToeicTestResult set passed=1 where userID="+userID+" and topicID="+topicID);
                    }
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public double passedPercent(int userID)
    {
        int pass=0;
        try{
            Statement statement=conn.createStatement();
            ResultSet resultSet=statement.executeQuery("select passed from ToeicTestResult where userID="+userID);
            while(resultSet.next())
            {
                if(resultSet.getInt(1)==1) pass++;
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return pass*2;
    }
    public void savedata(int userID,int turn)
    {
        int point=0;
        try{
            Statement statement=conn.createStatement();
            ResultSet resultSet=statement.executeQuery("select choose,write from ToeicTestResult where userID="+userID);
            while(resultSet.next())
            {
                point+=resultSet.getInt(1);
                point+=resultSet.getInt(2);
            }
            statement.executeUpdate("insert into history values("+userID+","+turn+","+point+")");
        }catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
    public HashMap<Integer,Integer> getHistory(int userID)
    {
        HashMap<Integer,Integer> data=new HashMap<>();
        try{
            Statement statement=conn.createStatement();
            ResultSet resultSet=statement.executeQuery("select turn,point from history where userID="+userID);
            while (resultSet.next())
            {
                int turn=resultSet.getInt(1);
                int point=resultSet.getInt(2);
                data.put(turn,point);
            }
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
        return data;
    }
}
